import request from '@/utils/request'

// 查询支付渠道列表
export function listChannels(query) {
  return request({
    url: '/qwk/channels/list',
    method: 'get',
    params: query
  })
}

// 查询支付渠道详细
export function getChannels(id) {
  return request({
    url: '/qwk/channels/' + id,
    method: 'get'
  })
}

// 新增支付渠道
export function addChannels(data) {
  return request({
    url: '/qwk/channels',
    method: 'post',
    data: data
  })
}

// 修改支付渠道
export function updateChannels(data) {
  return request({
    url: '/qwk/channels',
    method: 'put',
    data: data
  })
}

// 删除支付渠道
export function delChannels(id) {
  return request({
    url: '/qwk/channels/' + id,
    method: 'delete'
  })
}

// 查询所有启用的支付渠道
export function getByStatusList() {
  return request({
    url: '/qwk/channels/getByStatusList',
    method: 'get'
  })
}
