package cn.peng.spring.transaction.service;

import cn.peng.spring.transaction.vo.News;

public interface INewsService {
	
	public boolean add(News vo);
	public News get(Long nid);
}
