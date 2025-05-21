package com.employees.EmployeeRestAPI.controller;

import com.employees.EmployeeRestAPI.EmployeeRESTControlleer;
import com.employees.EmployeeRestAPI.entity.Employee;
import com.employees.EmployeeRestAPI.service.DaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(MockitoExtension.class) this should not be in controller class testing
@WebMvcTest(EmployeeRESTControlleer.class)
public class EmployeeRESTControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DaoService daoService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    private List<Employee> employeeList;
    @BeforeEach
    void setUp() {
        employeeList = Arrays.asList(
                new Employee(1,"Aditi","Singh","aditi.singh@gmail.com"),
                new Employee(2,"Astha","Singh","astha.singh@gmail.com")
        );
    }


    @Test
    public void findallEmployeeTest_success() throws Exception {

        when(daoService.findAll()).thenReturn(employeeList);

        mockMvc.perform(get("/api/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(employeeList.size()))
                .andExpect(jsonPath("$[1].firstName").value("Astha"))
               .andExpect(jsonPath("$[0].email").value("aditi.singh@gmail.com"));

    }

    @Test
    public void findEmployeebyIDTest_success() throws Exception {
        when(daoService.findAll()).thenReturn(employeeList);
        when(daoService.employeeByID(1)).thenReturn(employeeList.get(0));

        mockMvc.perform(get("/api/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value("Aditi"))
                .andExpect(jsonPath("$.email").value("aditi.singh@gmail.com"));

    }
    @Test
    public void findEmployeebyIDTest_failed() throws Exception {
        when(daoService.findAll()).thenReturn(employeeList);
        mockMvc.perform(get("/api/employee/-1"))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void createNewEmployeeTest_success() throws Exception {
        Employee newEmp = new Employee(3, "Alice", "Brown", "alice.brown@gmail.com");
        Employee savedEmp = new Employee(3, "Alice", "Brown", "alice.brown@gmail.com");
        when(daoService.CreateNewEmployee(any(Employee.class))).thenReturn(savedEmp);
       String content = objectMapper.writeValueAsString(newEmp);
        mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk());

    }
    @Test
    public void deleteEmployeeTest_success() throws Exception {
        when(daoService.findAll()).thenReturn(employeeList);
        when(daoService.deleteEmployee(1)).thenReturn(employeeList.get(0));

        mockMvc.perform(delete("/api/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.firstName").value("Aditi"))
                .andExpect(jsonPath("$.email").value("aditi.singh@gmail.com"));

    }
    @Test
    public void deleteEmployeeTest_failed() throws Exception {
        when(daoService.findAll()).thenReturn(employeeList);
        when(daoService.deleteEmployee(10)).thenReturn(employeeList.get(0));

        mockMvc.perform(delete("/api/employee/10"))
                .andExpect(status().is4xxClientError());

    }
    @Test
    public void updateEmployeesTest_success() throws Exception {
        Employee newEmp = new Employee(3, "Alice", "Brown", "alice.brown@gmail.com");
        Employee savedEmp = new Employee(3, "Alice", "Brown", "alice.brown@gmail.com");
        when(daoService.CreateNewEmployee(any(Employee.class))).thenReturn(savedEmp);
        String content = objectMapper.writeValueAsString(newEmp);
        mockMvc.perform(put("/api/employee").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk());

    }
}



