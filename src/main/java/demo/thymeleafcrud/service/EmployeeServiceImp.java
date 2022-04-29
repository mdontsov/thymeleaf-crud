package demo.thymeleafcrud.service;

import demo.thymeleafcrud.model.Employee;
import demo.thymeleafcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAllByOrderByLastNameAsc();
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) {
        repository.save(employee);
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return repository.getById(employeeId);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        repository.deleteById(employeeId);
    }
}
