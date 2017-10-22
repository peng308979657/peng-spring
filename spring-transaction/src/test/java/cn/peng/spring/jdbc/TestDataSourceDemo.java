package cn.peng.spring.jdbc;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import junit.framework.TestCase;

@ContextConfiguration(locations= {"classpath:spring/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDataSourceDemo extends TestCase {
	
	@Resource
	private DataSource dataSource;
	@Test
	public void testConnection() throws SQLException {
		System.out.println(this.dataSource.getConnection());
	}
}
