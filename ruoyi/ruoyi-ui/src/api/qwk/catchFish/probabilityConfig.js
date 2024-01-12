import request from '@/utils/request'

// 查询每种物品的中奖概率配置列表
export function listProbabilityConfig(query) {
  return request({
    url: '/qwk/probabilityConfig/list',
    method: 'get',
    params: query
  })
}

// 查询每种物品的中奖概率配置详细
export function getProbabilityConfig(id) {
  return request({
    url: '/qwk/probabilityConfig/' + id,
    method: 'get'
  })
}

// 新增每种物品的中奖概率配置
export function addProbabilityConfig(data) {
  return request({
    url: '/qwk/probabilityConfig',
    method: 'post',
    data: data
  })
}

// 修改每种物品的中奖概率配置
export function updateProbabilityConfig(data) {
  return request({
    url: '/qwk/probabilityConfig',
    method: 'put',
    data: data
  })
}

// 删除每种物品的中奖概率配置
export function delProbabilityConfig(id) {
  return request({
    url: '/qwk/probabilityConfig/' + id,
    method: 'delete'
  })
}
