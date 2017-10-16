package cn.peng.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.peng.spring.context.dao.IDeptDAO;
import cn.peng.spring.context.dao.impl.DeptDAOImpl;

@Configuration //表示当前的类是一个专门用于配置的实现类
public class pengConfig {
	@Bean
	public  IDeptDAO getDeptDAOInstance() {//方法名称随便写
		return new DeptDAOImpl();//返回一个实例化对象
	}
}
