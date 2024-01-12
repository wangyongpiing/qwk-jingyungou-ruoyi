import request from '@/utils/request'

// 查询道具爆率管理列表
export function listProbability(query) {
  return request({
    url: '/qwk/probability/list',
    method: 'get',
    params: query
  })
}

// 查询道具爆率管理详细
export function getProbability(id) {
  return request({
    url: '/qwk/probability/' + id,
    method: 'get'
  })
}

// 新增道具爆率管理
export function addProbability(data) {
  return request({
    url: '/qwk/probability',
    method: 'post',
    data: data
  })
}

// 修改道具爆率管理
export function updateProbability(data) {
  return request({
    url: '/qwk/probability',
    method: 'put',
    data: data
  })
}

// 删除道具爆率管理
export function delProbability(id) {
  return request({
    url: '/qwk/probability/' + id,
    method: 'delete'
  })
}
