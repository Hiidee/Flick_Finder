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
        <button class="hate" @click="hateMovie">Hate</button>
        <img v-bind:src="movie.poster" />
        <button class="like" @click="likeMovie">Like</button>
      </div>
      <div class="media">
        <div class="media-content">
          <p class="title is-4">{{ movie.title }}</p>
          <p class="subtitle is-6">Directed By: {{ movie.director }}</p>
          <div class="subtitle is-6">Starring: {{ movie.actors }}</div>
        </div>
      </div>

      <div class="overview">
        <h2>Overview: </h2>
        {{ movie.overview }}</div>
    </div>
    <button @click="addFavoriteMovie">Add to Favorites</button>
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
}

.card{
  display: grid;
  grid-template-columns: 0.25fr, 0.5fr, 0.25fr;
  grid-template-areas: "media card overview";
  column-gap: 75px;
}

.img-buttons {
  display: flex;
  grid-area: card;
}

.media-content {
  grid-area: media;
}

.overview {
  grid-area: overview;
}

.start {
  width: 200px;
  height: 125px;
}

.img-buttons {
  justify-content: center;
}

button {
  width: 75px;
  flex-grow: 0.075;
}

.like {
  background-color: limegreen;
}

.hate {
  background-color: red;
}
</style>