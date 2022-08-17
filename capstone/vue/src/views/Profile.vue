<template>
  <body>
    <div>
      <profile-display></profile-display>
      <profile-data></profile-data>
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
};
</script>