package cn.peng.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.peng.spring.jdbc.vo.News;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJDBCDemo extends TestCase {
	@Resource
	private JdbcTemplate jdbcTemplate;// 注入JdbcTemplate对象

	@Test
	public void testSelectCount() {
		String column = "title";
		String keyword = "我";
		String sql = " select count(*) from news where " +column+ " like ? ";
		Long count = this.jdbcTemplate.queryForObject(sql, Long.class,"%"+keyword+"%");
		System.out.println("数据量统计"+count);
	}
	@Test
	public void testSelectSplit() {
		String column = "title";
		String keyword = "使用";
		Long currentPage = 1L;
		Integer lineSize = 5;
		String sql = " select nid,title,pubdate,note,price,readcount from news where " +column+ " like ? limit ?,?";
		List<News> allNews = this.jdbcTemplate.query(sql, new Object[] {"%"+keyword+"%",(currentPage-1)*lineSize,lineSize}, new RowMapper<News>() {
			@Override
			public News mapRow(ResultSet rs, int rowNum) throws SQLException {
				News vo = new News();
				vo.setNid(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setPubdate(rs.getDate(3));
				vo.setNote(rs.getString(4));
				vo.setPrice(rs.getDouble(5));
				vo.setReadcount(rs.getInt(6));
				return vo;
			}
		});
		System.out.println(allNews);
	}

	@Test
	public void testSelectObject() {
		String sql = "select nid,title,pubdate,note,price,readcount from news where nid=?";
		News vo = this.jdbcTemplate.queryForObject(sql, new Object[] { 5 }, new RowMapper<News>() {

			@Override
			public News mapRow(ResultSet rs, int rowNum) throws SQLException {
				News vo = new News();
				vo.setNid(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setPubdate(rs.getDate(3));
				vo.setNote(rs.getString(4));
				vo.setPrice(rs.getDouble(5));
				vo.setReadcount(rs.getInt(6));
				return vo;
			}
		});
		System.out.println(vo);
	}

	@Test
	public void testAddBatch() {
		String sql = "insert into news(title,pubdate,note,price,readcount)values(?,?,?,?,?)";
		List<News> allNews = new ArrayList<News>();
		Random rand = new Random();
		for (int x = 0; x < 10; x++) {
			News vo = new News();
			vo.setTitle("我是你大爷" + x);
			vo.setPubdate(new Date());
			vo.setNote("我是你鹏大爷" + x);
			vo.setPrice(100.0);
			vo.setReadcount(rand.nextInt(666));
			allNews.add(vo);
		}
		int len[][] = this.jdbcTemplate.batchUpdate(sql, allNews, allNews.size(),
				new ParameterizedPreparedStatementSetter<News>() {
					@Override
					public void setValues(PreparedStatement ps, News vo) throws SQLException {
						ps.setString(1, vo.getTitle());
						ps.setDate(2, new java.sql.Date(vo.getPubdate().getTime()));
						ps.setString(3, vo.getNote());
						ps.setDouble(4, vo.getPrice());
						ps.setInt(5, vo.getReadcount());
					}
				});// 更新操作
		for (int x = 0; x < len.length; x++) {
			System.out.println("更新记录" + x + "" + Arrays.toString(len[x]));
		}
	}

	@Test
	public void testDelete() {
		String sql = "delete from news where nid=?";
		long nid = 3;
		int len = this.jdbcTemplate.update(sql, nid);// 更新操作
		System.out.println("更新行数" + len);
		TestCase.assertEquals(len, 1);// 跟新一行数据
	}

	@Test
	public void testEidt() {
		String sql = "update news set title=?,pubdate=?,note=?,price=?,readcount=? where nid=?";
		String title = "修改数据";
		Date pubdate = new Date();
		String note = "修改数据";
		double price = 100.0;
		int readcount = 1000;
		long nid = 3;
		int len = this.jdbcTemplate.update(sql, title, pubdate, note, price, readcount, nid);// 更新操作
		System.out.println("更新行数" + len);
		TestCase.assertEquals(len, 1);// 跟新一行数据
	}

	@Test
	public void testAdd1() {
		String sql = "insert into news(title,pubdate,note,price,readcount)values(?,?,?,?,?)";
		String title = "JDBC出入测试";
		Date pubdate = new Date();
		String note = "JDBC注入增加数据测试";
		double price = 100.0;
		int readcount = 10;
		int len = this.jdbcTemplate.update(sql, title, pubdate, note, price, readcount);// 更新操作
		System.out.println("更新行数" + len);
		TestCase.assertEquals(len, 1);// 跟新一行数据
	}

	@Test
	public void testAdd2() {
		String sql = "insert into news(title,pubdate,note,price,readcount)values(?,?,?,?,?)";
		String title = "使用PreparedStatementSetter来测试";
		Date pubdate = new Date();
		String note = "使用PreparedStatementSetter来测试";
		double price = 100.0;
		int readcount = 100000;
		int len = this.jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, title);
				ps.setDate(2, new java.sql.Date(pubdate.getTime()));
				ps.setString(3, note);
				ps.setDouble(4, price);
				ps.setInt(5, readcount);
			}
		});// 更新操作
		System.out.println("更新行数" + len);
		TestCase.assertEquals(len, 1);// 跟新一行数据

	}

	@Test
	public void testAdd3() {
		KeyHolder keyholder = new GeneratedKeyHolder();// 获得增长后的ID数据
		String sql = "insert into news(title,pubdate,note,price,readcount)values(?,?,?,?,?)";
		String title = "使用PreparedStatementSetter来测试";
		Date pubdate = new Date();
		String note = "使用PreparedStatementSetter来测试";
		double price = 100.0;
		int readcount = 100000;
		int len = this.jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, title);
				ps.setDate(2, new java.sql.Date(pubdate.getTime()));
				ps.setString(3, note);
				ps.setDouble(4, price);
				ps.setInt(5, readcount);
				return ps;
			}
		}, keyholder);
		System.out.println("更新行数" + len + "当前ID" + keyholder.getKey());
		TestCase.assertEquals(len, 1);// 跟新一行数据
	}

}
