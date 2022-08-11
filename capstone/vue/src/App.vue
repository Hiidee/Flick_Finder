<template>
<main>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'movies' }">Browse</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'flickFinder' }">Flick Finder</router-link>&nbsp;|&nbsp;
    </div>
    <router-view />
  </div>
</main>
</template>

 <style>
 template {
   background-image: url(https://wallpaperaccess.com/full/4840785.jpg);
 }
 </style>