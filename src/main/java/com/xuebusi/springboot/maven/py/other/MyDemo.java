package com.xuebusi.springboot.maven.py.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyDemo {


    public static void main(String[] args) {
        try {
            //String a=getPara("car").substring(1),b="D34567",c="LJeff34",d="iqngfao";
            //String[] args1=new String[]{ "python", "D:\\pyworkpeace\\9_30_1.py", a, b, c, d };
            //Process pr=Runtime.getRuntime().exec(args1);
            String url = "http://blog.csdn.net/thorny_v/article/details/61417386";
            System.out.println("start;" + url);
            String[] args1 = new String[]{"python", "/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/other/9_30_1.py", url};
            Process pr = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPara(String string) {
        // TODO Auto-generated method stub
        return null;
    }

}