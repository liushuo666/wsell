package com.liushuo.wsell.controller;

import com.alibaba.fastjson.JSONObject;
import com.liushuo.wsell.dataobject.wxobject.NomalKeys;
import com.liushuo.wsell.dataobject.UserInfo;
import com.liushuo.wsell.dataobject.wxobject.SubButton;
import com.liushuo.wsell.dataobject.wxobject.WButton;
import com.liushuo.wsell.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;


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
        getAccessToken();
        List<WButton> buttonList = combineMenu();
        System.out.println(buttonList.toString());
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("button",buttonList);
        String button = jsonObject.toJSONString();
        System.out.println(NomalKeys.ACCESS_TOKEN);
        String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",NomalKeys.ACCESS_TOKEN);
        System.out.println(button);
        String code= CommonUtil.sendPost(url,button);
        System.out.println(code);
    }
    /**
     * 获取普通的access_token*/
    public void getAccessToken(){
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                NomalKeys.APPID,NomalKeys.APPSECRET);
        log.info(url);
        net.sf.json.JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);
        NomalKeys.ACCESS_TOKEN = jsonObject.getString("access_token");
    }

    /**
     * 获取accessToken和openid
     * */
    public Map<String,String> getAccesstekonAndopenid(String code){
        Map<String ,String> map =new HashMap<>();
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                NomalKeys.APPID,NomalKeys.APPSECRET,code);
        log.info(url);
        net.sf.json.JSONObject jsonObject= CommonUtil.httpsRequest(url, "GET", null);
        log.info("打印json："+jsonObject.toString());
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        map.put("access_token",accessToken);
        map.put("openid",openid);
        log.info("获得accessToken为：{}，获得openid 为：{}",accessToken,openid);
        return map;
    }

    /** 获取用户信息*/
    public UserInfo getUserInfo(Map<String,String> map){
        UserInfo userInfo = new UserInfo();
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN",map.get("access_token"),map.get("openid"));
        log.info(url);
        net.sf.json.JSONObject jsonObject= CommonUtil.httpsRequest(url, "GET", null);
        log.info("用户信息json为："+jsonObject.toString());
        userInfo.setOpenid(jsonObject.getString("openid"));
        userInfo.setNickname(jsonObject.getString("nickname"));
        userInfo.setSex(jsonObject.getString("sex"));
        userInfo.setCity(jsonObject.getString("city"));
        userInfo.setProvince(jsonObject.getString("province"));
        userInfo.setCountry(jsonObject.getString("country"));
        userInfo.setPrivilege(jsonObject.getString("privilege"));
        userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
        log.info("获取到了用户信息");
        return  userInfo;
    }

    /**
     * 组装菜单
     * */
    public List<WButton> combineMenu(){
        List<WButton> buttonList = new ArrayList<>();
        WButton wButton = new WButton();
        wButton.setName("美食");
        wButton.setType("click");
        wButton.setKey("food");
        wButton.setSub_button(firstMenu());

        WButton wButton1 = new WButton();
        wButton1.setName("畅游");
        wButton1.setType("click");
        wButton1.setKey("play");
        wButton1.setSub_button(secondMenu());

        WButton wButton2= new WButton();
        wButton2.setName("更多内容");
        wButton2.setType("click");
        wButton2.setKey("others");
        wButton2.setSub_button(firstMenu());

        buttonList.add(wButton);
        buttonList.add(wButton1);
        buttonList.add(wButton2);
        return buttonList;
    }

    public List<SubButton> firstMenu(){
        List<SubButton> subButtonList = new ArrayList<>();

        SubButton subButton = new SubButton();
        subButton.setType("view");
        subButton.setName("今日首选");
        subButton.setUrl("https://www.baidu.com/");

        SubButton subButton1 = new SubButton();
        subButton1.setType("view");
        subButton1.setName("热卖畅销");
        subButton1.setUrl("https://www.baidu.com/");

        subButtonList.add(subButton);
        subButtonList.add(subButton1);
        return subButtonList;
    }

    public List<SubButton> secondMenu(){
        List<SubButton> subButtonList = new ArrayList<>();
        SubButton subButton = new SubButton();
        subButton.setType("view");
        subButton.setName("深圳旅游攻略");
        subButton.setUrl("https://www.baidu.com/");

        SubButton subButton1 = new SubButton();
        subButton1.setType("view");
        subButton1.setName("酒店住宿");
        subButton1.setUrl("https://www.baidu.com/");

        subButtonList.add(subButton);
        subButtonList.add(subButton1);
        return subButtonList;
    }
}
