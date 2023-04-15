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
import java.util.List;
import java.util.UUID;

public class WindowChangeEmps {
    private UUID id;
    private String name;
    private String surname;
    private Role role;
    private String StringRole;
    private String department;
    private String phonenumber,phonenumberAdmin;
    private String email;
    private String password;

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
    private Object[][] array = new String[][] {{"Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент"},{ name , surname,StringRole ,phonenumber,department}};
    private Object[][] array1 = new String[][] {{ "Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент"}};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент"};
    private Object[] columnsHeader1 = new String[] {"Имя", "Фамилия",
            "Роль","Телефонный номер","Департамент","id"};
    int tmp=0;
    String delId;
    UUID dID;
    public void showWindow(){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        final Communication communication=context.getBean("communication", Communication.class);
        List<Employee> allEmployees=communication.getAllEmployees();
        String output=allEmployees.toString();
        final JTable table1 = new JTable(array, columnsHeader);
        JButton btnAdd=new JButton("Add new employee");
        JButton btnSave=new JButton("Save");
        JButton btnDel=new JButton("Delete");
        JLabel lblDel=new JLabel("Delete employee with id: ");
        final JTextField txtDel=new JTextField();

        final JFrame frame=new JFrame();
        JPanel panel=new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new GridLayout(0,1));
        //lbl.setText(allEmployees.toString());
        panel.add(table1);
        panel.add(btnAdd);



        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name= (String) table1.getValueAt(1,0);
                surname= (String) table1.getValueAt(1,1);
                role= Role.valueOf((String) table1.getValueAt(1,2));
                StringRole=(String) table1.getValueAt(1,2);
                phonenumber= (String) table1.getValueAt(1,3);
                department= (String) table1.getValueAt(1,4);
                Employee employee = new Employee();
                employee.setId(UUID.randomUUID());
                employee.setName(getName());
                employee.setDepartment(getDepartment());
                employee.setRole(getRole());
                employee.setSurname(getSurname());
                employee.setPhonenumber(getPhonenumber());
                communication.saveEmployee(employee);
                frame.setVisible(false);
                WindowChangeEmps windowChangeEmps=new WindowChangeEmps();
                windowChangeEmps.setPhonenumberAdmin(phonenumber);
                windowChangeEmps.showWindow();
            }
        });

        final JSONArray jsonArray=new JSONArray(output);
        tmp=jsonArray.length();
        Object[][] ar = new String[jsonArray.length()+1][6];
        final UUID []idar=new UUID[jsonArray.length()];
        final Role []rolear=new Role[jsonArray.length()];
        final String []srolear=new String[jsonArray.length()];
        final String []namear=new String[jsonArray.length()];
        final String []surnamear=new String[jsonArray.length()];
        final String []phar=new String[jsonArray.length()];
        final String []depar=new String[jsonArray.length()];

        ar[0][0]="Имя";
        ar[0][1]= "Фамилия";
        ar[0][2]= "Роль";
        ar[0][3]="Телефонный номер";
        ar[0][4]="Департамент";
        ar[0][5]="id";

        for (int i = 0; i < jsonArray.length(); i++) {
            int s=i+1;
            JSONObject explrObject = jsonArray.getJSONObject(i);
            idar[i]=UUID.fromString(explrObject.getString("id"));
            rolear[i]=Role.valueOf(explrObject.getString("role"));
            srolear[i]=explrObject.getString("role");
            surnamear[i]=explrObject.getString("surname");
            namear[i]=explrObject.getString("name");
            phar[i]=explrObject.getString("phonenumber");
            depar[i]=explrObject.getString("department");
            ar[s][0]=namear[i];
            ar[s][1]=surnamear[i];
            ar[s][2]=srolear[i];
            ar[s][3]=phar[i];
            ar[s][4]=depar[i];
            ar[s][5]=idar[i].toString();
        }
        final JTable table3=new JTable(ar,columnsHeader1);
        panel.add(table3);
        JButton btnBack=new JButton("Back");
        panel.add(btnSave);
        panel.add(lblDel);
        panel.add(txtDel);
        panel.add(btnDel);
        panel.add(btnBack);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tmp; i++) {
                    namear[i]= (String) table3.getValueAt(i+1,0);
                    surnamear[i]= (String) table3.getValueAt(i+1,1);
                    rolear[i]= Role.valueOf((String) table3.getValueAt(i+1,2));
                    phar[i]= (String) table3.getValueAt(i+1,3);
                    depar[i]= (String) table3.getValueAt(i+1,4);
                    Employee employee = new Employee();
                    employee.setId(idar[i]);
                    employee.setName(namear[i]);
                    employee.setDepartment(depar[i]);
                    employee.setRole(Role.valueOf(srolear[i]));
                    employee.setSurname(surnamear[i]);
                    employee.setPhonenumber(phar[i]);
                    communication.saveEmployee(employee);
                }
                frame.setVisible(false);
                WindowChangeEmps windowChangeEmps=new WindowChangeEmps();
                windowChangeEmps.setPhonenumberAdmin(phonenumber);
                windowChangeEmps.showWindow();
            }
        });
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                WindowMain windowMain=new WindowMain();
                windowMain.setPhonenumber(getPhonenumberAdmin());
                windowMain.showWindow();
            }
        });
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                delId=txtDel.getText();
                communication.deleteEmployee(UUID.fromString(delId));
                WindowChangeEmps windowChangeEmps=new WindowChangeEmps();
                windowChangeEmps.setPhonenumberAdmin(phonenumber);
                windowChangeEmps.showWindow();
            }
        });
    }
}
