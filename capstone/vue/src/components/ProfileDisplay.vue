<template>
<main>

  <div class="personal-info">
      <p id="username">Username: {{$store.state.user.username}}</p>
      <p id="first-name">First Name: {{$store.state.userProfile.firstName}}</p>
      <p id="last-name">Last Name: {{$store.state.userProfile.lastName}}</p>
      <p id="email-address">Email Address: {{$store.state.userProfile.emailAddress}}</p>
  </div>

  <div class="favorite-genre">
      <h2>Favorite Genres: </h2>
      <div 
      v-for="genre in $store.state.userProfile.favoriteGenres"
      v-bind:key="genre.id"
      >
        <i class="fa-solid fa-film"></i> {{genre}}
      </div>
      
  </div>

  <div class="favorite-movies">
        <h2>Favorite Movies: </h2>
        <div
          class="favorite"
          v-for="favorite in this.$store.state.favorites"
          v-bind:key="favorite.id"
        >
          <div >{{favorite.title}}</div>
        </div>
      </div>
</main>
</template>

<script>
import MovieService from "@/services/MovieService.js"
import ProfileService from "@/services/ProfileService.js"

export default {
    name: 'profile-display',
    data() {
        return {
            
        }

    },

    created() {
        MovieService.listFavorites(this.$store.state.user.id).then( (response) => {
      response.data.forEach(movie => { 
        this.$store.commit("ADD_FAVORITE_MOVIE", movie);
        this.profile.favorites.push(movie.title)
      });
      ProfileService.profile(this.$store.state.user.id).then((response)=>{
        this.$store.commit("SAVE_PROFILE", response.data)
      })
    });
    }
}
</script>

<style>
  p {
    color: rgb(173, 186, 187);
  }
</style>