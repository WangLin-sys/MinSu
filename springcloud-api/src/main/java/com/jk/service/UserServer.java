package com.jk.service;

import com.jk.model.FangJianModel;
import com.jk.model.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface UserServer {

    @GetMapping("user/test")
    void test(@RequestParam("userName") String userName);

    @PostMapping("user/test")
    void test2(@RequestParam("userName") String userName);

    // 注册
    @PostMapping("user/zhuche")
    String zhucheUser(@RequestBody UserModel userModel);

    // 验证码
    @GetMapping("user/yanzhengma")
    String yzmcoud(@RequestParam("userPhone") String userPhone);

    //  登陆
    @PostMapping("user/login")
    UserModel userlogin(@RequestBody UserModel userModel);
    // 推荐信息
    @GetMapping("user/Fangtuijian")
    HashMap<String, Object> Fangtuijian();
    // 推荐详细信息
    @GetMapping("user/tuijianxianqing")
    FangJianModel tuijianxianqing(@RequestParam("fangId")Integer fangId);
    // 推荐详细信息图片
    @GetMapping("user/queryFangImg")
    List<String> queryFangImg(@RequestParam("fangId") Integer fangId);

    /**
     *  用户信息展示
     */
    @GetMapping("user/userxinxi")
    UserModel userxinxi(@RequestParam("userId")Integer userId);

    /**
     *  修改 名称
     */
    @GetMapping("user/updateUser")
    void updateUser(@RequestParam("userxiugai") String userxiugai,@RequestParam("usersize") String usersize,@RequestParam("userId") Integer userId);
    /**
     * 查询 房屋房东信息
     * @param fangId 房Id
     * @return
     */
    @GetMapping("user/queryFangZhuXX")
    UserModel queryFangZhuXX(@RequestParam("fangId") String fangId);
}
