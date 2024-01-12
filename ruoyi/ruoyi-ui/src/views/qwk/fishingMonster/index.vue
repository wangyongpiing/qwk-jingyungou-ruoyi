<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="编号" prop="id">-->
<!--        <el-input-->
<!--          v-model="queryParams.id"-->
<!--          placeholder="请输入编号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="怪物名称" prop="nanme">
        <el-input
          v-model="queryParams.nanme"
          placeholder="请输入怪物名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="怪物血量" prop="hp">
        <el-input
          v-model="queryParams.hp"
          placeholder="请输入怪物血量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="游行方式" prop="paradeMode">
        <el-input
          v-model="queryParams.paradeMode"
          placeholder="请输入游行方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="游行次数" prop="paradesNumber">
        <el-input
          v-model="queryParams.paradesNumber"
          placeholder="请输入游行次数"
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
          v-hasPermi="['qwk:fishingMonster:add']"
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
          v-hasPermi="['qwk:fishingMonster:edit']"
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
          v-hasPermi="['qwk:fishingMonster:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:fishingMonster:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fishingMonsterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="怪物名称" align="center" prop="nanme" />
      <el-table-column label="怪物血量" align="center" prop="hp" />
      <el-table-column label="价格最低档" align="center" prop="low" />
      <el-table-column label="价格最高档" align="center" prop="tall" />
      <el-table-column label="低级场权重" align="center" prop="weightLow" />
      <el-table-column label="中级场权重" align="center" prop="weightCentre" />
      <el-table-column label="高级场权重" align="center" prop="weightTall" />
      <el-table-column label="掉落编号" align="center" prop="fallId" />
      <el-table-column label="游行方式" align="center" prop="paradeMode" />
      <el-table-column label="游行次数" align="center" prop="paradesNumber" />
      <el-table-column label="模型动画" align="center" prop="modelAnimation" />
      <el-table-column label="死亡动画" align="center" prop="deathAnimation" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:fishingMonster:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qwk:fishingMonster:remove']"
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

    <!-- 添加或修改捕鱼怪物对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="怪物名称" prop="nanme">
          <el-input v-model="form.nanme" placeholder="请输入怪物名称" />
        </el-form-item>
        <el-form-item label="怪物血量" prop="hp">
          <el-input v-model="form.hp" placeholder="请输入怪物血量" />
        </el-form-item>
        <el-form-item label="掉落编号" prop="fallId">
          <el-input v-model="form.fallId" type="textarea" placeholder="请输入内容(用','隔开)" />
        </el-form-item>
        <el-form-item label="价值档位" prop="numberRange">
          <input-number-range
            :disabled="false"
            :precision=0
            v-model="numberRange"
          ></input-number-range>
        </el-form-item>
        <el-form-item label="低级场权重" prop="weightLow">
          <el-input v-model="form.weightLow" placeholder="请输入低级场权重" />
        </el-form-item>
        <el-form-item label="中级场权重" prop="weightCentre">
          <el-input v-model="form.weightCentre" placeholder="请输入中级场权重" />
        </el-form-item>
        <el-form-item label="高级场权重" prop="weightTall">
          <el-input v-model="form.weightTall" placeholder="请输入高级场权重" />
        </el-form-item>
        <el-form-item label="游行方式" prop="paradeMode">
          <el-input v-model="form.paradeMode" placeholder="请输入游行方式" />
        </el-form-item>
        <el-form-item label="游行次数" prop="paradesNumber">
          <el-input v-model="form.paradesNumber" placeholder="请输入游行次数" />
        </el-form-item>
        <el-form-item label="模型动画" prop="modelAnimation">
          <el-input v-model="form.modelAnimation" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="死亡动画" prop="deathAnimation">
          <el-input v-model="form.deathAnimation" type="textarea" placeholder="请输入内容" />
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
import { listFishingMonster, getFishingMonster, delFishingMonster, addFishingMonster, updateFishingMonster } from "@/api/qwk/catchFish/fishingMonster";

export default {
  name: "FishingMonster",
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
      // 捕鱼怪物表格数据
      fishingMonsterList: [],
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
        id: null,
        nanme: null,
        hp: null,
        tall: null,
        low: null,
        fallId: null,
        paradeMode: null,
        paradesNumber: null,
        modelAnimation: null,
        deathAnimation: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        nanme: [
          { required: true, message: "怪物名称不能为空", trigger: "blur" }
        ],
      },
      numberRange:[],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询捕鱼怪物列表 */
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
      listFishingMonster(this.queryParams).then(response => {
        this.fishingMonsterList = response.rows;
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
        nanme: null,
        hp: null,
        fallId: null,
        weightLow: null,
        weightCentre: null,
        weightTall: null,
        paradeMode: null,
        paradesNumber: null,
        modelAnimation: null,
        deathAnimation: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.numberRange = [];
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
      this.open = true;
      this.title = "添加捕鱼怪物";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFishingMonster(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改捕鱼怪物";
        this.numberRange.push(parseInt(response.data.low));
        this.numberRange.push(parseInt(response.data.tall));
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (null != this.numberRange && '' != this.numberRange) {
            this.form.low = this.numberRange[0];
            this.form.tall= this.numberRange[1];
          }
          if (this.form.id != null) {
            updateFishingMonster(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFishingMonster(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除捕鱼怪物编号为"' + ids + '"的数据项？').then(function() {
        return delFishingMonster(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/fishingMonster/export', {
        ...this.queryParams
      }, `fishingMonster_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
