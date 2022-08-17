<template>
  <body>
    <div>
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
body {
  justify-items: center;
  background-color: whitesmoke;
}

#edit-profile {
  color:skyblue
}

#edit-profile:hover {
  color: paleturquoise
}
</style>