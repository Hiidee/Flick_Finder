<template>
  <div class="container-browse">
      <div class = "searchbar">
            <label for="search">Filter Movies: </label>
          <input class="search" type="text"/>
      </div>
    <div
      class="browse-card"
      v-for="movie in browsing"
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
      browsing: [],
    };
  },
  created() {
    this.browsing = MovieService.listRandom();
  },
};
</script>

<style>
.container-browse{
    background-color: lightskyblue;
}

</style>