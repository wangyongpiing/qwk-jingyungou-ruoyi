import request from '@/utils/request'

// 查询仓库详情 列表
export function listDetails(query) {
  return request({
    url: '/qwk/details/list',
    method: 'get',
    params: query
  })
}

// 查询仓库详情 详细
export function getDetails(id) {
  return request({
    url: '/qwk/details/' + id,
    method: 'get'
  })
}

// 新增仓库详情 
export function addDetails(data) {
  return request({
    url: '/qwk/details',
    method: 'post',
    data: data
  })
}

// 修改仓库详情 
export function updateDetails(data) {
  return request({
    url: '/qwk/details',
    method: 'put',
    data: data
  })
}

// 删除仓库详情 
export function delDetails(id) {
  return request({
    url: '/qwk/details/' + id,
    method: 'delete'
  })
}
