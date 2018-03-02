package servletandjdbc;


import java.sql.*;

public class DBWorker {
    private static String username = "username";
    private static String password = "password";
    private static String URL = "jdbc:mysql://localhost:3306/employees";


    /*  Работаю с базой данных https://github.com/datacharmer/test_db
    сервлет намапплен на localhost:8888/empData/
    далее вводится номер сотрудника, например 10001 и в браузере отображается сводная таблица всех данных из всех таблиц базы данных
    по данному конкретному сотруднику
    Также выполняется перехват запроса и ответа с помощью фильтра. Данные запорса и ответа логируются в соответствующие файлы
    Настроен error handler, который перехватывает исключения и выводит данные о них в браузер
     */

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(URL, username, password);
            if (connection != null) {
                System.out.println("Connected to DB Successfully!");
            } else {
                System.out.println("Couldn't connect to DB");
                System.exit(0);
            }

        } catch (SQLException se) {
            se.printStackTrace();
            throw se;
        }
        return connection;
    }


    public static String getEmployeeData(Connection connection, int empNo) throws SQLException {
        if (connection == null) throw new IllegalArgumentException("Connection mustn't be null. Recheck!");
        StringBuilder data = new StringBuilder();
        String sql = "select ee.emp_no, ee.first_name, ee.last_name, ee.gender, ee.hire_date, avg(es.salary) as avg_salary, d.dept_no, d.dept_name\n" +
                "from employees.employees as ee\n" +
                "join employees.salaries as es\n" +
                "on ee.emp_no=es.emp_no\n" +
                "join employees.dept_emp as de\n" +
                "on ee.emp_no=de.emp_no\n" +
                "join employees.departments as d\n" +
                "on de.dept_no=d.dept_no\n" +
                "where ee.emp_no=" + empNo + "\n" +
                "group by ee.emp_no";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            int calc = rs.getMetaData().getColumnCount();
            data.append("<tr>\n");
            for (int i = 1; i <= calc; i++) { //записываю в response названия столбцов таблицы
                data.append("<th>" + rs.getMetaData().getColumnLabel(i) + "</th>\n");
            }
            data.append("</tr>\n");

            if (rs.next()) {
                data.append("<tr>\n");
                for (int i = 1; i <= calc; i++) {
                    data.append("<td>" + rs.getString(i) + "</td>\n");
                }
                data.append("</tr>\n");
            } else {
                throw new SQLException("No Data found! There is no employee with empNo=" + empNo);
            }

            System.out.println(data);
            return data.toString();
        } catch (SQLException se) {
            se.printStackTrace();
            throw se;
        }
    }


    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) con.close();
    }


}
