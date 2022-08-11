<template>
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
              src="https://bulma.io/images/placeholders/96x96.png"
              alt="Placeholder image"
            />
          </figure>
        </div>
        <div class="media-content">
          <p class="title is-4">John Smith</p>
          <p class="subtitle is-6">@johnsmith</p>
        </div>
      </div>

      <div class="content">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec
        iaculis mauris. <a>@bulmaio</a>. <a href="#">#css</a>
        <a href="#">#responsive</a>
        <br />
        <time datetime="2016-1-1">11:09 PM - 1 Jan 2016</time>
      </div>
    </div>
    <button @click="addFavoriteMovie">Add to Favorites</button>
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";

export default {
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
        }
      

      

    }
  },

  methods: {
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

    getRandom() {
      MovieService.listRandom().then((response) => {
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