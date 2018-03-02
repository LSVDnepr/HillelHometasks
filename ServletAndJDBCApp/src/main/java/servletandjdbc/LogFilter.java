package servletandjdbc;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

    public class LogFilter implements Filter {


        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            String ipAddress = req.getRemoteAddr();

            // Log the IP address and current timestamp.
            File reqLogs=new File(Paths.get(".\\ServletsPlusDBReqLogs.txt").toString());

            FileWriter reqFW=new FileWriter(reqLogs.getName(),true);

            reqFW.write("IP "+ ipAddress + ", Time " + new Date().toString()+"\n");
            if (req instanceof HttpServletRequest){
                String empNo=((HttpServletRequest) req).getPathInfo().substring(1);
                reqFW.write("Getting data about empNo="+empNo+"\n");
            }

            reqFW.close();

            File resLogs=new File(Paths.get(".\\ServletsPlusDBRResLogs.txt").toString());
            FileWriter resFW=new FileWriter(resLogs.getName(),true);
            if (res instanceof HttpServletResponse){
                String response=((HttpServletResponse)res).toString();
                resFW.write("Response info: \nDate: "+new Date()+"\n");
                resFW.write("Response data:\n"+response+"\n");
            }
            resFW.close();

            chain.doFilter(req,res);
        }

}
