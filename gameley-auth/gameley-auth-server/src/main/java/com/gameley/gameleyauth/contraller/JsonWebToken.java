package com.gameley.gameleyauth.contraller;


import com.gameley.common.msg.ObjectRestResponse;
import com.gameley.common.msg.auth.TokenErrorResponse;
import com.gameley.common.utils.vo.UserInfo;
import com.gameley.gameleyauth.bean.Audience;
import com.gameley.gameleyauth.feign.IUserService;
import com.gameley.gameleyauth.utils.Des;
import com.gameley.gameleyauth.utils.JwtHelper;
import com.gameley.gameleyauth.utils.ResultMsg;
import com.gameley.gameleyauth.utils.ResultStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Object getAccessToken(@RequestBody UserInfo userInfo)
    {

        ResultMsg resultMsg = null;
        try
        {
            String password = Des.strDec(userInfo.getPassword(),"xmob","X","MOB");
            UserInfo user=iUserService.validate(userInfo.getUsername(),password);
            if(user.getName()!=null){
                //拼装accessToken
                String accessToken = JwtHelper.createJWT(user.getName(), user.getId()
                        , audienceEntity.getClientId(), audienceEntity.getName(),
                        audienceEntity.getExpiresSecond() * 1000, audienceEntity.getBase64Secret());
                user.setToken(accessToken);
                ObjectRestResponse response=new ObjectRestResponse<UserInfo>();
                return response.data(user);


            }else {
               return new TokenErrorResponse("用户名或密码错误");
            }





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
