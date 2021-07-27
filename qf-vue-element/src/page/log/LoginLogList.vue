<template>
	<div>
		<el-card class="box-card">
			<div>
				<el-input placeholder="请输入登录账号" prefix-icon="el-icon-search" v-model="name" class="name"></el-input>
				<el-button type="primary" @click="getLoginLogList()" icon="el-icon-search">查询</el-button>
			</div>
			<el-table border :data="tableData" tooltip-effect="dark" style="width: 100%">
				<el-table-column prop="id" label="序号"></el-table-column>
				<el-table-column prop="name" label="登录账号"></el-table-column>
				<el-table-column prop="ip" label="ip"></el-table-column>
				<el-table-column prop="browser" label="浏览器"></el-table-column>
				<el-table-column prop="systemMc" label="操作系统" ></el-table-column>
				<el-table-column prop="insertDt" label="添加时间" width="180"></el-table-column>
			</el-table>
			<div style="text-align: center;margin-top: 30px;">
				<el-pagination background layout="total,prev, pager, next" :total="total"
					@current-change="currentChange" :page-size="limit"></el-pagination>
			</div>
		</el-card>
	</div>
</template>

<script>
	var that = null;
	export default {
		data() {
			return {
				tableData: [],
				total: 0,
				name: "",
				account: "",
				page: 1,
				limit: 10,
			}
		},
		mounted(e) {
			that = this;
			that.getLoginLogList();
		},
		methods: {
			getLoginLogList(){
				let params = {
					page: that.page,
					limit: that.limit,
					name: that.name
				}
				that.$getRequest("log/LoginLogList", params, function(res) {
					that.tableData = res.logList;
					that.total = res.counts;
				}, 0)
			},
			currentChange: function(page) {
				that.page = page;
				that.getLoginLogList()
			},
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
</style>
