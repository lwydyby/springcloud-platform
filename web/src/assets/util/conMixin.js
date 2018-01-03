import {MENUS_CONDITION} from '../constants/constant-common';
import utils from './utils';
var condition = {
  methods:{
    /**
     * 记忆需要记录的查询条件
     * @param menuId
     * @param condition
     */
    memoryCondition:function(menuId,con){
      let cons = sessionStorage.getItem(MENUS_CONDITION);
      if(utils.isNullUndefined(cons)){
        cons = [];
        cons.push({menuId:menuId,condition:con});
      }else {
        cons = JSON.parse(cons);
        let flag = false;
        for(let c in cons){
          if(cons[c]['menuId'] == menuId){
            cons[c]['condition'] = con;
            flag = true;
            break;
          }
        }
        if(!flag){
          cons.push({menuId:menuId,condition:con});
        }
      }
      sessionStorage.setItem(MENUS_CONDITION,JSON.stringify(cons));
    },
    /**
     * 获取记忆的查询条件
     * @param menuId
     * @returns {*}
     */
    getCondition4Memory(menuId){
      let cons = sessionStorage.getItem(MENUS_CONDITION);
      let con = null;
      if(!utils.isNullUndefined(cons)){
        cons = JSON.parse(cons);
        for(let c in cons){
          if(cons[c]['menuId'] == menuId){
            con = cons[c]['condition'];
            break;
          }
        }
      }
      return con;
    },
  }
}

export {condition};
