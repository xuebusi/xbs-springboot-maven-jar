package com.xuebusi.springboot.maven.py.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class demo01 {
	public static void main(String[] args) {
		String text="呢。\r\n" + 
				"　　他重新经过安检，回到了候机大厅。大厅里仍是一片嘈杂。他强迫自己镇静，在饮水机前喝了几口水，找了一处空椅子坐下，闭目养神。已经落网的赵德汉的形象适时浮现在眼前，他禁不住又沉浸到了对赵德汉的回忆中。昨天晚上，当此人捧着大海碗吃炸酱面时，老旧的木门“吱呀”一声开了，他代表命运来敲这位贪官的家门了。\r\n" + 
				"　　贪官一脸憨厚相，乍看上去，不太像机关干部，倒像个刚";
		//定义个获取结果的变量
		String result="";
		try {
			//调用python，其中字符串数组对应的是python，python文件路径，向python传递的参数
			String[] strs=new String[] {"python","/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/other/test1.py",text};
			//Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
		    //一般不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，但可以通过 getRuntime 方法获取当前Runtime运行时对象的引用。
			// exec(String[] cmdarray) 在单独的进程中执行指定命令和变量。 
			Process pr = Runtime.getRuntime().exec(strs);
			//使用缓冲流接受程序返回的结果
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"GBK"));//注意格式
			//定义一个接受python程序处理的返回结果
			String line=" ";
			while((line=in.readLine())!=null) {
				//循环打印出运行的结果
				result+=line+"\n";
			}
			//关闭in资源
			in.close();
			pr.waitFor();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("python传来的结果：");
		//打印返回结果
		System.out.println(result);
	}
}
