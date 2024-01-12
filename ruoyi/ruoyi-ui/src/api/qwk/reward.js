import request from '@/utils/request'

// 查询游戏奖励列表
export function listReward(query) {
  return request({
    url: '/qwk/reward/list',
    method: 'get',
    params: query
  })
}

// 查询游戏奖励详细
export function getReward(id) {
  return request({
    url: '/qwk/reward/' + id,
    method: 'get'
  })
}

// 新增游戏奖励
export function addReward(data) {
  return request({
    url: '/qwk/reward',
    method: 'post',
    data: data
  })
}

// 修改游戏奖励
export function updateReward(data) {
  return request({
    url: '/qwk/reward',
    method: 'put',
    data: data
  })
}

// 删除游戏奖励
export function delReward(id) {
  return request({
    url: '/qwk/reward/' + id,
    method: 'delete'
  })
}
