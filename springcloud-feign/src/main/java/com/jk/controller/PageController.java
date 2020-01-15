package com.jk.controller;

import com.jk.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("tz")
public class PageController {


    /**
     * @return   去 登陆页面  X
     */
    @RequestMapping("login")
    public String login(){

        return "UserHtml/login";
    }
    /**
     * @return   去 登陆页面
     */
    @RequestMapping("login1")
    public String login1(){

        return "UserHtml/login1";
    }

    /**
     * @return   去 注册 页面
     */
    @RequestMapping("zhuche")
    public String zhuche(){

        return "UserHtml/zhuche";
    }

    /**
     * @return   去 首页
     */
    @RequestMapping("MinShuShouYe")
    public String MinShuShouYe(HttpServletRequest request){

        UserModel user = (UserModel) request.getSession().getAttribute("user");

        if(user == null ){
            UserModel userModel = new UserModel();
            userModel.setUserNiCheng("未登录");
            request.getSession().setAttribute("user",userModel);
        }
        return "MinShuYeMian/shouye";
    }

    /**
     * @return   出租房源
     */
    @RequestMapping("ChuZhuFangYuan")
    public String ChuZhuFangYuan(){

        return "MinShuYeMian/ChuZhuFangYuan";
    }

    /**
     * @return   去 个人中心
     */
    @RequestMapping("userZhongXin")
    public String userZhongXin(HttpServletRequest request){

        UserModel user = (UserModel) request.getSession().getAttribute("user");

        if(user == null ){
            UserModel userModel = new UserModel();
            userModel.setUserNiCheng("未登录");
            request.getSession().setAttribute("user",userModel);
        }

        return "UserHtml/userZhongXin";
    }

    /**
     * @return  个人信息
     */
    @RequestMapping("userGeRenXinXI")
    public String userGeRenXinXI(){

        return "UserHtml/userGeRenXinXI";
    }

    /**
     *  地图
     * @return
     */
    @RequestMapping("contact")
    public String contact(){

        return "contact";
    }

}
