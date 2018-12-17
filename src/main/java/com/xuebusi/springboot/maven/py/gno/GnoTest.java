package com.xuebusi.springboot.maven.py.gno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GnoTest {
    public static void main(String[] args) {
        Process proc;
        try {
            String pypath = "/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/gno/evaluate_single_submission.py";
            String[] params = new String[]{"python", pypath};
            proc = Runtime.getRuntime().exec(params);
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
