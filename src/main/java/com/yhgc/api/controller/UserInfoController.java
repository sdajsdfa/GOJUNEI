package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.entity.UserInfoVo;
import com.yhgc.api.util.R;
import com.yhgc.api.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import java.util.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Api(tags = "用户信息2")
@RestController
@RequestMapping("/api")
public class UserInfoController extends BaseController {

    /**
     * app登录接口
     *
     * @return
     */
    @ApiOperation("app登录接口")
    @PostMapping(value = "/Ticket")
    public R appLogin(@RequestBody UserInfoVo user) {
        System.out.println(user.getAccountId()+"=====");
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isEmpty(user.getAccountId()) || StringUtils.isEmpty(user.getAccountSecret())) {
            return R.error("输入的用户名或密码或验证码不能为空");
        }
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Account", user.getAccountId());
        UserInfo userInfo =userInfoService.getOne(queryWrapper);
        if(userInfo==null){
            return R.error("用户名已经存在");
        }
        String md5Password = getMd5(user.getAccountId(), user.getAccountSecret());
        System.out.println(md5Password);
        if (!userInfo.getPassword().equals(md5Password)) {
            return R.error("用户名或密码错误");
        }
        Map mapOne = TokenUtil.createToken(userInfo);
        map.put("expire_in",mapOne.get("expire_in"));
        map.put("expire_time",mapOne.get("expire_time"));
//        session.setAttribute((String) mapOne.get("token"),authusers);
        redisTemplate.opsForValue().set(mapOne.get("token"), userInfo);
        return R.ok(map, (String) mapOne.get("token"));
    }


    //md5加密规则
    private String getMd5(String password, String account) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((account + password + account).getBytes()).toUpperCase();
        }
        return password;
    }
}

