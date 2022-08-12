<template>
  <main>
    <div id="app">
      <div class="banner">
        <banner-view></banner-view>
        <router-view />
      </div>
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
  background-image: url(https://wallpaperaccess.com/full/4840785.jpg);
  height: 100vh;
}
main {
  color: limegreen;
}
</style>
