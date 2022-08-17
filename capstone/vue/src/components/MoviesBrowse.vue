<template>
  <div class="browse">
    <div class="searchbar">
      <label for="search">Filter Movies: </label>
      <input class="search" type="text" v-model="filter" />
    </div>

    <div class="addMovie">
      <button @click="flipForm()" v-show="showButton">Add Movie</button>

      <div class="addMovieForm" v-show="showForm">
        <label for="title">Title: </label>
        <input type="text" name="title" id="title" v-model="newMovie.title" />

        <label for="overview">Overview: </label>
        <input
          type="text"
          name="overview"
          id="overview"
          v-model="newMovie.overview"
        />

        <label for="poster">Poster Image URL: </label>
        <input
          type="text"
          name="poster"
          id="poster"
          v-model="newMovie.poster"
        />

        <label for="release_date">Release Date: </label>
        <input
          type="date"
          name="release_date"
          id="release_date"
          v-model="newMovie.datePremiered"
        />

        <label for="runtime">Runtime(in minutes): </label>
        <input
          type="number"
          name="runtime"
          id="runtime"
          v-model="newMovie.runtime"
        />

        <button @click="addMovie(), flipForm()">Submit</button>
      </div>
    </div>

    <div class="container-browse">
      <div
        class="browse-card"
        v-for="movie in filterMovies"
        v-bind:key="movie.id"
      >
        <router-link
          class="to-movie-details"
          v-bind:to="{ name: 'movie-detail', params: { id: movie.id } }"
        >
          <div class="card-container">
            <img class="card-poster" v-bind:src="movie.poster" />
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";
export default {
  name: "browse",
  data() {
    return {
      filter: "",
      showForm: false,
      showButton: true,
      newMovie: {
        title: "",
        overview: "",
        poster: "",
        datePremiered: "",
        runtime: 0,
      },
    };
  },

  methods: {
    flipForm() {
      if (this.showButton == true) {
        this.showButton = false;
      } else {
        this.showButton = true;
      }

      if (this.showForm == false) {
        this.showForm = true;
      } else {
        this.showForm = false;
      }
    },

    addMovie() {
      MovieService.addMovie(this.newMovie),
        (this.newMovie.title = ""),
        (this.newMovie.overview = ""),
        (this.newMovie.poster = ""),
        (this.newMovie.datePremiered = ""),
        (this.newMovie.runtime = 0);
    },
  },

  computed: {
    filterMovies() {
      return this.$store.state.browse.filter((movie) => {
        if (this.filter == "") {
          return true;
        }
        for (let i = 0; i < movie.genres.length; i++) {
          const genreName = movie.genres[i].name.toUpperCase();
          if (genreName.includes(this.filter.toUpperCase())) {
            return true;
          }
        }
        for (let i = 0; i < movie.actors.length; i++) {
          if (
            movie.actors[i].name
              .toUpperCase()
              .includes(this.filter.toUpperCase())
          ) {
            return true;
          }
        }
        if (movie.director.toUpperCase().includes(this.filter.toUpperCase())) {
          return true;
        }
        if (movie.title.toUpperCase().includes(this.filter.toUpperCase())) {
          return true;
        }
        return false;
      });
    },
  },
};
</script>

<style >
.browse {
  background-color: rgb(73, 114, 139);
}

.container-browse {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.browse-card:hover {
  color: lightslategray;
  opacity: 40%;
}

.card-poster {
  width: 225px;
}
</style>