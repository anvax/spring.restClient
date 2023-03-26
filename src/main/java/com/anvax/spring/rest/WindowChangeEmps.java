package com.anvax.spring.rest;

import com.anvax.spring.rest.configuration.MyConfig;
import com.anvax.spring.rest.entity.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class WindowChangeEmps {
    private UUID id;
    private String name;
    private String surname;
    private String role;
    private String department;
    private String phonenumber,phonenumberAdmin;

    public String getPhonenumberAdmin() {
        return phonenumberAdmin;
    }

    public void setPhonenumberAdmin(String phonenumberAdmin) {
        this.phonenumberAdmin = phonenumberAdmin;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    }
    private Object[][] array = new String[][] {{"Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент"},{ name , surname, role ,phonenumber,department}};
    private Object[][] array1 = new String[][] {{ "Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент"}};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент"};
    public void showWindow(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        final Communication communication=context.getBean("communication", Communication.class);
        List<Employee> allEmployees=communication.getAllEmployees();
        String output=allEmployees.toString();
        final JTable table1 = new JTable(array, columnsHeader);
        JButton btnAdd=new JButton("Add new employee");
        final JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0,1));
        //lbl.setText(allEmployees.toString());
        panel.add(table1);
        panel.add(btnAdd);



        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name= (String) table1.getValueAt(0,0);
                surname= (String) table1.getValueAt(0,1);
                role= (String) table1.getValueAt(0,2);
                phonenumber= (String) table1.getValueAt(0,3);
                department= (String) table1.getValueAt(0,4);
                Employee employee = new Employee();
                employee.setId(UUID.randomUUID());
                employee.setName(getName());
                employee.setDepartment(getDepartment());
                employee.setRole(getRole());
                employee.setSurname(getSurname());
                employee.setPhonenumber(getPhonenumber());
                communication.saveEmployee(employee);
                frame.setVisible(false);
                WindowMain windowMain=new WindowMain();
                windowMain.showWindow();
            }
        });

        JSONArray jsonArray=new JSONArray(output);
        Object[][] ar = new String[jsonArray.length()+1][5];
        UUID []idar=new UUID[jsonArray.length()];
        String []rolear=new String[jsonArray.length()];
        String []namear=new String[jsonArray.length()];
        String []surnamear=new String[jsonArray.length()];
        String []phar=new String[jsonArray.length()];
        String []depar=new String[jsonArray.length()];

        ar[0][0]="Имя";
        ar[0][1]= "Фамилия";
        ar[0][2]= "Роль";
        ar[0][3]="Телефонный номер";
        ar[0][4]="Департамент";

        for (int i = 0; i < jsonArray.length(); i++) {
            int s=i+1;
            JSONObject explrObject = jsonArray.getJSONObject(i);
            idar[i]=UUID.fromString(explrObject.getString("id"));
            rolear[i]=explrObject.getString("role");
            surnamear[i]=explrObject.getString("surname");
            namear[i]=explrObject.getString("name");
            phar[i]=explrObject.getString("phonenumber");
            depar[i]=explrObject.getString("department");
            ar[s][0]=namear[i];
            ar[s][1]=surnamear[i];
            ar[s][2]=rolear[i];
            ar[s][3]=phar[i];
            ar[s][4]=depar[i];
        }
        JTable table3=new JTable(ar,columnsHeader);
        panel.add(table3);
        JButton btnBack=new JButton("Back");
        panel.add(btnBack);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WindowMain windowMain=new WindowMain();
                windowMain.setPhonenumber(phonenumberAdmin);
                windowMain.showWindow();
            }
        });

    }
}
