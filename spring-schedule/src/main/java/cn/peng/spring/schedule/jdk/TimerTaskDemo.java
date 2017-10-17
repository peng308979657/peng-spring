package cn.peng.spring.schedule.jdk;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask{//定义一个要执行的定时调度任务

	@Override
	public void run() {//表示要执行的任务主体代码
		System.out.println("当前日期时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new java.util.Date()));
		
	}
	
}
public class TimerTaskDemo {
	public static void main(String[] args) {
		Timer timer = new Timer();
		//立即启动，没有延迟，并且美妙执行一次
		timer.schedule(new MyTask(), 0,1000);
	}
}
