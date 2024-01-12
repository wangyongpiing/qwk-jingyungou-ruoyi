import request from '@/utils/request'

// 查询首页配置 列表
export function listConfig(query) {
  return request({
    url: '/qwk/config/list',
    method: 'get',
    params: query
  })
}

// 查询首页配置 详细
export function getConfig(id) {
  return request({
    url: '/qwk/config/' + id,
    method: 'get'
  })
}

// 新增首页配置 
export function addConfig(data) {
  return request({
    url: '/qwk/config',
    method: 'post',
    data: data
  })
}

// 修改首页配置 
export function updateConfig(data) {
  return request({
    url: '/qwk/config',
    method: 'put',
    data: data
  })
}

// 删除首页配置 
export function delConfig(id) {
  return request({
    url: '/qwk/config/' + id,
    method: 'delete'
  })
}
