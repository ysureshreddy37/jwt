package JWTtoken.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JWTtoken.jwt.entity.Employee;
import JWTtoken.jwt.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee emp) {
		return employeeService.saveEmployee(emp);
	}

	@GetMapping("/getall")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}
	@GetMapping("/getemp/{empid}")
	public Employee getEmployee(@PathVariable String empid) {
		return employeeService.getEmployee(Integer.parseInt(empid));
		
	}
	
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<?> deleteEmployee(@PathVariable String empid) {
		 employeeService.deleteEmployee(Integer.parseInt(empid));
		 return ResponseEntity.ok().build();
		 
	}
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return employeeService.updateEmployee(emp);
	}
	
	
	
	
	
}
