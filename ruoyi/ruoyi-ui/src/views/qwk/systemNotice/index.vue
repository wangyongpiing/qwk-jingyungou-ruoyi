<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="系统通知标题" prop="notificationTitle">
        <el-input
          v-model="queryParams.notificationTitle"
          placeholder="请输入系统通知标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消息通知标题" prop="notifyTitle">
        <el-input
          v-model="queryParams.notifyTitle"
          placeholder="请输入消息通知标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通知用户编号" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入通知用户编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.qwk_notify_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否通知所有" prop="isAll">
        <el-select v-model="queryParams.isAll" placeholder="请选择是否通知所有人" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 215px"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['qwk:systemNotice:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['qwk:systemNotice:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['qwk:systemNotice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:systemNotice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="systemNoticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="系统通知标题" align="center" prop="notificationTitle" />
      <el-table-column label="系统通知内容" align="center" prop="notificationContent" />
      <el-table-column label="消息通知标题" align="center" prop="notifyTitle" />
      <el-table-column label="消息主体内容" align="center" prop="messageBody" />
      <el-table-column label="通知用户编号" align="center" prop="userId" />
      <el-table-column label="是否通知所有人" align="center" prop="isAll">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.isAll"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.qwk_notify_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.state == '0'"
            size="mini"
            type="text"
            @click="sendMsg(scope.row)"
          >发送</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:systemNotice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qwk:systemNotice:remove']"
          >删除</el-button>
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

    <!-- 添加或修改系统消息通知对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="系统通知标题" prop="notificationTitle">
          <el-input v-model="form.notificationTitle" placeholder="请输入系统通知标题" />
        </el-form-item>
        <el-form-item label="系统通知内容" prop="notificationContent">
          <el-input v-model="form.notificationContent" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="消息通知标题" prop="notifyTitle">
          <el-input v-model="form.notifyTitle" placeholder="请输入消息通知标题" />
        </el-form-item>
        <el-form-item label="消息主体内容">
          <editor v-model="form.messageBody" :min-height="192"/>
        </el-form-item>
        <el-form-item label="通知用户编号" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入通知用户编号" />
        </el-form-item>
        <el-form-item label="是否通知所有" prop="isAll">
          <el-radio-group v-model="form.isAll">
            <el-radio
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listSystemNotice, getSystemNotice, delSystemNotice, addSystemNotice, updateSystemNotice ,sendNotice} from "@/api/qwk/systemNotice";

  export default {
    name: "SystemNotice",
    dicts: ['sys_yes_no','qwk_notify_state'],
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
        // 系统消息通知表格数据
        systemNoticeList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 备注时间范围
        daterangeCreateTime: [],
        // 备注时间范围
        daterangeUpdateTime: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          notificationTitle: null,
          notifyTitle: null,
          messageBody: null,
          userId: null,
          isAll: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 发送消息*/
      sendMsg(data){
        this.$modal.confirm('是否确认发送该系统消息通知').then(function() {
          return sendNotice(data);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("操作成功");
        }).catch(() => {});
      },
      /** 查询系统消息通知列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
          this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
          this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
        }
        if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
          this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
          this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
        }
        listSystemNotice(this.queryParams).then(response => {
          this.systemNoticeList = response.rows;
          this.total = response.total;
          this.loading = false;
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
          id: null,
          notificationTitle: null,
          notificationContent:null,
          notifyTitle: null,
          messageBody: null,
          userId: null,
          isAll: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeCreateTime = [];
        this.daterangeUpdateTime = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.form.isAll = '0';
        this.open = true;
        this.title = "添加系统消息通知";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getSystemNotice(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改系统消息通知";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateSystemNotice(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addSystemNotice(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除系统消息通知编号为"' + ids + '"的数据项？').then(function() {
          return delSystemNotice(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('qwk/systemNotice/export', {
          ...this.queryParams
        }, `systemNotice_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
