package com.gameley.gameleyauth.contraller;


import com.gameley.common.utils.vo.UserInfo;
import com.gameley.gameleyauth.bean.Audience;
import com.gameley.gameleyauth.feign.IUserService;
import com.gameley.gameleyauth.utils.JwtHelper;
import com.gameley.gameleyauth.utils.ResultMsg;
import com.gameley.gameleyauth.utils.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

/**
 * 登录验证
 *
 */
@RestController
@EnableConfigurationProperties(Audience.class)
public class JsonWebToken {


    @Autowired
    private Audience audienceEntity;
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "oauth/token",method = RequestMethod.POST)
    public Object getAccessToken(@RequestParam String username, @RequestParam String password)
    {
        ResultMsg resultMsg = null;
        try
        {
            UserInfo user=iUserService.validate(username,password);
            if("正确".equals(user.getDescription())){
                //拼装accessToken
                String accessToken = JwtHelper.createJWT("admin", "1"
                        , audienceEntity.getClientId(), audienceEntity.getName(),
                        audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());
               resultMsg=new ResultMsg(200,"登陆成功",accessToken);


            }else {
                resultMsg=new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
                        ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
            }




            return resultMsg;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(),
                    ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);
            return resultMsg;
        }
    }
}
