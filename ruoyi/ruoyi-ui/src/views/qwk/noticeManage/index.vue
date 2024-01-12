<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="点击数" prop="clickNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.clickNumber"-->
<!--          placeholder="请输入点击数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="发布时间">-->
<!--        <el-date-picker-->
<!--          v-model="daterangeReleaseTime"-->
<!--          style="width: 240px"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          type="daterange"-->
<!--          range-separator="-"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--        ></el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.wtb_on_off"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="消息类型" prop="messageType">
        <el-select v-model="queryParams.messageType" placeholder="请选择消息类型" clearable>
          <el-option
            v-for="dict in dict.type.qwk_message_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['qwk:notice:add']"
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
          v-hasPermi="['qwk:notice:edit']"
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
          v-hasPermi="['qwk:notice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:notice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
<!--        <template slot-scope="scope">-->
          <el-table-column type="selection" width="55" align="center" :selectable='checkboxSelect'>
          </el-table-column>
<!--        </template>-->

      <el-table-column label="标题" align="center" prop="title" />
<!--      <el-table-column label="推送图片" align="center" prop="img" width="100">-->
<!--        <template slot-scope="scope">-->
<!--          <image-preview :src="scope.row.img" :width="50" :height="50"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="详情" align="center" prop="details" />-->
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column label="点击数" align="center" prop="clickNumber" />
      <el-table-column label="发布时间" align="center" prop="releaseTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.releaseTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="releaseTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_on_off" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="消息类型" align="center" prop="messageType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.qwk_message_type" :value="scope.row.messageType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleDetails(scope.row)"
            v-hasPermi="['qwk:notice:edit']"
          >查看详情</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleDisable(scope.row)"
            v-hasPermi="['qwk:config:edit']">
            <span v-if="scope.row.state == 0 || scope.row.state == 3">上架</span>
            <span v-else >下架</span>
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:notice:edit']"
            v-if="scope.row.state != 1"
          >编辑</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qwk:notice:remove']"
            v-if="scope.row.state != 1"
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

    <!-- 添加或修改公告管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="!isDetails">
        <el-form-item label="消息类型" prop="messageType" v-if="isDetails">
          <el-select v-model="form.messageType" placeholder="请选择消息类型">
            <el-option
              v-for="dict in dict.type.qwk_message_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布时间" prop="releaseTime" v-if="isDetails">
          <el-date-picker clearable
                          v-model="form.releaseTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="标题" prop="title" v-if="isDetails">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="推送文案" prop="copywriting" v-if="isDetails">
          <el-input v-model="form.copywriting" type="textarea" placeholder="请输入内容" />
        </el-form-item>
<!--        <el-form-item label="点击数" prop="clickNumber">-->
<!--          <el-input v-model="form.clickNumber" placeholder="请输入点击数" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="状态" prop="state">-->
<!--          <el-select v-model="form.state" placeholder="请选择状态">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.wtb_on_off"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="parseInt(dict.value)"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->

        <el-form-item label="推送图片" prop="img" v-if="isDetails">
          <image-upload v-model="form.img" />
        </el-form-item>
        <el-form-item label="详情" v-if="isDetails">
          <editor v-model="form.details" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div class="ql-container ql-snow" v-if="!isDetails">
        <div class="ql-editor" v-html="form.details"/>
      </div>
      <image-preview :src="form.img" :width="960" :height="400" v-if="!isDetails"/>
      <div slot="footer" class="dialog-footer" v-if="isDetails">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listNotice, getNotice, delNotice, addNotice, updateNotice } from "@/api/qwk/notice";

export default {
  name: "Notice",
  dicts: ['qwk_message_type', 'wtb_on_off'],
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
      // 公告管理表格数据
      noticeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 消息类型时间范围
      daterangeReleaseTime: [],
      // 消息类型时间范围
      daterangeCreateTime: [],
      // 消息类型时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        clickNumber: null,
        releaseTime: null,
        state: null,
        messageType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        copywriting: [
          { required: true, message: "推送文案不能为空", trigger: "blur" }
        ],
        releaseTime: [
          { required: true, message: "发布时间不能为空", trigger: "blur" }
        ],
        messageType: [
          { required: true, message: "消息类型不能为空", trigger: "change" }
        ],
      },
      isDetails:true,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公告管理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeReleaseTime && '' != this.daterangeReleaseTime) {
        this.queryParams.params["beginReleaseTime"] = this.daterangeReleaseTime[0];
        this.queryParams.params["endReleaseTime"] = this.daterangeReleaseTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listNotice(this.queryParams).then(response => {
        this.noticeList = response.rows;
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
        title: null,
        copywriting: null,
        img: null,
        details: null,
        clickNumber: null,
        releaseTime: null,
        state: null,
        messageType: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.daterangeReleaseTime = [];
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
      this.open = true;
      this.title = "添加公告管理";
      this.form.state = 3;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.isDetails = true;
      this.reset();
      const id = row.id || this.ids
      getNotice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公告管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateNotice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNotice(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除公告管理编号为"' + ids + '"的数据项？').then(function() {
        return delNotice(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/notice/export', {
        ...this.queryParams
      }, `notice_${new Date().getTime()}.xlsx`)
    },
    /** 判断能否选中*/
    checkboxSelect(row,rowIndex){
      if (row.state == 1) {
        return false // 禁用
      }else{
        return true // 不禁用
      }
    },
    /** 上架或者下架操作 */
    handleDisable(row) {
      this.reset();
      this.form.id = row.id ;
      if (row.state == 0 || row.state == 3){
        this.form.state = 1;
      }else {
        this.form.state = 0;
      }
      updateNotice(this.form).then(response => {
        this.$modal.msgSuccess("操作成功");
        this.open = false;
        this.getList();
      });
    },
    /** 查看详情 */
    handleDetails(row){
      this.isDetails = false;
      this.reset();
      const id = row.id || this.ids
      getNotice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "";
      });
    },
  }
};
</script>
