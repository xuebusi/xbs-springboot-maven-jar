package com.xuebusi.springboot.maven.py;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
