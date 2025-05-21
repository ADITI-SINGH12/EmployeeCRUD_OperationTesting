package com.employees.EmployeeRestAPI.dao;

import com.employees.EmployeeRestAPI.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee findById(int id) {
    Employee employee =  entityManager.find(Employee.class,id);
    return employee;
    }

    @Override
    public List<Employee> showAllEmployee() {
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery
                ("FROM Employee", Employee.class);
        List<Employee> employeeList = employeeTypedQuery.getResultList();
        return employeeList;
    }

    @Override
    @Transactional
    public Employee CreateNewEmployee(Employee employee) {
        Employee employees = entityManager.merge(employee);
        return employees;
    }

    @Override
    @Transactional
    public Employee deleteEmployee(int id) {
         Employee employee = entityManager.find(Employee.class,id);
       entityManager.remove(employee);
        return employee;
    }

    @Override
    public Employee updateEmployeeMail(int id, String mail) {
        Employee employee =  entityManager.find(Employee.class,id);
        employee.setEmail(mail);
       return entityManager.merge(employee);
    }


}
