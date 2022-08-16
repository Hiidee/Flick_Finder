<template>
<div>
  <movie-card></movie-card>
  </div>
</template>

<script>

import MovieCard from '@/components/MovieCard.vue'

export default {
  components: {
    MovieCard
  },
  //   created() {
  //   window.addEventListener('beforeunload', (event) => {
  //     event.preventDefault();
  //     event.returnValue = ' ';
  //   });
  // }
}
</script>

<style>
</style>