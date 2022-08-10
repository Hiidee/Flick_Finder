<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'movies' }">Browse</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'flickfinder' }">Flick Finder</router-link>&nbsp;|&nbsp;
    </div>
    <router-view />
  </div>
</template>

 <style>
 </style>