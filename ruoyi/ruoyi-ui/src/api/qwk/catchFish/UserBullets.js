import request from '@/utils/request'

// 查询用户子弹数量记录列表
export function listUserBullets(query) {
  return request({
    url: '/qwk/UserBullets/list',
    method: 'get',
    params: query
  })
}

// 查询用户子弹数量记录详细
export function getUserBullets(id) {
  return request({
    url: '/qwk/UserBullets/' + id,
    method: 'get'
  })
}

// 新增用户子弹数量记录
export function addUserBullets(data) {
  return request({
    url: '/qwk/UserBullets',
    method: 'post',
    data: data
  })
}

// 修改用户子弹数量记录
export function updateUserBullets(data) {
  return request({
    url: '/qwk/UserBullets',
    method: 'put',
    data: data
  })
}

// 删除用户子弹数量记录
export function delUserBullets(id) {
  return request({
    url: '/qwk/UserBullets/' + id,
    method: 'delete'
  })
}
