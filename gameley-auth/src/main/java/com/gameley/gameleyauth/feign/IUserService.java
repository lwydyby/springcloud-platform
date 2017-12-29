package com.gameley.gameleyauth.feign;


import com.gameley.common.utils.vo.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * ${DESCRIPTION}
 *
 * @author lw
 * @create 2017-12-25
 */
@FeignClient(value = "user-service")
public interface IUserService {
  @RequestMapping(value = "/user/validate", method = RequestMethod.POST)
  public UserInfo validate(@RequestParam("username") String username, @RequestParam("password") String password);
}
