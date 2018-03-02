package myjdbcapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Employee {
    private int empNo;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate hireDate;
    private static final short fieldsCalc=6;


    public Employee(String firstName, String lastName, LocalDate birthDate, Gender gender, LocalDate hireDate) {
        if (firstName==null||firstName.length()==0)throw new IllegalArgumentException("first name mustn't be null!");
        if (lastName==null||lastName.length()==0)throw new IllegalArgumentException("last name mustn't be null!");
        if (birthDate==null||hireDate==null)throw new IllegalArgumentException("Birth date and hire date mustn't be null!");
        if (gender==null) throw new IllegalArgumentException("Gender mustn't be null!");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public Employee(ResultSet rs) throws SQLException {
        if (rs == null) throw new IllegalArgumentException("Result set is null!");
        int columnsCalc=rs.getMetaData().getColumnCount();

        if (columnsCalc < fieldsCalc) {
            throw new IllegalArgumentException("Cannot create new Employee: insufficient data in the result set!");
        }
        if (columnsCalc==fieldsCalc){
            int rsEmpNo=rs.getInt(1);
            empNo=rsEmpNo;

            LocalDate rsBirthDate=rs.getDate(2).toLocalDate();
            if (rsBirthDate==null)throw new IllegalArgumentException("Birth date mustn't be null!");
            birthDate=rsBirthDate;

            String rsFirstName=rs.getString(3);
            if (rsFirstName==null||rsFirstName.length()==0)throw new IllegalArgumentException("First name mustn't be null!");
            firstName=rsFirstName;

            String rsLastName=rs.getString(4);
            if (rsLastName==null||rsLastName.length()==0)throw new IllegalArgumentException("Last name mustn't be null!");
            lastName=rsLastName;

            Gender rsGender=Gender.valueOf(rs.getString(5));
            if (rsGender==null) throw new IllegalArgumentException("Gender mustn't be null!");
            gender=rsGender;

            LocalDate rsHireDate=rs.getDate(6).toLocalDate();
            if (rsHireDate==null)throw new IllegalArgumentException("Hire date mustn't be null!");
            hireDate=rsHireDate;

        }



    }


    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {//может, вообще убрать setter для empNo и depNo
        if (empNo==0)throw new IllegalArgumentException("Emp_no mustn't be 0");
        if (this.empNo != 0) {
            System.out.println("Employee number has been set earlier and cannot be changed. Emp.no= " + empNo);
            return;
        }

        this.empNo = empNo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate==null)throw new IllegalArgumentException("Birth date mustn't be null!");
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName==null||firstName.length()==0)throw new IllegalArgumentException("First name mustn't be null!");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName==null||lastName.length()==0)throw new IllegalArgumentException("Last name mustn't be null!");
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender==null) throw new IllegalArgumentException("Gender mustn't be null!");
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        if (hireDate==null)throw new IllegalArgumentException("Hire date mustn't be null!");
        this.hireDate = hireDate;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", hireDate=" + hireDate +
                '}';
    }
}
