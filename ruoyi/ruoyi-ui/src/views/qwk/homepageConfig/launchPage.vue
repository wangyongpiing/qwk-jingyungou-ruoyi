<template>
  <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

          <el-form-item label="图片" prop="imgUrl">
            <el-input
              v-model="queryParams.imgUrl"
              placeholder="请输入图片"
              clearable
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

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
              v-hasPermi="['qwk:config:add']"
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
              v-hasPermi="['qwk:config:edit']"
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
              v-hasPermi="['qwk:config:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['qwk:config:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="configList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="id " align="center" prop="id" />
          <el-table-column label="排序" align="center" prop="sort" />
          <el-table-column label="分类" align="center" prop="classify">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.wtb_home_sort" :value="scope.row.classify"/>
            </template>
          </el-table-column>
          <el-table-column label="图片" align="center" prop="imgUrl" >
            <template slot-scope="scope">
              <image-preview :src="scope.row.imgUrl" :width="150" :height="80"/>
            </template>
          </el-table-column>

          <el-table-column label="链接地址" align="center" prop="linkAddress" />

          <el-table-column label="有效时间" align="center" prop="startTime" width="180">
            <template slot-scope="scope">
              <el-row prop="startTime">
                <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </el-row>
              <el-row>
                -
              </el-row>
              <el-row prop="endTime">
                <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </el-row>
            </template>
          </el-table-column>

          <el-table-column label="状态" align="center" prop="state">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.wtb_on_off" :value="scope.row.state"/>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180">
            <template slot-scope="scope">
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
                v-hasPermi="['qwk:config:edit']"
                v-if="scope.row.state != 1"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['qwk:config:remove']"
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



    <!-- 添加或修改首页配置 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="显示排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="所属分类" prop="classify">
          <el-select v-model="form.classify" placeholder="请选择分类" disabled>
            <el-option
              v-for="dict in dict.type.wtb_home_sort"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="图片" prop="imgUrl">
          <image-upload v-model="form.imgUrl" :limit="1"/>
        </el-form-item>

        <el-form-item label="链接地址" prop="linkAddress">
          <el-input v-model="form.linkAddress" placeholder="请输入链接地址" />
        </el-form-item>

        <el-form-item label="显示状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.wtb_on_off"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="有效时间" prop="date">
          <el-date-picker
            v-model="form.date"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="yyyy-MM-dd HH:mm:ss"
            align="right">
          </el-date-picker>
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
import { listConfig, getConfig, delConfig, addConfig, updateConfig } from "@/api/qwk/config";

export default {
  name: "Config",
  dicts: ['wtb_on_off', 'wtb_home_sort','wtb_display_location'],
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
      // 首页配置 表格数据
      configList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sort: null,
        classify: 5,
        imgUrl: null,
        wide: null,
        high: null,
        linkAddress: null,
        creationTime: null,
        state: null,
        displayStatus: null,
        displayLocation: null,
        updatedBy: null,
        updatedTime: null
      },
      // 表单参数
      form: {
      },
      value2: [],
      // 表单校验
      rules: {
        sort: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
        classify: [
          { required: true, message: "分类不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        imgUrl: [
          { required: true, message: "图片不能为空", trigger: "blur" }
        ],
        date: [
          { required: true, message: "有效时间不能为空", trigger: "change" }
        ],
      },
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      classifyData : [],
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 分类选中事件 */
    handleNodeClick(data) {
      console.log(data);
    },
    /** 查询首页配置 列表 */
    getList() {
      this.loading = true;
      listConfig(this.queryParams).then(response => {
        // console.log(response.rows)
        this.configList = response.rows;
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
        sort: null,
        classify: null,
        title: null,
        imgUrl: null,
        wide: null,
        high: null,
        linkAddress: null,
        creationTime: null,
        state: null,
        displayStatus: null,
        displayLocation: null,
        startTime: null,
        endTime: null,
        updateBy: null,
        updateTime: null,
        createBy: null,
        createTime: null
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
      this.title = "添加首页配置 ";
      this.form.state = 3;
      this.form.classify = 5;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getConfig(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改首页配置 ";
      });
    },
    /** 禁用按钮操作 */
    handleDisable(row) {
      this.reset();
      this.form.id = row.id ;
      if (row.state == 0 || row.state == 3){
        this.form.state = 1;
      }else {
        this.form.state = 0;
      }
      updateConfig(this.form).then(response => {
        this.$modal.msgSuccess("操作成功");
        this.open = false;
        this.getList();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.startTime = this.form.date[0];
      this.form.endTime = this.form.date[1];
      console.log(this.form)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateConfig(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            // this.form.creationTime = new Date();
            addConfig(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除首页配置 编号为"' + ids + '"的数据项？').then(function() {
        return delConfig(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/config/export', {
        ...this.queryParams
      }, `config_${new Date().getTime()}.xlsx`)
    },
  }
};
</script>
