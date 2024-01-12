import request from '@/utils/request'

// 查询充值档位及比例 列表
export function listMoneyRatio(query) {
  return request({
    url: '/qwk/moneyRatio/list',
    method: 'get',
    params: query
  })
}

// 查询充值档位及比例 详细
export function getMoneyRatio(id) {
  return request({
    url: '/qwk/moneyRatio/' + id,
    method: 'get'
  })
}

// 新增充值档位及比例 
export function addMoneyRatio(data) {
  return request({
    url: '/qwk/moneyRatio',
    method: 'post',
    data: data
  })
}

// 修改充值档位及比例 
export function updateMoneyRatio(data) {
  return request({
    url: '/qwk/moneyRatio',
    method: 'put',
    data: data
  })
}

// 删除充值档位及比例 
export function delMoneyRatio(id) {
  return request({
    url: '/qwk/moneyRatio/' + id,
    method: 'delete'
  })
}
