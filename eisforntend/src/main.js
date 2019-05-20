// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
//import vuesession
import VueSession from "vue-session";
import VueResource from "vue-resource";

// Components
import "./components";

// Plugins
import "./plugins";

// Sync router with store
import { sync } from "vuex-router-sync";

// Application imports
import router from "./router";
import App from "./App";
import i18n from "@/i18n";
import store from "@/store";
import moment from "moment";
// import router from "@/router";

// import Datetime from 'vue-datetime'
// // You need a specific loader for CSS files
// import 'vue-datetime/dist/vue-datetime.css'

// Sync store with router
sync(store, router);

Vue.config.productionTip = false;
Vue.filter("formatDate", function(value) {
  if (value) {
    return moment(String(value)).format("DD/MM/YYYY");
  }
});

Vue.use(VueSession);
Vue.use(VueResource);
/* eslint-disable no-new */
new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
