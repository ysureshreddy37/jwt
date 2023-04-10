package JWTtoken.jwt.service;

import java.util.List;

import JWTtoken.jwt.entity.Employee;

public interface EmployeeService {
	

	public Employee saveEmployee(Employee employee) ;
	public List<Employee> getAllEmployee() ;
	public Employee getEmployee(Integer employee) ;
	public void deleteEmployee(Integer empid) ;
	public Employee updateEmployee(Employee employee) ;
	
}
