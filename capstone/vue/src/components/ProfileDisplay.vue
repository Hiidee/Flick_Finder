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
        class="browse-card"
        v-for="favorite in this.$store.state.favorites"
        v-bind:key="favorite.id"
      >
        <router-link
          class="to-movie-details"
          v-bind:to="{ name: 'movie-detail', params: { id: favorite.id } }"
        >
          <div class="card-container">
            <img class="card-poster" v-bind:src="favorite.poster" />
          </div>
        </router-link>
      </div>
        <!-- <div
          class="favorite"
          v-for="favorite in this.$store.state.favorites"
          v-bind:key="favorite.id"
        >
          <div >{{favorite.title}}</div>
        </div> -->
      </div>
</main>
</template>

<script>
import MovieService from "@/services/MovieService.js"
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
    });
    }
}
</script>

<style>

#username, 
#first-name, 
#last-name, 
#email-address {
    margin: 15px
}

.favorite-genre,
.favorite-movies {
    margin: 15px
}

</style>