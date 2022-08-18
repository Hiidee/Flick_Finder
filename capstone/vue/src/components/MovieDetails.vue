<template>
  <div class="details">
    <img v-bind:src="localMovie.poster" />
    <div class="media">
      <div class="media-content">
        <div class="left-header">
          <p class="title is-4">{{ localMovie.title }}</p>

          <div class="title is-5">
            Directed By:
            <div>
              <i class="fa-solid fa-clapperboard"></i> {{ localMovie.director }}
            </div>
          </div>
        </div>

        <div class="title is-5">
          Starring:
          <div></div>
          <div
            class="actor subtitle is-6"
            v-for="person in localMovie.actors"
            v-bind:key="person.id"
          >
            <i class="fa-solid fa-star"></i> {{ person.name }}
          </div>
        </div>

        <div class="title is-5">
          Genres:
          <div
            class="subtitle is-6"
            v-for="genre in localMovie.genres"
            v-bind:key="genre.id"
          >
           <i class="fa-solid fa-film"></i> {{ genre.name }}
          </div>
        </div>

        <div class="title is-5">
          Release Date:
          <div class="subtitle is-6">
            <i class="fa-solid fa-calendar-days"></i> {{ localMovie.datePremiered }}
          </div>
        </div>

        <div class="title is-5">
          Runtime:
          <div class="subtitle is-6">
            <i class="fa-solid fa-clock"></i> {{ localMovie.runtime }} minutes
          </div>
        </div>
      </div>
    </div>
    <div class="overview">
      <p id="overview-header" class="title is-4">Overview:</p>
      <p class="overview-item subtitle is-5">{{ localMovie.overview }}</p>

      <div class="favorite-button">
            <i
              class="fa-solid fa-heart-circle-plus fa-3x favorite-button"
              @click="addFavoriteMovie"
            ></i>
            <div class="subtitle is-4 favorite-label">Favorite</div>
          </div>

      <!-- <button class="favorite-button title is-6" @click="addFavoriteMovie" v-if="favorite===false">
        Add to Favorites
      </button>
      <button class="favorite-button title is-6" @click="removeFavoriteMovie" v-if="favorite===true">
        Remove from Favorites
      </button> -->


    </div>
  </div>
</template>

<script>
import MovieService from '@/services/MovieService.js'

export default {
    data(){
        return{
            favorite: false,
            localMovie: {
              id: 0,
              title: "",
              overview: "",
              genre: [],
              poster: "",
              director: "",
              runtime: 0,
              datePremiered: "",
              actors: [],
      },
        }
    },
  methods: {
    addFavoriteMovie() {
      this.$store.commit("ADD_FAVORITE_MOVIE", this.localMovie);
      this.favorite=true;
    },
    removeFavoriteMovie(){
        this.$store.commit("REMOVE_FAVORITE_MOVIE", this.localMovie);
        this.favorite=false;
    }
  },
  created() {
    // let movie = this.$store.state.browse.find((m) => {
    //     return m.id == this.$route.params.id;
    //   });
    //   if(movie==undefined){
    //     movie = this.$store.state.favorites.find((m)=> {
    //       return m.id == this.$route.params.id;
    //     })
    //   }
      MovieService.listById(this.$route.params.id).then((response) => {
        // this.localMovie.id = response.data.id
        // this.localMovie.title = response.data.title
        // this.localMovie.overview = response.data.overview
        // this.localMovie.genre = response.data.genre
        // this.localMovie.poster = response.data.poster
        // this.localMovie.director = response.data.director
        this.localMovie = response.data
      }); 
      
  },
  // computed: {
  //   getMovie() {
  //     let movie = this.$store.state.browse.find((m) => {
  //       return m.id == this.$route.params.id;
  //     });
  //     if(movie==undefined){
  //       movie = this.$store.state.favorites.find((m)=> {
  //         return m.id == this.$route.params.id;
  //       })
  //     }

  //     return movie;
  //   },
  // },
};
</script>

<style>
@media (min-width: 500px) {
  .details {
    display: flex;
    justify-content: space-evenly;
    background-color: white;
  }

  .overview {
    flex-grow: 0.75;
  }
}

@media (max-width: 500px) {
  .details {
    display: flex;
    flex-direction: column;
    align-content: space-around;
  }
}

</style>