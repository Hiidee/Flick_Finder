<template>
  <header>
    <div id="nav">
      <div class = "banner-button">
      <router-link class = "link" v-bind:to="{ name: 'home' }">Home</router-link>
      </div>
      <div class = "banner-button" v-if="$store.state.token != ''">
      <router-link class = "link"
        v-bind:to="{ name: 'logout' }"
        >Logout</router-link>
      </div>
      <div class = "banner-button">
      <router-link class = "link" v-bind:to="{ name: 'browse' }">Browse</router-link>
      </div>
      <div class = "banner-button">
      <router-link class = "link" v-bind:to="{ name: 'flickFinder' }">Flick Finder</router-link>
      </div>
      <div class = "banner-button" v-if="$store.state.token != ''">
      <router-link class = "link" v-bind:to="{ name: 'profile' }"
      >Profile</router-link>
      </div>
    </div>
  </header>
</template>

<script>
export default {
    name: 'banner'
};
</script>

<style>
#nav, .banner-button {
  padding-bottom: 10;
  display: flex;
  justify-content: space-around;
  
}

.banner-button{
  background-color: #418883;
  justify-content: center;
  flex-grow: 0.075;
  padding: 10px;
  border-radius: 5px;
  color: whitesmoke;
}

#nav{
background-color: darkblue;
padding: 20px;
}

.link{
  color:black;
}

</style>