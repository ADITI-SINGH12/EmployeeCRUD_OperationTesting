package com.employees.EmployeeRestAPI.dao;

import com.employees.EmployeeRestAPI.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public Employee findById(int id);
    public List<Employee> showAllEmployee();
    public Employee CreateNewEmployee(Employee employee);
    public Employee deleteEmployee(int id);
    public Employee updateEmployeeMail(int id,String mail);
}
