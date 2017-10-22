package cn.peng.spring.transaction;

import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public interface PlatformTransactionManager {
	/**
	 * 根据指定的事务操作标准定义，来获取一个事务的控制状态
	 * @param definitin 事务相关处理定义，包括：传播属性，隔离级，只读控制
	 * @return 事务控制状态，为所有的提交与回滚都依靠次状态完成
	 * @throws Transac tionException 事务处理异常
	 */
	public TransactionStatus geTransaction(@Nullable TransactionDefinition definitin) throws TransactionException;
	/**
	 * 事务的提交处理操作，真正的Spring开发过程之中，事务的提交操作应该有AOP自己负责
	 * @param status 事务的状态
	 * @throws TransactionException 事务处理异常
	 */
	public void commit(TransactionStatus status) throws TransactionException;
	/**
	 * 事务回滚处理
	 * @param status 根据状态来惊醒回滚
	 * @throws TransactionException 事务处理异常
	 */
	public void rollback(TransactionStatus status) throws TransactionException;
	
}

