package myjdbcapp;


import java.sql.*;
import java.util.LinkedList;


public class DepWorker {
    private DBWorker dbWorker = null;
    private static String fullTableName = "employees.departments";
    //НАДО КАК-ТО ОБЕСПЕЧИТЬ ПРАВИЛЬНЫЙ DEP_NO, на случай, когда удалят последний департамент, чтобы при этом следующий департамент не получил ID удаленного
    //надо добавить статическое поле и сравнивать полученный nextDepNo с предыдущим максимумом


    public DepWorker(DBWorker dbWorker) {
        if (dbWorker == null) throw new IllegalArgumentException("Illegal value: DBWorker mustn't be null!");
        this.dbWorker = dbWorker;
    }


    public String getFullTableName() {
        return fullTableName;
    }


    private String getNextDeptNo() throws Exception {
        String currDeptNo = "";
        short maxDeptNoLength = 4;

        String sql = "select max(dept_no) as dept_no from " + fullTableName;

        try (Connection con = dbWorker.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                currDeptNo = rs.getString("dept_no");
            }
            //System.out.println("Максимальный номер департамента на данный момент=" + currDeptNo);
            currDeptNo = String.valueOf(Integer.parseInt(currDeptNo.substring(1)) + 1);

            while (maxDeptNoLength - currDeptNo.length() > 1) {
                currDeptNo = "0".concat(currDeptNo);
            }
            // System.out.println("Следующий номер=" + currDeptNo);
            return "d".concat(currDeptNo);

        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
            throw sqlExc;
        }

    }


    //returns this Department object from database with field dept_no set
    public Department insertDep(Department dep) throws Exception {
        if (dep == null) throw new IllegalArgumentException("Illegal Value: department mustn't be null");
        if (dep.getDeptName() == null || dep.getDeptName().length() == 0) {
            throw new IllegalArgumentException("Illegal Value: department name mustn't be null or empty");
        }

        String deptNo = getNextDeptNo();
        //System.out.println("Next dept_no="+deptNo);
        String sql = "insert into " + getFullTableName() + " (dept_no, dept_name) values(?, ?)";
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {
            prSt.setString(1, deptNo);
            prSt.setString(2, dep.getDeptName());
            int rowsAffected = prSt.executeUpdate();
            if (rowsAffected == 0) throw new SQLException("Operation Failed! Rows affected=0");
            return getDepByDeptNo(deptNo);
        }

    }


    public int updateDep(Department dep) throws Exception {
        if (dep == null) throw new IllegalArgumentException("Illegal Value: department mustn't be null");
        if (dep.getDeptNo() == null) {
            throw new IllegalArgumentException("Illegal Value: department number mustn't be null");
        }
        String sql = "update " + getFullTableName() + " set dept_name=? where dept_no=?";
        int rowsAffected = 0;
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {

            prSt.setString(1, dep.getDeptName());
            prSt.setString(2, dep.getDeptNo());
            rowsAffected = prSt.executeUpdate();
        } catch (SQLException SQLExc) {
            SQLExc.printStackTrace();
        }

        return rowsAffected;
    }


    public int deleteDep(Department dep) throws Exception {

        if (dep == null) {
            throw new IllegalArgumentException("Illegal Value: department mustn't be null");
        }

        return deleteDep(dep.getDeptNo());


    }


    public int deleteDep(String deptNo) throws Exception {

        if (deptNo == null || deptNo.length() == 0) {
            throw new IllegalArgumentException("Illegal Value: department num mustn't be null or empty");
        }

        String sql = "delete from " + fullTableName + " where dept_no=?";
        int rowsAffected = 0;
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {
            prSt.setString(1, deptNo);
            rowsAffected = prSt.executeUpdate();
            System.out.println(rowsAffected == 0 ? "Couldn't find such dept_no! Rows affected=0" : "deleted successfully");
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
        return rowsAffected;

    }


    //selectDeptByDeptNo
    public Department getDepByDeptNo(String deptNo) throws Exception {
        if (deptNo == null || deptNo.length() == 0) {
            throw new IllegalArgumentException("deptNo mustn't be null or empty");
        }

        String sql = "select dept_no, dept_name " +
                "from " + fullTableName +
                " where dept_no=?";
        try (Connection con = dbWorker.getConnection(); PreparedStatement prSt = con.prepareStatement(sql)) {

            prSt.setString(1, deptNo);
            ResultSet rs = prSt.executeQuery();
            if (!rs.first()) System.out.println("Couldn't find department with such dept_no");
            // System.out.println("in result set of depWorker: deptNo="+rs.getString(1)+" deptName="+rs.getString(2));
            return new Department(rs);
        }
    }


    //param - String conditions, starting from 'where clause'
    public LinkedList<Department> selectDepWhere(String conditions) throws Exception {
        if (conditions == null) throw new IllegalArgumentException("Illegal value: conditions mustn't be null!");

        String sql = "select dept_no, dept_name from " + getFullTableName() + " " + conditions;
        LinkedList<Department> depList = new LinkedList<>();

        try (Connection con = dbWorker.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Department dep = new Department(rs);
                depList.add(dep);
            }
            return depList;
        }

    }


}
