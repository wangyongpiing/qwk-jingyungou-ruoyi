import request from '@/utils/request'

// 查询收货地址列表
export function listTakeDeliveryAddress(query) {
  return request({
    url: '/qwk/takeDeliveryAddress/list',
    method: 'get',
    params: query
  })
}

// 查询收货地址详细
export function getTakeDeliveryAddress(id) {
  return request({
    url: '/qwk/takeDeliveryAddress/' + id,
    method: 'get'
  })
}

// 新增收货地址
export function addTakeDeliveryAddress(data) {
  return request({
    url: '/qwk/takeDeliveryAddress',
    method: 'post',
    data: data
  })
}

// 修改收货地址
export function updateTakeDeliveryAddress(data) {
  return request({
    url: '/qwk/takeDeliveryAddress',
    method: 'put',
    data: data
  })
}

// 删除收货地址
export function delTakeDeliveryAddress(id) {
  return request({
    url: '/qwk/takeDeliveryAddress/' + id,
    method: 'delete'
  })
}
