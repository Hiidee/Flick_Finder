<template>
<main class = "profile-main">
<div class = "profile-container">
  <div class = "info-container">
  <div class="personal-info">
      <p id="username">Username: {{$store.state.user.username}}</p>
      <p id="first-name">First Name: {{localProfile.firstName}}</p>
      <p id="last-name">Last Name: {{localProfile.lastName}}</p>
      <p id="email-address">Email Address: {{localProfile.emailAddress}}</p>
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
  </div>
<div class = "favorite-container">
          <h2 id="favorite-movies">Favorite Movies: </h2>
  <div class="favorite-movies">
         <div
        class="browse-card"
        v-for="favorite in localFavorites"
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
      </div>
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
            localFavorites: [],
            localProfile: {
              firstName: "",
              lastName: "",
              emailAddress: "",
              favoriteGenres: [],
            }

            
        }

    },

    created() {
        MovieService.listFavorites(this.$store.state.user.id).then( (response) => {
      response.data.forEach(movie => { 
        this.localFavorites.push(movie)
      });
      ProfileService.profile(this.$store.state.user.id).then((response)=>{
        this.$store.commit("SAVE_PROFILE", response.data)
      })
      ProfileService.profile(this.$store.state.user.id).then((response) => {
          this.localProfile.firstName = response.data.firstName;
          this.localProfile.lastName = response.data.lastName;
          this.localProfile.emailAddress = response.data.emailAddress;
        })
    });
    
    },

}
</script>

<style>

.favorite-container{
}

.favorite-movies {
    display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

#favorite-movies,
#username, 
#first-name, 
#last-name, 
#email-address {
    margin: 15px
}

.favorite-genre {
    margin: 15px;
    width:40vh;
    border: black;
    border-style: solid;
    padding: 10px;
}

.info-container{
  display: flex;
}

.personal-info{

}

.profile-main{
height: 100vh;
}

.profile-container{
  display:flex;
  flex-direction:row;
}

</style>