package com.atguigu.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.atguigu.ssh.entities.Employee;

public class EmployeeDao extends BaseDao {
	
	
	
	
	public void delete(Integer id) {
		String hql = "DELETE FROM Employee e WHERE e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	public List<Employee> getAll(){
		//使用迫切左外链接 LEFT OUTER JOIN FETCH e.department 顺便把部门信息也获取了
		//获取 Employee 时使用 迫切左外连接同时初始化其关联的 Department 对象.
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department";
//		String hql = "FROM Employee";
		return getSession().createQuery(hql).list();
	}

	
	
	public void saveOrUpdate(Employee employee) {
		getSession().saveOrUpdate(employee);
	}
	
	
	
	public Employee getEmployeeByLastName(String lastName) {
		String hql = "FROM Employee e WHERE e.lastName = ?";
		Query query = getSession().createQuery(hql).setString(0, lastName);
		return (Employee) query.uniqueResult();
	}
	
	
	public Employee get(Integer id) {
		return (Employee) getSession().get(Employee.class, id);
	}
}
