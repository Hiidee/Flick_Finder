<template>
  <main>
    <div class = "body">
      <form>
        <div class="personal-info">
          <label for="firstName">First Name:</label>
          <input id="firstName" name="firstName" type="text" v-model="profile.firstName"/>
          <br />
          <label for="lastName">Last Name:</label>
          <input id="lastName" name="lastName" type="text" v-model="profile.lastName"/>
          <br />
          <label for="email">Email Address:</label>
          <input id="email" name="email" type="email" v-model="profile.emailAddress"/>
          <br /><br />
        </div>

        <div class="favorite-genres">
          Favorite Genres:
          <div class="oneFour">
            <div class="button-one">
              <input type="checkbox" id="genre1" name="genre1" value="action" v-model="profile.favoriteGenres"/>
              <label for="genre1">Action</label>
            </div>
            <div class="button-two">
              <input
                type="checkbox"
                id="genre2"
                name="genre2"
                value="adventure"
                v-model="profile.favoriteGenres"
              />
              <label for="genre2">Adventure</label>
            </div>
            <div class="button-three">
              <input
                type="checkbox"
                id="genre3"
                name="genre3"
                value="animation"
                v-model="profile.favoriteGenres"
              />
              <label for="genre3">Animation</label>
            </div>
            <div class="button-four">
              <input type="checkbox" id="genre4" name="genre4" value="comedy" v-model="profile.favoriteGenres"/>
              <label for="genre4">Comedy</label>
            </div>
          </div>
          <div class="fiveEight">
            <div class="button-five">
              <input type="checkbox" id="genre5" name="genre5" value="crime" v-model="profile.favoriteGenres"/>
              <label for="genre5">Crime</label>
            </div>
            <div class="button-six">
              <input
                type="checkbox"
                id="genre6"
                name="genre6"
                value="documentary"
                v-model="profile.favoriteGenres"
              />
              <label for="genre6">Documentary</label>
            </div>
            <div class="button-seven">
              <input type="checkbox" id="genre7" name="genre7" value="drama" v-model="profile.favoriteGenres"/>
              <label for="genre7">Drama</label>
            </div>
            <div class="button-eight">
              <input type="checkbox" id="genre8" name="genre8" value="family" v-model="profile.favoriteGenres"/>
              <label for="genre8">Family</label>
            </div>
          </div>
          <div class="nineTwelve">
            <div class="button-nine">
              <input
                type="checkbox"
                id="genre9"
                name="genre9"
                value="fantasy"
                v-model="profile.favoriteGenres"
              />
              <label for="genre9">Fantasy</label>
            </div>
            <div class="button-ten">
              <input
                type="checkbox"
                id="genre10"
                name="genre10"
                value="history"
                v-model="profile.favoriteGenres"
              />
              <label for="genre10">History</label>
            </div>
            <div class="button-eleven">
              <input
                type="checkbox"
                id="genre11"
                name="genre11"
                value="horror"
                v-model="profile.favoriteGenres"
              />
              <label for="genre11">Horror</label>
            </div>
            <div class="button-twelve">
              <input
                type="checkbox"
                id="genre12"
                name="genre12"
                value="music"
                v-model="profile.favoriteGenres"
              />
              <label for="genre12">Music</label>
            </div>
          </div>
          <div class="thirteenSixteen">
            <div class="button-thirteen">
              <input
                type="checkbox"
                id="genre13"
                name="genre13"
                value="mystery"
                v-model="profile.favoriteGenres"
              />
              <label for="genre13">Mystery</label>
            </div>
            <div class="button-fourteen">
              <input
                type="checkbox"
                id="genre14"
                name="genre14"
                value="romance"
                v-model="profile.favoriteGenres"
              />
              <label for="genre14">Romance</label>
            </div>
            <div class="button-fifteen">
              <input
                type="checkbox"
                id="genre15"
                name="genre15"
                value="scifi"
                v-model="profile.favoriteGenres"
              />
              <label for="genre15">SciFi</label>
            </div>
            <div class="button-sixteen">
              <input
                type="checkbox"
                id="genre16"
                name="genre16"
                value="tvmovie"
                v-model="profile.favoriteGenres"
              />
              <label for="genre16">TvMovie</label>
            </div>
          </div>
          <div class="seventeenNineteen">
            <div class="button-seventeen">
              <input
                type="checkbox"
                id="genre17"
                name="genre17"
                value="thriller"
                v-model="profile.favoriteGenres"
              />
              <label for="genre17">Thriller</label>
            </div>
            <div class="button-eighteen">
              <input type="checkbox" id="genre18" name="genre18" value="war" v-model="profile.favoriteGenres"/>
              <label for="genre18">War</label>
            </div>
            <div class="button-nineteen">
              <input
                type="checkbox"
                id="genre19"
                name="genre19"
                value="western"
                v-model="profile.favoriteGenres"
              />
              <label for="genre19">Western</label>
            </div>
          </div>
        </div>
      </form>

    </div>
      <button v-on:click.prevent="saveProfile">Save</button>
  </main>
</template>


<script>
import MovieService from "@/services/MovieService.js";
import ProfileService from "@/services/ProfileService.js";

export default {
  name: "profile-data",
  data() {
    return {
      profile: {
        user_id: 0,
        firstName: "",
        lastName: "",
        emailAddress: "",
        favoriteGenres: [],
      },
    };
  },
  created() {
    this.profile.user_id = this.$store.state.user.id;
  },

  methods: {
    saveProfile() {
      this.$store.commit("SAVE_PROFILE", this.profile);
      ProfileService.updateProfile(this.profile);
      this.profile = {};
      this.$store.commit('FLIP_PROFILE_STATUS')
    },

    addFavoriteMovies() {
      MovieService.saveFavorites(
        this.$store.state.user.id,
        this.$store.state.favorites
      );
    },
  },
};
</script>

<style>
main {
  margin-left: auto;
  margin-right: auto;
  
}

.favorite-genres {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.oneFour,
.fiveEight,
.nineTwelve,
.thirteenSixteen,
.seventeenNineteen {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
</style>