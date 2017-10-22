package cn.peng.spring.transaction.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.peng.spring.transaction.service.INewsService;
import cn.peng.spring.transaction.vo.News;
import junit.framework.TestCase;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestNewsService extends TestCase {
	
	@Resource
	private INewsService newsService;
	@Test
	public void testAdd(){
		News vo = new News();
		vo.setTitle("aaaaaaaaaa");
		vo.setPubdate(new Date());
		vo.setNote("bbbbbbbbb");
		vo.setPrice(100.0);
		vo.setReadcount(123123);
		TestCase.assertTrue(this.newsService.add(vo));
	}
	@Test
	public void testGet() {
		News vo = this.newsService.get(100L);
		System.err.println(vo);
		TestCase.assertNotNull(vo);
	}
}
