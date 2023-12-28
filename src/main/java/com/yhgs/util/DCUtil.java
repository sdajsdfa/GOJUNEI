package com.yhgs.util;

import com.alibaba.fastjson.JSONObject;
import com.yhgs.model.SssDoc;

import java.io.File;
import java.io.IOException;

public class DCUtil {


    /**
     *   高低变下载
     */
    public static byte[] SSData(String dataUrl) {
        File file = new File(dataUrl);
        if (file.exists()) {
            SssDoc sssDoc = new SssDoc();
            try {
                ReaderMethodUtil readerMethodUtil = new ReaderMethodUtil();
                String jsonStr = readerMethodUtil.readerMethod(file);
                SssDoc sd = JSONObject.parseObject(jsonStr,SssDoc.class);
                return sssDoc.makeSssFile(sd);
            }catch (IOException e){
                e.getMessage();
            }
        }
        return null;
    }
}
