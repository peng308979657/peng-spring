package cn.peng.spring.schedule.test;


import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestScheduleTask extends TestCase {
	@Test
	public void testTask() {
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
