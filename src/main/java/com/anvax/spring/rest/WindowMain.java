package com.anvax.spring.rest;

import com.anvax.spring.rest.configuration.MyConfig;
import com.anvax.spring.rest.entity.Employee;
import com.anvax.spring.rest.entity.Role;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class WindowMain {
    private UUID id;
    private String name;
    private String surname;
    private Role role;
    private String email;
    private String password;
    private String department;
    private String phonenumber;
    public String getPhonenumber() {
        return phonenumber;
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

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void main(String[] args) {
        showWindow();
    }
    public void showWindow(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication=context.getBean("communication", Communication.class);
        List<Employee> allEmployees=communication.getAllEmployees();
        String output=allEmployees.toString();
        int indexPhone,indexId;
        JLabel lbl=new JLabel();
        //JButton btn=new JButton("Submit");
        final JFrame frame=new JFrame();
        String StringRole;
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0,1));
        //JSONObject object=new JSONObject(output);
        JLabel lblGreetings=new JLabel();
        JButton btnBack=new JButton("Back");
        panel.add(lblGreetings);
        //panel.add(btn);
        panel.add(lbl);
        panel.add(btnBack);

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WindowAuthorization windowAuthorization=new WindowAuthorization();
                windowAuthorization.showWindow();
            }
        });
        System.out.println(phonenumber);
        if(output.contains(phonenumber)){
            JSONArray jsonArray=new JSONArray(output);
            System.out.println(jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                System.out.println(explrObject.toString());
                System.out.println(explrObject.getString("phonenumber"));
                if(explrObject.getString("phonenumber").equals(phonenumber)){
                    id=UUID.fromString(explrObject.getString("id"));
                    role= Role.valueOf(explrObject.getString("role"));
                    StringRole=explrObject.getString("role");
                    surname=explrObject.getString("surname");
                    name=explrObject.getString("name");
                    phonenumber=explrObject.getString("phonenumber");
                    department=explrObject.getString("department");
                    System.out.println(role);
                    lblGreetings.setText("Hello, "+name);
                    if(role==Role.valueOf(StringRole)){
                        JButton btnChangeEmps=new JButton("Change employees");
                        panel.add(btnChangeEmps);
                        JButton btnChangePhone=new JButton("Change phone number");
                        panel.add(btnChangePhone);
                        btnChangePhone.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                WindowChangePhone windowChangePhone=new WindowChangePhone();
                                windowChangePhone.setId(id);
                                windowChangePhone.setName(name);
                                windowChangePhone.setDepartment(department);
                                windowChangePhone.setRole(role);
                                windowChangePhone.setSurname(surname);
                                windowChangePhone.setPhonenumber(phonenumber);
                                windowChangePhone.setEmail(email);
                                windowChangePhone.setPassword(password);
                                windowChangePhone.showWindow();
                            }
                        });
                        btnChangeEmps.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                WindowChangeEmps windowChangeEmps=new WindowChangeEmps();
                                windowChangeEmps.setPhonenumberAdmin(phonenumber);
                                windowChangeEmps.showWindow();
                            }
                        });
                    }else{
                        JButton btnChangePhone=new JButton("Change phone number");
                        panel.add(btnChangePhone);
                        btnChangePhone.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                frame.setVisible(false);
                                WindowChangePhone windowChangePhone=new WindowChangePhone();
                                windowChangePhone.setId(id);
                                windowChangePhone.setName(name);
                                windowChangePhone.setDepartment(department);
                                windowChangePhone.setRole(role);
                                windowChangePhone.setSurname(surname);
                                windowChangePhone.setPhonenumber(phonenumber);
                                windowChangePhone.setEmail(email);
                                windowChangePhone.setPassword(password);
                                windowChangePhone.showWindow();
                            }
                        });
                    }
                }
            }

        }else{
            frame.setVisible(false);
            WindowReg windowReg=new WindowReg();
            windowReg.showWindow();
        }

        //lbl.setText(allEmployees.toString());

        /*btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });*/

    }
}
