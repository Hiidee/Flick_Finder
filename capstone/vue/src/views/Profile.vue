<template>
  <body>
    <div class = "profile-page">
      <div>
        <i class="fa-solid fa-pen-to-square fa-2x" id="edit-profile" @click="flipStatus"></i>
    </div>
      <profile-display v-if="!showForm"></profile-display>
      <profile-data v-if="showForm"></profile-data>
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
      if(this.showForm == false) {
        this.showForm = true
      } else {
        this.showForm = false
      }
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
.profile-page{
  justify-items: center;
  background-color: whitesmoke;
  height: 100vh;
}

#edit-profile {
  color:skyblue;
}

#edit-profile:hover {
  color: paleturquoise;
}
</style>