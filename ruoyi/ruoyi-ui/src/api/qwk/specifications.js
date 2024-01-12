import request from '@/utils/request'

// 查询商品规格 列表
export function listSpecifications(query) {
  return request({
    url: '/qwk/specifications/list',
    method: 'get',
    params: query
  })
}

// 查询商品规格 详细
export function getSpecifications(id) {
  return request({
    url: '/qwk/specifications/' + id,
    method: 'get'
  })
}

// 新增商品规格
export function addSpecifications(data) {
  return request({
    url: '/qwk/specifications',
    method: 'post',
    data: data
  })
}

// 修改商品规格
export function updateSpecifications(data) {
  return request({
    url: '/qwk/specifications',
    method: 'put',
    data: data
  })
}

// 删除商品规格
export function delSpecifications(id) {
  return request({
    url: '/qwk/specifications/' + id,
    method: 'delete'
  })
}

//查询所有商品规格
export function getPSList() {
  return request({
    url: '/qwk/specifications/getPSList',
    method: 'get'
  })
}
