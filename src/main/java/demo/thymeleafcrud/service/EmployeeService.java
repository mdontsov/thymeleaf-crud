package demo.thymeleafcrud.service;

import demo.thymeleafcrud.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    void saveOrUpdateEmployee(Employee employee);

    Employee getEmployee(int employeeId);

    void deleteEmployee(int employeeId);
}
