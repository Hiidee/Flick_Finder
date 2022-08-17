<template>
  <main class = "main">
    <div id="app">
      <div class="banner">
        <banner-view></banner-view>
      </div>
      <router-view class = "router" />
      <div class="baseline">
        <baseline-view class="footer"></baseline-view>
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
  background: linear-gradient(0deg, #0C090A, #52595D 40%, #262a30);
  height: 120%;
  color: rgb(184, 207, 209);
}

.router{
  height: 100vh;
}
</style>