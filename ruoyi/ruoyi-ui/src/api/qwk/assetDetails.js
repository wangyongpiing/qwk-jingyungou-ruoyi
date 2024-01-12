import request from '@/utils/request'

// 查询资产详情 列表
export function listAssetDetails(query) {
  return request({
    url: '/qwk/assetDetails/list',
    method: 'get',
    params: query
  })
}

// 查询资产详情 详细
export function getAssetDetails(id) {
  return request({
    url: '/qwk/assetDetails/' + id,
    method: 'get'
  })
}

// 新增资产详情 
export function addAssetDetails(data) {
  return request({
    url: '/qwk/assetDetails',
    method: 'post',
    data: data
  })
}

// 修改资产详情 
export function updateAssetDetails(data) {
  return request({
    url: '/qwk/assetDetails',
    method: 'put',
    data: data
  })
}

// 删除资产详情 
export function delAssetDetails(id) {
  return request({
    url: '/qwk/assetDetails/' + id,
    method: 'delete'
  })
}
