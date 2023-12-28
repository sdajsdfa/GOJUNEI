package com.yhgs.util;

import com.google.gson.Gson;
import com.yhgs.model.SbeDoc;

import java.io.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MybatisPlusAutoBuilder
 * @since 2023-11-22
 */
public class SCUtil {

    /**
     * 声测数据下载
     *
     * @return
     */
    public static byte[] SSData(String dataUrl) {
        File file = new File(dataUrl);
        if (file.exists()) {
            try {
                ReaderMethodUtil readerMethodUtil = new ReaderMethodUtil();
                String jsonStr = readerMethodUtil.readerMethod(file);
                Gson gson = new Gson();
                SbeDoc sd = new SbeDoc(gson.fromJson(jsonStr, SbeDoc.class));
                return sd.ToEditionSixBytes();
            }catch (IOException e){
                e.getMessage();
            }
        }
        return null;
    }


}
