package servletandjdbc;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionHandler extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) servletName = "Unknown";

        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");

        if (requestUri == null) {
            requestUri = "Unknown";
        }

        res.setContentType("text/html charset=utf-8");
        PrintWriter pw = res.getWriter();
        String title = "Error/Exception Information";
        String doctype = "<!doctype html>";
        pw.print(doctype + "\n<html>\n<head>\n<title>" + title + "</title>\n</head>\n<body>");
        if (throwable == null && statusCode == null) {
            pw.println("<h2>Error information is missing</h2>");
            pw.println("Please return to the <a href=\"" + res.encodeURL("http://localhost:8888/") +
                    "\">Home Page</a>.");
        } else {
            pw.println("<h2>Error information</h2>");
            pw.println("Servlet Name : " + servletName + "<br/></br/>");
            if (statusCode != null) pw.println("The status code : " + statusCode + "<br/><br/>");
            if (throwable != null) {
                pw.println("Exception Type : " + throwable.getClass().getName() + "</br></br>");
            }
            pw.println("The request URI: " + requestUri + "<br/><br/>");
            pw.println("The exception message: " + throwable.getMessage());
        }
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }


}
