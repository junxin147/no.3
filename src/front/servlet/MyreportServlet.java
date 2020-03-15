package front.servlet;

import backgroud.servlet.BaseServlet;
import com.net.Myallow;
import dao.FactoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyreportServlet",urlPatterns = "/MyreportServlet")

public class MyreportServlet extends BaseServlet {

    /**查看我的报告
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        if (Myallow.getMyallow().allowed(request,response)){
            String myID=request.getParameter("myid");
            System.out.println(myID);
            com.sample.ReportTable reportTable= FactoryDao.getReportDao().queryById(myID);
            request.setAttribute("reportTable",reportTable);
            request.getRequestDispatcher("front/jsp/index13.jsp").forward(request,response);
        }else{
            Myallow.getMyallow().userremoveSession(request,response);
        }

    }
}
