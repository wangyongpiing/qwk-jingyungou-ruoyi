<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择商品类型" clearable style="width: 220px">
          <el-option
            v-for="dict in dict.type.wtb_goods_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品分类" prop="type">
        <el-select v-model="queryParams.categoryId" placeholder="请选择商品分类" clearable style="width: 220px">
          <el-option
            v-for="dict in category"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 220px"
        />
      </el-form-item>
      <el-form-item label="供应商" prop="supplier">
        <el-input
          v-model="queryParams.supplier"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 220px"
        />
      </el-form-item>
      <el-form-item label="平台价" prop="platformPrice">
        <el-input
          v-model="queryParams.platformPrice"
          placeholder="请输入平台价"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 220px"
        />
      </el-form-item>
      <el-form-item label="已售数量" prop="soldQuantity">
        <el-input
          v-model="queryParams.soldQuantity"
          placeholder="请输入已售数量"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 220px"
        />
      </el-form-item>
      <el-form-item label="是否碎片" prop="isDebris">
        <el-select style="width: 220px" v-model="queryParams.isDebris" placeholder="请选择是否碎片" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="上架时间">
        <el-date-picker
          v-model="daterangeListingTime"
          style="width: 220px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"

        ></el-date-picker>
      </el-form-item>
      <el-form-item label="商品状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择商品状态" clearable style="width: 220px">
          <el-option
            v-for="dict in dict.type.wtb_goods_state"
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
          v-hasPermi="['qwk:goods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="upper"
          v-hasPermi="['qwk:goods:edit']"
        >上架</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="below"
          v-hasPermi="['qwk:goods:edit']"
        >下架</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['qwk:goods:edit']"
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
          v-hasPermi="['qwk:goods:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:goods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="商品类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_goods_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="商品名称" align="center" prop="name" show-overflow-tooltip/>
      <el-table-column label="商品图片" align="center" prop="image" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品货号" align="center" prop="number" />
      <el-table-column label="供应商" align="center" prop="supplier" />
      <el-table-column label="成本价" sortable align="center" prop="costPrice" width="90"/>
      <el-table-column label="平台价" sortable align="center" prop="platformPrice" width="90"/>
      <el-table-column label="现金价" align="center" prop="cashPrice" width="90"/>
      <el-table-column label="已售数量" sortable align="center" prop="soldQuantity" width="100"/>
      <el-table-column label="库存" sortable align="center" prop="inventory" width="80"/>
      <el-table-column label="上架时间" align="center" prop="listingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.listingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="下架时间" align="center" prop="offShelfTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.offShelfTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商品状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_goods_state" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
<!--      <el-table-column label="商品规格" align="center" prop="pSId" />-->
<!--      <el-table-column label="商品描述" align="center" prop="describe" />-->
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" align="center" class-name="small-padding fixed-width" width="120">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qwk:goods:remove']"
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

    <!-- 添加或修改商品 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%"  append-to-body >
      <div class="content" style="height: 650px; overflow-y:auto; ">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="商品类型" prop="type">
            <el-radio-group v-model="form.type" size="medium">
              <el-radio
                v-for="dict in dict.type.wtb_goods_type"
                :key="dict.value"
                :label="parseInt(dict.value)"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="商品分类" prop="categoryId">
            <el-select v-model="form.categoryId" placeholder="请选择商品分类" clearable style="width: 220px">
              <el-option
                v-for="dict in category"
                :key="dict.id"
                :label="dict.name"
                :value="dict.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入商品名称" />
          </el-form-item>
          <el-form-item label="商品图片" prop="image">
            <image-upload v-model="form.image"/>
          </el-form-item>
          <el-form-item label="商品货号" prop="number">
            <el-input v-model="form.number" placeholder="请输入商品货号" />
          </el-form-item>
          <el-form-item label="供应商" prop="supplier">
            <el-input v-model="form.supplier" placeholder="请输入供应商" />
          </el-form-item>
          <el-form-item label="成本价" prop="costPrice" v-if="form.type == 1">
            <el-input v-model="form.costPrice" placeholder="请输入成本价" />
          </el-form-item>
          <el-form-item label="平台价" prop="platformPrice">
            <el-input v-model="form.platformPrice" placeholder="请输入平台价" />
          </el-form-item>
          <el-form-item label="现金价" prop="cashPrice">
            <el-input v-model="form.cashPrice" placeholder="请输入现金价" />
          </el-form-item>
<!--          <el-form-item label="已售数量" prop="soldQuantity">-->
<!--            <el-input v-model="form.soldQuantity" placeholder="请输入已售数量" />-->
<!--          </el-form-item>-->
          <el-form-item label="库存" prop="inventory" v-if="form.type == 1">
            <el-input v-model="form.inventory" placeholder="请输入库存" />
          </el-form-item>
          <el-form-item label="邮费" prop="postage" v-if="form.type == 1">
            <el-input v-model="form.postage" placeholder="请输入邮费" />
          </el-form-item>
          <!--        <el-form-item label="上架时间" prop="listingTime">-->
          <!--          <el-date-picker clearable-->
          <!--            v-model="form.listingTime"-->
          <!--            type="date"-->
          <!--            value-format="yyyy-MM-dd"-->
          <!--            placeholder="请选择上架时间">-->
          <!--          </el-date-picker>-->
          <!--        </el-form-item>-->
          <!--        <el-form-item label="下架时间" prop="offShelfTime">-->
          <!--          <el-date-picker clearable-->
          <!--            v-model="form.offShelfTime"-->
          <!--            type="date"-->
          <!--            value-format="yyyy-MM-dd"-->
          <!--            placeholder="请选择下架时间">-->
          <!--          </el-date-picker>-->
          <!--        </el-form-item>-->
<!--          <el-form-item label="商品状态" prop="status">-->
<!--            <el-select v-model="form.status" placeholder="请选择商品状态">-->
<!--              <el-option-->
<!--                v-for="dict in dict.type.wtb_goods_state"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              ></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
          <el-form-item label="排序" prop="sort">
            <el-input v-model="form.sort" placeholder="请输入排序" />
          </el-form-item>
          <el-form-item label="是否碎片" prop="isDebris">
            <el-radio-group v-model="form.isDebris">
              <el-radio
                v-for="dict in dict.type.sys_yes_no"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="中奖权重" prop="weight">
            <el-input v-model="form.weight" placeholder="请输入中奖权重" />
          </el-form-item>
          <el-form-item label="商品规格" prop="pSId">
<!--            scope.row.pSId ? scope.row.pSId.split(',') : []-->
<!--            <el-input v-model="form.pSId" placeholder="请输入商品规格" />-->
            <template slot-scope="scope">
              <el-select v-model="form.pSId" multiple placeholder="请选择">
                <el-option
                  v-for="item in specs"
                  :key="item.id+''"
                  :label="item.name"
                  :value="item.id+''">
                </el-option>
              </el-select>
              <el-button type="text" @click="handleSpecsAdd">添加规格项目</el-button>
            </template>

          </el-form-item>
          <el-form-item label="商品描述" prop="describe">
            <!--          <el-input v-model="form.describe" type="textarea" placeholder="请输入内容" />-->
            <editor v-model ="form.describe"  :minHeight="300"></editor>
          </el-form-item>
        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="titleSpecs" :visible.sync="openSpecs" width="500px" append-to-body>
      <el-form ref="formSpecs" :model="formSpecs" :rules="rulesSpecs" label-width="80px">
        <el-form-item label="规格名称" prop="name">
          <el-input v-model="formSpecs.name" placeholder="请输入规格名称" />
        </el-form-item>
        <el-form-item label="规格值" prop="value">
          <el-input v-model="formSpecs.value" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormSpecs">确 定</el-button>
        <el-button @click="cancelSpecs">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listGoods, getGoods, delGoods, addGoods, updateGoods ,updateGoodsList, getCategoryByParentId} from "@/api/qwk/goods";
import {  addSpecifications,getPSList } from "@/api/qwk/specifications";

export default {
  name: "Goods",
  dicts: ['wtb_goods_state', 'wtb_goods_type','sys_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      modifyList: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品 表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 更新时间时间范围
      daterangeListingTime: [],
      // 更新时间时间范围
      daterangeOffShelfTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        name: null,
        image: null,
        number: null,
        supplier: null,
        costPrice: null,
        platformPrice: null,
        soldQuantity: null,
        inventory: null,
        listingTime: null,
        offShelfTime: null,
        status: null,
        sort: null,
        pSId: null,
        describe: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "商品类型不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        image: [
          { required: true, message: "商品图片不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "商品货号不能为空", trigger: "blur" }
        ],
        supplier: [
          { required: true, message: "供应商不能为空", trigger: "blur" }
        ],
        platformPrice: [
          { required: true, message: "平台价不能为空", trigger: "blur" }
        ],
        cashPrice: [
          { required: true, message: "现金价不能为空", trigger: "blur" }
        ],
      },
      //商品规格
      specs :[],
      formSpecs: {},
      rulesSpecs: {
        name: [
          { required: true, message: "规格名称不能为空", trigger: "blur" }
        ],
        value: [
          { required: true, message: "规格值不能为空", trigger: "blur" }
        ],
      },
      titleSpecs: "",
      openSpecs: false,
      category:[],
    };
  },
  created() {
    this.getList();
    this.getSpecs();
    this.getCategory();
  },
  methods: {
    /** 查询商品 列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeListingTime && '' != this.daterangeListingTime) {
        this.queryParams.params["beginListingTime"] = this.daterangeListingTime[0];
        this.queryParams.params["endListingTime"] = this.daterangeListingTime[1];
      }
      if (null != this.daterangeOffShelfTime && '' != this.daterangeOffShelfTime) {
        this.queryParams.params["beginOffShelfTime"] = this.daterangeOffShelfTime[0];
        this.queryParams.params["endOffShelfTime"] = this.daterangeOffShelfTime[1];
      }
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getSpecs(){
      getPSList().then(res=>{
        this.specs = res.data;
      })
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
        type: 1,
        name: null,
        image: null,
        number: null,
        supplier: null,
        costPrice: null,
        platformPrice: null,
        soldQuantity: null,
        inventory: null,
        listingTime: null,
        offShelfTime: null,
        status: null,
        sort: null,
        isDebris: null,
        weight: null,
        describe: null,
        pSId: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        cashPrice:null
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
      this.daterangeListingTime = [];
      this.daterangeOffShelfTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.modifyList = selection;
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.isDebris = '1';
      this.open = true;
      this.title = "添加商品 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGoods(id).then(response => {
        this.form = response.data;
        if (this.form.pSId != null && this.form.pSId != undefined && this.form.pSId != ""){
          this.form.pSId =  response.data.pSId.split(',');
        }
        // let value = []
        // value =  response.data.pSId.split(',');
        // console.log(value)
        this.open = true;
        this.title = "修改商品 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.pSId = this.form.pSId +'';
          if (this.form.id != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 商品规格提交按钮 */
    submitFormSpecs() {
      this.$refs["formSpecs"].validate(valid => {
        if (valid) {
          if (this.formSpecs.id != null) {
            // updateSpecifications(this.form).then(response => {
            //   this.$modal.msgSuccess("修改成功");
            //   this.open = false;
            //   this.getList();
            // });
          } else {
            addSpecifications(this.formSpecs).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openSpecs = false;
              this.getSpecs();
            });
          }
        }
      });
    },
    cancelSpecs() {
      this.openSpecs = false;
      this.resetSpecs();
    },
    // 表单重置
    resetSpecs() {
      this.formSpecs = {
        id: null,
        name: null,
        value: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      };
      this.resetForm("formSpecs");
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除商品 编号为"' + ids + '"的数据项？').then(function() {
        return delGoods(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //上架
    upper(row){
      const ids = row.id || this.ids;
      let value = this.modifyList.map(item=>({
        id:item.id,
        status:'1'
      }))
      this.$modal.confirm('是否确认上架商品编号为"' + ids + '"的数据项？').then(function() {
        return updateGoodsList(value);
      }).then(() => {
        this.getList();
        this.open = false;
        this.$modal.msgSuccess("成功上架"+value.length+"个商品!");
      }).catch(() => {});
    },
    //上架
    below(row){
      const ids = row.id || this.ids;
      let value = this.modifyList.map(item=>({
        id:item.id,
        status:'2'
      }))
      this.$modal.confirm('是否确认下架商品编号为"' + ids + '"的数据项？').then(function() {
        return updateGoodsList(value);
      }).then(() => {
        this.getList();
        this.open = false;
        this.$modal.msgSuccess("成功下架"+value.length+"个商品!");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    },
    /**新增商品规格*/
    handleSpecsAdd() {
      this.resetSpecs();
      this.openSpecs = true;
      this.titleSpecs = "添加商品规格 ";
    },
  //  获取分类列表
    getCategory(){
      getCategoryByParentId(0).then(res=>{
        this.category=res.data;
      })
    },
  }
};
</script>
