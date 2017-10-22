package cn.peng.spring.transaction.dao;

import cn.peng.spring.transaction.vo.News;

public interface INewsDAO {
	public boolean doCreate(News vo);
	public News findById(Long nid);
}
