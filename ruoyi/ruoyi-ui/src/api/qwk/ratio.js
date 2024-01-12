import request from '@/utils/request'

// 查询积分兑换比例设置列表
export function listRatio(query) {
  return request({
    url: '/qwk/ratio/list',
    method: 'get',
    params: query
  })
}

// 查询积分兑换比例设置详细
export function getRatio(id) {
  return request({
    url: '/qwk/ratio/' + id,
    method: 'get'
  })
}

// 新增积分兑换比例设置
export function addRatio(data) {
  return request({
    url: '/qwk/ratio',
    method: 'post',
    data: data
  })
}

// 修改积分兑换比例设置
export function updateRatio(data) {
  return request({
    url: '/qwk/ratio',
    method: 'put',
    data: data
  })
}

// 删除积分兑换比例设置
export function delRatio(id) {
  return request({
    url: '/qwk/ratio/' + id,
    method: 'delete'
  })
}
