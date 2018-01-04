/* eslint-disable no-dupe-keys */

import chartUtil from "./chartUtil";
import {ALL_MENUS} from "../constants/constant-common";

let utils = {
  thranemoney(data) {
    if (data === undefined || data === "") {
      return 0.0
    }
    let str = data.toString();
    // return s.replace(/[0-9]+?(?=(?:([0-9]{3}))+$)/g, function (a) {
    //   return a + ','
    // })

    var newStr = "";
    var count = 0;

    if (str.indexOf(".") == -1) {
      for (var i = str.length - 1; i >= 0; i--) {
        if (count % 3 == 0 && count != 0) {
          newStr = str.charAt(i) + "," + newStr;
        } else {
          newStr = str.charAt(i) + newStr;
        }
        count++;
      }
      str = newStr; //自动补小数点后两位 + ".00"

    }
    else {
      for (var i = str.indexOf(".") - 1; i >= 0; i--) {
        if (count % 3 == 0 && count != 0) {
          newStr = str.charAt(i) + "," + newStr; //碰到3的倍数则加上“,”号
        } else {
          newStr = str.charAt(i) + newStr; //逐个字符相接起来
        }
        count++;
      }
      str = newStr + (str + "00").substr((str + "00").indexOf("."), 3);

    }
    return str
  },
    clone(obj) {
      var o
      switch (typeof obj) {
        case 'undefined':
          break
        case 'string' :
          o = obj + '';
          break
        case 'number' :
          o = obj - 0;
          break
        case 'boolean' :
          o = obj;
          break
        case 'object' :
          if (obj === null) {
            o = null
          } else {
            if (obj instanceof Array) {
              o = []
              for (var i = 0, len = obj.length; i < len; i++) {
                o.push(this.clone(obj[i]))
              }
            } else {
              o = {}
              for (var k in obj) {
                o[k] = this.clone(obj[k])
              }
            }
          }
          break
        default:
          o = obj;
          break
      }
      return o
    },
    /**
     *合并两个json
     var jsonObj1 = {a : 1};
     var jsonObj2 = {b : 2, c : 3, d : 4};
     那么result = {a : 1, b : 2, c: 3, d : 4}.
     * @param jsonbject1
     * @param jsonbject2
     * @returns {{}}
     */
    merge(jsonbject1, jsonbject2) {
      var resultJsonObject = {}
      for (var attr in jsonbject1) {
        resultJsonObject[attr] = jsonbject1[attr]
      }
      for (var attr in jsonbject2) {
        resultJsonObject[attr] = jsonbject2[attr]
      }
      return resultJsonObject
    },
    /**
     * 合并两个json的值到第一个对象上
     * var obj1 = {a:0,b:0};
     * var obj2 = {a:1,b:2,c:3};
     */
    mergeObjValue2First(obj1, obj2) {
      var resultJsonObject = {}
      for (var attr in obj1) {
        if (obj2.hasOwnProperty(attr)) {
          resultJsonObject[attr] = obj2[attr]
        } else {
          resultJsonObject[attr] = obj1[attr]
        }
      }
      return resultJsonObject
    },
    /**
     *合并两个数组并去重
     var arr1= [1,2,3]
     var arr2 = [2,3,4]
     那么result = [1,2,3,4]
     * @param arr1
     * @param arr2
     * @returns {{}}
     */
    mergeArr(arr1, arr2) {
      var res = []
      var obj = {}
      if (!utils.isEmptyArr(arr1)) {
        for (var i in arr1) {
          let item = arr1[i]
          if (!obj[item]) {
            res.push(item)
            obj[item] = 1
          }
        }
      }
      if (!utils.isEmptyArr(arr2)) {
        for (var i in arr2) {
          let item = arr2[i]
          if (!obj[item]) {
            res.push(item)
            obj[item] = 1
          }
        }
      }
      return res
    },
    /**
     *合并两个对象数组
     var arr1= [object1,object2,object3]
     var arr2 = [object4,object5]
     那么result = [object1,object2,object3,object4,object5]
     * @param arr1
     * @param arr2
     * @returns {{}}
     */
    mergeObjArr(arr1, arr2) {
      var res = []
      if (!utils.isEmptyArr(arr1)) {
        for (var i in arr1) {
          let item = arr1[i]
          res.push(item)
        }
      }
      if (!utils.isEmptyArr(arr2)) {
        for (var i in arr2) {
          let item = arr2[i]
          res.push(item)
        }
      }
      return res
    },
    /**
     *  返回一个新数组，新数组本身为新对象，但里边的各个元素对象地址不变
     * @param arr  传入一个数组
     */
    getArray(arr) {
      let result = []
      for (var i in arr) {
        let item = arr[i]
        result.push(item)
      }
      return result
    },
    /**
     * 获取指定对象的索引
     * @param arr
     * @param id
     * @returns index
     */
    getIndex(arr, id) {
      let index = -1
      for (var i in arr) {
        let item = arr[i]
        if (item.id === id) {
          index = i
          break
        }
      }
      return index
    },
    isSuccess(result) {
      let flag = false
      if (result === 0) {
        flag = true
      }
      return flag
    },
    /**
     *  空串
     * @param str
     * @returns {boolean}
     */
    isEmptyStr(str) {
      let result = false
      if (str === null || str.trim() === '') {
        result = true
      }
      return result
    },
    /**
     * 是数组吗
     * @param arr
     * @returns {boolean}
     */
    isArray: function (arr) {
      return Object.prototype.toString.apply(arr) === '[object Array]'
    },
    /**
     * 是空数组吗
     * @param arr
     * @returns {boolean}
     */
    isEmptyArr: function (arr) {
      let result = true
      if (utils.isArray(arr)) {
        if (arr != null && arr.length > 0) {
          result = false
        }
      }
      return result
    },
    /**
     * 传入时间戳 传出2016-08-08 23:12:12
     * @param timestamp
     */
    getDtStr(timestamp) {
      if (timestamp === null || timestamp === undefined || timestamp === '') {
        return ''
      }
      var result = timestamp
      // 比如需要这样的格式 yyyy-MM-dd hh:mm:ss
      var date = new Date(timestamp)
      let Y = date.getFullYear() + '-'
      let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
      let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
      let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
      let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
      let s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds())
      result = Y + M + D + h + m + s
      // let m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes());
      // result = Y+M+D+h+m;
      // 输出结果：2014-04-23 18:55:49
      return result
    },
    /**
     * 传入时间戳 传出2016-08-08
     * @param timestamp
     */
    getDtStr2(timestamp) {
      if (timestamp === null || timestamp === undefined) {
        return ''
      }
      var result = timestamp
      // 比如需要这样的格式 yyyy-MM-dd hh:mm:ss
      var date = new Date(timestamp)
      let Y = date.getFullYear() + '-'
      let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
      let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
      result = Y + M + D
      // 输出结果：2014-04-23
      if(result==='NaN-NaN-NaN'){
        return '暂无'
      }
      return result
    },
  getDtStr6(timestamp) {
    if (timestamp === null || timestamp === undefined) {
      return ''
    }
    var result = timestamp
    // 比如需要这样的格式 yyyy-MM-dd hh:mm:ss
    var date = new Date(timestamp)
    let Y = date.getFullYear() + '-'
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    let D = (date.getDate() < 10 ? '0' + (date.getDate() + 1) : (date.getDate() + 1))
    console.log(D)
    result = Y + M + D
    // 输出结果：2014-04-23
    if (result === 'NaN-NaN-NaN') {
      return '暂无'
    }
    return result
  },
  getDtStr3(timestamp) {
    if (timestamp === null || timestamp === undefined || timestamp === '') {
      return ''
    }
    var result = timestamp
    // 比如需要这样的格式 yyyy-MM-dd hh:mm
    var date = new Date(timestamp)
    let Y = date.getFullYear() + '-'
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
    let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
    let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
    result = Y + M + D + h + m
    // let m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes());
    // result = Y+M+D+h+m;
    // 输出结果：2014-04-23 18:55
    return result
  },
    /**
     *  传入时间戳，传出2016
     */
    getDtYear(timestamp) {
      if (timestamp === null || timestamp === undefined || timestamp === '') {
        return null
      }
      var result = timestamp
      let date = new Date(timestamp)
      result = date.getFullYear()
      return result
    },
    /**
     * 传入时间戳 传出2016-08-08 23:12
     * @param timestamp
     */
    getDtStr4(timestamp) {
      if (timestamp === null || timestamp === undefined) {
        return ''
      }
      var result = timestamp
      // 比如需要这样的格式 yyyy-MM-dd hh:mm:ss
      var date = new Date(timestamp)
      let Y = date.getFullYear() + '-'
      let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
      let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
      let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
      let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
      result = Y + M + D + h + m
      // 输出结果：2014-04-23 18:55
      return result
    },
    /**
     * 根据路径获取菜单ID
     * @param allMenus
     * @param path
     * @returns {*}
     */
    getMenuIdByPath(allMenus, path) {
      for (let i in allMenus) {
        let item = allMenus[i]
        for (let j in item.model_menus) {
          let item2 = item.model_menus[j]
          if (item2.menu_path === path) {
            return item2.menu_id
          }
        }
      }
      return 0
    },
    /**
     * 根据路径获取菜单ID
     * @param allMenus
     * @param menuId
     * @returns {*}
     */
    getMenuPathByMenuId(allMenus, menuId) {
      if (menuId === 0) {
        return '/hello'
      }
      for (let i in allMenus) {
        let item = allMenus[i]
        for (let j in item.model_menus) {
          let item2 = item.model_menus[j]
          if (item2.menu_id === menuId) {
            return item2.menu_path
          }
        }
      }
      return '/hello'
    },
    /**
     * 获取请求数据
     * @param menuId
     * @returns {{menu_id: *}}
     */
    getRequestData(menuId) {
      let data = this.getBaseRequestData()
      data.menu_id = menuId
      return data
    },
  getGlobalRequestData(menuId) {
    let data = this.getGlobalBaseData()
    data.menu_id = menuId;
    return data
  },
  getGlobalBaseData() {
    let data = {'menu_id': 0, 'sid': '', 'user_id': 0, 'ofid': 0, 'request_data': ''}
    data.sid = localStorage.user.sid;
    data.user_id = localStorage.user.uid;
    return data
  },
    /**
     * 获取请求数据
     * @param menuId
     * @param requestData
     * @returns {{menu_id: *, request_data: *}}
     */
    getRequestData(menuId, requestData) {
      // console.log("21:"+JSON.stringify(requestData));
      // if(requestData!=undefined && requestData['dt']){
      //   console.log("type:"+typeof requestData['dt']+" value:"+requestData['dt']);
      // }

      let req = JSON.stringify(requestData, utils.dtReplacer)
      // console.log("2:"+req);
      let data = this.getBaseRequestData()
      data.menu_id = menuId
      data.request_data = req
      return data
    },
    dtReplacer: function (key, value) {
      if (key === 'dt' || key === 'startDt' || key === 'endDt') {
        // console.log("type:"+typeof value+" value:"+value);
        return utils.getDtStr(value)
      }
      return value
    },
    getBaseRequestData() {
      let data = {'menu_id': 0, 'sid': '', 'user_id': 0, 'ofid': 0, 'request_data': ''}
      return this.clone(data)
    },
    /**
     * 根据菜单ID返回模块ID
     * @param menuId
     */
    getModelIdByMenuId(menuId) {
      let modelId = menuId % 1000
      return modelId
    },
    /**
     * 根据菜单ID获取菜单名称
     * @param menuId
     */
    getModelNameByMenuId(allMenus, menuId) {
      let modelId = this.getModelIdByMenuId(menuId)
      for (let i in allMenus) {
        let item = allMenus[i]
        if (item.model_id === modelId) {
          return item.model_name
        }
      }
      return ''
    },
    /**
     * 根据菜单ID获取所有上层菜单名称
     * @param allMenus
     * @param menuId
     */
    getMenuNamesByMenuId(allMenus, menuId) {
      let menus = []
      let menu = this.getMenu(allMenus, menuId)
      menus.push(menu.menu_name)
      while (menu.menu_pid !== 0) {
        menu = this.getMenu(allMenus, menu.menu_pid)
        menus.push(menu.menu_name)
      }
      return menus.reverse()
    },
    /**
     * 获取菜单对象
     * @param allMenus
     * @param menuId
     * @returns {*}
     */
    getMenu(allMenus, menuId) {
      let modelId = this.getModelIdByMenuId(menuId)
      let menus = this.getModelMenus(allMenus, modelId)
      for (let i in menus) {
        let item = menus[i]
        if (item.menu_id === menuId) {
          return item
        }
      }
      return null
    },
    /**
     *  获取某个模块的所有菜单
     * @param allMenus
     * @param modelId
     * @returns {Array}
     */
    getModelMenus(allMenus, modelId) {
      let menus = []
      for (let i in allMenus) {
        let item = allMenus[i]
        // console.log("utils.js:getModelMenus:+++++++++++++++++"+item.model_id+"++++++++")
        if (item.model_id === modelId) {
          menus = item.model_menus
          break
        }
      }
      return menus
    },
    /**
     * 查看是否有对应模块某些菜单的权限
     * @param allMenus
     * @param menuIds
     * @returns {boolean}
     */
    getHiddenButtons(allMenus, menuIds) {
        for(let i=0;i<allMenus.length;i++){
          if(allMenus[i]===menuIds){
            return false;
          }

        }
      return true;
    },
    /**
     * 按模块顺序 获得所有有访问路径的菜单
     * @param allMenus
     * @param models
     */
    getAllPathMenus(allMenus, models) {
      let menus = []
      for (let k in models) {
        let modelMenus = this.getModelMenus(allMenus, models[k])
        for (let i in modelMenus) {
          let item = modelMenus[i]
          if (item.menu_path !== undefined && item.menu_path !== '') {
            menus.push(item)
          }
        }
      }
      return menus
    },

    /**
     * 登出
     */
    logout() {
      localStorage.clear()
    },
    /**
     * 根据ID获取名字
     * @param id
     * @param nameInfo
     * @param name
     */
    getName(id, nameInfo, name) {
      let result = id
      for (var i in nameInfo) {
        let item = nameInfo[i]
        if (item.id === id) {
          result = item[name]
          break
        }
      }
      return result
    },
    /**
     * 根据ID获取名字
     * @param ids
     * @param nameInfo
     * @param name
     */
    getNames(ids, nameInfo, name) {
      let result = ''
      if (utils.isEmptyStr(ids)) {
        return ids
      }
      let idArr = ids.split(',')
      if (utils.isEmptyArr(idArr)) {
        return ids
      }
      for (var k in idArr) {
        let id = idArr[k]
        let flag = false
        for (var i in nameInfo) {
          let item = nameInfo[i]
          if (item.id === id) {
            flag = true
            result = result + item[name] + ' | '
            break
          }
        }
        if (!flag) {
          result = result + id + ' | '
        }
      }
      return result.substr(0, result.length - 3)
    },
    /**
     * 将整数数组转换成逗号分隔的字符串
     * @param ids
     */
    getStrByArray(idArr) {
      let result = ''
      if (utils.isEmptyArr(idArr)) {
        return result
      }
      for (var k in idArr) {
        result = result + idArr[k] + ','
      }
      return result.substr(0, result.length - 1)
    },
    /**
     * 将逗号分隔的字符串转成数组
     * @param str
     * @returns {*}
     */
    getArrByStr(ids) {
      if (utils.isEmptyStr(ids)) {
        return ids
      }
      let res = []
      let arr = ids.split(',')
      for (var i in arr) {
        res.push(parseInt(arr[i]))
      }
      return res
    },
    /**
     * 将金额转换成中文显示
     * @param money
     * @returns {string}
     */
    /** 数字金额大写转换(可以处理整数,小数,负数) */
    smalltoBIG(n) {

      return this.convertCurrency(n);
    },
    /**
     * 时间范围对象list转成开始和结束时间串
     * 例 [new Date(),new Date()] ==>
     * {"startDt":'2017-03-23',"endDt":'2017-03-23' }
     */
    convertCurrency(money) {
      //汉字的数字
      var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
      //基本单位
      var cnIntRadice = new Array('', '拾', '佰', '仟');
      //对应整数部分扩展单位
      var cnIntUnits = new Array('', '万', '亿', '兆');
      //对应小数部分单位
      var cnDecUnits = new Array('角', '分', '毫', '厘');
      //整数金额时后面跟的字符
      var cnInteger = '整';
      //整型完以后的单位
      var cnIntLast = '元';
      //最大处理的数字
      var maxNum = 999999999999999.9999;
      //金额整数部分
      var integerNum;
      //金额小数部分
      var decimalNum;
      //输出的中文金额字符串
      var chineseStr = '';
      //分离金额后用的数组，预定义
      var parts;
      if (money == '') {
        return '';
      }
      money = parseFloat(money);
      if (money >= maxNum) {
        //超出最大处理数字
        return '';
      }
      if (money == 0) {
        chineseStr = cnNums[0] + cnIntLast + cnInteger;
        return chineseStr;
      }
      //转换为字符串
      money = money.toString();
      if (money.indexOf('.') == -1) {
        integerNum = money;
        decimalNum = '';
      } else {
        parts = money.split('.');
        integerNum = parts[0];
        decimalNum = parts[1].substr(0, 4);
      }
      //获取整型部分转换
      if (parseInt(integerNum, 10) > 0) {
        var zeroCount = 0;
        var IntLen = integerNum.length;
        for (var i = 0; i < IntLen; i++) {
          var n = integerNum.substr(i, 1);
          var p = IntLen - i - 1;
          var q = p / 4;
          var m = p % 4;
          if (n == '0') {
            zeroCount++;
          } else {
            if (zeroCount > 0) {
              chineseStr += cnNums[0];
            }
            //归零
            zeroCount = 0;
            chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
          }
          if (m == 0 && zeroCount < 4) {
            chineseStr += cnIntUnits[q];
          }
        }
        chineseStr += cnIntLast;
      }
      //小数部分
      if (decimalNum != '') {
        var decLen = decimalNum.length;
        for (var i = 0; i < decLen; i++) {
          var n = decimalNum.substr(i, 1);
          if (n != '0') {
            chineseStr += cnNums[Number(n)] + cnDecUnits[i];
          }
        }
      }
      if (chineseStr == '') {
        chineseStr += cnNums[0] + cnIntLast + cnInteger;
      }
      else if (decimalNum == '') {
        chineseStr += cnInteger;
      }
      return chineseStr;
    },

  timeToObject: function (dtList) {
      let to = {startDt: null, endDt: null}
      if (dtList.length > 0) {
        if (dtList[0] == null) {
          to.startDt = null
          to.endDt = null
        } else {
          to.startDt = dtList[0]
          to.endDt = dtList[1]
        }
      }
      return to
    },
    /**
     * 今天前几天的日期
     * @param days
     * return "2017-03-23 00:00:00"
     */
    getBeforeTime: function (days) {
      return this.getDateBefore(new Date(), days)
    },
    /**
     * 获取某日期的前几天的时间
     * dt: new Date()
     * day: 日期的前几天
     */
    getDateBefore: function (dt, day) {
      dt.setHours(0)
      dt.setMinutes(0)
      dt.setSeconds(0)
      dt.setTime(dt.getTime() - 3600 * 1000 * 24 * day)
      return dt
    },
    getRequest: function () {
      let url = location.search // 获取url中"?"符后的字串
      let theRequest = new Object()
      if (url.indexOf('?') !== -1) {
        let str = url.substr(1)
        if (str.indexOf('&') !== -1) {
          let strs = str.split('&')
          for (let i = 0; i < strs.length; i++) {
            theRequest[strs[i].split('=')[0]] = unescape(strs[i].split('=')[1])
          }
        } else {
          theRequest[str.split('=')[0]] = unescape(str.split('=')[1])
        }
      }
      return theRequest
    },
    /**
     * 对象是否为空、或者未定义
     */
    isNullUndefined(obj) {
      return obj == null || obj === undefined
    },
    /**
     * 检测数值的key是否是该对象中全部包含
     * @constructor
     */
    CheckObjKey(array, obj) {
      if (this.isNullUndefined(obj) || this.isEmptyArr(array)) {
        return false
      }
      for (let i in array) {
        if (!obj.hasOwnProperty(array[i])) {
          return false
        }
      }
      return true
    }
}

export default utils
