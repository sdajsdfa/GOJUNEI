package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.SbBasicInfo;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.util.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@RestController
@RequestMapping("/api")
public class SbBasicInfoController extends BaseController {


    public static void main(String[] args) {

    }

    /**
     *  新增声测试验基础数据
     * @param
     * @return
     */
    @PostMapping(value = "/ScData")
    public R scData(@RequestBody SbBasicInfo sbBasicInfo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<SbBasicInfo> query = new QueryWrapper<>();
        query.eq("MachineId",sbBasicInfo.getMachineId());
        query.eq("SerialNo",sbBasicInfo.getSerialNo());
        query.eq("PileNo",sbBasicInfo.getPileNo());
        query.eq("TestTime",sbBasicInfo.getTestTime());
        List<SbBasicInfo> list =sbBasicInfoService.list(query);
        if(list.size()>0){
            for (SbBasicInfo sbBasicInfoVo:list){
                File dataUrl = new File(sbBasicInfoVo.getDataUrl());
                dataUrl.delete();
                QueryWrapper<SbBasicInfo> queryTab = new QueryWrapper<>();
                queryTab.eq("BasicInfoId",sbBasicInfoVo.getBasicInfoId());
                sbBasicInfoService.remove(queryTab);
            }
        }
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(request.getHeader("Authorization"));
//        Authusers authusers = (Authusers) request.getSession().getAttribute(request.getHeader("Authorization"));
        if(userInfo==null){
            return R.error(-1,"Ticket失效");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());
        String sourceZip = "D:\\root\\dc\\"+ currentDate +"\\";
//        String sourceZip = "D:\\"+authusers.getDeptnamecode().toLowerCase()+File.separator+sbBasicInfo.getMachineId().toLowerCase()+ File.separator +sbBasicInfo.getSerialNo().toLowerCase()+File.separator;
        String filePath = null;
        try {
            filePath = sourceZip + UUID.randomUUID()+ ".zip";
            File sourceZipFile = new File(filePath);
            if (!sourceZipFile.exists()) {   //文件不存在则创建文件，先创建目录
                File dir = new File(sourceZipFile.getParent());
                dir.mkdirs();
                sourceZipFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(sbBasicInfo.getZipFileData());
            outputStream.close();
            outputStream.flush();
            System.out.println(filePath);
        } catch (Exception e) {
            e.getMessage();
        }
        sbBasicInfo.setDataUrl(filePath);
        sbBasicInfo.setIsValid(1);
        sbBasicInfo.setComId(userInfo.getCompId());
        sbBasicInfo.setCreateTime(new Date());
        sbBasicInfo.setCreateName("RS");
        sbBasicInfoService.save(sbBasicInfo);
        map.put("baseInfoId",sbBasicInfo.getBaseInfoId());
        return R.ok(map);
    }



}

