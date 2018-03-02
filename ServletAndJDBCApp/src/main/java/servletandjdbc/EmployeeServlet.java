package servletandjdbc;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html charset=utf-8");
        int empNo = Integer.parseInt(req.getPathInfo().substring(1));
        System.out.println(empNo);
        String fullDBresp = getDbResponse(empNo);
        PrintWriter pw = res.getWriter();
        pw.write(fullDBresp);
        pw.close();

    }


    private static String getDbResponse(int key) throws IOException {
        String dbResponse = null;

        try (Connection con = DBWorker.getConnection()) {
            dbResponse = DBWorker.getEmployeeData(con, key);
            if (dbResponse == null) throw new NullPointerException("DB response is null!!! Recheck");
            return formatDBresponse(dbResponse);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new IOException("No Data Found. There is no employee with empNo=" + key);
        }

    }


    private static String formatDBresponse(String dbresponse) {
        if ((dbresponse == null) || (dbresponse.isEmpty()))
            throw new IllegalArgumentException("Illegal value: dbresponse=" + dbresponse);
        StringBuilder fullResponse = new StringBuilder(dbresponse.length());
        String docType = "<!doctype html>";
        String title = "Employee info";
        fullResponse.append(docType).append("<html>\n");

        fullResponse.append("<head>\n<title>" + title + "</title>\n");

        fullResponse.append("<style>\n");
        fullResponse.append("table, th, td {\n");
        fullResponse.append("border: 1px solid black;\nborder-collapse: collapse;\n}");
        fullResponse.append("th, td {\npadding: 5px;\ntext-align: center;\n}");
        fullResponse.append("</style>\n</head>");

        fullResponse.append("<body>\n");
        fullResponse.append("<table style=width:100%>\n<caption><b>").append(title).append("</b></caption>\n");
        fullResponse.append(dbresponse).append("\n</table>\n");
        fullResponse.append("</body>\n</html>");

        return fullResponse.toString();
    }


}
