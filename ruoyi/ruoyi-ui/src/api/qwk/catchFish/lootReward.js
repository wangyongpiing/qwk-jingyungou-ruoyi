import request from '@/utils/request'

// 查询掉落奖励列表
export function listLootReward(query) {
  return request({
    url: '/qwk/lootReward/list',
    method: 'get',
    params: query
  })
}

// 查询掉落奖励详细
export function getLootReward(id) {
  return request({
    url: '/qwk/lootReward/' + id,
    method: 'get'
  })
}

// 新增掉落奖励
export function addLootReward(data) {
  return request({
    url: '/qwk/lootReward',
    method: 'post',
    data: data
  })
}

// 修改掉落奖励
export function updateLootReward(data) {
  return request({
    url: '/qwk/lootReward',
    method: 'put',
    data: data
  })
}

// 删除掉落奖励
export function delLootReward(id) {
  return request({
    url: '/qwk/lootReward/' + id,
    method: 'delete'
  })
}
