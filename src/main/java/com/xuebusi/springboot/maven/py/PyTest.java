package com.xuebusi.springboot.maven.py;

import org.python.core.Py;
import org.python.core.PySystemState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Process proc;
		try {
			String[] params = new String[]{"python", "/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/test.py"};
			proc = Runtime.getRuntime().exec(params);// 执行py文件
//			proc = Runtime.getRuntime().exec("python \\Users\\v_shiyanjun\\github\\xbs-springboot-maven-jar\\py\\test.py");// 执行py文件
			//用输入输出流来截取结果
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