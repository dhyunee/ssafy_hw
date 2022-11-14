import Vue from "vue";
import App from "./App.vue";
import router from "./routers/routers.js";
Vue.config.productionTip = false;

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
