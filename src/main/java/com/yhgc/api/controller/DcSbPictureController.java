package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yhgc.api.entity.DcSbPicture;
import com.yhgc.api.entity.PicUploadResult;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.util.R;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-12-05
 */
@RestController
@RequestMapping("/api")
public class DcSbPictureController extends BaseController {

    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".png",".gif"};

//    @GetMapping("/abc")
//    public void abc(HttpServletResponse response) throws IOException {
//        byte[] dc= SCUtil.SSData("D://json//sc//20231226//15TK186.json");
//        response.reset();
//        response.setCharacterEncoding("UTF-8");
//        response.addHeader("Content-Disposition", "attachment;filename=" + "1234"+".sss");
//        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//        response.setContentType("application/octet-stream;charset=UTF-8");
//        toClient.write(dc);
//        toClient.flush();
//        toClient.close();
//    }


    @PostMapping("/UploadImg")
    public R uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("baseInfoId") String baseInfoId, HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(request.getHeader("Authorization"));
        if(userInfo==null){
            return R.error(-1,"Ticket失效");
        }
        DcSbPicture dcSbPicture =new DcSbPicture();
        boolean isFlag = false;
        for (String type : IMAGE_TYPE) {
            System.out.println(file.getOriginalFilename());
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                isFlag = true;
                break;
            }
        }
        if (isFlag) {
            PicUploadResult picUploadResult = dcSbPictureService.uplodadImg(file, request);
            boolean isLegal = picUploadResult.isLegal();
            if (isLegal) {
                Map resMap = new HashMap<>();
                resMap.put("imgPath", picUploadResult.getImgPath());
                dcSbPicture.setBaseInfoId(baseInfoId);
                dcSbPicture.setImageUrl(picUploadResult.getImgPath());
                dcSbPicture.setComId(userInfo.getCompId());
                System.out.println(dcSbPicture);
                dcSbPictureService.save(dcSbPicture);
                return R.ok(resMap);
            } else {
                return R.error(-3,"图片上传有误");
            }
        } else {
            return R.error(-2,"上传的图片格式必须为:bmp,jpg,jpeg,png");
        }
    }

}

