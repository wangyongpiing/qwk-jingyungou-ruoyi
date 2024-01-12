import request from '@/utils/request'

// 查询积分兑换规则列表
export function listRules(query) {
  return request({
    url: '/qwk/rules/list',
    method: 'get',
    params: query
  })
}

// 查询积分兑换规则详细
export function getRules(id) {
  return request({
    url: '/qwk/rules/' + id,
    method: 'get'
  })
}

// 新增积分兑换规则
export function addRules(data) {
  return request({
    url: '/qwk/rules',
    method: 'post',
    data: data
  })
}

// 修改积分兑换规则
export function updateRules(data) {
  return request({
    url: '/qwk/rules',
    method: 'put',
    data: data
  })
}

// 删除积分兑换规则
export function delRules(id) {
  return request({
    url: '/qwk/rules/' + id,
    method: 'delete'
  })
}
