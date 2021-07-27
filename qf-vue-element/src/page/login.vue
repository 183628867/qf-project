<template>
	<div class="login-wrap">
		<el-form class="login-form" label-position="top" label-width="80px" :model="formdata">
			<h2>用户登录</h2>
			<el-form-item label="用户名">
				<el-input v-model="formdata.account"></el-input>
			</el-form-item>
			<el-form-item label="密码">
				<el-input v-model="formdata.password"></el-input>
			</el-form-item>

			<el-button class="login-btn" type="primary" @click="handleLodin()">登录</el-button>
		</el-form>
	</div>
</template>
<script>
	var that = null;
	export default {
		data() {
			return {
				formdata: {
					account: "1997",
					password: "123",
				},
			}
		},
		mounted() {
			that = this;
			//	防止页面后退
			history.pushState(null, null, document.URL);
			window.addEventListener('popstate', function() {
				history.pushState(null, null, document.URL);
			});
		},
		methods: {
			handleLodin() {
				if (that.formdata.account == '' || that.formdata.password == '') {
					that.$message({
						showClose: true,
						message: '用户名和密码不能为空',
						type: 'error'
					});
					return
				}
				//正式版本需要发送请求
				//url是要发送请求的地址
				that.$postRequest("login/loginIn", that.formdata, function(res) {
					sessionStorage.setItem('token', res.token)
					sessionStorage.setItem('sessionId', res.sessionId)
					sessionStorage.setItem('manager', JSON.stringify(res.manager))
					//  1.跳转home页面
					that.$router.push({
						path: '/home'
					})
					//  2.提示成功
					that.$message({
						showClose: true,
						message: '登录成功',
						type: 'success'
					});
					localStorage.setItem("menuData", JSON.stringify(res.menuList))
				}, 1)
			}
		}
	}
</script>
<style scoped="scoped" >
	.login-wrap {
		height: 100%;
		background: #324152;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.login-form {
		width: 400px;
		background: #fff;
		border-radius: 5px;
		padding: 30px;
		text-align: left;
	}

	.login-btn {
		width: 100%;
	}
</style>
