<template>
  <header>
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'browse' }">Browse</router-link
      >&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'flickFinder' }">Flick Finder</router-link
      >&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'profile' }">Profile</router-link>&nbsp;|&nbsp;
    </div>
  </header>
</template>

<script>
export default {
    name: 'banner'
};
</script>

<style>
#nav {
  padding-bottom: 10;
}
</style>