package com.gameley.web;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gameley.common.utils.vo.UserInfo;
import com.gameley.entity.BaseUser;
import com.gameley.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



/**
 * 登录验证
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BaseUserService baseUserService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @GetMapping("/hi")
    public String hi(){
        return "hello world";
    }

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public UserInfo validate(String username,String password){
        UserInfo user=new UserInfo();
//
        int count=baseUserService.selectCount(Condition.create().eq("username",username));
//                .eq("password",encoder.encode(password)));
        if(count==1){
            user.setDescription("正确");
        }else{
            user.setDescription("错误");
        }
        return user;
    }

}
