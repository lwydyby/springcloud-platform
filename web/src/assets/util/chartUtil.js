/**
 * 图表的辅助util
 */
import utils from './utils'
let chartUtil =  {
  /**
   * 日期范围数组
   * 例 ["2017-01-01","2017-01-02","2017-01-03"]
   * @param beginDt
   * @param endDt
     */
  dateRageArray:function(beginDt,endDt){
    let dayArr = [];
    dayArr.push(utils.getDtStr2(beginDt.getTime()));
    let nextDay =utils.getDateBefore(beginDt,-1);
    while(nextDay <= endDt ){
      dayArr.push(utils.getDtStr2(beginDt.getTime()));
      nextDay =utils.getDateBefore(beginDt,-1);
    }
    return dayArr;
  },
  /**
   * 字符串数组转成字符串
   * @param items
     */
  getStrByArr:function(items){
    let str = "";
    if(items != undefined && items.length > 0){
      for(let i in items){
        str += items[i];
      }
    }
    return str;
  },
  /**
   * 字符串数组转成字符串
   * @param items
   */
  getStrByArr2:function(items){
    let str = "";
    if(items != undefined && items.length > 0){
      for(let i in items){
        str += items[i]+"|";
      }
      str=str.substring(0,str.lastIndexOf("|"))
    }
    return str;
  },
  /**
   *
   * @param obj1
   * @param obj2
   * @returns {{}}
     */
  mergeObjValue2First(obj1,obj2){
    for(var attr in obj1){
      if(obj2.hasOwnProperty(attr)){
        obj1[attr]=obj2[attr];
      }
    }
  },
  mergeAttrValue2First(obj1,obj2){
    for(let i=0;i<obj1.length;i++){
      this.mergeObjValue2First(obj1[i],obj2[i])
    }

  },
  /**
   * 去重
   * @param infos
   */

  getDistinctInfos(infos){
    let json=[];
    let data=[];
    for(let i=0;i<infos.length;i++){


    }
  }
};
export default chartUtil;
