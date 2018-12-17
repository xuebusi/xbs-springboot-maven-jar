package com.xuebusi.springboot.maven.py.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PyTest2 {
    public static void main(String[] args) {
        int a = 18;
        int b = 23;
        try {
            String[] argss = new String[] { "python", "/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/other/test2.py", String.valueOf(a), String.valueOf(b) };
            Process proc = Runtime.getRuntime().exec(argss);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
