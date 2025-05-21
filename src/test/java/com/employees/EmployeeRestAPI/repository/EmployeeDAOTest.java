package com.employees.EmployeeRestAPI.repository;

import com.employees.EmployeeRestAPI.dao.EmployeeDAO;
import com.employees.EmployeeRestAPI.entity.Employee;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;

//@DataJpaTest it's only for Spring Data JPA repositories (CrudRepository, JpaRepository, etc.).
@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.properties")
public class EmployeeDAOTest {

    @Autowired
    EmployeeDAO employeeDAO;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void CreateNewEmployee_Test(){
        Employee employee = new Employee("Aditi","Singh","aditi.singh@gmail.com");
        Employee savedEmployee = employeeDAO.CreateNewEmployee(employee);
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertEquals(employee.getEmail(),savedEmployee.getEmail());
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void showAllEmployee_Test(){
//        Employee employee = new Employee("Aditi","Singh","aditi.singh@gmail.com");
      List<Employee> employeeList =  employeeDAO.showAllEmployee();
        Assertions.assertThat(employeeList.size()).isGreaterThan(0);

    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void findById_Test(){

        Employee employee =  employeeDAO.findById(1);
        assertNotNull(employee);

    }
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployee_Test(){
        Employee employee =  employeeDAO.updateEmployeeMail(1,"aditi@gmail.com");
       assertEquals("aditi@gmail.com",employee.getEmail());
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployee_Test(){
        Employee employee =  employeeDAO.deleteEmployee(1);
        assertNotNull(employee);
    }
}
