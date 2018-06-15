import Vue from 'vue'
import Router from 'vue-router'
import navbar from '@/components/navbar'
import welcome from '@/components/welcome'
import discover from '@/components/discover'
import user_page from '@/components/user_page'
import shopping_cart from '@/components/shopping_cart'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/welcome',
      name: 'welcome',
      component: welcome
    },
    {
      path: '/discover',
      name: 'discover',
      component: discover
    },
    {
      path: '/user',
      name: 'user',
      component: user_page
    },
    {
      path: '/shopping_cart',
      name: 'shopping_cart',
      component: shopping_cart
    },
  ]
})
