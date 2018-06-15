// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import navbar from './components/navbar'
import discover from './components/discover'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
	el:'#navbar',
	template: '<navbar/>',
	components:{navbar}
})


new Vue({
	el:'#discover',
	template:'<discover/>',
	components:{discover}
})
