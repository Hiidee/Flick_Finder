<template>
  <header>
    <div id="nav">
      <router-link class="link" v-bind:to="{ name: 'home' }"
        ><div class="banner-button">Home</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'flickFinder' }"
        ><div class="banner-button">Flick Finder</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'browse' }"
        ><div class="banner-button">Browse</div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'profile' }"
        ><div class="banner-button" v-if="$store.state.token != ''">
          Profile
        </div></router-link
      >
      <router-link class="link" v-bind:to="{ name: 'logout' }"
        ><div class="banner-button" v-if="$store.state.token != ''">
          Logout
        </div></router-link
      >
    </div>
  </header>
</template>

<script>
export default {
  name: "banner",
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