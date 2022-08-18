<template>
  <div class="container">
    <button class="button-85" @click="getRecommendations" v-if="this.$store.state.recommendations.length < 1" role="button">Start Swiping</button>
    <div class="card" v-if="this.$store.state.recommendations.length > 0">
      <div class="img-buttons">
        <button id="hate" class="swipe-button" @click="hateMovie">
          <i class="fa-solid fa-thumbs-down fa-3x"></i>
        </button>
        <img class="center-poster" v-bind:src="movie.poster" />
        <button id="like" class="swipe-button" @click="likeMovie">
          <i class="fa-solid fa-thumbs-up fa-3x"></i>
        </button>
      </div>
      <div class="media">
        <div class="media-content">
          <div class="left-header">
            <p class="title is-4">{{ movie.title }}</p>

            <div class="title is-5">
              Directed By:
              <div>
                <i class="fa-solid fa-clapperboard"></i> {{ movie.director }}
              </div>
            </div>
          </div>

          <div class="title is-5">
            Starring:
            <div></div>
            <div
              class="actor subtitle is-6"
              v-for="person in movie.actors"
              v-bind:key="person.id"
            >
              <i class="fa-solid fa-star"></i> {{ person.name }}
            </div>
          </div>

          <div class="title is-5">
            Genres:
            <div
              class="subtitle is-6"
              v-for="genre in movie.genres"
              v-bind:key="genre.id"
            >
              <i class="fa-solid fa-film"></i> {{ genre.name }}
            </div>
          </div>

          <div class="title is-5">
            Release Date:
            <div class="subtitle is-6">
              <i class="fa-solid fa-calendar-days"></i>
              {{ movie.datePremiered }}
            </div>
          </div>

          <div class="title is-5">
            Runtime:
            <div class="subtitle is-6">
              <i class="fa-solid fa-clock"></i> {{ movie.runtime }} minutes
            </div>
          </div>
        </div>

        <div class="overview">
          <p id="overview-header" class="title is-4">Overview:</p>
          <p class="overview-item subtitle is-6">{{ movie.overview }}</p>
           <div class = "review container" v-for="review in ratings" v-bind:key ="review.Source">
        <p>{{review.Source}}: {{review.Value}}</p>
      </div>

          <div class="favorite-button">
            <i
              class="fa-solid fa-heart-circle-plus fa-3x favorite-button"
              @click="addFavoriteMovie"
            ></i>
            <div class="subtitle is-4 favorite-label">Favorite</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";
import OMDBService from "@/services/OMDBService.js";

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
      ratings:[],
    };
  },
  computed: {},
  created() {
    this.movie = this.$store.state.recommendations[this.i];
    OMDBService.getRatings(this.movie.title).then((response) => {
        console.log(response.data);
        this.ratings = response.data.Ratings;
      });
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
  display: flex;
  justify-content: center;
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

.left-header {
  margin-bottom: 20px;
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
  border-style: solid;
  border-width: 2px;
  border-radius: 5px;
  padding: 5px;
  flex-grow: 1;
}

.favorite-button {
  color: paleturquoise;
  flex-grow: 0.3;
  margin: 20px 20px 5px 170px;
}

.favorite-label {
  margin: 20px 5px 20px 160px;
  color: paleturquoise;
}

.favorite-label:hover,
.favorite-button:hover {
  color: palevioletred;
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

#like,
#hate,
.fa-thumbs-up,
.fa-thumbs-down {
  color: transparent;
  background-color: transparent;
  border: none;
}

#like:hover,
#hate:hover {
  background-color: darkgrey;
  opacity: 25%;
}

#like:hover > .fa-thumbs-up,
#hate:hover > .fa-thumbs-down {
  color: black;
  opacity: 100%;
}

/* CSS */
.button-85 {
  padding: 0.4em 2em;
  border: none;
  outline: none;
  color: rgb(255, 255, 255);
  background:black;
  cursor: pointer;
  position: relative;
  z-index: 0;
  border-radius: 10px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  height: 50vh;
  width: 50%;
  margin: auto;
  margin-top:10vh;
  margin-bottom:10vh;
  font-size: 500%;
  display: flex;
  align-content: center;
  justify-content: center;
}
.button-85:before {
  content: "";
  background: linear-gradient(
    45deg,
    #FF0000,
    #FF7300,
    #FFFB00,
    #48FF00,
    #00FFD5,
    #002BFF,
    #7A00FF,
    #FF00C8,
    #FF0000
  );
  position: absolute;
  top: -2px;
  left: -2px;
  background-size: 400%;
  z-index: -1;
  filter: blur(5px);
  -webkit-filter: blur(5px);
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  animation: glowing-button-85 20s linear infinite;
  transition: opacity 0.3s ease-in-out;
  border-radius: 10px;
}
@keyframes glowing-button-85 {
  0% {
    background-position: 0 0;
  }
  50% {
    background-position: 400% 0;
  }
  100% {
    background-position: 0 0;
  }
}
.button-85:after {
  z-index: -1;
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  background: #222;
  left: 0;
  top: 0;
  border-radius: 10px;
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
