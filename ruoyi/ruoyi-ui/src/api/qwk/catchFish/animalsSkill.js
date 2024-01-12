import request from '@/utils/request'

// 查询技能配置列表
export function listAnimalsSkill(query) {
  return request({
    url: '/qwk/animalsSkill/list',
    method: 'get',
    params: query
  })
}

// 查询技能配置详细
export function getAnimalsSkill(id) {
  return request({
    url: '/qwk/animalsSkill/' + id,
    method: 'get'
  })
}

// 新增技能配置
export function addAnimalsSkill(data) {
  return request({
    url: '/qwk/animalsSkill',
    method: 'post',
    data: data
  })
}

// 修改技能配置
export function updateAnimalsSkill(data) {
  return request({
    url: '/qwk/animalsSkill',
    method: 'put',
    data: data
  })
}

// 删除技能配置
export function delAnimalsSkill(id) {
  return request({
    url: '/qwk/animalsSkill/' + id,
    method: 'delete'
  })
}
