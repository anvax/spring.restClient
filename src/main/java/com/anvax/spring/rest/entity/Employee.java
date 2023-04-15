package com.anvax.spring.rest.entity;



import java.util.UUID;


public class Employee {
    private UUID id;
    private String name;
    private String surname;
    private String department;
    private String phonenumber;
    private String email;
    private String password;
    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "{" +'"'+
                "id" + '"'+':'+'"'+id +'"'+
                ','+'"'+"name"+ '"'+':'+'"'+ name + '"' +
                ','+'"'+"surname"+ '"'+':'+'"'+ surname + '"' +
                ','+'"'+"role"+ '"'+':'+'"'+ role + '"' +
                ','+'"'+"department"+ '"'+':'+'"'+ department + '"' +
                ','+'"'+"phonenumber"+ '"'+':'+'"'+ phonenumber + '"' +
                '}';
    }
}
