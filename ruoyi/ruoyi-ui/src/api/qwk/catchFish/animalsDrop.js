import request from '@/utils/request'

// 查询掉落配置列表
export function listAnimalsDrop(query) {
  return request({
    url: '/qwk/animalsDrop/list',
    method: 'get',
    params: query
  })
}

// 查询掉落配置详细
export function getAnimalsDrop(id) {
  return request({
    url: '/qwk/animalsDrop/' + id,
    method: 'get'
  })
}

// 新增掉落配置
export function addAnimalsDrop(data) {
  return request({
    url: '/qwk/animalsDrop',
    method: 'post',
    data: data
  })
}

// 修改掉落配置
export function updateAnimalsDrop(data) {
  return request({
    url: '/qwk/animalsDrop',
    method: 'put',
    data: data
  })
}

// 删除掉落配置
export function delAnimalsDrop(id) {
  return request({
    url: '/qwk/animalsDrop/' + id,
    method: 'delete'
  })
}
