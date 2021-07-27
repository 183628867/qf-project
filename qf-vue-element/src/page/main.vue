<template>
	<el-container class="container">

		<el-header>
			<Header></Header>
		</el-header>
		

		<el-container>
			<el-aside width="200px">
				<Menu ref="Menu"></Menu>
			</el-aside>
			<el-container>
				<el-header class="breadCrumb-head">
					<BreadCrumb ref="BreadCrumb"></BreadCrumb>
				</el-header>
				<el-main>
					<router-view></router-view>
				</el-main>
			</el-container>
		</el-container>

	</el-container>
</template>

<script>
	import BreadCrumb from './component/BreadCrumb.vue'
	import Menu from './component/Menu.vue'
	import Header from './component/Header.vue'


	export default {
		components: {
			BreadCrumb,
			Menu,
			Header
		},
		data() {
			return {
				activeIndex: '1',
				activeIndex2: '1',
				menuData: []
			};
		},
		
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			//	退出登录
			logout() {
				this.$confirm("退出登录, 是否继续?", "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning",
				}).then(() => {
					this.$router.push({
						path: "/"
					})
				}).catch((err) => {
					console.log(err);
				});
			},
			clickMenuItem() {
				console.log("....");
			},
			//回到首页
			clickTitle() {},
			home() {
				this.$router.push({
					path: "/main"
				})
			},
			//	修改密码
			updatePassword: function() {
				console.log(this.ruleForm);
			},
			//	重置
			resetForm() {
				this.$refs['ruleForm'].resetFields();
			}
		}
	}
</script>

<style>
	.container {
		position: absolute;
		width: 100%;
		top: 0px;
		left: 0;
		bottom: 0;
	}

	.el-header {
		background: #f4f4f4;
		padding: 0;
		height: 30px;
	}

	/* // header菜单需要靠右的添加.fr即可(如：<el-menu-item class="fr" index="3">消息中心</el-menu-item>) */
	.el-header .fr {
		float: right;
	}



	.el-aside {
		background: #f4f4f4;
	}



	/* 个人中心信息 */
	.el-drawer {
		overflow: auto;
	}

	.el-drawer::-webkit-scrollbar {
		display: none;
		/* Chrome Safari */
	}

	.text {
		font-size: 14px;
	}

	.item {
		padding: 18px 0;
	}

	.box-card {
		width: 90%;
		margin: 10px auto;
	}

	.registe-info {
		text-align: center;
		padding-top: 10px;
	}

	.personal-relation {
		font-size: 16px;
		padding: 0px 5px 15px;
		margin-right: 1px;
		width: 100%
	}



	.breadCrumb-head {
		height: 40px !important;
		line-height: 40px !important;
		border-bottom: 1px solid #e6e6e6;
	}
</style>
