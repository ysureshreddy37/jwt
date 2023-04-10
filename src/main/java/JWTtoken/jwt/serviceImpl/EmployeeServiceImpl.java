package JWTtoken.jwt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JWTtoken.jwt.entity.Employee;
import JWTtoken.jwt.repository.EmployeeRepository;
import JWTtoken.jwt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Integer empId) {
		// TODO Auto-generated method stub
		return employeeRepository.getById(empId);
	}

	@Override
	public void deleteEmployee(Integer empid) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(empid);

	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

}
