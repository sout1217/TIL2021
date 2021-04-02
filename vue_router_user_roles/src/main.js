import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import VueRouterUserRoles from 'vue-router-user-roles';

Vue.config.productionTip = false;
Vue.use(VueRouterUserRoles, { router });

let authentication = Promise.resolve({ role: 'guest', level: 0 });

authentication.then(user => {
  Vue.prototype.$user.set(user);
  new Vue({
    router,
    store,
    render: h => h(App),
  }).$mount('#app');
});
