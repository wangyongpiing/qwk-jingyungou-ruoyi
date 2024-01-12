import request from '@/utils/request'

// 查询装备配置列表
export function listEquip(query) {
  return request({
    url: '/qwk/equip/list',
    method: 'get',
    params: query
  })
}

// 查询装备配置详细
export function getEquip(id) {
  return request({
    url: '/qwk/equip/' + id,
    method: 'get'
  })
}

// 新增装备配置
export function addEquip(data) {
  return request({
    url: '/qwk/equip',
    method: 'post',
    data: data
  })
}

// 修改装备配置
export function updateEquip(data) {
  return request({
    url: '/qwk/equip',
    method: 'put',
    data: data
  })
}

// 删除装备配置
export function delEquip(id) {
  return request({
    url: '/qwk/equip/' + id,
    method: 'delete'
  })
}
