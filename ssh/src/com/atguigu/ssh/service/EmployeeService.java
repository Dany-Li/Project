package com.atguigu.ssh.service;

import java.util.List;

import com.atguigu.ssh.dao.EmployeeDao;
import com.atguigu.ssh.entities.Employee;

public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	
	public void delete(Integer id) {
		employeeDao.delete(id);
	}
	
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	
	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOrUpdate(employee);
	}
	
	
	//验证用户名的
	public boolean lastNameIsValid(String lastName) {
		return employeeDao.getEmployeeByLastName(lastName) == null;
		
	}

	
	public Employee get(Integer id) {
		return employeeDao.get(id);
	}
}

