import request from '@/utils/request'

// 查询仓库商品积分详情 列表
export function listPointsDetails(query) {
  return request({
    url: '/qwk/pointsDetails/list',
    method: 'get',
    params: query
  })
}

// 查询仓库商品积分详情 详细
export function getPointsDetails(id) {
  return request({
    url: '/qwk/pointsDetails/' + id,
    method: 'get'
  })
}

// 新增仓库商品积分详情 
export function addPointsDetails(data) {
  return request({
    url: '/qwk/pointsDetails',
    method: 'post',
    data: data
  })
}

// 修改仓库商品积分详情 
export function updatePointsDetails(data) {
  return request({
    url: '/qwk/pointsDetails',
    method: 'put',
    data: data
  })
}

// 删除仓库商品积分详情 
export function delPointsDetails(id) {
  return request({
    url: '/qwk/pointsDetails/' + id,
    method: 'delete'
  })
}
