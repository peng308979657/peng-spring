package cn.peng.spring.context.dao.impl;


import cn.peng.spring.context.dao.IDeptDAO;
import cn.peng.spring.context.vo.Dept;
//@Repository
public class DeptDAOImpl implements IDeptDAO {

	@Override
	public boolean doCreate(Dept dept) {
		System.out.println("【数据层】DeptDAO 增加数据层"+dept);
		return true;
	}

}
