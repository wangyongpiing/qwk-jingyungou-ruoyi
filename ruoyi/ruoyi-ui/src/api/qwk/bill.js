import request from '@/utils/request'

// 查询充值流水 列表
export function listBill(query) {
  return request({
    url: '/qwk/bill/list',
    method: 'get',
    params: query
  })
}

// 查询充值流水 详细
export function getBill(id) {
  return request({
    url: '/qwk/bill/' + id,
    method: 'get'
  })
}

// 新增充值流水
export function addBill(data) {
  return request({
    url: '/qwk/bill',
    method: 'post',
    data: data
  })
}

// 修改充值流水
export function updateBill(data) {
  return request({
    url: '/qwk/bill',
    method: 'put',
    data: data
  })
}

// 删除充值流水
export function delBill(id) {
  return request({
    url: '/qwk/bill/' + id,
    method: 'delete'
  })
}


// 查询统计数据
export function getStatistics() {
  return request({
    url: '/qwk/bill/getStatistics',
    method: 'get'
  })
}
