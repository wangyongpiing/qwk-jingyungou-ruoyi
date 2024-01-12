import request from '@/utils/request'

// 查询商品 列表
export function listGoods(query) {
  return request({
    url: '/qwk/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品 详细
export function getGoods(id) {
  return request({
    url: '/qwk/goods/' + id,
    method: 'get'
  })
}

// 新增商品
export function addGoods(data) {
  return request({
    url: '/qwk/goods',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateGoods(data) {
  return request({
    url: '/qwk/goods',
    method: 'put',
    data: data
  })
}

// 上架下架商品
export function updateGoodsList(data) {
  return request({
    url: '/qwk/goods/updateGoodsList',
    method: 'put',
    data: data
  })
}

// 删除商品
export function delGoods(id) {
  return request({
    url: '/qwk/goods/' + id,
    method: 'delete'
  })
}


// 查询商品分类
export function getCategoryByParentId(id) {
  return request({
    url: '/qwk/category/getByParentId/' + id,
    method: 'get'
  })
}
