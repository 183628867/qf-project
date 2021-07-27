<template>
	<div>
		<el-card class="box-card">
			<div>
				<el-input placeholder="请输入姓名" prefix-icon="el-icon-search" v-model="name" class="name"></el-input>
				<el-input placeholder="请输入账号" prefix-icon="el-icon-search" v-model="account" class="name"></el-input>
				<el-button type="primary" @click="getManagerList()" icon="el-icon-search">查询</el-button>
			</div>
			<el-button size="small" type="primary" icon="el-icon-plus" class="add" @click="add()">添加</el-button>
			<el-table border :data="tableData" tooltip-effect="dark" style="width: 100%">
				<el-table-column prop="account" label="账号"></el-table-column>
				<el-table-column prop="name" label="姓名"></el-table-column>
				<el-table-column prop="sex" label="性别" width="180">
					<template slot-scope="scope">
						{{getChangeType(scope.row.sex)}}
					</template>
				</el-table-column>
				<el-table-column prop="roleName" label="角色"></el-table-column>
				<el-table-column prop="img" label="头像" show-overflow-tooltip></el-table-column>
				<el-table-column prop="insertDt" label="添加时间" width="180"></el-table-column>
				<el-table-column prop="disable" label="禁用" width="180">
					<template slot-scope="scope">
						<el-switch on-text="是" on-color="#5B7BFA" off-color="#dadde5"
							v-model="scope.row.disable == 0 ? false:true"
							@change="changeDisable(scope.$index,scope.row)">
						</el-switch>
					</template>
				</el-table-column>
				<el-table-column label="操作" width="220">
					<template slot-scope="scope">
						<el-button size="mini" type="primary" plain @click="fj(scope.row)">赋角</el-button>
						<el-button size="mini" type="warning" plain @click="updateManager(scope.row)">修改</el-button>
						<el-button size="mini" type="danger" plain @click="deleteManager(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div style="text-align: center;margin-top: 30px;">
				<el-pagination background layout="total,prev, pager, next" :total="total"
					@current-change="currentChange" :page-size="limit"></el-pagination>
			</div>
		</el-card>




		<!--赋权弹窗-->
		<el-dialog title="用户赋角" width="30%" :visible.sync="iconFormVisibleFJ">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="角色">
					<el-select v-model="value" placeholder="请选择">
						<el-option v-for="item in options" :key="item.id" :label="item.roleName" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
			</el-form>

			<div slot="footer" class="dialog-footer">
				<el-button @click="iconFormVisibleFJ = false">取 消</el-button>
				<el-button type="primary" @click="submitPermissions()">确 定</el-button>
			</div>
		</el-dialog>


		<!--赋权弹窗-->
		<el-dialog :title="managerTitle" width="30%" :visible.sync="iconFormVisibleManager">
			<el-form ref="form" :model="form1" label-width="80px">
				<el-form-item label="登录账号">
					<el-input v-model="form1.account" class="w200px"></el-input>
				</el-form-item>
				<el-form-item label="用户名">
					<el-input v-model="form1.name" class="w200px"></el-input>
				</el-form-item>
				<el-form-item label="性别">
					<el-select v-model="form1.sex" placeholder="请选择性别" class="w200px">
						<el-option label="男" value="0"></el-option>
						<el-option label="女" value="1"></el-option>
					</el-select>
				</el-form-item>

			</el-form>

			<div slot="footer" class="dialog-footer">
				<el-button @click="iconFormVisibleManager = false">取 消</el-button>
				<el-button type="primary" @click="submitManager()">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	var that = null;
	export default {
		data() {
			return {
				disableValue: "",
				tableData: [],
				total: 0,
				name: "",
				account: "",
				page: 1,
				limit: 10,
				iconFormVisibleFJ: false, //	赋角
				iconFormVisibleManager: false,
				form: {
					region: '',
				},
				options: [],
				value: "",
				userInfo: {},

				sexList: [{
					value: '0',
					label: '男'
				}, {
					value: '1',
					label: '女'
				}],
				form1: {
					account: '', //	登录账户
					name: '', //	用户名
					sex: '', //	性别
				},
				managerTitle: "添加账户"
			}
		},
		mounted(e) {
			that = this;
			that.getManagerList();
		},
		methods: {
			//使用插槽
			getChangeType(e) {
				for (var i = 0; i < that.sexList.length; i++) {
					if (that.sexList[i].value == e) {
						return that.sexList[i].label;
					}
				}
			},
			getManagerList() {
				let params = {
					page: that.page,
					limit: that.limit,
					name: that.name,
					account: that.account
				}
				that.$getRequest("manager/findAllUser", params, function(res) {
					that.tableData = res.userList;
					that.total = res.counts;
				}, 0)
			},
			currentChange: function(page) {
				that.page = page;
				that.getManagerList()
			},
			// 赋角
			fj: function(row) {
				that.iconFormVisibleFJ = true;
				that.userInfo = row;
				let params = {
					page: 1,
					limit: 100
				}
				that.$getRequest("role/selectAllRole", params, function(res) {
					that.options = res.roleList;
				}, 0)
			},
			//	确认权限
			submitPermissions: function() {
				let params = {
					roleId: that.value,
					id: that.userInfo.id
				}
				that.$postRequest("manager/updateUser", params, function(res) {
					that.options = res.roleList;
					that.iconFormVisibleFJ = false;
					that.$message.success("赋角成功")
					that.getManagerList();
				}, 0)
			},
			//	添加账户
			add() {
				that.managerTitle = "添加账户";
				that.iconFormVisibleManager = true;
			},
			//	修改账户
			updateManager(row) {
				that.iconFormVisibleManager = true;
				that.form1 = row;
				that.managerTitle = "修改账户";
			},
			submitManager() {
				let params = that.form1

				let url = "";
				if (that.managerTitle = "添加账户") {
					url = "manager/addUser";
				}
				if (that.managerTitle = "修改账户") {
					url = "manager/updateUser";
				}

				that.$postRequest(url, params, function(res) {
					that.$message.success("成功!")
				}, 0)
				that.getManagerList();
				that.iconFormVisibleManager = false;
			},
			//	删除账户
			deleteManager(row) {
				let params = {
					id: row.id,
					deleteFlag: 1,
					disable: 1
				}
				that.$common.deleteConfirm("确认删除此账号?", function() {
					that.$postRequest("manager/updateUser", params, function(res) {
						that.$message.success("删除账号成功!")
						that.getManagerList();
					}, 0)
				})
			},
			//	禁用 / 启用账号
			changeDisable(index, row) {
				let text = "";
				let textSucc = "";
				let disable = "";
				if (row.disable == 0) {
					text = "确认禁用?";
					textSucc = "禁用账号成功!";
					disable = 1;
				} else {
					text = "确认启用?"
					textSucc = "启用账号成功!";
					disable = 0;
				}
				let params = {
					id: row.id,
					disable: disable
				}
				that.$common.deleteConfirm(text, function() {
					that.$postRequest("manager/updateUser", params, function(res) {
						that.$message.success(textSucc)
						that.getManagerList();
					}, 0)
				})
			}
		}
	}
</script>

<style scoped>
	.name {
		margin: 20px;
		width: 300px;
	}

	.box-card {
		width: 100%;
	}

	.w200px {
		width: 200px;
	}
</style>
