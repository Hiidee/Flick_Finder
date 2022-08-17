<template>
  <main class="main">
    <div id="app">
      <banner-view class = "banner"></banner-view>
      <router-view class="router" />
      <baseline-view class="footer"></baseline-view>
    </div>
  </main>
</template>

<script>
import BannerView from "@/views/BannerView.vue";
import BaselineView from "@/views/BaselineView.vue";

export default {
  components: {
    BannerView,
    BaselineView,
  },
};
</script>

 <style>
#app {
  background: linear-gradient(0deg, #0c090a, #52595d 40%, #262a30);
  height: 120%;
  color: rgb(184, 207, 209);
}

.banner{

}

.router {
}

.footer {
}
</style>