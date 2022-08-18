<template>
  <body>
    <div class="home">
      <div class = "wheel-container">
         <h2 class="favorites-wheel title is-3" for="favorites-container">Top 10 Favorited Movies: </h2>
          <div class="favorites-container">
            <a class = "movie-container" v-for="movie in homeMovies" v-bind:key="movie.id"><img class= "home-poster" v-bind:src="movie.poster"/></a>
          </div>
      </div>
      <div id="about" class="container">
        <h1 class = "home-header title is-3">About Flick Finder:</h1>
        <div>
          <br />
          <br />
          <p>
            How many times have you browsed through pages of random movies only
            to be even more confused about what you want to watch? You know you
            saw some titles, posters, or descriptions that were intriguing, but
            by the end of the search, you can't remember them, and going back
            through the whole list again is too time consuming. Don't you wish
            there was a way to flag multiple movies, and based on those
            selections, get recommendations?
          </p>
            <br />
            <br />
            <strong>Now there is!</strong>
            <br />
            <br />
            With Flick Finder, your swipes help determine what you may be in the
            mood for. The powerful algorithm identifies your swipe trends based
            on genres, actors, directors, and much more to narrow down an ideal
            selection list. You can also maintain a library of all of your
            favorite flicks.
            <br />
            <br />
            <strong>It's like Tinder... For movies!</strong>
            <br />
            <br />
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import MovieService from "@/services/MovieService.js";
export default {
  data(){
    return{
      homeMovies:[],
    }
  },
  name: "home",

  created() {
    MovieService.listRandom().then((response) => {
      this.$store.state.browse = response.data;
    });
    MovieService.displayHomeMovies().then((response)=>{
      this.homeMovies = response.data;
    })
  },
};
</script>

<style>
h1 {
  position: absolute;
}

body,
strong {
  color: rgb(54, 155, 223);
  font-size: 20px;
  font-weight: bold;
  -webkit-text-stroke: 0.6px black;
}
div.favorites-container {
  background-color: #333;
  overflow-x: auto;
  white-space: nowrap;
  max-width: 70%;
}
div.favorites-container a {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px;
  text-decoration: none;
}
div.favorites-container a:hover {
  background-color: #777;
}
div.favorites-container::-webkit-scrollbar {
    display: none;
}
.container {
  opacity: 2;
}

.home-header{
  color:rgb(54, 155, 223);
}

.home {
  height: 120vh;
}
.home-poster{
  height : 35vh;
}

#about{
  background: black;
  padding: 20px;
  border-radius:10px;
}

.favorites-wheel{
  
  color:white;
  margin-top:10vh;
  margin-right:auto;
  margin-left:70vh;
  width:50%;
}

.favorites-container{
  margin:auto;
  width: 50;
}

</style>