package cn.peng.spring.schedule.quzrtz;

import java.text.SimpleDateFormat;


//定义一个专门负责任务处理的程序类
public class MyTask {
	
	
	protected void runTack() {//自己定义了一个方法名称
		System.out.println("当前日期时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new java.util.Date()));

	}

}
