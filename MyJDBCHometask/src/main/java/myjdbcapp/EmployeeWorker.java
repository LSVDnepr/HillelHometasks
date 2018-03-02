package myjdbcapp;

import java.sql.*;
import java.util.LinkedList;

public class EmployeeWorker {
    private DBWorker dbWorker = null;
    private static String fullTableName = "employees.employees";


    public EmployeeWorker(DBWorker dbWorker) {
        if (dbWorker == null) throw new IllegalArgumentException("Illegal value: DBWorker mustn't be null!");
        this.dbWorker = dbWorker;
    }


    public String getFullTableName() {
        return fullTableName;
    }

    //returns this Employee object from database with field emp_no set by getNextEmpNo method

    public Employee insertEmp(Employee emp) throws Exception {
        if (emp == null) throw new IllegalArgumentException("Illegal Value: employee mustn't be null");
        int nextEmpNo = getNextEmpNo();
        System.out.println("next empNo=" + nextEmpNo);
        String sql = "insert into " + getFullTableName() + " (emp_no, birth_date, first_name, last_name, gender ,hire_date )" +
                " values(?, ?, ?, ?, ?, ?)";
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {
            Date birthDate = Date.valueOf(emp.getBirthDate());
            String firstName = emp.getFirstName();
            String lastName = emp.getLastName();
            String gender = emp.getGender().name();
            Date hireDate = Date.valueOf(emp.getHireDate());

            prSt.setInt(1, nextEmpNo);
            prSt.setDate(2, birthDate);
            prSt.setString(3, firstName);
            prSt.setString(4, lastName);
            prSt.setString(5, gender);
            prSt.setDate(6, hireDate);
            int rowsAffected = prSt.executeUpdate();
            if (rowsAffected == 0) throw new SQLException("Operation Failed! Rows affected=0");

            return getEmpByEmpNo(nextEmpNo);
        }

    }


    private int getNextEmpNo() throws Exception {
        String sql = "select max(emp_no) from " + getFullTableName();
        try (Connection con = dbWorker.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (!rs.first()) {
                throw new SQLException("Couldn't get emp_no info from DB");
            }
            return rs.getInt(1) + 1;
        }

    }


    public int deleteEmployee(Employee emp) throws Exception {

        if (emp == null) {
            throw new IllegalArgumentException("Illegal Value: employee mustn't be null");
        }
        return deleteEmployee(emp.getEmpNo());

    }


    public int deleteEmployee(int empNo) throws Exception {
        if (empNo == 0) {
            throw new IllegalArgumentException("Illegal Value: employee mustn't be null");
        }
        int rowsAffected = 0;
        String sql = "delete from " + fullTableName + " where emp_no=?";
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {
            prSt.setInt(1, empNo);
            rowsAffected = prSt.executeUpdate();
            System.out.println(rowsAffected == 0 ? "Couldn't find such emp_no! Rows affected=0" : "deleted successfully");
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }

        return rowsAffected;
    }


    public int updateEmp(Employee emp) throws Exception {
        if (emp == null) throw new IllegalArgumentException("Illegal Value: employee mustn't be null");
        if (emp.getEmpNo() == 0) {
            throw new IllegalArgumentException("Illegal Value: employee number equals 0");
        }
        String sql = "update " + getFullTableName() +
                " set birth_date=? , first_name=?, last_name=?, gender=?, hire_date=?" +
                "   where emp_no=?";
        int rowsAffected = 0;
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {
            prSt.setDate(1, Date.valueOf(emp.getBirthDate()));
            prSt.setString(2, emp.getFirstName());
            prSt.setString(3, emp.getLastName());
            prSt.setString(4, emp.getGender().name());
            prSt.setDate(5, Date.valueOf(emp.getHireDate()));
            prSt.setInt(6, emp.getEmpNo());

            rowsAffected = prSt.executeUpdate();
        } catch (SQLException SQLExc) {
            SQLExc.printStackTrace();
        }

        return rowsAffected;
    }


    //select Employee By EmpNo
    public Employee getEmpByEmpNo(int empNo) throws Exception {
        if (empNo == 0) {
            throw new IllegalArgumentException("Illegal value: emp_no=0. Recheck!");
        }
        String sql = "select emp_no, birth_date, first_name, last_name, gender, hire_date from " + fullTableName + " where emp_no=?";
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {

            prSt.setInt(1, empNo);
            ResultSet rs = prSt.executeQuery();
            if (!rs.first()) System.out.println("Couldn't find employee with such emp_no");
            return new Employee(rs);
        }
    }


    //param - String conditions, starting from 'where clause'
    public LinkedList<Employee> selectEmpWhere(String conditions) throws Exception {
        if (conditions == null) throw new IllegalArgumentException("Illegal value: conditions mustn't be null!");

        String sql = "select emp_no, birth_date, first_name, last_name, gender, hire_date" +
                "  from " + getFullTableName() + " " + conditions;
        LinkedList<Employee> empList = new LinkedList<>();
        try (Connection con = dbWorker.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee(rs);
                empList.add(emp);
            }
            return empList;
        }
    }


}
