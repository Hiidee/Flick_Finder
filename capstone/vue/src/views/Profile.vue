<template>
  <body>
    <div>
      <profile-data></profile-data>
    </div>
  </body>
</template>

<script>
import ProfileData from "@/components/ProfileData.vue";

export default {
  components: {
    ProfileData,
  },
};
</script>

<style>
body {
  justify-content: center;
}
</style>