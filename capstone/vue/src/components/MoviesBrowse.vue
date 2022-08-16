<template>
  <div class="container">
      <div class = "searchbar">
            <label for="search">Filter Movies: </label>
          <input class="search" type="text"/>
      </div>
    <div
      class="browse-card"
      v-for="movie in $store.state.recommendations"
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
      recommendations: [],
    };
  },
  created() {
    if (this.$store.state.recommendations.length < 1) {
      this.recommendations = MovieService.listRecommendations();
      this.$store.state.recommendations = this.recommendations;
    } else {
      this.recommendations = this.$store.state.recommendations;
    }
  },
};
</script>

<style>
.container{
    background-color: lightskyblue;
}

</style>