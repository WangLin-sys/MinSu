package com.jk.controller;

import com.jk.model.FangJianModel;
import com.jk.model.UserModel;
import com.jk.service.UserServer;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController implements UserServer {

    @GetMapping("test")
    @Override
    public void test(String userName) {

        System.out.println("接收成功get"+userName);
    }

    @PostMapping("test")
    @Override
    public void test2(String userName) {

        System.out.println("接收成功post"+userName);
    }


    @Autowired
    private UserService userService;

    /** =================================================================
     * 注册帐号
     * @param userModel
     * @return
     */
    @PostMapping("zhuche")
    @Override
    public String zhucheUser( UserModel userModel) {

        return userService.zhucheUser(userModel);
    }

    /**
     * @return发送验证码
     */
    @GetMapping("yanzhengma")
    @Override
    public String yzmcoud(String userPhone) {

        return userService.yzmcoud(userPhone);
    }
    // 登陆
    @PostMapping("login")
    @Override
    public UserModel userlogin(UserModel userModel) {

        return userService.userlogin(userModel);
    }
    // 推荐 信息
    @GetMapping("Fangtuijian")
    @Override
    public HashMap<String, Object> Fangtuijian() {

        return userService.Fangtuijian();
    }

    // 推荐 详细 信息
    @GetMapping("tuijianxianqing")
    @Override
    public FangJianModel tuijianxianqing(Integer fangId) {

        return userService.tuijianxianqing(fangId);
    }
    // 推荐 详细 信息 图片
    @GetMapping("queryFangImg")
    @Override
    public List<String> queryFangImg(Integer fangId) {

        return userService.queryFangImg(fangId);
    }


}
