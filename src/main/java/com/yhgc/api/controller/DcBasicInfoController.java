package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.DcBasicInfo;
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
public class DcBasicInfoController extends BaseController {


    /**
     *  新增高应变和低应变试验数据
     * @param
     * @return
     */
    @PostMapping(value = "/DcData")
    public R dcData(@RequestBody DcBasicInfo dcBasicInfo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<DcBasicInfo> query = new QueryWrapper<>();
        query.eq("MachineId",dcBasicInfo.getMachineId());
        query.eq("SerialNo",dcBasicInfo.getSerialNo());
        query.eq("PileNo",dcBasicInfo.getPileNo());
        query.eq("TestTime",dcBasicInfo.getTestTime());
        List<DcBasicInfo> list =dcBasicInfoService.list(query);
        if(list.size()>0){
            for (DcBasicInfo dcBasicInfoVo:list){
                File dataUrl = new File(dcBasicInfoVo.getDataUrl());
                dataUrl.delete();
                QueryWrapper<DcBasicInfo> queryTab = new QueryWrapper<>();
                queryTab.eq("BaseInfoId",dcBasicInfoVo.getBaseInfoId());
                dcBasicInfoService.remove(queryTab);
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
//        String sourceZip = "D:\\"+authusers.getDeptnamecode().toLowerCase()+"\\"+dcBasicInfo.getMachineId().toLowerCase()+"\\"+dcBasicInfo.getSerialNo().toLowerCase()+"\\";
        String filePath = null;
        try {
            try {
                filePath = sourceZip + UUID.randomUUID()+ ".zip";
                File sourceZipFile = new File(filePath);
                if (!sourceZipFile.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(sourceZipFile.getParent());
                    dir.mkdirs();
                    sourceZipFile.createNewFile();
                }
                FileOutputStream outputStream = new FileOutputStream(filePath);
                outputStream.write(dcBasicInfo.getZipFileData());
                outputStream.close();
                outputStream.flush();
                System.out.println(filePath);
            } catch (Exception e) {
                e.getMessage();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        dcBasicInfo.setDataUrl(filePath);
        dcBasicInfo.setIsValid(1);
        dcBasicInfo.setComId(userInfo.getCompId());
        dcBasicInfo.setCreateTime(new Date());
        dcBasicInfo.setCreateName("RS");
        dcBasicInfoService.save(dcBasicInfo);
        map.put("baseInfoId",dcBasicInfo.getBaseInfoId());
        return R.ok(map);
    }


}

