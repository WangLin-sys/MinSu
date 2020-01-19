package com.jk.controller;

import com.jk.service.HTService;
import com.jk.util.CheckImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("ht")
public class HTController {

    @Autowired
    private HTService htService;

    /**
     *  图片 验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("gainCode")
    @ResponseBody
    public void gainCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        CheckImgUtil.checkImg(request, response);
    }

    /**
     *  登陆
     * @param userModel
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(UserModel userModel, HttpServletRequest request){

        //验证验证码
      /*  String codeName = request.getSession().getAttribute("checkcode").toString();
        if(!userModel.getCode().equalsIgnoreCase(codeName)){
            return "验证码错误！";
        }*/
        String userAccount = userModel.getUserAccount();
        UserModel user = userService.userlogin(userModel);
        //验证用户名
        if(user==null){
            return "用户名不存在！";
        }
        //验证密码
        if(!userModel.getUserPasw().equals(user.getUserPasw())){
            return "密码错误！";
        }
        request.getSession().setAttribute("user",user);
        return "登录成功！";
    }

    /**
     * 注销 账号
     * @param request
     */
    @RequestMapping("zhuxiao")
    public String login(HttpServletRequest request){
        request.getSession().removeAttribute("user");

        return "redirect:/tz/MinShuShouYe";
    }









}
