import request from '@/utils/request'

// 查询常见问题列表
export function listCommonQuestions(query) {
  return request({
    url: '/qwk/commonQuestions/list',
    method: 'get',
    params: query
  })
}

// 查询常见问题详细
export function getCommonQuestions(id) {
  return request({
    url: '/qwk/commonQuestions/' + id,
    method: 'get'
  })
}

// 新增常见问题
export function addCommonQuestions(data) {
  return request({
    url: '/qwk/commonQuestions',
    method: 'post',
    data: data
  })
}

// 修改常见问题
export function updateCommonQuestions(data) {
  return request({
    url: '/qwk/commonQuestions',
    method: 'put',
    data: data
  })
}

// 删除常见问题
export function delCommonQuestions(id) {
  return request({
    url: '/qwk/commonQuestions/' + id,
    method: 'delete'
  })
}
