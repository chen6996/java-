<template>
	<div>
		<figure class="hover-menu">
			<img src=".\img\file.jpeg" />
			<div>
				<a href="#" @click="dialogFormVisible = true">登录</a>
				<a href="#">关于</a>
			</div>
		</figure>

		<!-- Form -->

		<el-dialog title="登录" :visible.sync="dialogFormVisible" width="30%">
			<el-form :model="form" ref="form">
				<el-form-item label="用户名" :label-width="formLabelWidth">
					<el-input v-model="form.username" autocomplete="off" placeholder="请输入用户名"></el-input>
				</el-form-item>
				<el-form-item label="密码" :label-width="formLabelWidth"><el-input v-model="form.password" autocomplete="off" placeholder="请输入密码"></el-input></el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancel">取 消</el-button>
				<el-button type="primary" @click="confirm" :loading="addLoading">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
export default {
	inject: ['reload'],
	data() {
		return {
			dialogFormVisible: false,
			form: {
				username: '',
				password: ''
			},
			formLabelWidth: '120px',
			addLoading: false //确定按钮加载效果
		};
	},
	mounted() {
		// this.loadUserlistByPage();
	},
	methods: {
		cancel() {
			this.dialogFormVisible = false;
			(this.form.name = ''), (this.form.password = ''), this.reload();
			// this.$nextTick(() => {  this.form.name="",
			// this.form.password = ""})
		},
		confirm() {
			//加载框
			this.addLoading = true;
			//获取form表单中的数据 ,将form中的数据拷贝到param对象中
			let param = Object.assign({}, this.form);
			console.log('这里应该开始新增的操作了 .  ', param);
			this.$http.post('/selectUsers', param).then(res => {
				//提交成功
				if (res.data.success) {
					//停止添加按钮的加载框
					this.addLoading = false;
					this.$message({
						message: '欢迎登录,您好:' + res.data.msg + '!',
						type: 'success'
					});
					//弹出框关闭
					this.cancel();
					//让守卫通过
					var storage = window.sessionStorage;
					storage.setItem('token', true);

					this.$router.push({
						name: 'Home'
					});
					//重置form表单
					if (this.$refs['form'] !== undefined) {
						this.$refs['form'].resetFields();
					}
				} else {
					this.$notify.error({
						//侧边栏停留的时间更长
						title: '错误',
						message: res.data.msg,
						duration: 0 // 这个属性表示,该框不会自动关闭,必须点击关闭按钮才能关闭
					});
				}
			});
			this.dialogFormVisible = false;
			this.cancel();
		}
	},
	components: {
		//fujian1: fujian1Vue, //将别名aa 变成 组件 fujian1Vue
	}
};
</script>

<style>
.el-dialog__title {
	text-align: left;
	display: block;
}
.hover-menu {
	position: relative;
	overflow: hidden;
	margin: 8px;
	min-width: 340px;
	max-width: 1500px;
	max-height: 700px;
	width: 100%;
	background: #000;
	text-align: center;
	box-sizing: border-box;
}

.hover-menu * {
	box-sizing: border-box;
}

.hover-menu img {
	text-align: center;
	position: relative;
	max-width: 100%;
	max-height: 100%;
	top: 0;
	right: 0;
	opacity: 1;
	transition: 0.3s ease-in-out;
}

.hover-menu div {
	position: absolute;
	top: 0;
	left: -120px;
	width: 120px;
	height: 100%;
	padding: 8px 4px;
	background: #000;
	transition: 0.3s ease-in-out;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.hover-menu div a {
	display: block;
	line-height: 2;
	color: #fff;
	text-decoration: none;
	opacity: 0.8;
	padding: 5px 15px;
	position: relative;
	transition: 0.3s ease-in-out;
}

.hover-menu div a:hover {
	text-decoration: underline;
}

.hover-menu:hover img {
	opacity: 0.5;
	right: -120px;
}

.hover-menu:hover div {
	left: 0;
	opacity: 1;
}
</style>
