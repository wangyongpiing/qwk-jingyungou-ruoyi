import request from '@/utils/request'

// 查询奖励包列表
export function listRewardPackage(query) {
  return request({
    url: '/qwk/rewardPackage/list',
    method: 'get',
    params: query
  })
}

// 查询奖励包详细
export function getRewardPackage(id) {
  return request({
    url: '/qwk/rewardPackage/' + id,
    method: 'get'
  })
}

// 新增奖励包
export function addRewardPackage(data) {
  return request({
    url: '/qwk/rewardPackage',
    method: 'post',
    data: data
  })
}

// 修改奖励包
export function updateRewardPackage(data) {
  return request({
    url: '/qwk/rewardPackage',
    method: 'put',
    data: data
  })
}

// 删除奖励包
export function delRewardPackage(id) {
  return request({
    url: '/qwk/rewardPackage/' + id,
    method: 'delete'
  })
}
