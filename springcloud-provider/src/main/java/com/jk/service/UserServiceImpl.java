package com.jk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.UserMapper;
import com.jk.model.FangJianModel;
import com.jk.model.UserModel;
import com.jk.util.CheckSumBuilder;
import com.jk.util.CommonConf;
import com.jk.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String ,Object> redisTemplate;


    /**
     * 注册账号
     * @param userModel
     * @return
     */
    @Override
    public String zhucheUser(UserModel userModel) {

        String phone = userModel.getUserPhone();
        //1、判断手机号存在
        UserModel user =  userMapper.queryUserName(phone);
        if(user == null){

            //2、判断验证码是否存在：有没有获取过、有没有过期
            String key = CommonConf.MEG_CODE+phone;
            Boolean flag = redisTemplate.hasKey(key);
            if(!flag){
                return "重新获取验证码！";
            }
            //3、判断验证码是否一致
            String redisCode = redisTemplate.opsForValue().get(key).toString();

        if(!redisCode.equals(userModel.getCode())){
            return "验证码错误！";
        }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String number = UUID.randomUUID().toString().substring(1,6);

            String Account = number + sdf.format(date);
            userModel.setUserAccount(Account);
            userMapper.zhucheUser(userModel);

            redisTemplate.delete(key);   // 注销验证码
            return "注册成功,你的账号是  "+Account;
        }
            return "手机号错误,请更换手机号";
    }

    /**
     * @return 发送验证码
     */
    @Override
    public String yzmcoud(String userPhone) {
//1、判断手机号是否存在
        UserModel user = userMapper.queryUserName(userPhone);

        if(user == null){

            //2、a、生成随机数  6位  999999 100000
            long round = Math.round((Math.random()*899999+100000));
            System.out.println("随机数："+round);

//            TODO b、调用短信接口发送短信
//            String url = "https://api.netease.im/sms/sendcode.action";
//            HashMap<String, Object> params = new HashMap<String, Object>();
//
//            params.put("mobile", userPhone);//手机号
//            params.put("templateid", 14843179);//模板编号(如不指定则使用配置的默认模版)
//            params.put("authCode", round);//客户自定义验证码，长度为4～10个数字
//
//            //放header中的公共参数
//            HashMap<String, Object> headerParam = new HashMap<String, Object>();
//            headerParam.put("AppKey", CommonConf.APP_KEY);//开发者平台分配的appkey
//            String nonce = UUID.randomUUID().toString();
//            headerParam.put("Nonce", nonce);//随机数（最大长度128个字符）
//            String curTime = String.valueOf(System.currentTimeMillis()/1000);
//            headerParam.put("CurTime", curTime);//当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
//            //SHA1(AppSecret + Nonce + CurTime)，三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
//            String appSecret = CommonConf.APP_SECRET;
//            headerParam.put("CheckSum", CheckSumBuilder.getCheckSum(appSecret, nonce, curTime));
//
//            String str = HttpClientUtil.post3(url, params ,headerParam);
//            JSONObject parseObject = JSON.parseObject(str);
//            int code = parseObject.getIntValue("code");
//
//            if(code!=200){//发送失败
//                return "短信发送失败！";
//            }
            //3、
            String key = CommonConf.MEG_CODE+userPhone;
            redisTemplate.opsForValue().set(key, round,5, TimeUnit.MINUTES);
            return "短信发送成功！";
        }else if(user != null){

            return "该手机号已注册,请更换手机号！";
        }

            return "网络错误,请检查网络......";
    }

    /**
     * @param userModel
     * @return 用户信息
     */
    @Override
    public UserModel userlogin(UserModel userModel) {
        String userAccount = userModel.getUserAccount();
        return userMapper.queryUserAccount(userAccount);
    }

    /**
     * 主页面 推荐
     * @return
     */
    @Override
    public HashMap<String, Object> Fangtuijian() {

        HashMap<String, Object> map = new HashMap<>();

        List<FangJianModel> list = userMapper.Fangtuijian();
        map.put("rows",list);
        return map;
    }
    // 根据房间 详情
    @Override
    public FangJianModel tuijianxianqing(Integer fangId) {

        return userMapper.tuijianxianqing(fangId);
    }

    // 根据房主Id 查询 图片
    @Override
    public List<String> queryFangImg(Integer fangId) {

        List<String> FangImg = userMapper.queryFangImg(fangId);
        return FangImg;
    }
    /**
     *  用户信息展示
     * @param request
     * @return
     */
    @Override
    public UserModel userxinxi(Integer userId) {

        return userMapper.userxinxi(userId);
    }
    /**
     *  修改 名称
     */
    @Override
    public void updateUser(String userxiugai, String usersize,Integer userId) {

        if(usersize.equals("昵称")){
            userMapper.updateNiCheng(userxiugai,userId);
        }else if(usersize.equals("Mail")){
            userMapper.updateMail(userxiugai,userId);
        }else if(usersize.equals("Phone")){
            userMapper.updatePhone(userxiugai,userId);
        }
    }


}
