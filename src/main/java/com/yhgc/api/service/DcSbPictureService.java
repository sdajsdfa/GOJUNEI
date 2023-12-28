package com.yhgc.api.service;

import com.yhgc.api.entity.DcSbPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.entity.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-12-05
 */
public interface DcSbPictureService extends IService<DcSbPicture> {

    /**
     * 图片上传
     * @param multipartFile
     * @param request
     * @return
     */
    PicUploadResult uplodadImg(MultipartFile multipartFile, HttpServletRequest request);

}
