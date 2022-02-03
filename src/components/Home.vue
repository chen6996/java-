<template>
	<div id="home">
		<el-container>
			<el-header height="68px">
				<img src="../views/img/dong.gif" align="left" alt="" srcset="" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="hcqFont hcqStyle2">新闻资讯后台管理系统</span>
			</el-header>
			<el-container>
				<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
					<el-menu :default-openeds="['1', '3']">
						<el-submenu index="1">
							<template slot="title">
								<i class="el-icon-s-unfold"></i>
								新闻
							</template>
							<el-menu-item-group>
								<el-menu-item index="1-1" @click="guanli">
									<i class="el-icon-menu"></i>
									新闻类别管理
								</el-menu-item>
								<el-menu-item index="1-2" @click="guanli2">
									<i class="el-icon-menu"></i>
									新闻管理
								</el-menu-item>
							</el-menu-item-group>
						</el-submenu>
					</el-menu>
				</el-aside>
				<el-container>
					<el-main v-if="main_if1" :class="classmain ? 'el-main-1' : 'el-main-2'">
						<span v-if="biaobiao" class="ziti">欢迎使用新闻资讯后台管理系统</span>
						<!-- 表导航栏 -->
						<div v-if="navigationSum" style="margin-bottom: 15px;color: #6a6a6a;font-size: 14px;">
							<span>新闻类别管理 /</span>
							<span v-if="navigation === 1">新闻类别列表</span>
							<span v-if="navigation === 2">添加新闻类别</span>
							<span v-if="navigation === 3">更新新闻类别</span>
						</div>
						<div v-if="sheet">
							<el-form :inline="true" :model="formInline" class="demo-form-inline">
								<el-form-item label="新闻类别名称"><el-input v-model="formInline.name" placeholder="新闻类别名称"></el-input></el-form-item>
								<el-form-item><el-button size="small" type="warning" @click="onempty">清空</el-button></el-form-item>
								<el-form-item><el-button size="small" type="primary" @click="onSubmit">查询</el-button></el-form-item>
								<el-form-item><el-button size="small" type="success" @click="add">新增</el-button></el-form-item>
							</el-form>
							<el-table class="inner" v-loading="loading" :data="tableData" style="width: 80%" @selection-change="handleSelectionChange">
								<el-table-column type="selection" width="50%"></el-table-column>
								<el-table-column prop="newsTypeId" label="编号" width="60%"></el-table-column>
								<el-table-column prop="newsTypeName" label="标题" width="140%"></el-table-column>
								<el-table-column prop="newsTypeExplain" label="内容"></el-table-column>
								<el-table-column prop="createTime" label="时间" width="140%"></el-table-column>
								<el-table-column label="操作">
									<template slot-scope="scope">
										<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
										<el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
									</template>
								</el-table-column>
							</el-table>
							<div class="block">
								<el-row style="text-align: left;margin-top: 10px;">
									<el-button type="danger" @click="batchRemove" :dithis="this.sels.length == 0">批量删除</el-button>
								</el-row>
								<el-pagination
									@size-change="handleSizeChange"
									@current-change="handleCurrentChange"
									:current-page="currentPage"
									:page-sizes="pageSizes"
									:page-size="pageSize"
									layout="total, sizes, prev, pager, next, jumper"
									:total="total"
									style="text-align: center;"
								></el-pagination>
							</div>
						</div>
						<!-- 新增和修改按钮 -->
						<div v-if="dialogVisible">
							<el-form :model="form" :rules="addFormRules" ref="addForm" :visible.sync="dialogFormVisible">
								<el-form-item label="新闻类别编号" :label-width="formLabelWidth" prop="newsTypeId" v-if="navigation === 3">
									<el-input style="width: 40%" v-model="form.newsTypeId" autocomplete="off"></el-input>
								</el-form-item>
								<el-form-item label="新闻类别名称" :label-width="formLabelWidth" prop="newsTypeName">
									<el-input style="width: 40%" v-model="form.newsTypeName" autocomplete="off"></el-input>
								</el-form-item>

								<el-form-item label="新闻类别描述" :label-width="formLabelWidth" prop="newsTypeExplain">
									<el-input style="width: 40%" v-model="form.newsTypeExplain" autocomplete="off"></el-input>
								</el-form-item>
							</el-form>
							<div slot="footer" class="dialog-footer">
								<el-button @click="toFather">取 消</el-button>
								<el-button type="primary" @click="queding" :loading="addLoading">确 定</el-button>
							</div>
						</div>
					</el-main>
					<el-main v-if="main_if2" class="el-main-2">
						<!-- 表导航栏 -->
						<div v-if="navigationSum" style="margin-bottom: 15px;color: #6a6a6a;font-size: 14px;">
							<span>新闻管理 /</span>
							<span v-if="navigation === 1">新闻列表</span>
							<span v-if="navigation === 2">添加新闻</span>
							<span v-if="navigation === 3">更新新闻</span>
						</div>
						<div v-if="sheet">
							<el-form :inline="true" :model="formInline" class="demo-form-inline">
								<el-form-item label="新闻标题"><el-input v-model="formInline.name" placeholder="新闻标题"></el-input></el-form-item>
								<el-form-item label="新闻内容"><el-input v-model="formInline.be" placeholder="新闻内容"></el-input></el-form-item>
								<el-form-item><el-button size="small" type="warning" @click="onempty">清空</el-button></el-form-item>
								<el-form-item><el-button size="small" type="primary" @click="onSubmit">查询</el-button></el-form-item>
								<el-form-item><el-button size="small" type="success" @click="add">新增</el-button></el-form-item>
							</el-form>
							<el-table class="inner" v-loading="loading" :data="tableData2" style="width: 80%" @selection-change="handleSelectionChange2">
								<el-table-column type="selection" width="50%"></el-table-column>
								<el-table-column prop="newsId" label="新闻编号" width="140%"></el-table-column>
								<el-table-column prop="newsTitle" label="新闻标题" width="240%"></el-table-column>
								<el-table-column prop="createTime" label="创建时间" width="180%"></el-table-column>
								<el-table-column prop="newsType.newsTypeName" label="所属新闻类别" width="220%"></el-table-column>
								<el-table-column label="操作">
									<template slot-scope="scope">
										<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
										<el-button size="mini" type="danger" @click="handleDelete2(scope.$index, scope.row)">删除</el-button>
									</template>
								</el-table-column>
							</el-table>
							<div class="block">
								<el-row style="text-align: left;margin-top: 10px;">
									<el-button type="danger" @click="batchRemove2" :dithis="this.sels.length == 0">批量删除</el-button>
								</el-row>
								<el-pagination
									@size-change="handleSizeChange"
									@current-change="handleCurrentChange"
									:current-page="currentPage"
									:page-sizes="pageSizes"
									:page-size="pageSize"
									layout="total, sizes, prev, pager, next, jumper"
									:total="total2"
									style="text-align: center;"
								></el-pagination>
							</div>
						</div>
						<!-- 新增和修改按钮 -->
						<div v-if="dialogVisible">
							<el-form :model="form" :rules="addFormRules" ref="addForm" :visible.sync="dialogFormVisible">
								<el-form-item label="新闻编号" :label-width="formLabelWidth" prop="newsId" v-if="navigation === 3">
									<el-input style="width: 40%" v-model="form.newsId" autocomplete="off"></el-input>
								</el-form-item>
								<el-form-item label="新闻标题" :label-width="formLabelWidth" prop="newsTitle">
									<el-input style="width: 40%" v-model="form.newsTitle" autocomplete="off"></el-input>
								</el-form-item>
								<el-form-item label="新闻内容" :label-width="formLabelWidth" prop="newsContent">
									<el-input style="width: 40%" v-model="form.newsContent" autocomplete="off"></el-input>
								</el-form-item>
								<el-form-item label="所属新闻类别" :label-width="formLabelWidth" prop="newsType.newsTypeName">
									<el-select
									  v-model="form.newsTypeId"
									  placeholder="请选择"
									  auto-complete="off"
									  style="width: 30%"
									>
									  <!-- v-model的值为当前被选中的el-option的 value 属性值,即数据库会保存options中的value值而不保存label值 -->
									  <el-option
									     v-for="item in options"
									          :key="item.value"
									    :label="item.label"
									    :value="item.value"
									  >
									  </el-option>
									</el-select>
								</el-form-item>
							</el-form>
							<div slot="footer" class="dialog-footer">
								<el-button @click="toFather">取 消</el-button>
								<el-button type="primary" @click="queding2" :loading="addLoading">确 定</el-button>
							</div>
						</div>
					</el-main>
				</el-container>
			</el-container>
			<el-footer>
				<br />
				<span style="font-size: 6px;">版权所有:陈小雨</span>
			</el-footer>
		</el-container>
	</div>
</template>

<script>
export default {
	name: 'HelloWorld',
	props: {
		msg: String
	},
	data() {
		return {
			main_if1: true, //主页面第一个
			main_if2: false, //主页面第二个
			//导航表头
			navigationSum: false,
			navigation: 2,
			//dialog弹窗的初始化显示和隐藏 新增和修改共用
			dialogVisible: false,
			title: '',
			dialogFormVisible: true,
			form: {
				newsTypeId: '',
				newsTypeName: '',
				newsTypeExplain: '',
				createTime: '',
				newsId:'',
				newsTitle: '',
				newsContent: '',
				
			},
			options: [
			  {
			    value: '20',
			    label: "士大夫但是",
			  },
			  
			],
			formLabelWidth: '100px',
			addLoading: false, //确定按钮加载效果
			addFormRules: {
				ename: [
					{
						required: true,
						message: '请输入姓名',
						trigger: 'blur'
					}
				],
				salary: [
					{
						required: true,
						message: '请输入工资',
						trigger: 'blur'
					}
				]
			},
			sheet: false, //新闻表单
			biaobiao: true, //main下的标题
			classmain: true, //main得css样式
			loading: true,
			// tableData: Array(20).fill(item)
			tableData: [],
			tableData2: [], //页面2的所有内容表单
			sels: [],
			sels2: [],
			//表单查询
			formInline: {
				name: '',
				be: ''
			},
			//分页相关
			currentPage: 1, //当前页默认第一页
			pageSize: 5, //每页默认显示10条
			pageSizes: [5, 10, 20, 30], // 默认数组
			total: 0, //总条数
			total2: 0, //页面2的总条数
			dept: '' //部门变量
		};
	},
	mounted() {
		this.loadUserlistByPage();
	},
	methods: {
		loadUserlistByPage() {
			let params = {
				currentPage: this.currentPage,
				pageSize: this.pageSize,
				name: this.formInline.name //姓名的变量
			};
			this.$http
				.patch('/selectall', params)
				.then(response => {
					console.log(response.data); //res.data就是后台返回回来的分页对象PageResult,它里面包含了total和result两个属性
					this.total = response.data.total; //分别给到总条数
					this.tableData = response.data.result; //给到当前页
					this.options=[];
					for (let var1 of response.data.result) {
						console.log(var1)
						this.options.push({
						value : var1.newsTypeId,
						label : var1.newsTypeName
					})
					}
					this.loading = false;		//数据回传完毕后,别忘了把加载按钮给关了
				})
				.catch(error => {
					console.log(error);
				});
		},
		loadUserlistByPage2() {
			let params = {
				currentPage: this.currentPage,
				pageSize: this.pageSize,
				name: this.formInline.name, //姓名的变量
				be: this.formInline.be
			};
			this.$http
				.patch('/selectall2', params)
				.then(response => {
					console.log(response.data); //res.data就是后台返回回来的分页对象PageResult,它里面包含了total和result两个属性
					this.total2 = response.data.total; //分别给到总条数
					this.tableData2 = response.data.result; //给到当前页
					//数据回传完毕后,别忘了把加载按钮给关了
					this.loading = false;
				})
				.catch(error => {
					console.log(error);
				});
		},
		handleSelectionChange(val) {
			//查看选中元素
			console.log(val);
			this.sels = val;
		},
		handleSelectionChange2(val) {
			//查看选中元素
			console.log(val);
			this.sels2 = val;
		},
		handleSizeChange(val) {
			//每页条数改变时的触发事件
			console.log(`每页 ${val} 条`);
			this.currentPage = 1; //这里用于当重置每页几条时,重新回到第1页去
			this.pageSize = val;
			this.loadUserlistByPage2();
			this.loadUserlistByPage();
			this.loading = true;
		},
		handleCurrentChange(val) {
			//当前页改变时的触发事件
			console.log(`当前页: ${val}`);
			this.currentPage = val;
			this.loadUserlistByPage2();
			this.loadUserlistByPage();
			this.loading = true;
		},
		handleDelete(index, row) {		//永久删除该记录
			console.log(index, row);
			//elementui 中的 messageBox
			this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
				.then(() => {
					//get  delete 传参传的就是一个或者多个参数 ;    put /post/ patch; 传参数是对象
					//这里需要注意.delete方式和get方式提交表单都是值传值,不能传对象 .只能参数一个一个传过去
					this.$http.delete('/delete/' + row.newsTypeId).then(res => {
						if (res.data.success) {
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.loadUserlistByPage2();
							this.loadUserlistByPage();
						} else {
							this.$message.error(res.data.msg);
						}
					});
				})
				.catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
		},
		handleDelete2(index, row) {	//永久删除该记录
			console.log(index, row);
			//elementui 中的 messageBox
			this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
				.then(() => {
					//get  delete 传参传的就是一个或者多个参数 ;    put /post/ patch; 传参数是对象
					//这里需要注意.delete方式和get方式提交表单都是值传值,不能传对象 .只能参数一个一个传过去
					this.$http.delete('/delete2/' + row.newsId).then(res => {
						if (res.data.success) {
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.loadUserlistByPage2();
							this.loadUserlistByPage();
						} else {
							this.$message.error(res.data.msg);
						}
					});
				})
				.catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
		},
		batchRemove() {//批量删除该记录
			this.$confirm('确认批量删除该记录吗?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
				.then(() => {
					this.$http.patch('/batchDelete', this.sels).then(res => {
						if (res.data.success) {
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.loadUserlistByPage2();
							this.loadUserlistByPage();
						} else {
							this.$message.error(res.data.msg);
						}
					});
				})
				.catch(() => {});
		},
		batchRemove2() {//批量删除该记录
			this.$confirm('确认批量删除该记录吗?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			})
				.then(() => {
					this.$http.patch('/batchDelete2', this.sels2).then(res => {
						if (res.data.success) {
							this.$message({
								message: '删除成功',
								type: 'success'
							});
							this.loadUserlistByPage2();
							this.loadUserlistByPage();
						} else {
							this.$message.error(res.data.msg);
						}
					});
				})
				.catch(() => {});
		},
		onSubmit() {
			// 高级查询,模糊搜索姓名
			this.currentPage = 1; //先把当前页初始化成1
			this.loadUserlistByPage2();
			this.loadUserlistByPage();
		},
		guanli() {
			//新闻类别管理
			this.dialogVisible=false;
			this.main_if2 = false;
			this.main_if1 = true;
			this.classmain = false;
			this.biaobiao = false;
			this.navigationSum = true;
			this.navigation = 1;
			this.dialogVisible = false;
			this.sheet = true;
			this.loadUserlistByPage();
		},
		guanli2() {
			this.dialogVisible=false;
			this.loadUserlistByPage2();
			this.main_if2 = true;
			this.main_if1 = false;
			this.navigationSum = true;
			this.navigation = 1;
			this.sheet = true;
		},
		onempty() {
			// 清空按钮
			this.formInline.name = null;
			this.formInline.be = null;
			this.loadUserlistByPage();
			this.loadUserlistByPage2();
		},
		add() {
			// 新增按钮
			this.navigation = 2;
			this.dialogVisible = true;
			this.sheet = false;
			this.loadUserlistByPage();
			this.loadUserlistByPage2();
		},
		queding() {
			//验证form表单  $refs.addForm   就是在找 ref="addForm"的表单,判断他验证规则是否通过
			this.$refs.addForm.validate(valid => {
				if (valid) {
					//校验通过之后就进入该代码块
					//加载框
					this.addLoading = true;
					//获取form表单中的数据 ,将form中的数据拷贝到param对象中
					let param = Object.assign({}, this.form);

					console.log('这里应该开始新增的操作了 .  ', param);

					if (param.newsTypeId) {
						//如果有id,进入的是修改操作环节

						this.$http.post('/update', param).then(res => {
							//提交成功
							if (res.data.success) {
								//停止添加按钮的加载框
								this.addLoading = false;
								this.$message({
									message: '修改成功',
									type: 'success'
								});
								//关闭
								this.toFather();
								//重置form表单
								this.$refs['addForm'].resetFields();
								//加载列表
								this.loadUserlistByPage();
								this.loadUserlistByPage2();
							} else {
								this.$notify.error({
									//侧边栏停留的时间更长
									title: '错误',
									message: res.data.msg,
									duration: 0 // 这个属性表示,该框不会自动关闭,必须点击关闭按钮才能关闭
								});
							}
						});
					} else {
						//没有id,进入添加操作环节
						this.$http.put('/save', param).then(res => {
							//先判断回传回来的res.data.success变量是true还是false
							if (res.data.success) {
								//停止添加按钮的加载框
								this.addLoading = false;
								this.$message({
									//elementui框架自带的通知框,上部弹出框
									message: '添加成功',
									type: 'success'
								});
								//关闭界面
								this.toFather();
								//重置form表单
								this.$refs['addForm'].resetFields();
								//加载列表
								this.loadUserlistByPage();
								this.loadUserlistByPage2();
							} else {
								this.toFather();
								this.$notify.error({
									//elementui框架自带的通知框,侧边弹出框
									title: '错误',
									message: res.data.msg,
									duration: 0 // 这个属性表示,该框不会自动关闭,必须点击关闭按钮才能关闭
								});
							}
						});
					}
				}
			});
			//新增,修改结束后 , 重置this.form ,千万注意 ,如果有id属性 ,要删除掉id属性
			this.form = {
				newsTypeId: '',
				newsTypeName: '',
				newsTypeExplain: '',
				createTime: '',
				newsId:'',
				newsTitle: '',
				newsContent: '',
			};
		},
		queding2() {
			//验证form表单  $refs.addForm   就是在找 ref="addForm"的表单,判断他验证规则是否通过
			this.$refs.addForm.validate(valid => {
				if (valid) { 		//校验通过之后就进入该代码块
					this.addLoading = true;//加载框
					let param = Object.assign({}, this.form);		//获取form表单中的数据 ,将form中的数据拷贝到param对象中
					console.log('这里应该开始新增的操作了 .  ', param);
					if (param.newsId) {			//如果有id,进入的是修改操作环节
						this.$http.post('/update2', param).then(res => {   //提交成功
							if (res.data.success) {
								this.addLoading = false;		//停止添加按钮的加载框
								this.$message({
									message: '修改成功',
									type: 'success'
								});
								this.toFather();		//关闭
								this.$refs['addForm'].resetFields();		//重置form表单
								this.loadUserlistByPage();		//加载列表
								this.loadUserlistByPage2();
							} else {
								this.$notify.error({		//侧边栏停留的时间更长
									title: '错误',
									message: res.data.msg,
									duration: 0 		// 这个属性表示,该框不会自动关闭,必须点击关闭按钮才能关闭
								});
							}
						});
					} else {		//没有id,进入添加操作环节
						this.$http.put('/save2', param).then(res => {
							//先判断回传回来的res.data.success变量是true还是false
							if (res.data.success) {
								//停止添加按钮的加载框
								this.addLoading = false;
								this.$message({
									//elementui框架自带的通知框,上部弹出框
									message: '添加成功',
									type: 'success'
								});
								//关闭界面
								this.toFather();
								//重置form表单
								this.$refs['addForm'].resetFields();
								//加载列表
								this.loadUserlistByPage();
								this.loadUserlistByPage2();
							} else {
								this.toFather();
								this.$notify.error({
									//elementui框架自带的通知框,侧边弹出框
									title: '错误',
									message: res.data.msg,
									duration: 0 // 这个属性表示,该框不会自动关闭,必须点击关闭按钮才能关闭
								});
							}
						});
					}
				}
			});
			//新增,修改结束后 , 重置this.form ,千万注意 ,如果有id属性 ,要删除掉id属性
			this.form = {
				newsTypeId: '',
				newsTypeName: '',
				newsTypeExplain: '',
				createTime: '',
				newsId:'',
				newsTitle: '',
				newsContent: '',
			};
		},
		handleEdit(index, row) {
			//编辑按钮触发
			this.navigation = 3;
			this.sheet = false;
			console.log(index, row);
			this.form = {
				newsTypeId: row.newsTypeId, // 这块必须要加上,不然没有id就是新增了.
				newsTypeName: row.newsTypeName,
				newsTypeExplain: row.newsTypeExplain,
				newsId:row.newsId,
				newsTitle:row.newsTitle,
				newsContent:row.newsContent,
			};
			this.dialogVisible = true; //激活窗体
		},
		
		toFather() {
			this.sheet = true;
			this.navigation = 1;
			this.dialogVisible = false;
			this.loadUserlistByPage();
			this.loadUserlistByPage2();
			this.form = {
				newsTypeId: '',
				newsTypeName: '',
				newsTypeExplain: '',
				createTime: '',
				newsId:'',
				newsTitle: '',
				newsContent: '',
			};
		}
	}
};
</script>
<style>
/* 标题导航 */
.el-menu-demo {
	/* margin-bottom: 500px; */
	padding-bottom: 20px;
}
.inner {
	position: absolute;
	background-color: darkcyan;
	top: 5%;
	left: 10%;
}
/* main中标题字体 */
.ziti {
	margin-top: 30px;
	color: #ffffff;
	font-size: 38px;
	text-shadow: #d3dce6 0px 0px 8px;
	text-align: center;
}

/* 标题字体 */
.hcqFont {
	position: relative;
	letter-spacing: 0.07em;
	font-size: 26px;
	font-weight: normal;
	margin: 0 auto;
}
.hcqStyle2 {
	display: inline-block;
	font-weight: bold;
	color: #def;
	text-shadow: 0 0 1px currentColor, -1px -1px 1px #000, 0 -1px 1px #000, 1px -1px 1px #000, 1px 0 1px #000, 1px 1px 1px #000, 0 1px 1px #000, -1px 1px 1px #000, -1px 0 1px #000;
	-webkit-filter: url(#diff1);
	filter: url(#diff1);
}
/* 页眉 */
.el-header {
	background: linear-gradient(to right, #5d4157, #a8caba);
	background-color: #ffffff;
	color: #333;
	text-align: left;
	line-height: 60px;
}
/* 左上角图片 */
#home img:nth-child(1) {
	padding: 0;
	height: 68px;
	width: 91px;
}
/* 中间图片 */
/* #home img:nth-child(2) {
	margin-top: 100px;
	width: 25%;
	box-sizing: border-box;
	padding: 2px;
} */
/* 页脚 */
.el-footer {
	background-color: #ffffff;
	color: #333;
	text-align: center;
	line-height: 60px;
}
/* 左边侧栏 */
.el-aside {
	background-color: #d3dce6;
	color: #333;
	text-align: center;
	line-height: 200px;
}
/* 主页内容 */
.el-main-1 {
	background-image: url(../views/img/file1.jpeg);
	background-repeat: no-repeat; /* 不重复 */
	background-position: 50% 20%;
	color: #333;
	text-align: center;
	line-height: 160px;
	min-height: 500px;
}
.el-main-2 {
	background-color: #e9eef3;
	color: #333;
	text-align: left;
	line-height: 40px;
	min-height: 500px;
}

body > .el-container {
	margin-bottom: 40px;
}

.el-container:nth-child(1) .el-aside {
	line-height: 260px;
}
</style>
