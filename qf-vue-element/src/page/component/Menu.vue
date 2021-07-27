<template>
	<div>
		<el-row class="tac">
			<el-col>
				<el-menu router class="el-menu-vertical-demo" active-text-color="#2196f3">
					<div v-for="(menu, subIndex) in navList" :key="subIndex">
						<!--只有一级菜单-->
						<el-menu-item v-if="!menu.children" :index="menu.path">
							<i :class="menu.icon"></i>
							{{ menu.name }}
						</el-menu-item>
						<!-- 多级菜单 -->
						<el-submenu v-else :index="subIndex + ''">
							<template slot="title">
								<i :class="menu.icon"></i>
								<span>{{ menu.name }}</span>
							</template>
							<el-menu-item-group>
								<el-menu-item v-for="(subMenu, index) in menu.children" :index="subMenu.path" :key="index">
									<i :class="subMenu.icon"></i>
									{{ subMenu.name }}
								</el-menu-item>
							</el-menu-item-group>
						</el-submenu>
					</div>
				</el-menu>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	var that = null;
	export default {
		data() {
			return {
				navList: []
			}
		},
		mounted() {
			that = this;
			let menuData = localStorage.getItem("menuData");
			console.log(menuData)
			that.navList = JSON.parse(menuData)
		},
		methods: {
			
		}
	}
</script>
<style>
	.el-col {
		width: 100%;
	}
</style>
