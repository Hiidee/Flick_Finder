<template>
<main>
<form>
  <label for="firstName">First Name:</label>
  <input id="firstName" name="firstName" type="text" />
  <br />

  <label for="lastName">Last Name:</label>
  <input id="lastName" name="lastName" type="text" />
  <br />

  <label for="email">Email Address:</label>
  <input id="email" name="email" type="email" />
  <br /><br />

  <label for="favoriteGenres">Favorite Genres:</label><br />
  <select name="favoriteGenres" id="favoriteGenres" multiple="true">
      <option>Action</option>
      <option>Adventure</option>
      <option>Animation</option>
      <option>Comedy</option>
      <option>Crime</option>
      <option>Documentary</option>
      <option>Drama</option>
      <option>Family</option>
      <option>Fantasy</option>
      <option>History</option>
      <option>Horror</option>
      <option>Music</option>
      <option>Mystery</option>
      <option>Romance</option>
      <option>SciFi</option>
      <option>TV Movie</option>
      <option>Thriller</option>
      <option>War</option>
      <option>Western</option>
  </select>
</form>
<div class="favorites">
    <h2>Favorite Movies</h2>
    <div class="favorite" v-for="favorite in $store.state.favorites" v-bind:key="favorite.id">
        <div v-html="favorite.content"></div>
</div>
</div>
</main>
</template>

<script>
export default {
    name: 'profile-data'
};
</script>

<style>
</style>