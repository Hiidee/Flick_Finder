<template>
  <main>
    <div id="app">
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
    </div>
  </main>
</template>

 <style>
main {
  background-image: url(https://wallpaperaccess.com/full/4840785.jpg);
}
</style>