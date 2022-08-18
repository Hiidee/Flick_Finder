<template>
  <body>
    <div class="home">
      <div id="about" class="container">
        <h1>About Flick Finder:</h1>
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
          </p>
          <div class="favorites-container">
            <a href="#movie1">Movie 1</a>
            <a href="#movie2">Movie 2</a>
            <a href="#movie3">Movie 3</a>
            <a href="#movie4">Movie 4</a>
            <a href="#movie5">Movie 5</a>
            <a href="#movie6">Movie 6</a>
            <a href="#movie7">Movie 7</a>
            <a href="#movie8">Movie 8</a>
            <a href="#movie9">Movie 9</a>
            <a href="#movie10">Movie 10</a>
            <a href="#movie11">Movie 11</a>
            <a href="#movie12">Movie 12</a>
            <a href="#movie13">Movie 13</a>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import MovieService from "@/services/MovieService.js";
export default {
  name: "home",

  created() {
    MovieService.listRandom().then((response) => {
      this.$store.state.browse = response.data;
    });
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
  overflow: auto;
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
.container {
  opacity: 2;
}
.home {
  display: flex;
  height: 100vh;
}
</style>