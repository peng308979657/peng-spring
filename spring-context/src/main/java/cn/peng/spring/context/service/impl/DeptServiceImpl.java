package cn.peng.spring.context.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.peng.spring.context.dao.IDeptDAO;
import cn.peng.spring.context.service.IDeptService;
import cn.peng.spring.context.vo.Dept;
@Service
public class DeptServiceImpl implements IDeptService {
	@Resource
	private IDeptDAO deptdao;//定义IDept 接口对象,表示该对象通过容器注入
	@Override
	public boolean add(Dept dept) {
		System.out.println("*****Dept业务层调用*****");
		return this.deptdao.doCreate(dept);
	}

}
