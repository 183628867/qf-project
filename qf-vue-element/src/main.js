import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from "./router/index.js"
import axios from 'axios'
import echarts from 'echarts'
import common from "./page/common/common.js"



// import locale from 'element-ui/lib/locale/lang/en'	//	英文


import {
	getRequest,
	postRequest
} from './page/common/request.js'
Vue.prototype.$getRequest = getRequest;
Vue.prototype.$postRequest = postRequest;
Vue.prototype.$http = axios;
Vue.prototype.$echarts = echarts;
Vue.prototype.$common = common;

Vue.use(ElementUI)

new Vue({
	el: '#app',
	router,
	render: h => h(App)
})
