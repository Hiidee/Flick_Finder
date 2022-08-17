<template>
  <div class="details">
    <img v-bind:src="movie.poster" />
    <div class="media">
      <div class="media-content">
        <div class="left-header">
          <p class="title is-4">{{ movie.title }}</p>

          <div class="title is-5">
            Directed By:
            <div>
              <i class="fa-solid fa-clapperboard"></i> {{ movie.director }}
            </div>
          </div>
        </div>

        <div class="title is-5">
          Starring:
          <div></div>
          <div
            class="actor subtitle is-6"
            v-for="person in movie.actors"
            v-bind:key="person.id"
          >
            <i class="fa-solid fa-star"></i> {{ person.name }}
          </div>
        </div>

        <div class="title is-5">
          Genres:
          <div
            class="subtitle is-6"
            v-for="genre in movie.genres"
            v-bind:key="genre.id"
          >
           <i class="fa-solid fa-film"></i> {{ genre.name }}
          </div>
        </div>

        <div class="title is-5">
          Release Date:
          <div class="subtitle is-6">
            <i class="fa-solid fa-calendar-days"></i> {{ movie.datePremiered }}
          </div>
        </div>

        <div class="title is-5">
          Runtime:
          <div class="subtitle is-6">
            <i class="fa-solid fa-clock"></i> {{ movie.runtime }} minutes
          </div>
        </div>
      </div>
    </div>
    <div class="overview">
      <p id="overview-header" class="title is-4">Overview:</p>
      <p class="overview-item subtitle is-5">{{ movie.overview }}</p>
      <button class="favorite-button title is-6" @click="addFavoriteMovie" v-if="favorite===false">
        Add to Favorites
      </button>
      <button class="favorite-button title is-6" @click="removeFavoriteMovie" v-if="favorite===true">
        Remove from Favorites
      </button>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            favorite:false,
        }
    },
  methods: {
    addFavoriteMovie() {
      this.$store.commit("ADD_FAVORITE_MOVIE", this.movie);
      this.favorite=true;
    },
    removeFavoriteMovie(){
        this.$store.commit("REMOVE_FAVORITE_MOVIE", this.movie);
        this.favorite=false;
    }
  },
  computed: {
    movie() {
      return this.$store.state.browse.find((m) => {
        return m.id == this.$route.params.id;
      });
    },
  },
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