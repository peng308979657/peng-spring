package cn.peng.spring.schedule.task;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//定义一个专门负责任务处理的程序类
@Component //必须明确的告诉Spring，现在的myTask是一个Spring容器管理的Bean
public class MyTask {
	@Scheduled(cron="* * * * * ?")
	public void runJobTack() throws InterruptedException {//自己定义了一个方法名称
		TimeUnit.SECONDS.sleep(5);
		System.out.println("1111#####当前日期时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new java.util.Date()));

	}

}
