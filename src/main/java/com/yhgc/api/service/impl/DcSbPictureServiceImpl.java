package com.yhgc.api.service.impl;

import com.yhgc.api.entity.DcSbPicture;
import com.yhgc.api.entity.PicUploadResult;
import com.yhgc.api.mapper.DcSbPictureMapper;
import com.yhgc.api.service.DcSbPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-12-05
 */
@Service
public class DcSbPictureServiceImpl extends ServiceImpl<DcSbPictureMapper, DcSbPicture> implements DcSbPictureService {

    // 项目上传至绝对路径(在yml里配置你的项目绝对路径)
    private String baseFilePath = "/root/photos/";

    @Override
    public PicUploadResult uplodadImg(MultipartFile multipartFile, HttpServletRequest request) {
        PicUploadResult picUploadResult = new PicUploadResult();

        if (multipartFile.isEmpty()) {
            //返回选择文件提示
            picUploadResult.setLegal(false);
            return picUploadResult;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(baseFilePath + format);
        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = multipartFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            multipartFile.transferTo(newFile);
//            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/picbook/" + UPLOAD_PATH_PREFIX + format + newName;
            String filePath = baseFilePath + format +"/"+ newName;
            picUploadResult.setLegal(true);
            picUploadResult.setImgPath(filePath);
            return picUploadResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        picUploadResult.setLegal(false);
        return picUploadResult;
    }

}
