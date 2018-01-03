package com.gameley.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.gameley.common.utils.vo.UserInfo;
import com.gameley.entity.User;
import com.gameley.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WWMXD
 * @since 2018-01-03 14:39:38
 */

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "validate", method = RequestMethod.POST)
    public UserInfo validate(@RequestParam("username") String username, @RequestParam("password") String password){
        User user=userService.selectOne(Condition.create().eq("username",username).eq("password",password));
        UserInfo userInfo=new UserInfo();
        userInfo.setId(String.valueOf(user.getId()));
        userInfo.setName(user.getUsername());
        return userInfo;

    }
	
}
