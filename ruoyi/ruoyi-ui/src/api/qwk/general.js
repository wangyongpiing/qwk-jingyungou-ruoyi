import request from '@/utils/request'

// 查询app通用配置列表
export function listGeneral(query) {
  return request({
    url: '/qwk/general/list',
    method: 'get',
    params: query
  })
}

// 查询app通用配置详细
export function getGeneral(id) {
  return request({
    url: '/qwk/general/' + id,
    method: 'get'
  })
}

// 新增app通用配置
export function addGeneral(data) {
  return request({
    url: '/qwk/general',
    method: 'post',
    data: data
  })
}

// 修改app通用配置
export function updateGeneral(data) {
  return request({
    url: '/qwk/general',
    method: 'put',
    data: data
  })
}

// 删除app通用配置
export function delGeneral(id) {
  return request({
    url: '/qwk/general/' + id,
    method: 'delete'
  })
}
