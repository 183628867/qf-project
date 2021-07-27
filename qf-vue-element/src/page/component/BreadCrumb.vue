<template>
	<div class="navbar clearfix">
		<el-breadcrumb class="breadcrumb-header" separator-class="el-icon-arrow-right">
			<el-breadcrumb-item v-for="item in breadList" :key="item.path" :to="item.path">{{item.name}}
			</el-breadcrumb-item>
		</el-breadcrumb>
	</div>
</template>



<script>
	export default {
		data() {
			return {
				breadList: [] // 路由集合
			};
		},
		watch: {
			$route() {
				this.getBreadcrumb();
			}
		},
		methods: {
			isHome(route) {
				return route.name === "home";
			},
			getBreadcrumb() {
				let matched = this.$route.matched;

				//如果不是首页
				if (!this.isHome(matched[0])) {
					matched = [{
						path: "home",
						meta: {
							title: "首页"
						}
					}].concat(matched);
				}
				this.breadList = matched;

				console.log(matched);
			}
		},
		created() {
			this.getBreadcrumb();
		}
	};
</script>
<style>
	.breadcrumb-header {
		height: 40px;
		line-height: 40px;
	}
</style>
