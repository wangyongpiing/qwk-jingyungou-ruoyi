import request from '@/utils/request'

// 查询商品订单 列表
export function listOrders(query) {
  return request({
    url: '/qwk/orders/list',
    method: 'get',
    params: query
  })
}

// 查询商品订单 详细
export function getOrders(id) {
  return request({
    url: '/qwk/orders/' + id,
    method: 'get'
  })
}

// 新增商品订单 
export function addOrders(data) {
  return request({
    url: '/qwk/orders',
    method: 'post',
    data: data
  })
}

// 修改商品订单 
export function updateOrders(data) {
  return request({
    url: '/qwk/orders',
    method: 'put',
    data: data
  })
}

// 删除商品订单 
export function delOrders(id) {
  return request({
    url: '/qwk/orders/' + id,
    method: 'delete'
  })
}
