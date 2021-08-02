import axios from 'axios'
import ElementUI from 'element-ui';
import {
	Loading
} from 'element-ui';

import doCookie from './cookieUtil.js'
let loading;

axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('sessionId')


// import qs from 'qs'
axios.defaults.withCredentials = true; //	允许跨域
axios.interceptors.request.use(config => {
	let append = document.getElementsByName('body')
	append.innerHTML = '<img style="position:fixed;\n' +
		' left:47%;\n' +
		' top:40%;\n' +
		' transform: translateY(-50%),translateX(-50%);"' +
		' src="../../static/img/a.jpg"/>'
	return config
}, err => {
	return Promise.resolve(err)
})

let base = "http://localhost:8088/api/" // 接口域名

export const request = (url, params, method, Func, isJson) => {
	// console.log("请求的接口--->" + base + url);
	// console.log("请求的方法--->" + method);
	// console.log("请求格式--->" + isJson);
	// console.log("请求的参数--->" + JSON.stringify(params));
	startLoding()
	var _this = this;
	axios({
		method: method,
		url: `${base}${url}`,
		data: method === 'post' ? params : '',
		transformRequest: [function(data) {
			if (isJson === 1) {
				// console.log("判断是否json格式或者是表单提交形式");
				// debugger       // 判断是否json格式或者是表单提交形式
				return JSON.stringify(data)
			}
			let ret = ''
			for (let it in data) {
				ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
			}
			return ret // 便于直接取到内部data
		}],
		headers: {
			// 认证和请求方式
			'Content-Type': isJson === 1 ? 'application/json' : 'application/x-www-form-urlencoded',
			'token': sessionStorage.getItem('token'),
			'Authorization': sessionStorage.getItem('sessionId'),
		},
		params: method === 'get' ? params : '',
	}).then(data => {
		endLoading()
		// console.log("返回结果：----》" + JSON.stringify(data.data));
		if (data.data.code == 0) {
			Func(data.data)
		} else {
			//	错误提示
			ElementUI.Message({
				message: data.data.msg,
				type: 'error'
			});
		}
	})
}



// uploadFileRequest  图片上传
export const uploadFileRequest = (url, params) => {
	return axios({
		method: 'post',
		url: `${base}${url}`,
		data: params,
		headers: {
			'Content-Type': 'multipart/form-data',
			'token': localStorage.getItem('token')
			// 'authorization':'admin',
			// 'token':'740a1d6be9c14292a13811cabb99950b'
		}
	})
}

// get 

export const getRequest = (url, params, Func, isJson) => {
	request(url, params, 'get', Func, isJson)
}

// post
export const postRequest = (url, params, Func, isJson) => {
	request(url, params, 'post', Func, isJson)
}


function startLoding() {
	loading = Loading.service({
		lock: true,
		text: '加载中...',
		spinner: 'el-icon-loading',
	});
}

function endLoading(){
	loading.close()
}
