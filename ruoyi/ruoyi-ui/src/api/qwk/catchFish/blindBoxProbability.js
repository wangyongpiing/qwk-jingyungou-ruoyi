import request from '@/utils/request'

// 查询盲盒概率列表
export function listBlindBoxProbability(query) {
  return request({
    url: '/qwk/blindBoxProbability/list',
    method: 'get',
    params: query
  })
}

// 查询盲盒概率详细
export function getBlindBoxProbability(id) {
  return request({
    url: '/qwk/blindBoxProbability/' + id,
    method: 'get'
  })
}

// 新增盲盒概率
export function addBlindBoxProbability(data) {
  return request({
    url: '/qwk/blindBoxProbability',
    method: 'post',
    data: data
  })
}

// 修改盲盒概率
export function updateBlindBoxProbability(data) {
  return request({
    url: '/qwk/blindBoxProbability',
    method: 'put',
    data: data
  })
}

// 删除盲盒概率
export function delBlindBoxProbability(id) {
  return request({
    url: '/qwk/blindBoxProbability/' + id,
    method: 'delete'
  })
}
