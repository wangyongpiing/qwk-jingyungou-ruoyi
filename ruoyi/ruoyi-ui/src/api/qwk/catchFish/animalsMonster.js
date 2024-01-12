import request from '@/utils/request'

// 查询怪物配置列表
export function listAnimalsMonster(query) {
  return request({
    url: '/qwk/animalsMonster/list',
    method: 'get',
    params: query
  })
}

// 查询怪物配置详细
export function getAnimalsMonster(id) {
  return request({
    url: '/qwk/animalsMonster/' + id,
    method: 'get'
  })
}

// 新增怪物配置
export function addAnimalsMonster(data) {
  return request({
    url: '/qwk/animalsMonster',
    method: 'post',
    data: data
  })
}

// 修改怪物配置
export function updateAnimalsMonster(data) {
  return request({
    url: '/qwk/animalsMonster',
    method: 'put',
    data: data
  })
}

// 删除怪物配置
export function delAnimalsMonster(id) {
  return request({
    url: '/qwk/animalsMonster/' + id,
    method: 'delete'
  })
}
