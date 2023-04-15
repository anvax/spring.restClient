package com.anvax.spring.rest;

import com.anvax.spring.rest.configuration.MyConfig;
import com.anvax.spring.rest.entity.Employee;
import com.anvax.spring.rest.entity.Role;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class WindowReg {
    private UUID id;
    private String name;
    private String surname;
    private Role role;
    private String department;
    private String phonenumber;
    private String email;
    private String password;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
    public void main(String[] args) {
        showWindow();
    }
    public void showWindow(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        final Communication communication=context.getBean("communication", Communication.class);
        List<Employee> allEmployees=communication.getAllEmployees();
        JButton btn=new JButton("Confirm");
        final JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0,1));
        JLabel lblname=new JLabel("Write your name: ");
        JLabel lblsurname=new JLabel("Write your surname: ");
        JLabel lblphone=new JLabel("Write your phone: ");
        JLabel lbldepartment=new JLabel("Write your department: ");
        JLabel lblEmail=new JLabel("Write your email: ");
        JLabel lblPassword=new JLabel("Write your password: ");
        final JTextField txtname=new JTextField();
        final JTextField txtsurname=new JTextField();
        final JTextField txtphone=new JTextField();
        final JTextField txtdepartment=new JTextField();
        final JTextField txtemail=new JTextField();
        final JTextField txtpassword=new JTextField();
        panel.add(lblname);
        panel.add(txtname);
        panel.add(lblsurname);
        panel.add(txtsurname);
        panel.add(lblphone);
        panel.add(txtphone);
        panel.add(lblEmail);
        panel.add(txtemail);
        panel.add(lbldepartment);
        panel.add(txtdepartment);
        panel.add(lblPassword);
        panel.add(txtpassword);
        panel.add(btn);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name=txtname.getText();
                surname=txtsurname.getText();
                phonenumber=txtphone.getText();
                department=txtdepartment.getText();
                password=txtpassword.getText();
                email=txtemail.getText();
                Employee employee = new Employee();
                employee.setId(UUID.randomUUID());
                employee.setName(getName());
                employee.setDepartment(getDepartment());
                employee.setRole(Role.valueOf("USER"));
                employee.setSurname(getSurname());
                employee.setPhonenumber(getPhonenumber());
                employee.setEmail(getEmail());
                employee.setPassword(getPassword());
                communication.saveEmployee(employee);
                frame.setVisible(false);
                WindowMain windowMain=new WindowMain();
                windowMain.setPhonenumber(phonenumber);
                windowMain.showWindow();
            }
        });

    }
}
