package com.yhgc.api.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PicUploadResult {

   private boolean isLegal;

   private String imgPath;

   private List<String> imgPahts;

}