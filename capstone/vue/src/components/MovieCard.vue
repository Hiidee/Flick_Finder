<template>
  <div class="container">
    <button
      class="start"
      @click="getRecommendations"
      v-if="this.$store.state.recommendations.length < 1"
    >
      Start Swiping
    </button>
    <div class="card" v-if="this.$store.state.recommendations.length > 0">
      <div class="img-buttons">
        <button id = "hate" class="swipe-button" @click="hateMovie"><i class="fa-solid fa-heart-crack"></i></button>
        <img class="center-poster" v-bind:src="movie.poster" />
        <button id = "like" class="swipe-button" @click="likeMovie"><i class="fa-solid fa-heart"></i></button>
      </div>
      <div class="media">
        <div class="media-content">
          <div class="left-header">
            <p class="title is-4">{{ movie.title }}</p>
            <div class="subtitle is-6">
              Directed By:
              <i class="fa-solid fa-clapperboard"></i> {{ movie.director }}
            </div>
          </div>
          <div class="title is-5">
            Starring:
            </div>
            <div
              class="actor subtitle is-6"
              v-for="person in movie.actors"
              v-bind:key="person.id"
            >
              <i class="fa-solid fa-star"></i>{{ person.name }}
          </div>
          <div class="subtitle is-6">
            Genres:
            <div v-for="genre in movie.genre" v-bind:key="genre">
              {{ movie.genre }}
            </div>

          <div class='subtitle is-6'>Release Date: 
            <div><i class="fa-solid fa-calendar-days"></i> {{movie.datePremiered}}</div>
            </div>

          <div class='subtitle is-6'>Runtime: 
            <div><i class="fa-solid fa-timer"></i>{{movie.runtime}} minutes</div>
            </div>
        </div>
      </div>

      
      <div class="overview">
        <p id="overview-header" class="title is-4">Overview:</p>
        <p class="overview-item">{{ movie.overview }}</p>
        <button class="favorite-button" @click="addFavoriteMovie">
          Add to Favorites
        </button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";

export default {
  name: "movie-card",
  data() {
    return {
      movie: {
        id: 0,
        title: "",
        overview: "",
        genre: [],
        poster: "",
        directorID: 0,
        director: "",
        runtime: 0,
        datePremiered: "",
        actors: [],
      },
      i: 0,
    };
  },
  computed: {},
  created() {
    this.movie = this.$store.state.recommendations[this.i];
  },
  methods: {
    likeMovie() {
      this.movie = this.$store.state.recommendations[this.i];
      this.i++;
      this.$store.commit("STORE_SWIPE", this.movie);
      if (this.$store.state.swipes.length >= 10) {
        this.getRecommendations();
        this.$store.commit("CLEAR_SWIPES");
        this.i = 0;
      }
    },

    hateMovie() {
      this.movie = this.$store.state.recommendations[this.i];
      this.i++;
    },

    getRecommendations() {
      MovieService.listRecommendations(
        this.$store.state.user.id,
        this.$store.state.swipes
      ).then((response) => {
        this.$store.commit("STORE_MOVIE", response.data);
        this.movie = this.$store.state.recommendations[0];
      });
    },

    addFavoriteMovie() {
      this.$store.commit("ADD_FAVORITE_MOVIE", this.movie);
    },
  },
};
</script>

<style>
.container {
  margin-top: 30px;
  margin-bottom: 14px;
}

.card {
  display: grid;
  grid-template-columns: 0.3fr, 0.5fr, 0.2fr;
  grid-template-areas: "media center";
  column-gap: 50px;
  background-color: lightgray;
  border: white;
  border-style: solid;
  border-width: 10px;
}

.img-buttons {
  display: flex;
  grid-area: center;
}

.media {
  grid-area: media;
  margin-left: 15px;
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: space-between;
}

.actors {
  border-top: 20px;
  border-bottom: 20px;
  margin-top: 20px;
}

.actor {
  display: inline-block;
  margin-right: 5px;
}

.overview {
  grid-area: overview;
  margin-right: 15px;
  margin-top: 20px;
  display: flex;
  align-content: space-between;
  flex-direction: column;
}

#overview-header {
}

.overview-item {
  border: black;
  border-style: dashed;
  border-width: 2px;
  border-radius: 5px;
  padding: 5px;
  flex-grow: 1;
}

.favorite-button {
  background-color: gold;
  flex-grow: 0.2;
  margin: 20px;
}

.start {
  width: 200px;
  height: 125px;
}

.img-buttons {
  justify-content: center;
}

.center-poster {
  width: 75%;
}

.swipe-button {
  width: 200px;
}

#like {
  background-color: limegreen;
}

#hate {
  background-color: red;
}

@media (max-width: 700px) {
  .card {
    grid-template-columns: 1fr;
    grid-template-rows: 0.5fr, 0.25fr, 0.25fr;
    grid-template-areas:
      "center"
      "media"
      "overview";
  }
}
</style>
