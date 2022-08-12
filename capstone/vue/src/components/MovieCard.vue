<template>
<div class='container'>

  <button @click="getRecommendations" v-if="this.$store.state.recommendations.length<1">Start Swiping</button>
  <div class="card" v-if="this.$store.state.recommendations.length>0">
  <button @click="hateMovie">Hate</button>
    <div class="card-image">
      <figure class="image is-3by2">
        <img
          v-bind:src="movie.poster"
          alt="Placeholder image"
        />
      </figure>
    </div>
    <button @click="likeMovie">Like</button>
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
          <div class='subtitle is-6'>Starring: 
            <div v-for="person in movie.actors" v-bind:key="person.id">-{{person.name}}</div>
            </div>
          <p class='subtitle is-6'>Genres: {{movie.genre}}</p>
          <p class='subtitle is-6'>Release Date: {{movie.datePremiered}}</p>
          <p class='subtitle is-6'>Runtime: {{movie.runtime}}</p>
        </div>
      </div>

      <div class="content">
        {{movie.overview}} 
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
        i: 1,
        actorList: []
      

    }
  },
  computed:{
    sendSwipes(){
      if(this.$store.state.swipes.length>=10){
        this.getRecommendations();
      }
        return true;
    },
  },
  methods: { 
    
    likeMovie() {
      this.movie = this.$store.state.recommendations[this.i];
      this.i++;
      this.$store.commit('STORE_SWIPE', this.movie);
    },

    hateMovie() {
      this.movie = this.$store.state.recommendations[this.i];
      this.i++;
    },

    getRecommendations() {
      MovieService.listRecommendations(this.$store.state.user.id, this.$store.state.swipes).then((response) => {
        this.$store.commit('STORE_MOVIE', response.data);
        this.movie = this.$store.state.recommendations[0];
      })
    },
  
    addFavoriteMovie() {
      this.$store.commit('ADD_FAVORITE_MOVIE',this.movie);
    },
  }
  
};
</script>

<style scoped>

</style>