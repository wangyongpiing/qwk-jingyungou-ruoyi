import request from '@/utils/request'

// 查询道具产出记录列表
export function listRecord(query) {
  return request({
    url: '/qwk/record/list',
    method: 'get',
    params: query
  })
}

// 查询道具产出记录详细
export function getRecord(id) {
  return request({
    url: '/qwk/record/' + id,
    method: 'get'
  })
}

// 新增道具产出记录
export function addRecord(data) {
  return request({
    url: '/qwk/record',
    method: 'post',
    data: data
  })
}

// 修改道具产出记录
export function updateRecord(data) {
  return request({
    url: '/qwk/record',
    method: 'put',
    data: data
  })
}

// 删除道具产出记录
export function delRecord(id) {
  return request({
    url: '/qwk/record/' + id,
    method: 'delete'
  })
}
