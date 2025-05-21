package com.employees.EmployeeRestAPI;

import com.employees.EmployeeRestAPI.entity.Employee;
import com.employees.EmployeeRestAPI.exception.EmployeeNotFoundException;
import com.employees.EmployeeRestAPI.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRESTControlleer {
    private DaoService daoService;

    @Autowired
    public EmployeeRESTControlleer(DaoService daoService) {
        this.daoService = daoService;
    }

    @GetMapping("/employee")
    public List<Employee> findallEmployee(){
        return daoService.findAll();
    }
    @GetMapping("/employee/{id}")
    public Employee findEmployeebyID(@PathVariable int id){
        if(id < 0){
            throw new EmployeeNotFoundException("This current Employee ID is not available");
        }
        return daoService.employeeByID(id);
    }
    @PostMapping("/employee")
    public Employee createNewEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee employee1 = daoService.CreateNewEmployee(employee);
        return employee1;
    }
    @DeleteMapping("/employee/{id}")
    public Employee deleteEmployee(@PathVariable int id){
        boolean exists = daoService.findAll().stream().anyMatch(e -> e.getId() == id);
        if(!exists|| id < 0){
            throw new EmployeeNotFoundException("This current Employee ID is not available");
        }
        return daoService.deleteEmployee(id);
    }
   @PutMapping("/employee")
    public Employee updateEmployees(@RequestBody Employee employee){
        Employee theemployee = daoService.CreateNewEmployee(employee);
        return theemployee;
   }
}
