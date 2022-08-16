<template>
  <body>
    <div class="home">
      <div id="about" class="container">
        <h1>About Flick Finder:</h1>
        <br>
        <p>
          How many times have you browsed through pages of random movies only to be even more confused
          about what you want to watch? You know you saw some titles, posters, or descriptions that 
          were intriguing, but by the end of the search, you can't remember them, and going back through
          the whole list again is too time consuming. Don't you wish there was a way to flag multiple 
          movies, and based on those selections, get recommendations?
          <br>
          <br />
          <strong>Now there is!!!</strong>
          <br>
          <br />
          With Flick Finder, your swipes help determine what you may be in the mood for. The powerful
          algorithm identifies your swipe trends based on genres, actors, directors, and much more to 
          narrow down an ideal selection list. You can also maintain a library of all of your favorite
          flicks. 
          <br>
          <br />
          What are you waiting for? Go flick some flicks!!
        </p>
      </div>
    </div>
  </body>
</template>

<script>
import MovieService from "@/services/MovieService.js"
export default {
  name: "home",

  created() {
      MovieService.listRandom().then((response) => {
        this.$store.state.browse = response.data;
      })
  
  }
};
</script>

<style>
body, strong {
  font-size: 20px;
  font-weight: bold;
  -webkit-text-stroke: .6px black;
}

.home{
  background-color: white;
  opacity: 50%;
}

.container{
  opacity:2;
}
</style>