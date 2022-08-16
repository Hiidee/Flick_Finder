<template>
  <header>
    <div id="nav">
      <router-link class="link" v-bind:to="{ name: 'home' }" v-on:click="saveFavoriteMovie()"
        ><div class="banner-button">Home</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'flickFinder' }" v-on:click="saveFavoriteMovie()"
        ><div class="banner-button">Flick Finder</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'browse' }" v-on:click="saveFavoriteMovie()"
        ><div class="banner-button">Browse</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'profile' }" v-on:click="saveFavoriteMovie()"
        ><div class="banner-button" v-if="$store.state.token != ''">
          Profile
        </div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'logout' }" v-on:click="saveFavoriteMovie()"
        ><div class="banner-button" v-if="$store.state.token != ''">
          Logout
        </div></router-link
      >
    </div>
  </header>
</template>

<script>
<<<<<<< HEAD
import MovieService from "@/services/MovieService.js";

export default {
  name: "banner",

  methods: {
    saveFavoriteMovie() {
      MovieService.saveFavorites(
        this.$store.state.currentUser.id,
        this.$store.state.favorites
      ).then((response) => {
        this.$store.commit(response.data);
      });
    },
  },
=======

export default {
  name: "banner",
  
>>>>>>> 73d32b0cfcbf0c9b24de10070ba295ba938455b7
};
</script>

<style>
#nav,
.banner-button {
  padding-bottom: 10;
  display: flex;
  justify-content: space-around;
}

.banner-button {
  justify-content: center;
  flex-grow: 0.075;
  padding: 10px;
  border-radius: 5px;
  color: white;
}

.banner-button:hover {
  background-color: white;
  color: black;
}

#nav {
  background-color: darkblue;
  padding: 20px;
}

.link {
  color: black;
}
</style>