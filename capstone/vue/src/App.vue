<template>
  <main>
    <div id="app">
      <banner></banner>
      <header>
        <div id="nav">
          <router-link v-bind:to="{ name: 'home' }">Home</router-link
          >&nbsp;|&nbsp;
          <router-link
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
          <router-link v-bind:to="{ name: 'browse' }">Browse</router-link
          >&nbsp;|&nbsp;
          <router-link v-bind:to="{ name: 'flickFinder' }"
            >Flick Finder</router-link
          >&nbsp;|&nbsp;
        </div>
      </header>
      <router-view />
      <footer></footer>
    </div>
  </main>
</template>

<script>
// import Banner from '@/components/Banner.vue';
// import Footer from '@/components/Footer.vue';

export default { 
}
</script>


 <style>
#app {
  background-image: url(https://wallpaperaccess.com/full/4840785.jpg);
  height: 100vh;
}
</style>
