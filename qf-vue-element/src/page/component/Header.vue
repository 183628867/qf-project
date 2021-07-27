<template>
	<div>
		<el-menu :default-active="activeIndex" background-color="#545c64" text-color="#fff" active-text-color="#0090d8"
			class="el-menu-demo" mode="horizontal">
			<el-menu-item class="logo">
				<img src="../../static/img/logotm.png">
				<span>清枫后台管理平台</span>
			</el-menu-item>
			<el-menu-item>
				<span class="el-icon-s-fold"></span>
			</el-menu-item>
			<el-submenu index="1" class="fr">
				<template slot="title">我的工作台</template>
				<el-menu-item @click="drawer = true">个人中心</el-menu-item>
				<el-menu-item @click="logout()">退出登录</el-menu-item>
			</el-submenu>
		</el-menu>


		<el-drawer title="个人中心" :visible.sync="drawer" :with-header="false">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span>基本信息</span>
				</div>
				<div class="registe-info">
					<div class="block">
						<el-avatar :size="50" :src="dataForm.managerImg"></el-avatar>
					</div>
					<!-- <span class="sender">Admin - {{dataForm.nickName}}</span> -->
				</div>
				<div class="registe-info">
					<span class="">
						注册时间：
						<li class="el-icon-time"></li>
						{{dataForm.insertDt}}
					</span>
				</div>
				<el-divider></el-divider>
				<div class="personal-relation">
					<div class="relation-item">登录账号:
						<div style="float: right; padding-right:20px;">{{dataForm.account}}</div>
					</div>
				</div>
				<div class="personal-relation">
					<div class="relation-item">登录姓名:
						<div style="float: right; padding-right:20px;">{{dataForm.managerName}}</div>
					</div>
				</div>
			</el-card>

			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span>修改密码</span>
				</div>
				<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
					class="demo-ruleForm">
					<el-form-item label="原密码" prop="oldPass">
						<el-input type="password" v-model="ruleForm.oldPass" autocomplete="off" show-password>
						</el-input>
					</el-form-item>
					<el-form-item label="新密码" prop="newPass">
						<el-input type="password" v-model="ruleForm.newPass" autocomplete="off" show-password>
						</el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="checkPass">
						<el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" show-password>
						</el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
						<el-button @click="resetForm('ruleForm')">重置</el-button>
					</el-form-item>
				</el-form>
			</el-card>
		</el-drawer>
	</div>
</template>

<script>
	var that = null;

	export default {
		data() {
			var checkPass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请再次输入密码'));
				} else if (value !== this.ruleForm.newPass) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			};
			var oldPass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入密码'));
				} else {
					callback();
				}
			};
			var newPass = (rule, value, callback) => {
				console.log(value)
				if (!value) {
					return callback(new Error('密码不能为空'));
				}
				setTimeout(() => {
					if (value.length < 3) {
						callback(new Error('密码必须大于3位'));
					} else {
						callback();
					}
				}, 1000);
			};
			return {
				activeIndex: '1',
				drawer: false,
				dataForm: {
					managerName: '超级管理员',
					account: '173567777777',
					insertDt: '',
					managerImg: "http://www.ys99.top:9000/story/web/6f98b63e9ffc490c8b23ffc2bb102961.png",
				},
				id: '',
				ruleForm: {
					oldPass: '',
					newPass: '',
					checkPass: ''
				},
				rules: {
					oldPass: [{
						validator: oldPass,
						trigger: 'blur'
					}],
					newPass: [{
						validator: newPass,
						trigger: 'blur'
					}],
					checkPass: [{
						validator: checkPass,
						trigger: 'blur'
					}]
				},

			};
		},
		mounted() {
			that = this;
			let manager = JSON.parse(sessionStorage.getItem("manager"));
			that.dataForm.managerName = manager.name;
			that.dataForm.account = manager.account;
			that.dataForm.insertDt = manager.insertDt;
			let img = "http://www.ys99.top:9000/story/web/6f98b63e9ffc490c8b23ffc2bb102961.png";
			
			if(manager.img == null){
				that.dataForm.managerImg = img;
			}else{
				that.dataForm.managerImg = "http://www.ys99.top:9000/" + manager.img
			}
			that.id = manager.id;

		},
		methods: {
			logout: function() {
				that.$router.push({
					path: '/'
				})
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						that.updatePassword()
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			updatePassword() {
				let params = {
					id: that.id,
					oldPass: that.ruleForm.oldPass,
					newPass: that.ruleForm.newPass,
					password: that.ruleForm.checkPass
				}

				that.$common.deleteConfirm("确认修改账户密码?", function() {
					that.$postRequest("manager/updatePassword", params, function(res) {
						that.$message.success("修改密码成功!")
						that.logout()
					}, 0)
				})
			}

		}
	}
</script>
<style>
	.logo {
		width: 200px;
	}

	.logo img {
		height: 50px;
	}
</style>
