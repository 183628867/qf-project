<template>
	<div>
		<el-card class="box-card">
			<div>
				<el-input placeholder="请输入角色名" prefix-icon="el-icon-search" v-model="name" class="name"></el-input>
				<el-button type="primary" @click="getRolesList()" icon="el-icon-search">查询</el-button>
			</div>
			<el-button type="primary" icon="el-icon-plus" class="add" @click="add()">添加</el-button>
			<el-table border :data="tableData" tooltip-effect="dark" style="width: 100%">
				<!-- <el-table-column type="selection" width="55"></el-table-column> -->
				<el-table-column prop="roleName" label="角色"></el-table-column>
				<el-table-column prop="insertDt" label="添加时间"></el-table-column>
				<el-table-column prop="updateDt" label="修改时间"></el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button size="mini" type="primary" plain @click="fq(scope.row)">赋权</el-button>
						<el-button size="mini" type="warning" plain @click="handleEdit(scope.$index, scope.row)">修改
						</el-button>
						<el-button size="mini" type="danger" plain @click="remove(scope.$index, scope.row)">删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-card>
		<div style="text-align: center;margin-top: 30px;">
			<el-pagination background layout="total,prev, pager, next" :total="total" @current-change="currentChange"
				:page-size="limit"></el-pagination>
		</div>

		<!--添加/编辑角色弹窗-->
		<el-dialog :title="dialogTitle" width="30%" :visible.sync="iconFormVisible">
			<el-form :inline="true" :model="userInfo" class="demo-form-inline" @submit.native.prevent>
				<el-form-item label="角色名">
					<el-input v-model="userInfo.roleName" placeholder="角色名"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="iconFormVisible = false">取 消</el-button>
				<el-button type="primary" @click="submitUser()">确 定</el-button>
			</div>
		</el-dialog>

		<!--赋权弹窗-->
		<el-dialog title="角色赋权" width="30%" :visible.sync="iconFormVisibleFQ">
			<el-tree show-checkbox :check-strictly="false" :data="menuData" :props="defaultProps" node-key="id"
				ref="tree">
			</el-tree>
			<div slot="footer" class="dialog-footer">
				<el-button @click="iconFormVisibleFQ = false">取 消</el-button>
				<el-button type="primary" @click="submitPermissions()">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	var that = null;
	export default {
		data() {
			return {
				menuData: [],
				tableData: [],
				total: 0,
				name: "",
				page: 1,
				limit: 10,
				iconFormVisible: false, //	添加/编辑
				iconFormVisibleFQ: false, //	赋权
				userInfo: {},
				dialogTitle: '增加',
				rowIndex: null,
				defaultProps: {
					children: 'children',
					label: 'name'
				},
				roleId: ""
			}
		},
		mounted(e) {
			that = this;
			that.getRolesList();
		},
		methods: {
			getRolesList() {
				let params = {
					page: that.page,
					limit: that.limit,
					roleName: that.name,
				}
				that.$getRequest("role/selectAllRole", params, function(res) {
					that.tableData = res.roleList;
					that.total = res.counts;
				}, 0)

			},
			currentChange: function(page) {
				that.page = page;
				that.getRolesList()
			},
			//	角色赋权
			fq: function(row) {
				that.iconFormVisibleFQ = true;
				that.roleId = row.id;
				var params = {
					page: 1,
					limit: 100,
				}
				that.$getRequest("menu/selectMenus", params, function(res) {
					that.menuData = res.menuList;
				}, 0)
				var params = {
					id: row.id
				}
				setTimeout(function() {
					that.$postRequest("menu/findMenus", params, function(res) {
						let menuList = res.menuList;
						// // 循环遍历将此角色拥有的菜单选中
						for (var i = 0; i < menuList.length; i++) {
							var node = that.$refs.tree.getNode(menuList[i].id);
							if (node.isLeaf) {
								that.$refs.tree.setChecked(node, true);
							}
						}
					}, 1)
				}, 500)
			},
			//	确定赋权
			submitPermissions: function() {
				let str = that.$refs.tree.getCheckedKeys().concat(that.$refs.tree.getHalfCheckedKeys())
				let arr = [];
				for (let i = 0; i < str.length; i++) {
					if (i == str.length - 1) {
						arr += str[i]
					} else {
						arr += str[i] + ",";
					}
				}
				var params = {
					roleId: that.roleId,
					list: arr
				}
				that.$postRequest("role/empowerment", params, function(res) {
					that.iconFormVisibleFQ = false;
					that.$message.success("赋权成功")
				}, 1)
			},
			//	添加
			add() {
				that.dialogTitle = '增加';
				that.userInfo = {};
				that.iconFormVisible = true;
			},
			// 编辑
			handleEdit(index, row) {
				console.log(JSON.stringify(row));
				that.dialogTitle = '编辑';
				that.userInfo = row;
				that.iconFormVisible = true;
				that.rowIndex = index;
			},
			submitUser() {
				if (that.dialogTitle === '编辑') {
					that.tableData.splice(that.rowIndex, 1, that.userInfo);
					that.iconFormVisible = false;
					return;
				}
				that.tableData.splice(0, 0, that.userInfo);
				that.iconFormVisible = false;
				that.$postRequest("role/addRole", that.userInfo, function(res) {}, 0)
			},
			// 删除
			remove(index, row) {
				that.$confirm(`确定删除${row.roleName}吗?`, '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'error',
				}).then(() => {
					that.tableData.splice(index, 1);
					let params = {
						roleId: row.id,
						deleteFlag: 1
					}
					that.$postRequest("role/updateRoleId", params, function(res) {}, 0)
				}).catch((err) => {
					console.log(err);
				});
			},
		}
	}
</script>

<style>
	.name {
		margin: 20px;
		width: 300px;
	}
	
	.box-card{
		width: 100%;
	}
</style>
