<template>
<div class="browse">
<div class = "searchbar">
            <label for="search">Filter Movies: </label>
          <input class="search" type="text" v-model="filter"/>
      </div>
  <div class="container-browse">
    <div
      class="browse-card"
      v-for="movie in filterMovies"
      v-bind:key="movie.id"
    >
    <router-link class="to-movie-details" v-bind:to="{name:'movie-detail', params: {id: movie.id}}">
    <div class="card-container">
      <img class="card-poster" v-bind:src="movie.poster" />
    </div>
    </router-link>
    </div>

  </div>
</div>
</template>

<script>
//import MovieService from "@/services/MovieService.js";

export default {
  name: "browse",
  data() {
    return {
        filter:'',
        browsing:[],
    };
  },
//   created() {
//     this.browsing=this.$store.state.browse;
//     this.$store.state.browse = MovieService.listRandom();
//   },
  computed:{
      filterMovies(){
          return this.$store.state.browse.filter((movie)=>{
              if(this.filter==''){
                  return true;
              }
              for(let i=0; i<movie.genres.length; i++){
                  const genreName = movie.genres[i].name.toUpperCase();
                if(genreName.includes(this.filter.toUpperCase())){
                    return true;
                }
              }
              for(let i=0; i<movie.actors.length; i++){
                  if(movie.actors[i].name.toUpperCase().includes(this.filter.toUpperCase())){
                      return true;
                  }
              }
              if(movie.director.toUpperCase().includes(this.filter.toUpperCase())){
                    return true;
                }
              if(movie.title.toUpperCase().includes(this.filter.toUpperCase())){
                  return true;
              }  
            return false;
          })
      },
  }
};
</script>

<style>

.browse{
    background-color: lightskyblue;

}

.container-browse{
    display:flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

.browse-card:hover{
    color:lightslategray;
    opacity:40%;
}

.card-poster{
    width: 225px;
}

</style>