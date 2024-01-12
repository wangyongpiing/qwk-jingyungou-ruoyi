import request from '@/utils/request'

// 查询捕鱼怪物列表
export function listFishingMonster(query) {
  return request({
    url: '/qwk/fishingMonster/list',
    method: 'get',
    params: query
  })
}

// 查询捕鱼怪物详细
export function getFishingMonster(id) {
  return request({
    url: '/qwk/fishingMonster/' + id,
    method: 'get'
  })
}

// 新增捕鱼怪物
export function addFishingMonster(data) {
  return request({
    url: '/qwk/fishingMonster',
    method: 'post',
    data: data
  })
}

// 修改捕鱼怪物
export function updateFishingMonster(data) {
  return request({
    url: '/qwk/fishingMonster',
    method: 'put',
    data: data
  })
}

// 删除捕鱼怪物
export function delFishingMonster(id) {
  return request({
    url: '/qwk/fishingMonster/' + id,
    method: 'delete'
  })
}
