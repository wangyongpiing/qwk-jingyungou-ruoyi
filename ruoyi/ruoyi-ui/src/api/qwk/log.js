import request from '@/utils/request'

// 查询用户状态日志列表
export function listLog(query) {
  return request({
    url: '/qwk/log/list',
    method: 'get',
    params: query
  })
}

// 查询用户状态日志详细
export function getLog(id) {
  return request({
    url: '/qwk/log/' + id,
    method: 'get'
  })
}

// 新增用户状态日志
export function addLog(data) {
  return request({
    url: '/qwk/log',
    method: 'post',
    data: data
  })
}

// 修改用户状态日志
export function updateLog(data) {
  return request({
    url: '/qwk/log',
    method: 'put',
    data: data
  })
}

// 删除用户状态日志
export function delLog(id) {
  return request({
    url: '/qwk/log/' + id,
    method: 'delete'
  })
}
