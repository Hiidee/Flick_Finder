<template>
  <div class="container-browse">
      <div class = "searchbar">
            <label for="search">Filter Movies: </label>
          <input class="search" type="text" v-model="filter"/>
      </div>
    <div
      class="browse-card"
      v-for="movie in this.$store.state.browse"
      v-bind:key="movie.id"
    >
      <img v-bind:src="movie.poster" />
    </div>
  </div>
</template>

<script>
import MovieService from "@/services/MovieService.js";

export default {
  name: "browse",
  data() {
    return {
        filter:'',
    };
  },
  created() {
    this.browsing=this.$store.state.browse;
    this.$store.state.browse = MovieService.listRandom();
  },
  computed:{
      filterMovies(){
          return this.browsing.filter((movie)=>{
              if(this.filter==''){
                  return true;
              }
              for(let i=0; i<movie.genres.length; i++){
                if(movie.genres[i].name.toLowerCase.contains(this.filter.toLowerCase)){
                    return true;
                }
              }
              for(let i=0; i<movie.actors.length; i++){
                  if(movie.actors[i].name.toLowerCase.contains(this.filter.toLowerCase)){
                      return true;
                  }
              }
              if(movie.director.toLowerCase.contains(this.filter.toLowerCase)){
                    return true;
                }
            return false;
          })
      }
  }
};
</script>

<style>
.container-browse{
    background-color: lightskyblue;
}

</style>