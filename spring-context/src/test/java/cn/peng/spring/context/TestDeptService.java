package cn.peng.spring.context;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.peng.spring.context.service.IDeptService;
import cn.peng.spring.context.vo.Dept;
import junit.framework.TestCase;
@ContextConfiguration(locations= {"classpath:spring/spring-context.xml"})
//资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)
//设置要使用的测试工具
public class TestDeptService extends TestCase {
	@Resource
	private IDeptService deptService;
	@Test
	public void testAdd() {
		Dept dept = new Dept();
		dept.setDeptno(10L);
		dept.setDname("财务部");
		TestCase.assertTrue(this.deptService.add(dept));
	}
}
