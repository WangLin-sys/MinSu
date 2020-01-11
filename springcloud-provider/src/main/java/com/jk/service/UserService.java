package com.jk.service;

import com.jk.model.FangJianModel;
import com.jk.model.UserModel;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    String zhucheUser(UserModel userModel);

    String yzmcoud(String userPhone);

    UserModel userlogin(UserModel userModel);

    HashMap<String, Object> Fangtuijian();

    FangJianModel tuijianxianqing(Integer fangId);

    List<String> queryFangImg(Integer fangId);
}
