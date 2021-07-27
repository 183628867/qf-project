import { MessageBox } from 'element-ui';
export default {

	deleteConfirm(text, func) {
		MessageBox.confirm(text, '提示', {
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			type: 'warning'
		}).then(() => {
			func()
		}).catch(() => {
			
		});
	}
}
