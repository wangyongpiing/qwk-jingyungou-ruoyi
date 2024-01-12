import request from '@/utils/request'

// 查询物流 列表
export function listLogistics(query) {
  return request({
    url: '/qwk/logistics/list',
    method: 'get',
    params: query
  })
}

// 查询物流 详细
export function getLogistics(id) {
  return request({
    url: '/qwk/logistics/' + id,
    method: 'get'
  })
}

// 新增物流
export function addLogistics(data) {
  return request({
    url: '/qwk/logistics',
    method: 'post',
    data: data
  })
}

// 修改物流
export function updateLogistics(data) {
  return request({
    url: '/qwk/logistics',
    method: 'put',
    data: data
  })
}

// 删除物流
export function delLogistics(id) {
  return request({
    url: '/qwk/logistics/' + id,
    method: 'delete'
  })
}

//发货
export function sendOutGoods(data) {
  return request({
    url: '/qwk/logistics/addLogistics',
    method: 'post',
    data: data
  })
}

// 查询快递物流
export function expressLogistics(query) {
  return request({
    url: '/fastMail/query',
    method: 'get',
    params: query
  })
}



