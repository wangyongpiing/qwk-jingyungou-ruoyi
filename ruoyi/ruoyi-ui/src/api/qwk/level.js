import request from '@/utils/request'

// 查询vip等级列表
export function listLevel(query) {
  return request({
    url: '/qwk/level/list',
    method: 'get',
    params: query
  })
}

// 查询vip等级详细
export function getLevel(id) {
  return request({
    url: '/qwk/level/' + id,
    method: 'get'
  })
}

// 新增vip等级
export function addLevel(data) {
  return request({
    url: '/qwk/level',
    method: 'post',
    data: data
  })
}

// 修改vip等级
export function updateLevel(data) {
  return request({
    url: '/qwk/level',
    method: 'put',
    data: data
  })
}

// 删除vip等级
export function delLevel(id) {
  return request({
    url: '/qwk/level/' + id,
    method: 'delete'
  })
}
