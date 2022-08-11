<template>
<div class='container'>
  <button @click="getRandomMovie">Start Swiping</button>
  <div class="card">
    <div class="card-image">
      <figure class="image is-4by3">
        <img
          v-bind:src="movie.poster"
          alt="Placeholder image"
        />
      </figure>
    </div>
    <div class="card-content">
      <div class="media">
        <div class="media-left">
          <figure class="image is-48x48">
            <img
              v-bind:src="movie.poster"
              alt="Placeholder image"
            />
          </figure>
        </div>
        <div class="media-content">
          <p class="title is-4">{{movie.title}}</p>
          <p class="subtitle is-6">Directed By: {{movie.director}}</p>
          <div class='subtitle is-6'>Starring: {{movie.actors}}</div>
        </div>
      </div>

      <div class="content">
        {{movie.overview}} 
        Placeholder Overview
      </div>
    </div>
    <button @click="addFavoriteMovie">Add to Favorites</button>
  </div>
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";

export default {
  name: 'movie-card',
  data() {
    return {
      movie: 
        {
          id: 0,
          title: '',
          overview: '',
          genre: [],
          poster: '',
          directorID: 0,
          director: '',
          runtime: 0,
          datePremiered: '',
          actors: []
        },
      

    }
  },

  // created() {
  //     MovieService.listRandom().then((response) => {
  //       this.movie.id = response.data.id;
  //       this.movie.title = response.data.title;
  //       this.movie.overview = response.data.overview;
  //       this.movie.genre = response.data.genre;
  //       this.movie.poster = response.data.poster;
  //       this.movie.directorID = response.data.directorID;
  //       this.movie.director = response.data.director;
  //       this.movie.runtime = response.data.runtime;
  //       this.movie.datePremiered = response.data.datePremiered;
  //       this.movie.actors = response.data.actors;
  //     })
  // },

  methods: {
    getRandomMovie() {
      MovieService.listRandom().then((response) => {
        response.data.map((item) => {
        this.movie.id = item.id;
        this.movie.title = item.title;
        this.movie.overview = item.overview;
        this.movie.genre = item.genres;
        this.movie.poster = item.poster;
        this.movie.directorID = item.directorId;
        this.movie.director = item.director;
        this.movie.runtime = item.runtime;
        this.movie.datePremiered = item.datePremiered;
        this.movie.actors = item.actors;

        })
      })

    },
    getByActor() {
      MovieService.listByActor(this.$route.params.personID).then((response) => {
        this.movie.id = response.data.id;
        this.movie.title = response.data.title;
        this.movie.overview = response.data.overview;
        this.movie.genre = response.data.genre;
        this.movie.poster = response.data.poster;
        this.movie.directorID = response.data.directorID;
        this.movie.director = response.data.director;
        this.movie.runtime = response.data.runtime;
        this.movie.datePremiered = response.data.datePremiered;
        this.movie.actors = response.data.actors;
      })
    },

    getByGenre() {
      MovieService.listByGenre(this.$route.params.genreName).then((response) => {
        this.movie.id = response.data.id;
        this.movie.title = response.data.title;
        this.movie.overview = response.data.overview;
        this.movie.genre = response.data.genre;
        this.movie.poster = response.data.poster;
        this.movie.directorID = response.data.directorID;
        this.movie.director = response.data.director;
        this.movie.runtime = response.data.runtime;
        this.movie.datePremiered = response.data.datePremiered;
        this.movie.actors = response.data.actors;
      })
    },

    getByDirector() {
      MovieService.listByDirector(this.$route.params.directorName).then((response) => {
        this.movie.id = response.data.id;
        this.movie.title = response.data.title;
        this.movie.overview = response.data.overview;
        this.movie.genre = response.data.genre;
        this.movie.poster = response.data.poster;
        this.movie.directorID = response.data.directorID;
        this.movie.director = response.data.director;
        this.movie.runtime = response.data.runtime;
        this.movie.datePremiered = response.data.datePremiered;
        this.movie.actors = response.data.actors;
      })
    },

    getByTitle() {
      MovieService.listByTitle(this.$route.params.title).then((response) => {
        this.movie.id = response.data.id;
        this.movie.title = response.data.title;
        this.movie.overview = response.data.overview;
        this.movie.genre = response.data.genre;
        this.movie.poster = response.data.poster;
        this.movie.directorID = response.data.directorID;
        this.movie.director = response.data.director;
        this.movie.runtime = response.data.runtime;
        this.movie.datePremiered = response.data.datePremiered;
        this.movie.actors = response.data.actors;
      })
    },
    addFavoriteMovie() {
      this.$store.commit('ADD_FAVORITE_MOVIE');
    }
  }
  
};
</script>