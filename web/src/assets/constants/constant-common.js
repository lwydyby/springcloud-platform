import utils from '../../assets/util/utils'
import Vue from 'vue'





const IP = 'http://127.0.0.1:5000'
export const URL_CENTER_URL = IP + '/api/'


export const URL = URL_CENTER_URL+"user-api"
export const USER = 'user'
export const MENUS = 'menus'
export const ALL_MENUS = 'allmenus'
export const ERR_MSG = '您的账号已在另一地点登录~'
export const MENUID = {
  getmenutree:"/role/getMenuTree",
  getrolename:"/role/getRoleName",
  getrolebyid:"/role/getRoleById",
  addRole:"/role/addRole",
  editRole:"/role/editRole",
  saveRolebyId:"/role/saveRolebyId",
  getUserName:"/user/getUserName/",
  editUserRole:"/user/editUserRole",
  addDemo:"/demo/add",
  editDemo:"/demo/edit",
  deleteDemo:"/demo/delete",
  getallDemo:"/demo/getall",
  getpageDemo:"/demo/getpage",




}
