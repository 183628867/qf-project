<template>
	<div id="app">
		<router-view ></router-view>
	</div>
</template>

<script>
	export default {
		methods: {

		}
	}
</script>

<style>
	#app {
		font-family: Helvetica, sans-serif;
		height: 100%;
	}

	* {
		padding: 0;
		margin: 0;
	}

	html,
	body {
		width: 100%;
		height: 100%;
	}
	.add{
		margin: 0 0 20px 20px;
	}
	/* 面包屑 */
	.breadcrumb{
		margin: 10px;
	}
	
</style>
