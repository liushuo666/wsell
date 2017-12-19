package com.liushuo.wsell.controller;

import com.liushuo.wsell.dataobject.NomalKeys;
import com.liushuo.wsell.dataobject.UserInfo;
import com.liushuo.wsell.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Create by liushuo on 2017/12/13.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WinXinController {

    @RequestMapping(value = "/auths",method = RequestMethod.GET)
    public String checkToken(HttpServletRequest request, HttpServletResponse response){
        log.info("请求进来了...");
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature =request.getParameter("signature");
        // 时间戳
        String timestamp =request.getParameter("timestamp");
        // 随机数
        String nonce =request.getParameter("nonce");
        // 随机字符串
        String echostr =request.getParameter("echostr");

        String token = "3.1415926";

        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);

        Collections.sort(list);

        return  echostr;
//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
//            out.print(echostr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            out.close();
//
//        }
    }

    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public void auth(@RequestParam("code") String code,HttpServletResponse response) throws IOException {
        log.info("开始获取code信息：{}",code);
        Map<String, String> accesstekonAndopenid = getAccesstekonAndopenid(code);
        UserInfo userInfo = getUserInfo(accesstekonAndopenid);
        System.out.println(userInfo.toString());
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print(userInfo);
    }
    @RequestMapping(value = "setMenu",method = RequestMethod.GET)
    public void setMenu(){

    }

    public void getAccessToken(){

    }

    /**
     * 获取accessToken和openid
     * */
    public Map<String,String> getAccesstekonAndopenid(String code){
        Map<String ,String> map =new HashMap<>();
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                NomalKeys.APPID,NomalKeys.APPSECRET,code);
        log.info(url);
        JSONObject jsonObject= CommonUtil.httpsRequest(url, "GET", null);
        log.info("打印json："+jsonObject.toString());
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        map.put("access_token",accessToken);
        map.put("openid",openid);
        log.info("获得accessToken为：{}，获得openid 为：{}",accessToken,openid);
        return map;
    }
    public UserInfo getUserInfo(Map<String,String> map){
        UserInfo userInfo = new UserInfo();
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN",map.get("access_token"),map.get("openid"));
        log.info(url);
        JSONObject jsonObject= CommonUtil.httpsRequest(url, "GET", null);
        log.info("用户信息json为："+jsonObject.toString());
        userInfo.setOpenid(jsonObject.getString("openid"));
        userInfo.setNickname(jsonObject.getString("nickname"));
        userInfo.setSex(jsonObject.getString("sex"));
        userInfo.setCity(jsonObject.getString("city"));
        userInfo.setProvince(jsonObject.getString("province"));
        userInfo.setCountry(jsonObject.getString("country"));
        userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
        userInfo.setPrivilege(jsonObject.getString("privilege"));
        log.info("获取到了用户信息");
        return  userInfo;
    }


}
