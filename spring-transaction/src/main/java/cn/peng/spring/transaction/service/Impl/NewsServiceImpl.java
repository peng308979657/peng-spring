package cn.peng.spring.transaction.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.peng.spring.transaction.dao.INewsDAO;
import cn.peng.spring.transaction.service.INewsService;
import cn.peng.spring.transaction.vo.News;

@Service
public class NewsServiceImpl implements INewsService {
	@Resource
	//可以注入的只是一个接口的实例化对象，但是这些对象还需要配置隔离级别，传播属性
	private PlatformTransactionManager transactionManager;
	@Resource
	private INewsDAO newsDAO;
	
	@Override
	public boolean add(News vo) {
		boolean flag = false;
		//TransactionDefinition通过此类设置传播属性，隔离级别，是否为只读，超时访问
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		//设置传播属性，表示必须有一个事务启动
		transactionDefinition.setPropagationBehavior(transactionDefinition.PROPAGATION_REQUIRED);
		//隔离级别一定要使用默认的隔离级别进行控制
		transactionDefinition.setIsolationLevel(transactionDefinition.ISOLATION_DEFAULT);
		//获取事务的提交状态
		TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
		
		try {
			flag = this.newsDAO.doCreate(vo);
			this.transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			this.transactionManager.rollback(transactionStatus);
		}
		return flag;
	}

	@Override
	public News get(Long nid) {
		News vo = null;
		//TransactionDefinition通过此类设置传播属性，隔离级别，是否为只读，超时访问
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		//设置传播属性，表示必须有一个事务启动
		transactionDefinition.setPropagationBehavior(transactionDefinition.PROPAGATION_REQUIRED);
		//隔离级别一定要使用默认的隔离级别进行控制
		transactionDefinition.setIsolationLevel(transactionDefinition.ISOLATION_DEFAULT);
		//只读操作
		transactionDefinition.setReadOnly(true);
		//获取事务的提交状态
		TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
		
		try {
			vo = this.newsDAO.findById(nid);
			this.transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			this.transactionManager.rollback(transactionStatus);
		}
		return vo;
	}
}
