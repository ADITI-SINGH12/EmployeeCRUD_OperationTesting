package com.employees.EmployeeRestAPI.service;

import com.employees.EmployeeRestAPI.dao.EmployeeDAOImpl;
import com.employees.EmployeeRestAPI.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DaoServiceUnitTest {

    @InjectMocks
    DaoService daoService;

    @Mock
    EmployeeDAOImpl employeeDAO;

    @Test
    public void employeeByIDTest(){
        Employee employee = new Employee(1,"Aditi","Singh","aditi.singh@gmail.com");
        when(employeeDAO.findById(anyInt())).thenReturn(employee);
       Employee getEmployee = daoService.employeeByID(anyInt());
       verify(employeeDAO).findById(anyInt());

    }

    @Test
    public void findAllTest(){
        Employee employee1 = new Employee(1,"Aditi","Singh","aditi.singh@gmail.com");
        Employee employee2 = new Employee(2,"Astha","Singh","astha.singh@gmail.com");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeDAO.showAllEmployee()).thenReturn(employeeList);

        List<Employee> reuireList = daoService.findAll();

        assertEquals(2,reuireList.size());
        verify(employeeDAO).showAllEmployee();
    }

    @Test
    public void CreateNewEmployeeTest(){
        Employee employee = new Employee(1,"Aditi","Singh","aditi.singh@gmail.com");
        when(employeeDAO.CreateNewEmployee(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = daoService.CreateNewEmployee(employee);
        assertEquals(employee,savedEmployee);
        verify(employeeDAO).CreateNewEmployee(any(Employee.class));

    }

  @Test
  public void deleteEmployeeTest(){
      Employee employee = new Employee(1,"Aditi","Singh","aditi.singh@gmail.com");
      when(employeeDAO.deleteEmployee(anyInt())).thenReturn(employee);

      Employee deletedEmployee = daoService.deleteEmployee(1);
      assertEquals(employee,deletedEmployee);
      verify(employeeDAO).deleteEmployee(anyInt());
  }

}
