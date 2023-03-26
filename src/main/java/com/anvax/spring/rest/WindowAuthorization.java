package com.anvax.spring.rest;

import com.anvax.spring.rest.configuration.MyConfig;
import com.anvax.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class WindowAuthorization
{

    public static void main( String[] args ) {
        showWindow();

        //Employee empById=communication.getEmployee(UUID.fromString("63656566-6364-6261-2d63-3863652d3131"));
       // System.out.println(empById);
        /*Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setName("Max");
        employee.setDepartment("IT");
        employee.setRole("User");
        employee.setSurname("Krist");
        employee.setPhonenumber("89385849304");
        communication.saveEmployee(employee);*/


    }
    public static void showWindow(){
        //AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        //Communication communication=context.getBean("communication", Communication.class);
        //List<Employee> allEmployees=communication.getAllEmployees();
        JLabel lblPhone=new JLabel("Enter phone");
        JLabel lbl=new JLabel();
        final JTextField txt=new JTextField();
        JButton btn=new JButton("Submit");
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
                frame.setVisible(false);
                WindowMain windowMain=new WindowMain();
                windowMain.setPhonenumber(txt.getText());
                windowMain.showWindow();
            }
        });

    }
}
