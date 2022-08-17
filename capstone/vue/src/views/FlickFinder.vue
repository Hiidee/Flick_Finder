<template>
<div class = "movie-card-view">
  <movie-card class = "flick"></movie-card>
</div>
</template>

<script>

import MovieCard from '@/components/MovieCard.vue'

export default {
  components: {
    MovieCard
  }
}
</script>

<style>
.movie-card-view{
display:flex;
flex-grow:1;
}
</style>