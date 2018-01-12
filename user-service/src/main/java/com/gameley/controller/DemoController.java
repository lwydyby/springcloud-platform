package com.gameley.controller;


import com.gameley.common.util.MapUtil;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.gameley.service.DemoService;
import com.gameley.common.msg.BaseResponse;
import com.gameley.common.msg.ObjectRestResponse;
import com.alibaba.fastjson.JSONObject;
import com.gameley.common.constant.RestCodeConstants;
import com.gameley.common.msg.ListRestResponse;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.Condition;
import com.gameley.entity.Demo;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-12 10:59:07
 */

@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController {

	@Autowired
    private DemoService demoService;

    @ApiOperation(value ="增加", httpMethod ="POST")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public BaseResponse addDemo(@RequestBody Demo demo){
        Boolean flag=demoService.insert(demo);
        if(flag){
            return new BaseResponse(200,"创建成功");
        }else {
            return new BaseResponse(RestCodeConstants.ADD_ERROR_CODE,"创建失败");
        }

    }

    @ApiOperation(value ="编辑", httpMethod ="POST")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public BaseResponse editDemo(@RequestBody Demo demo){
        Boolean flag=demoService.updateById(demo);
        if(flag){
            return new BaseResponse(200,"编辑成功");
        }else {
            return new BaseResponse(RestCodeConstants.EDIT_ERROR_CODE,"编辑失败");

        }

    }

    @ApiOperation(value ="删除", httpMethod ="POST")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResponse deleteDemo(@RequestBody Integer id){
        Boolean flag=demoService.deleteById(id);
        if(flag){
            return new BaseResponse(200,"删除成功");
        }else {
            return new BaseResponse(RestCodeConstants.DELETE_ERROR_CODE,"删除失败");
        }

    }

    @ApiOperation(value ="查询所有", httpMethod ="POST")
    @RequestMapping(value = "getall", method = RequestMethod.POST)
    public ListRestResponse getAllDemo(){
        ListRestResponse listRestResponse=new ListRestResponse<>();
        List<Demo> allinfos=demoService.selectList(Condition.EMPTY);
        listRestResponse.setResult(allinfos);
        listRestResponse.setCount(allinfos.size());
        listRestResponse.setMsg("所有信息");
        return listRestResponse;

    }
    @ApiOperation(value ="查询单页", httpMethod ="POST")
    @RequestMapping(value = "getpage", method = RequestMethod.POST)
    public ListRestResponse getPageDemo(@RequestBody JSONObject jsonObject){
        Page<Demo> page=new Page(jsonObject.getInteger("current"),jsonObject.getInteger("size"));
        Map<String,Object> queryInfos=MapUtil.changeToMap(jsonObject,"id","time");
        ListRestResponse listRestResponse=new ListRestResponse<>();
        Page<Demo> pageInfo=demoService.selectPage(page);
        int count=demoService.selectCount(Condition.create().allEq(queryInfos));
        List<Demo> allinfos=pageInfo.getRecords();
        listRestResponse.setResult(allinfos);
        listRestResponse.setCount(count);
        listRestResponse.setMsg("所有信息");
        return listRestResponse;

    }





}
