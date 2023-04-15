package com.anvax.spring.rest;

import com.anvax.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL="http://localhost:8080/api/v1/controller/employees";
    public List<Employee> getAllEmployees(){
        ResponseEntity<List<Employee>> responseEntity=
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
        List<Employee> allEmployees=responseEntity.getBody();
        return allEmployees;
    }
    public Employee getEmployee(UUID id){
        Employee employee=restTemplate.getForObject(URL+"/"+id,Employee.class);
        return employee;
    }
    public void saveEmployee(Employee employee){
        UUID uuid=employee.getId();
        if(uuid==null){
            ResponseEntity<String> responseEntity=restTemplate.postForEntity(URL,employee,String.class);
            System.out.println("New emp was added to DB");
            System.out.println(responseEntity.getBody());
        }else{
            restTemplate.put(URL,employee);
            System.out.println("Emp with id "+uuid+" was updated");
        }

    }
    public void updateEmployee(Employee employee){
        UUID uuid=employee.getId();
        restTemplate.put(URL,employee);
        System.out.println("Emp with id "+uuid+" was updated");
    }
    public void deleteEmployee(UUID id){
        restTemplate.delete(URL+"/"+id);
        System.out.println("Emp with id "+id+" was deleted");

    }
}
