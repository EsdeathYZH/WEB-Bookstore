// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import navbar from './components/navbar'
import shopping_cart from './components/shopping_cart'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el:'#navbar',
  template: '<navbar/>',
  components:{navbar}
})

new Vue({
  el:'#shopping_cart',
  template:'<shopping_cart/>',
  components:{shopping_cart}
})
