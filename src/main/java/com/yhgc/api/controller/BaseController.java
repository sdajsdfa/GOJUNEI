package com.yhgc.api.controller;

import com.yhgc.api.service.*;
import org.springframework.data.redis.core.RedisTemplate;


import javax.annotation.Resource;

/**
 * 功能描述：基础控制器
 *
 */
public class BaseController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    SbBasicInfoService sbBasicInfoService;


    @Resource
    DcSbPictureService dcSbPictureService;

    @Resource
    DcBasicInfoService dcBasicInfoService;

    @Resource
    RedisTemplate redisTemplate;

}
