import request from '@/utils/request'

// 登录
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data: data
  })
}

// 付款
export function payment(data) {
  return request({
    url: '/qwk/userPrize/batchInsertSerialCardWx',
    method: 'post',
    data: data
  })
}

// 刷新
export function refresh(data) {
  return request({
    url: '/qwk/userPrize/card/list',
    method: 'get',
    data: data
  })
}

