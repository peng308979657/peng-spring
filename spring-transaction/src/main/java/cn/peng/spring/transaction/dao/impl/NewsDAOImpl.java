package cn.peng.spring.transaction.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.peng.spring.transaction.dao.INewsDAO;
import cn.peng.spring.transaction.vo.News;

@Repository
public class NewsDAOImpl implements INewsDAO {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean doCreate(News vo) {
		String sql = "insert into news(title,pubdate,note,price,readcount)values(?,?,?,?,?)";
		int len = this.jdbcTemplate.update(sql, vo.getTitle(), vo.getPubdate(), vo.getNote(), vo.getPrice(),
				vo.getReadcount());
		return len > 0;
	}

	@Override
	public News findById(Long nid) {
		String sql = "select nid,title,pubdate,note,price,readcount from news where nid=?";
		News vo = this.jdbcTemplate.queryForObject(sql, new Object[] { nid }, new RowMapper<News>() {

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
		return vo;
	}

}
