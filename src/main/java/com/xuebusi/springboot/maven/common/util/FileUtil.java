package com.xuebusi.springboot.maven.common.util;

import java.io.FileInputStream;

/**
 * 文件操作工具类
 */
public class FileUtil {

    /**
     * 读取文件内容
     * @return
     */
    public static byte[] readContent(String filePath) {
        FileInputStream fs;
        try {
            fs = new FileInputStream(filePath);
            byte[] content=new byte[fs.available()];
            fs.read(content,0,content.length);
            fs.close();
            return content;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
