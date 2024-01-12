import request from '@/utils/request'

// 查询皮肤配置列表
export function listSkin(query) {
  return request({
    url: '/qwk/skin/list',
    method: 'get',
    params: query
  })
}

// 查询皮肤配置详细
export function getSkin(id) {
  return request({
    url: '/qwk/skin/' + id,
    method: 'get'
  })
}

// 新增皮肤配置
export function addSkin(data) {
  return request({
    url: '/qwk/skin',
    method: 'post',
    data: data
  })
}

// 修改皮肤配置
export function updateSkin(data) {
  return request({
    url: '/qwk/skin',
    method: 'put',
    data: data
  })
}

// 删除皮肤配置
export function delSkin(id) {
  return request({
    url: '/qwk/skin/' + id,
    method: 'delete'
  })
}
