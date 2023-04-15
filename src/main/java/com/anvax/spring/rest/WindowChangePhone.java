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

public class WindowChangePhone {
    private UUID id;
    private String name;
    private String surname;
    private Role role;
    private String department;
    private String phonenumber;
    private String email;
    private String password;

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
        JLabel lblPhone=new JLabel("Enter new phone number");
        JLabel lbl=new JLabel();
        final JTextField txt=new JTextField();
        JButton btn=new JButton("Confirm");
        final JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0,1));
        //lbl.setText(allEmployees.toString());
        panel.add(lblPhone);
        panel.add(txt);
        panel.add(btn);
        panel.add(lbl);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Employee employee = new Employee();
                employee.setId(getId());
                employee.setName(getName());
                employee.setDepartment(getDepartment());
                employee.setRole(getRole());
                employee.setSurname(getSurname());
                employee.setPhonenumber(txt.getText().toString());
                communication.updateEmployee(employee);
                frame.setVisible(false);
                WindowMain windowMain=new WindowMain();
                windowMain.setPhonenumber(txt.getText());
                windowMain.showWindow();
            }
        });

    }
}
