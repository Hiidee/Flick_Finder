<template>
  <main>
    <div id="app">
      <div class="banner">
        <banner-view></banner-view>
      </div>
      <router-view />
      <div class="baseline">
        <baseline-view></baseline-view>
      </div>
    </div>
  </main>
</template>

<script>
import BannerView from '@/views/BannerView.vue';
import BaselineView from '@/views/BaselineView.vue';

export default {
  components: {
    BannerView,
    BaselineView,
  },
};
</script>

 <style>
#app {
  /* background-image: url(https://wallpaperaccess.com/full/4840785.jpg); */
  background: linear-gradient(0deg, #0C090A, #52595D 40%, #262a30);
  height: 100vh;
}
main {
}
</style>