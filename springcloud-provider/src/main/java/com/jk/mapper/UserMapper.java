package com.jk.mapper;

import com.jk.model.FangJianModel;
import com.jk.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {

    // 注册账号
    void zhucheUser(UserModel userModel);

    // 注册 根据手机号查询
    UserModel queryUserName(String userPhone);

    // 登陆 查询 用户
    UserModel queryUserAccount(String userAccount);


    // 推荐 信息
    List<FangJianModel> Fangtuijian();

    // 推荐 详细 信息
    FangJianModel tuijianxianqing(Integer fangId);

    // 根据房子ID 查询图片
    List<String> queryFangImg(Integer fangId);
    /**
     *  用户信息展示
     * @return
     */
    UserModel userxinxi(Integer userId);

    // 修改 昵称
    void updateNiCheng(@Param("userxiugai") String userxiugai,@Param("userId")  Integer userId);
    // 修改 Mail
    void updateMail(@Param("userxiugai")String userxiugai,@Param("userId")  Integer userId);
    // 修改 Phone
    void updatePhone(@Param("userxiugai")String userxiugai,@Param("userId")  Integer userId);



}
