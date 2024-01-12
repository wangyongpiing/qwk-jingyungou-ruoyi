<template>
  <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="用户名称" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入用户名称"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="用户昵称" prop="nickName">
            <el-input
              v-model="queryParams.nickName"
              placeholder="请输入用户昵称"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="手机号码" prop="phonenumber">
            <el-input
              v-model="queryParams.phonenumber"
              placeholder="请输入手机号码"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="身份证号" prop="idCard">
            <el-input
              v-model="queryParams.idCard"
              placeholder="请输入身份证号"
              style="width: 240px"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="用户性别" prop="sex">
            <el-select v-model="queryParams.sex" placeholder="请选择用户性别" clearable style="width: 240px">
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="邀请人id" prop="inviterId" >
            <el-input
              v-model="queryParams.inviterId"
              placeholder="请输入邀请人id"
              clearable
              @keyup.enter.native="handleQuery"
              style="width: 240px"
            />
          </el-form-item>
          <el-form-item label="在线状态" prop="onLineStatus">
            <el-select v-model="queryParams.onLineStatus" placeholder="请选择在线状态" clearable>
              <el-option
                v-for="dict in dict.type.sys_on_line_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
                style="width: 240px"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="用户状态"
              clearable
              style="width: 240px"
            >
              <el-option
                v-for="dict in dict.type.sys_user_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="注册时间">
            <el-date-picker
              v-model="dateRange"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="20" class="mb8">
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="danger"-->
<!--              plain-->
<!--              icon="el-icon-delete"-->
<!--              size="mini"-->
<!--              :disabled="multiple"-->
<!--              @click="handleDelete"-->
<!--              v-hasPermi="['system:user:remove']"-->
<!--            >删除</el-button>-->
<!--          </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['system:user:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="用户编号" align="center" key="userId" prop="userId"  />
          <el-table-column label="用户头像" align="center" prop="imgUrl" >
            <template slot-scope="scope">
              <image-preview :src="scope.row.avatar" :width="80" :height="80"/>
            </template>
          </el-table-column>
          <el-table-column label="用户账号" align="center" key="userName" prop="userName"  :show-overflow-tooltip="true" />
          <el-table-column label="用户昵称" align="center" key="nickName" prop="nickName"  :show-overflow-tooltip="true" />
          <el-table-column label="姓名" align="center" key="realName" prop="realName"  :show-overflow-tooltip="true" />
          <el-table-column label="邀请人id" align="center" key="InviterId" prop="InviterId"  :show-overflow-tooltip="true" />
          <el-table-column label="邀请人数" align="center" key="InviterNumber" prop="InviterNumber"  :show-overflow-tooltip="true" />
          <el-table-column label="性别" align="center" key="sex" >
            <template slot-scope="scope">
              <span v-if="scope.row.isAuthentication == '1'">未实名</span>
              <dict-tag v-else :options="dict.type. sys_user_sex" :value="scope.row.sex"/>
            </template>
          </el-table-column>
<!--          <el-table-column label="关联微信" align="center" key="wechat" prop="wechat"  :show-overflow-tooltip="true" />-->
          <el-table-column label="积分余额" align="center" key="integral" prop="integral"  :show-overflow-tooltip="true" />
          <el-table-column label="钻石余额" align="center" key="diamond" prop="diamond"  :show-overflow-tooltip="true" >
            <template slot-scope="scope">
<!--              {{getAssets(scope.row.userId,1).then()}}-->
            </template>
          </el-table-column>
          <el-table-column label="金币余额" align="center" key="species" prop="species"  :show-overflow-tooltip="true" />
          <el-table-column label="身份证号" align="center" key="idCard" prop="idCard"  :show-overflow-tooltip="true" >
            <template slot-scope="scope">
              {{scope.row.isAuthentication == '1' ? "未实名":scope.row.idCard}}
            </template>
          </el-table-column>
          <el-table-column label="手机号码" align="center" key="phonenumber" prop="phonenumber"  width="120" />
          <el-table-column label="帐号状态" align="center" prop="status">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_user_status" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="在线状态" align="center" prop="onLineStatus">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_on_line_status" :value="scope.row.onLineStatus"/>
            </template>
          </el-table-column>
          <el-table-column label="注册时间" align="center" prop="createTime"  width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" >
              <el-button
                size="mini"
                type="text"
                icon="el-icon-view"
                @click="getUserFriends(scope.row.userId)"
              >查看好友</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-view"
                @click="getDetails(scope.row)"
              >查看资料</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '0'"
                @click="handleStatusChange(scope.row,2)"
              >冻结</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '0'"
                @click="handleStatusChange(scope.row,1)"
              >禁用</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '0'"
                @click="handleStatusChange(scope.row,3)"
              >限制</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '1'"
                @click="handleStatusChange(scope.row,0)"
              >解除禁用</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '2'"
                @click="handleStatusChange(scope.row,0)"
              >解除冻结</el-button>
              <el-button
                size="mini"
                type="text"
                v-if="scope.row.status == '3'"
                @click="handleStatusChange(scope.row,0)"
              >解除限制</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />

    <!-- 查看好友对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="daterangeCreateTime"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleFriendsQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetFriendsQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="friendsList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column
          type="index"
          label="序号"
          width="50">
        </el-table-column>
        <el-table-column label="用户头像" align="center" key="avatar" prop="user.avatar" >
          <template slot-scope="scope">
            <image-preview :src="scope.row.user.avatar" :width="80" :height="80"/>
          </template>
        </el-table-column>
        <el-table-column label="用户昵称" align="center" key="user.nickName" prop="user.nickName"  :show-overflow-tooltip="true" >
        </el-table-column>
        <el-table-column label="姓名" align="center" key="realName" prop="user.realName"  :show-overflow-tooltip="true" />
        <el-table-column label="性别" align="center" key="sex" prop="user.sex">
          <template slot-scope="scope">
            <dict-tag :options="dict.type. sys_user_sex" :value="scope.row.user.sex"/>
          </template>
        </el-table-column>
        <el-table-column label="手机号码" align="center" key="phonenumber" prop="user.phonenumber" width="120" />
        <el-table-column label="成为好友时间" align="center" prop="createTime" width="160">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="160"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope" >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="friendsParams.pageNum"
        :limit.sync="friendsParams.pageSize"
        @pagination="getUserFriends"
      />
    </el-dialog>

  </div>
</template>

<script>
import { listUser, getUser, delUser, changeUserStatus ,listFriends} from "@/api/system/user";
import { getAssetsByUserIdType } from "@/api/qwk/assets";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex','sys_yes_no','sys_on_line_status','sys_user_status'],
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 用户好友 表格数据
      friendsList: [],
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined,
        inviterId:undefined,
        onLineStatus:undefined,
        sex:undefined,
        nickName:undefined,
      },
      //好友查询参数
      friendsParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        friendsId: undefined,
        createTime: undefined,
        deleted: undefined
      },
      // 是否删除时间范围
      daterangeCreateTime: [],
      // 列信息
      columns: [
        { key: 0, label: `用户编号`, visible: true },
        { key: 1, label: `用户名称`, visible: true },
        { key: 2, label: `用户昵称`, visible: true },
        { key: 3, label: `部门`, visible: true },
        { key: 4, label: `手机号码`, visible: true },
        { key: 5, label: `状态`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ],
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
        ],
        email: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    // this.getUserFriends();
    // this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
  },
  methods: {
    /** 查看用户好友列表 */
    getUserFriends(id) {
      this.open = true;
      this.title = "好友列表";
      this.friendsParams.userId =id;
      this.getUserFriendsList();
    },
    /** 查看用户好友列表 */
    getUserFriendsList() {
      this.loading = true;
      this.friendsParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.friendsParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.friendsParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listFriends(this.friendsParams).then(response => {
        this.friendsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询用户列表 */
    async getList() {
      this.loading = true;
      this.queryParams.deptId = 200;
      let list = [];
      await listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.userList = response.rows;
          // list = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
      // let v = [];
      // list.forEach(item=>{
      //   let value = this.getAssets(item.userId,item);
      //   v.push(value);
      // });
      // console.log(v)
      // this.userList = v;
    },
    // test(item){
    //   item.then(result => {
    //     // 在Promise被解析时执行的回调函数
    //     console.log(result); // 输出Promise的值
    //     return result;
    //   }).catch(error => {
    //     // 在Promise被拒绝时执行的回调函数
    //     console.error(error); // 输出Promise被拒绝的原因
    //   });
    // },
    /** 查询余额*/
    async getAssets(id,item){
       let value = ''
      await getAssetsByUserIdType(id,item).then(res=>{
        return res.data.total;
        // console.log(res.data.total)
      });
       // console.log(value)
       // return value;
      // // let value = item;
      // getAssetsByUserId(id).then( res=>{
      //    res.data.forEach(ite=>{
      //       switch (ite.type) {
      //         case 1 :
      //           let integral = {'integral':ite.total}
      //           Object.assign(item,integral)
      //           // value.integral = ite.total;
      //           break;
      //         case 2 :
      //           // value.diamond = ite.total;
      //           break;
      //         case 3 :
      //           // value.species = ite.total;
      //           break;
      //       }
      //     })
      //   })
      // return item;
    },
    /** 查询部门下拉树结构 */
    // getDeptTree() {
    //   deptTreeSelect().then(response => {
    //     this.deptOptions = response.data;
    //   });
    // },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.handleQuery();
    },
    // 用户状态修改
    handleStatusChange(row,status) {
      let text = '';
      switch (status) {
        case 0:
          text = '恢复正常'
          break;
        case 1 :
          text = '禁用'
          break;
        case 2 :
          text = '冻结'
          break;
        case 3 :
          text = '限制'
          break;
      }
      this.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗？').then(function() {
        return changeUserStatus(row.userId,status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
        this.getList();
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        onLineStatus:undefined,
        remark: undefined,
        postIds: [],
        roleIds: []
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 好友列表搜索按钮操作 */
    handleFriendsQuery() {
      this.friendsParams.pageNum = 1;
      this.getUserFriendsList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.queryParams.deptId = undefined;
      this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
    },
    resetFriendsQuery() {
      this.daterangeCreateTime = [];
      this.handleFriendsQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleResetPwd":
          this.handleResetPwd(row);
          break;
        case "handleAuthRole":
          this.handleAuthRole(row);
          break;
        default:
          break;
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getUser().then(response => {
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.title = "添加用户";
        this.form.password = this.initPassword;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();
      // const userId = row.userId || this.ids;
      // getUser(userId).then(response => {
      //   this.form = response.data;
      //   this.postOptions = response.posts;
      //   this.roleOptions = response.roles;
      //   this.$set(this.form, "postIds", response.postIds);
      //   this.$set(this.form, "roleIds", response.roleIds);
      //   this.open = true;
      //   this.title = "修改用户";
      //   this.form.password = "";
      // });
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      // this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
      //   confirmButtonText: "确定",
      //   cancelButtonText: "取消",
      //   closeOnClickModal: false,
      //   inputPattern: /^.{5,20}$/,
      //   inputErrorMessage: "用户密码长度必须介于 5 和 20 之间"
      // }).then(({ value }) => {
      //     resetUserPwd(row.userId, value).then(response => {
      //       this.$modal.msgSuccess("修改成功，新密码是：" + value);
      //     });
      //   }).catch(() => {});
    },
    /** 分配角色操作 */
    handleAuthRole: function(row) {
      // const userId = row.userId;
      // this.$router.push("/system/user-auth/role/" + userId);
    },
    /** 提交按钮 */
    submitForm: function() {
      // this.$refs["form"].validate(valid => {
      //   if (valid) {
      //     if (this.form.userId != undefined) {
      //       updateUser(this.form).then(response => {
      //         this.$modal.msgSuccess("修改成功");
      //         this.open = false;
      //         this.getList();
      //       });
      //     } else {
      //       addUser(this.form).then(response => {
      //         this.$modal.msgSuccess("新增成功");
      //         this.open = false;
      //         this.getList();
      //       });
      //     }
      //   }
      // });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/user/importTemplate', {
      }, `user_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    getDetails(row){
       this.$router.push({
        path: '/gameUser/userBill',
        query: {
          id: row.userId
        }
      })
    },
  }
};
</script>
