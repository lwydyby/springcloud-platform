import utils from '../../assets/util/utils'
import Vue from 'vue'





const IP = 'http://127.0.0.1:5000'
export const URL_CENTER_URL = IP + '/api/'


export const URL = URL_CENTER_URL + 'ReqeuestEntryHandler'
export const USER = 'user'
export const MENUS = 'menus'
export const ALL_MENUS = 'allmenus'
export const ERR_MSG = '您的账号已在另一地点登录~'
export const MENUID = {
  getmenupermision:200,
  editmenupermision:202,
  adduser:301,
  edituser:302,
}
