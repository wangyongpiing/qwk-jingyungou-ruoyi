import request from '@/utils/request'

// 查询子弹配置列表
export function listBullets(query) {
  return request({
    url: '/qwk/bullets/list',
    method: 'get',
    params: query
  })
}

// 查询子弹配置详细
export function getBullets(id) {
  return request({
    url: '/qwk/bullets/' + id,
    method: 'get'
  })
}

// 新增子弹配置
export function addBullets(data) {
  return request({
    url: '/qwk/bullets',
    method: 'post',
    data: data
  })
}

// 修改子弹配置
export function updateBullets(data) {
  return request({
    url: '/qwk/bullets',
    method: 'put',
    data: data
  })
}

// 删除子弹配置
export function delBullets(id) {
  return request({
    url: '/qwk/bullets/' + id,
    method: 'delete'
  })
}
