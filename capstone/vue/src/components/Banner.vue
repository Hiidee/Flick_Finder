<template>
  <header>
    <div id="nav">
      <router-link class="link" v-bind:to="{ name: 'home' }" v-on:click.native="saveFavoriteMovie()"
        ><div class="banner-button">Home</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'flickFinder' }" v-on:click.native="saveFavoriteMovie()"
        ><div class="banner-button">Flick Finder</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'browse' }" v-on:click.native="saveFavoriteMovie()"
        ><div class="banner-button">Browse</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'profile' }" v-on:click.native="saveFavoriteMovie()"
        ><div class="banner-button" v-if="$store.state.token != ''">
          Profile
        </div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'logout' }" v-on:click.native="saveFavoriteMovie()"
        ><div class="banner-button" v-if="$store.state.token != ''">
          Logout
        </div></router-link
        >
    </div>
  </header>
</template>

<script>
import MovieService from "@/services/MovieService.js";

export default {
  name: "banner",

  methods: {
    saveFavoriteMovie() {
      MovieService.saveFavorites(
        this.$store.state.user.id,
        this.$store.state.favorites
      ).then((response) => {
        this.$store.commit("CLEAR_FAVORITES");
        return response;
      });
    },
  },
};
</script>

<style>
#nav,

/*.banner-button {
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
}*/

.banner-button {
  padding-bottom: 10;
  display: flex;
  justify-content: space-around;
}

.banner-button {
  justify-content: center;
  flex-grow: 0.75;
  padding: 10px;
  border-radius: 5px;
  color: var(--clr-neon);
  cursor: pointer;
  text-decoration: none;
  border: currentColor 0.125em solid;

  text-shadow: 0 0 0.125em hsl(0 0% 100% / 0.3), 0 0 0.45em currentColor;

  box-shadow: inset 0 0 0.5em 0 var (--clr-neon), 0 0 0.5em 0 var (--clr-neon);

}

/* .banner-button::before {
  content: "";
  position: absolute;
  background: var(--clr-neon);
  top: 120%;
  left: 0;
  width: 100%;
  height: 100%;

  transform: perspective(1em) rotateX (40deg) scale(1, 0.35);
  filter: blur(1em);
  opacity: 0.7;

} */

#nav {
  background-color: rgb(37, 37, 110);
  padding: 20px;
}

.link {
  color: white;
}
</style>