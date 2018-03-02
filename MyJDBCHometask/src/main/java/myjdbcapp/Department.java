package myjdbcapp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {
    private String deptNo;
    private String deptName;
    private static final short fieldsCalc = 2;


    public Department(String deptName) {
        if (deptName == null || deptName.length() == 0) {
            throw new IllegalArgumentException("Dep. name mustn't be null or empty");
        }
        this.deptName = deptName;

    }


    public Department(ResultSet rs) throws SQLException {
        if (rs == null) throw new IllegalArgumentException("Result set is null!");
        int columnsCalc=rs.getMetaData().getColumnCount();

        if (columnsCalc < fieldsCalc) {
            throw new IllegalArgumentException("Cannot create new Department: insufficient data in the result set!");
        }
        if (columnsCalc==fieldsCalc){
            String rsDepNo=rs.getString(1);
            if (rsDepNo==null||rsDepNo.length()==0)throw new IllegalArgumentException("Dep.no mustn't be null or empty");
            String rsDepName=rs.getString(2);
            if (rsDepName==null||rsDepName.length()==0)throw new IllegalArgumentException("Dep.name mustn't be null or empty");
                deptNo=rsDepNo;
                deptName=rsDepName;
        }
    }

    public String getDeptNo() {
        return deptNo;
    }


    public void setDeptNo(String deptNo) {
        if (deptNo == null || deptNo.length() == 0)
            throw new IllegalArgumentException("Dep.no mustn't be null or empty");
        if (this.deptNo != null) {
            System.out.println("Dep. number has been set earlier and cannot be changed. Dep.no= " + deptNo);
            return;
        }
        this.deptNo = deptNo;
    }


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        if (deptName == null || deptName.length() == 0) {
            throw new IllegalArgumentException("Dep. name mustn't be null or empty");
        }
        this.deptName = deptName;
    }


    @Override
    public String toString() {
        return "Department{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
