import request from '@/utils/request'

// 查询版本管理列表
export function listVersioning(query) {
  return request({
    url: '/qwk/versioning/list',
    method: 'get',
    params: query
  })
}

// 查询版本管理详细
export function getVersioning(id) {
  return request({
    url: '/qwk/versioning/' + id,
    method: 'get'
  })
}

// 新增版本管理
export function addVersioning(data) {
  return request({
    url: '/qwk/versioning',
    method: 'post',
    data: data
  })
}

// 修改版本管理
export function updateVersioning(data) {
  return request({
    url: '/qwk/versioning',
    method: 'put',
    data: data
  })
}

// 删除版本管理
export function delVersioning(id) {
  return request({
    url: '/qwk/versioning/' + id,
    method: 'delete'
  })
}
