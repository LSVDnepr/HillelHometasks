package myjdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private String username = null;
    private String password = null;
    private String URL = null;


    public DBWorker(String username, String password, String URL) {
        if (username==null||username.length()==0)throw new IllegalArgumentException("Illegal value: username="+username);
        if (password==null||password.length()==0)throw new IllegalArgumentException("Illegal value: password="+password);
        if (URL==null||URL.length()==0)throw new IllegalArgumentException("Illegal value: URL="+URL);
        this.username = username;
        this.password = password;
        this.URL = URL;
    }

    public Connection getConnection() throws Exception {
        Connection con = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(URL, username, password);
            if (con != null) {
             //   System.out.println("Connected to DB successfully");
            } else {
                throw new Exception("Couldn't connect to DB!");
                //может, просто exit?
            }
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
            throw sqlExc;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return con;

    }



}
