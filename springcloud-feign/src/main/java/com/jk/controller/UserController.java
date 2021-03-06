package com.jk.controller;

import com.jk.model.FangJianModel;
import com.jk.model.UserModel;
import com.jk.service.UserService;
import com.jk.util.CheckImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("test")
    public void test(String userName){

        userService.test(userName);
    }

    @PostMapping("test")
    public void test2(String userName){

        userService.test2(userName);
    }

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
    @GetMapping("login")
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
    @GetMapping("zhuxiao")
    public String login(HttpServletRequest request){
        request.getSession().removeAttribute("user");

        return "redirect:/tz/MinShuShouYe";
    }


    /**
     * 注册
     * @param  userModel
     * @return
     */
    @PostMapping("zhuche")
    @ResponseBody
    public String zhucheUser(UserModel userModel){

        return userService.zhucheUser(userModel);
    }

    /**
     * @return 短信验证码
     */
    @GetMapping("yzmcoud")
    @ResponseBody
    public String yzmcoud(String userPhone){
        return userService.yzmcoud(userPhone);
    }

    /**
     * @return 推荐 数据
     */
    @GetMapping("Fangtuijian")
    @ResponseBody
    public HashMap<String, Object> Fangtuijian(){
        return userService.Fangtuijian();
    }

    /**
     *  推荐 数据 详情 回显
     * @param fangId
     * @param model
     * @return
     */
    @GetMapping("tuijianxianqing")
    public String tuijianxianqing(Integer fangId, HttpServletRequest request){

        FangJianModel fangJianModel = userService.tuijianxianqing(fangId);
        request.getSession().setAttribute("fang",fangJianModel);
        if(fangJianModel !=null){
            return "MinShuYeMian/xiangqing";
        }
            return "网络错误";
    }
    // 查询房间图片
    @GetMapping("queryFangImg")
    @ResponseBody
    public List<String> queryFangImg(HttpServletRequest request){
        FangJianModel fang = (FangJianModel) request.getSession().getAttribute("fang");
        Integer fangId = fang.getFangId();
        List<String> imgUil = userService.queryFangImg(fangId);
        return imgUil;
    }

    /**
     *  用户信息展示
     * @param request
     * @return
     */
    @GetMapping("userxinxi")
    @ResponseBody
    public UserModel userxinxi(HttpServletRequest request){
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        UserModel userModel = userService.userxinxi(userId);
        return userModel;
    }

    /**
     * 修改 名称
     * @param userxiugai
     * @param usersize
     */
    @GetMapping("updateUser")
    @ResponseBody
    public void updateUser(String userxiugai,String usersize,HttpServletRequest request){

        UserModel user = (UserModel) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();

        userService.updateUser(userxiugai,usersize,userId);
    }

    // 房屋详情 可订 日期 功能未写
    @PostMapping("yudingdate")
    public String yudingdate(String startDate,String endDate,Integer NumDate){

        System.out.println();
        return "";
    }


    /**
     * 查询 房屋房东信息
     * @param fangId 房Id
     * @return
     */
    @GetMapping("queryFangZhuXX")
    @ResponseBody
    public UserModel queryFangZhuXX(String fangId){

        return  userService.queryFangZhuXX(fangId);
    }


}
