package com.gameley.feign;



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
public interface ElementService {
  @RequestMapping(value = "/element/permission", method = RequestMethod.POST)
  public Integer getpermission(String uri);
}
