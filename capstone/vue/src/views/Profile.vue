<template>
<div>
  <h1>Profile Form</h1>
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

  <label for="favoriteMovieGenres">Favorite Movie Genres:</label><br />
  <select name="favoriteMovieGenres" id="favoriteMovieGenres" multiple="true">
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
</form>
</div>
</template>

<script>
export default {};
</script>

<style>
</style>