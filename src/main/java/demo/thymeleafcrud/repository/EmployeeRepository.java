package demo.thymeleafcrud.repository;

import demo.thymeleafcrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// With DATA REST no Controller, DAO and Service Implementation required
// However this is applicable ONLY to backend purposes. In web application DATA REST has no meaning
// By default Spring will create endpoints based on Entity type - first character of Entity in lowercase + "s" at the end
@RepositoryRestResource(path = "members") // <- Custom endpoint name, /employees -> /members
public interface EmployeeRepository extends JpaRepository<Employee, Integer> { // <- Entity type, Primary key type

    // sort by last name
    List<Employee> findAllByOrderByLastNameAsc(); // <- Spring Boot will build the query itself
}
