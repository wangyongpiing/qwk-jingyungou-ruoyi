import request from '@/utils/request'

// 查询用户资产 列表
export function listAssets(query) {
  return request({
    url: '/qwk/assets/list',
    method: 'get',
    params: query
  })
}

// 查询用户资产 详细
export function getAssets(id) {
  return request({
    url: '/qwk/assets/' + id,
    method: 'get'
  })
}

// 新增用户资产
export function addAssets(data) {
  return request({
    url: '/qwk/assets',
    method: 'post',
    data: data
  })
}

// 修改用户资产
export function updateAssets(data) {
  return request({
    url: '/qwk/assets',
    method: 'put',
    data: data
  })
}

// 删除用户资产
export function delAssets(id) {
  return request({
    url: '/qwk/assets/' + id,
    method: 'delete'
  })
}

// 根据用户id查询用户资产信息
export async function getAssetsByUserId(id) {
  return await request({
    url: '/qwk/assets/getAssets/' + id,
    method: 'get'
  })
}

// 根据用户id,资产类型查询用户资产信息
export async function getAssetsByUserIdType(id,type) {
  return await request({
    url: '/qwk/assets/getAssetsByUserId/' + id+'/'+type,
    method: 'get'
  })
}

