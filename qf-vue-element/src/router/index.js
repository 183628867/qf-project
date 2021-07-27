import Vue from "vue";
import VueRouter from "vue-router";
import login from '../page/login.vue';
import Home from '../page/home.vue';
import Main from '../page/main.vue';
import notFound from '../page/notFound.vue';

import ManagerList from '../page/manager/managerList.vue';
import RolesList from '../page/manager/rolesList.vue';
import MenuList from '../page/manager/menuList.vue';
import loginLogList from '../page/log/LoginLogList.vue';

Vue.use(VueRouter);
//以下代码解决路由地址重复的报错问题(一劳永逸)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
const routes = [{
		path: '/',
		name: 'login',
		component: login
	},
	{
		path: '/main',
		name: '主页面',
		component: Main,
		children: [{
				path: '/home',
				name: '首页',
				component: Home
			}, {
				path: '/managerList',
				name: '后台用户信息',
				component: ManagerList,
			},
			{
				path: '/rolesList',
				name: '角色信息',
				component: RolesList,
			},
			{
				path: '/menuList',
				name: '菜单信息',
				component: MenuList,
			},
			{
				path: '/loginLogList',
				name: '登录日志',
				component: loginLogList,
			},
		]
	},
	{
		path: "/notFound",
		name: "notFound",
		component: notFound
	},
	{
		path: '*',
		redirect: '/notFound'
	}
]

export default new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})
