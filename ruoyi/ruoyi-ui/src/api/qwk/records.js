import request from '@/utils/request'

// 查询兑换记录列表
export function listRecords(query) {
  return request({
    url: '/qwk/records/list',
    method: 'get',
    params: query
  })
}

// 查询兑换记录详细
export function getRecords(id) {
  return request({
    url: '/qwk/records/' + id,
    method: 'get'
  })
}

// 新增兑换记录
export function addRecords(data) {
  return request({
    url: '/qwk/records',
    method: 'post',
    data: data
  })
}

// 修改兑换记录
export function updateRecords(data) {
  return request({
    url: '/qwk/records',
    method: 'put',
    data: data
  })
}

// 删除兑换记录
export function delRecords(id) {
  return request({
    url: '/qwk/records/' + id,
    method: 'delete'
  })
}
