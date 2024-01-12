import request from '@/utils/request'

// 查询问题投诉列表
export function listProblem(query) {
  return request({
    url: '/qwk/problem/list',
    method: 'get',
    params: query
  })
}

// 查询问题投诉详细
export function getProblem(id) {
  return request({
    url: '/qwk/problem/' + id,
    method: 'get'
  })
}

// 新增问题投诉
export function addProblem(data) {
  return request({
    url: '/qwk/problem',
    method: 'post',
    data: data
  })
}

// 修改问题投诉
export function updateProblem(data) {
  return request({
    url: '/qwk/problem',
    method: 'put',
    data: data
  })
}

// 删除问题投诉
export function delProblem(id) {
  return request({
    url: '/qwk/problem/' + id,
    method: 'delete'
  })
}
