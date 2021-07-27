<template>
	<div>
		<el-card>
			<div class="head">
				<el-card class="box-card">
					<div>今日访客量</div>
					<div class="f30">{{todayFk}}</div>
				</el-card>
				<el-card class="box-card">
					<div>昨日访客量</div>
					<div class="f30">{{yesterdayFk}}</div>
				</el-card>
				<el-card class="box-card">
					<div>总访客量</div>
					<div class="f30">{{sumFk}}</div>
				</el-card>
				<el-card class="box-card">
					<div>近一周访客量</div>
					<div class="f30">{{weekFk}}</div>
				</el-card>
			</div>
		</el-card>
		<div class="cont">
			<div id="main"></div>
			<div>
				<div class="text-one">清枫后台管理框架</div>
				<div class="text-two">
					做这个系统的初衷是因为我本人是后端开发，但有时也会用到前端，做新项目时就可以直接拷过来用了。
					网上看了很多开源的项目，最好的应该是若依吧，但并不适合我，可能比较菜，本页面借鉴于若依。
					<br/>
					清枫取自我游戏id,懒得想直接就搬过来了
				</div>
				<div class="text-three">当前版本: v1.0.1</div>
			</div>
		</div>
		<div class="foot">
			<el-card class="foot-one">
				<div slot="header" class="clearfix">
					<span>技术</span>
				</div>
				<div class="foot_card">
					<div>
						<div>前端</div>
						<br />
						<div>vue</div>
						<div>element-ui</div>
						<div>axios</div>
						<div>sass</div>
						<div>js</div>
						<div>...</div>
					</div>
					<div>
						<div>后端</div>
						<br />
						<div>springboot</div>
						<div>maven</div>
						<div>shiro</div>
						<div>redis</div>
						<div>minio</div>
						<div>mysql</div>
						<div>...</div>
					</div>
				</div>

			</el-card>
			<el-card class="foot-two">
				<div slot="header" class="clearfix">
					<span>更新日志</span>
				</div>
				<div>
					<updateLog></updateLog>
				</div>
			</el-card>
			<el-card class="foot-three">
				<div slot="header" class="clearfix">
					<span>捐赠支持</span>
				</div>
				<div class="imgPay">
					<img src="../assets/aliPay.jpg" />
					<img src="../assets/wechatPay.jpg" />
				</div>
			</el-card>
		</div>
	</div>
</template>
<script>
	var that = null;

	import updateLog from "./component/UpdateLog.vue"
	export default {

		components: {
			updateLog
		},

		data() {
			return {
				todayFk: 0,
				yesterdayFk: 0,
				sumFk: 0,
				weekFk: 0
			};
		},
		mounted() {
			that = this;

			that.getData();
		},
		methods: {
			echarts(data) {

				let num1 = new Array();
				let num2 = new Array();
				for (let i = 0; i < data.length; i++) {
					num1.push(data[i].num)
					num2.push(data[i].click_date)
				}
				var chartDom = document.getElementById('main');
				var myChart = that.$echarts.init(chartDom);

				let option = {
					xAxis: {
						type: 'category',
						data: num2
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						data: num1,
						type: 'line'
					}]
				};
				myChart.setOption(option);
			},
			getData() {
				that.$postRequest("log/traffic", {}, function(res) {
					that.echarts(res.data.weekList);
					that.todayFk = res.data.traffic[0]
					that.yesterdayFk = res.data.traffic[1]
					that.weekFk = res.data.traffic[2]
					that.sumFk = res.data.traffic[3]
				}, 0)
			}
		}
	}
</script>
<style>
	.head {
		height: 150px;
		width: 100%;
		display: flex;
	}

	.head>div {
		width: 20%;
		color: #FFFFFF;
		background-color: #008787;
	}

	.f30 {
		font-size: 50px;
	}

	.cont {
		height: 300px;
		width: 100%;
		margin-top: 10px;
		display: flex;
		color: #909090;
	}

	.cont>div:first-child {
		width: 65%;
	}

	.cont>div {
		height: 300px;
		margin: 10px;
	}

	.text-one {
		font-size: 30px;
	}

	.text-two {
		margin-top: 30px;
	}

	.text-three {
		margin-top: 50px;
	}




	.foot {
		display: flex;
	}

	.foot .el-card {
		margin: 10px;
	}

	.foot-one {
		height: 300px;
		width: 30%;
	}

	.foot-two {
		width: 30%;
	}

	.foot-three {
		height: 300px;
		width: 40%;
	}

	.foot_card {
		display: flex;
	}

	.foot_card>div {
		width: 50%;
	}

	.imgPay {
		display: flex;
	}

	.imgPay img {
		border-radius: 5px;
		width: 45%;
		margin: 10px;
	}
</style>
