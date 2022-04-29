package demo.thymeleafcrud.controller;

import demo.thymeleafcrud.model.Employee;
import demo.thymeleafcrud.service.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Because Thymeleaf uses HTML then only @Get and @Post requests can be used in REST controller here */

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired // <- Constructor DI injection is most recommended one
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // add mapping for /list
    @GetMapping("/list")
    public String listEmployees(@NotNull Model model) {

        // get employees from the database
        List<Employee> employees = service.getEmployees();

        // add to the Spring model
        model.addAttribute("aEmployees", employees);
        return "employees-list";
    }

    @PostMapping("/saveOrUpdate")
    public String addEmployee(@ModelAttribute("aEmployee") Employee oEmployee) {
        service.saveOrUpdateEmployee(oEmployee);
        return "redirect:/employees/list"; // <- prevent duplicate submissions
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam int employeeId) {
        service.deleteEmployee(employeeId);
        return "redirect:/employees/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam int employeeId, Model model) {
        Employee oEmployee = service.getEmployee(employeeId);
        model.addAttribute("aEmployee", oEmployee);
        return "employee-form";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(@NotNull Model model) {
        Employee oEmployee = new Employee();
        model.addAttribute("aEmployee", oEmployee);
        return "employee-form";
    }

    @RequestMapping("/**")
    public String unmappedRequest() {
        return "redirect:/employees/list";
    }
}
