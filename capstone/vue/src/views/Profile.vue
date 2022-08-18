<template id="profile-template">
  <body id="profile-body">
    <div class = "profile-page">
      <div>
        <i class="fa-solid fa-pen-to-square fa-2x" id="edit-profile" @click="flipStatus"></i>
    </div>
      <profile-display v-if="!$store.state.showProfile"></profile-display>
      <profile-data v-if="$store.state.showProfile"></profile-data>
    </div>
  </body>
</template>

<script>
import ProfileData from "@/components/ProfileData.vue";
import ProfileDisplay from "@/components/ProfileDisplay.vue"


export default {
  components: {
    ProfileData,
    ProfileDisplay
  },

  methods: {
    flipStatus() {
      this.$store.commit('FLIP_PROFILE_STATUS')
    }
  },

  data() {
    return {
      showForm: false
    }
  }
};
</script>

<style>
#profile-body{
  margin-bottom: 150px;
  background-color: white;
  height: 100vh
}


.profile-page{
  justify-items: center;
  background-color: white;
  height: 100vh;
}

#edit-profile {
  color:skyblue;
}

#edit-profile:hover {
  color: paleturquoise;
}
</style>