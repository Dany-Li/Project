package com.atguigu.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.atguigu.ssh.entities.Employee;
import com.atguigu.ssh.service.DepartmentService;
import com.atguigu.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction extends ActionSupport implements RequestAware,
	ModelDriven<Employee>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
	private String lastName;
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String validateLastName() throws UnsupportedEncodingException {
		if(employeeService.lastNameIsValid(lastName)) {
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else {
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-success";
	}
	
	
	public String input() {
		
		request.put("departments", departmentService.getAll());
		return "input";
	}
	
	
	
	public void prepareInput() {
		if(id != null) {
			model = employeeService.get(id);
		}
	}
	
	
	//id 是可以作为属性的
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String save() {
//		System.out.println(model);
		if(id == null) {
			model.setCreateTime(new Date());
		}
		
		
		employeeService.saveOrUpdate(model);
		return "success";
	}
	
	
	/**
	 * 可以根据 id 来判断为 save 方法准备的 model 是 new 的还是从数据库获取的!
	 */
	public void prepareSave() {
		if(id == null) {
			model = new Employee();
		}else {
			model = employeeService.get(id);
		}
		
	}
	
	//删除的时候要用到
	private InputStream inputStream;

    public InputStream getInputStream() {

        return inputStream;

    }
	
	
	public String delete() {
		try {
			employeeService.delete(id);
			//删除成功，返回1
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//删除失败，返回0，跟 emp-list.jsp 页面联系
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				//异常为支不支持 UTF-8
				e1.printStackTrace();
			}
			
		}
		
		return "ajax-success";
	}
	
	
	public String list() {
		
		//实现 RequestAware 接口，把信息放在请求域中
		request.put("employees", employeeService.getAll());
		return "list";
	}

	private Map<String,Object> request;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
		
	}


	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		//这里可以不用写任何的代码
	}


	
	private Employee model;
	
	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
