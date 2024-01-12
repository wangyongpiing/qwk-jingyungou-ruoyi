import request from '@/utils/request'

// 查询用户盲盒爆出奖品列表
export function listUserPrize(query) {
  return request({
    url: '/qwk/userPrize/list',
    method: 'get',
    params: query
  })
}

// 查询用户盲盒爆出奖品详细
export function getUserPrize(id) {
  return request({
    url: '/qwk/userPrize/' + id,
    method: 'get'
  })
}

// 新增用户盲盒爆出奖品
export function addUserPrize(data) {
  return request({
    url: '/qwk/userPrize',
    method: 'post',
    data: data
  })
}

// 修改用户盲盒爆出奖品
export function updateUserPrize(data) {
  return request({
    url: '/qwk/userPrize',
    method: 'put',
    data: data
  })
}

// 删除用户盲盒爆出奖品
export function delUserPrize(id) {
  return request({
    url: '/qwk/userPrize/' + id,
    method: 'delete'
  })
}
