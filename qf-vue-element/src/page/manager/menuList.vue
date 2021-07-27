<template>
	<div>
		<el-card class="box-card">
			<el-button type="primary" icon="el-icon-plus" class="add" @click="add()">添加</el-button>
			<el-table :data="tableData" style="width: 100%;margin-bottom: 20px;" row-key="id" border default-expand-all
				:tree-props="{children: 'children', hasChildren: 'hasChildren'}">
				<el-table-column prop="order" label="排序"></el-table-column>
				<el-table-column prop="name" label="菜单名称"></el-table-column>
				<el-table-column prop="parentName" label="父级菜单"></el-table-column>
				<el-table-column prop="path" label="菜单路径" show-overflow-tooltip></el-table-column>
				<el-table-column prop="icon" label="菜单图标" show-overflow-tooltip></el-table-column>
				<el-table-column prop="insertDt" label="添加时间"></el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button size="mini" type="warning" plain @click="handleEdit(scope.$index, scope.row)">修改
						</el-button>
						<el-button size="mini" type="danger" plain @click="deleteMenu(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-card>

		<!--添加/编辑菜单弹窗-->
		<el-dialog :title="dialogTitle" width="30%" :visible.sync="iconFormVisible">
			<el-form :inline="true" :model="menuInfo" class="demo-form-inline">
				<el-form-item label="父级菜单">
					<el-select v-model="parentId" placeholder="默认就是父级菜单" style="width: 206px;">
						<el-option v-for="item in menuData" :key="item.id" :label="item.name" :value="item.id">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="菜单名称">
					<el-input v-model="menuInfo.menuName" placeholder="菜单名称"></el-input>
				</el-form-item>
				<el-form-item label="菜单路径">
					<el-input v-model="menuInfo.menuPath" placeholder="父级菜单填不填即可"></el-input>
				</el-form-item>
				<el-form-item label="菜单图标">
					<el-input v-model="menuInfo.menuIcon" placeholder="elememtUI图标"></el-input>
				</el-form-item>
				<el-form-item label="菜单排序">
					<el-input v-model="menuInfo.order" placeholder="按顺序排"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="iconFormVisible = false">取 消</el-button>
				<el-button type="primary" @click="submitUser()">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	var that = null;
	export default {
		data() {
			return {
				name: "",
				tableData: [],
				iconFormVisible: false, //	添加/编辑
				menuInfo: {
					menuName: "",
					menuPath: "",
					menuIcon: "",
					order: ""
				},
				menuData: [], //	菜单
				dialogTitle: "菜单",
				parentId: "",
				id: ""
			}
		},
		mounted(e) {
			that = this;
			that.getMenuList();
		},
		methods: {
			getMenuList() {
				let params = {

				}
				that.$getRequest("menu/selectMenus", params, function(res) {
					that.tableData = res.menuList;
				}, 0)
			},
			getMenus() {
				var params = {
					page: 1,
					limit: 100,
				}
				let data = []
				that.$getRequest("menu/selectMenus", params, function(res) {
					let menuList = res.menuList;
					for (let i = 0; i < menuList.length; i++) {
						if (menuList[i].parentId == 0) {
							data.push(menuList[i])
						}
					}
					that.menuData = data;
				}, 0)
			},
			//	添加
			add() {
				that.dialogTitle = '添加菜单';
				that.menuInfo = {};
				that.parentId = "";
				that.iconFormVisible = true;
				that.getMenus()
			},
			submitUser() {
				if (that.dialogTitle == "添加菜单") {
					var params = {
						parentId: that.parentId,
						menuName: that.menuInfo.menuName,
						menuPath: that.menuInfo.menuPath,
						menuIcon: that.menuInfo.menuIcon,
						order: that.menuInfo.order
					}
					that.$postRequest("menu/addMenu", params, function(res) {
						that.iconFormVisible = false;
						that.$message.success("添加菜单成功")
						that.getMenuList();
					}, 0)
				}
				if (that.dialogTitle == "修改菜单") {
					var params = {
						parentId: that.parentId,
						menuName: that.menuInfo.menuName,
						menuPath: that.menuInfo.menuPath,
						menuIcon: that.menuInfo.menuIcon,
						order: that.menuInfo.order,
						id: that.id
					}
					that.$postRequest("menu/updateMenu", params, function(res) {
						that.iconFormVisible = false;
						that.$message.success("修改菜单成功")
						that.getMenuList();
					}, 0)
				}
			},
			handleEdit(index, row) {
				console.log(row)
				that.dialogTitle = '修改菜单';
				that.menuInfo.menuName = row.name;
				that.menuInfo.menuPath = row.path;
				that.menuInfo.order = row.order;
				that.menuInfo.menuIcon = row.icon;
				that.iconFormVisible = true;
				that.parentId = row.parentId;
				that.id = row.id;
				that.getMenus()
			},
			deleteMenu(row) {
				console.log(row)
				let params = {
					id: row.id
				}

				that.$common.deleteConfirm("确定删除此菜单", function() {
					that.$postRequest("menu/deleteMenu", params, function(res) {
						that.$message.success("删除菜单成功")
						that.getMenuList();
					}, 0)
				})
			}
		}
	}
</script>

<style>
	.name {
		margin: 20px;
		width: 300px;
	}

	.box-card {
		width: 100%;
	}
</style>
