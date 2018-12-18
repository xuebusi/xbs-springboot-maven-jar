package com.xuebusi.springboot.maven.py;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 总结一下java调用python脚本遇到的问题：
 * （1）pyhton的环境变量配置问题,下面是我的mac笔记本~/.bash_profile文件中的所有配置，重点在PYTHONPATH的配置，必须配置为项目的根目录，不同的项目，改目录不同；
 *      export M2_HOME=/Users/v_shiyanjun/soft/apache-maven-3.5.4
 *      export PATH=$PATH:$M2_HOME/bin
 *      export PATH=$PATH:/usr/local/mysql/bin
 *      export PYTHONPATH=$PATH:/Users/v_shiyanjun/baidu/ai/platform-road
 *      export PYTHONPATH
 *      PATH=$PATH:/usr/local/bin/python3.7
 *      export PATH
 *
 * （2）给python脚本传参数时，如果参数是文件目录，千万注意绝对路径和相对路径的问题，一定要传绝对目录；
 *
 *  以上是踩到的两个大坑，耗费了不少时间。
 */
public class PyTest {
    public static void main(String[] args) {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python /Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/gno/evaluate_single_submission.py");
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
