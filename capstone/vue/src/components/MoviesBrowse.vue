<template>
  <div class="container-browse">
      <div class = "searchbar">
            <label for="search">Filter Movies: </label>
          <input class="search" type="text"/>
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
export default {
  name: "browse",
  data() {
    return {
        filter:'',
    };
  },
};
</script>

<style>
.container-browse{
    background-color: lightskyblue;
}

</style>