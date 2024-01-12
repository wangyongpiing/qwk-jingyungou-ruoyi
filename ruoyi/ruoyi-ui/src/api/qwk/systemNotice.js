import request from '@/utils/request'

// 查询系统消息通知列表
export function listSystemNotice(query) {
  return request({
    url: '/qwk/systemNotice/list',
    method: 'get',
    params: query
  })
}

// 查询系统消息通知详细
export function getSystemNotice(id) {
  return request({
    url: '/qwk/systemNotice/' + id,
    method: 'get'
  })
}

// 新增系统消息通知
export function addSystemNotice(data) {
  return request({
    url: '/qwk/systemNotice',
    method: 'post',
    data: data
  })
}

// 修改系统消息通知
export function updateSystemNotice(data) {
  return request({
    url: '/qwk/systemNotice',
    method: 'put',
    data: data
  })
}

// 删除系统消息通知
export function delSystemNotice(id) {
  return request({
    url: '/qwk/systemNotice/' + id,
    method: 'delete'
  })
}

// 发送系统消息通知
export function sendNotice(data) {
  return request({
    url: '/qwk/systemNotice/sendNotice',
    method: 'post',
    data: data
  })
}
