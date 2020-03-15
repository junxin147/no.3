package front.servlet;

import backgroud.servlet.BaseServlet;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.Page;
import javabean.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserPageServlet",urlPatterns = "/UserPageServlet")

public class UserPageServlet extends BaseServlet {
    /**
     * 预约管理翻页
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void userAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            System.out.println(" 预约列表+userAppointment");
        Patient patient = (Patient) request.getSession().getAttribute("patient");
        int a= FactoryDao.getFrontUser().count(patient.getPatient_Account());
        System.out.println("记录总数="+a);
        //每条记录数
        int c = 5;
        //b设置为总页数
        int b = (a > c) ? (a / c) + 1 : 1;
        int nowpage = 1;
        String pageNoStr = request.getParameter("mypage");
        System.out.println(pageNoStr + "=pageNoStr");
        if (pageNoStr != null) {
            nowpage = Integer.parseInt(pageNoStr);
        }
        List<com.sample.Appointment> array = FactoryDao.getFrontUser().queryAllAppointment(nowpage * 5 - 4,
                nowpage * 5, patient.getPatient_Account());
        Page mypage = new Page(nowpage, b);
        request.setAttribute("page",mypage);
        request.setAttribute("myarray",array);
        request.getRequestDispatcher("front/jsp/index3.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }

    /**我的资金翻页
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void mypursepage(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        if (Myallow.getMyallow().allowed(request, response)) {

            System.out.println("我进入的是用户的的资金账号");
        Patient patient=(Patient)request.getSession().getAttribute("patient");
        int a=FactoryDao.getUserDao().myQueryBuyRecordCount(null,patient.getPatient_Account());
        System.out.println("记录总数="+a);
        //每条记录数
        int c = 5;
        //b设置为总页数
        int b = (a > c) ? (a / c) + 1 : 1;
        int nowpage = 1;
        String pageNoStr = request.getParameter("mypage");
        if (pageNoStr != null) {
            nowpage = Integer.parseInt(pageNoStr);
        }
        List<com.sample.Buyrecord> array = FactoryDao.getUserDao().
                myQueryBuyRecord(nowpage * 5 - 4,
                        nowpage * 5,null,patient.getPatient_Account());
        Page mypage = new Page(nowpage, b);
        System.out.println("array.size()="+array.size());
        request.setAttribute("page",mypage);
        request.setAttribute("myarray",array);
        Patient patient1=FactoryDao.getFrontUser().queryByUserAccount(patient.getPatient_Account());
        request.getSession().setAttribute("patient", patient1);
        request.getRequestDispatcher("front/jsp/index9.jsp").forward(request, response);
    } else {
        Myallow.getMyallow().userremoveSession(request, response);
    }
    }

    /**
     * 我的报告翻页
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        if (Myallow.getMyallow().allowed(request, response)) {
            Patient patient = (Patient) request.getSession().getAttribute("patient");
            String date1 = "";
            String date2 = "";
            String score1 = "";
            String score2 = "";
            int a = FactoryDao.getReportDao().mySubjectCount(patient.getPatient_Account(),
                    date1, date2, score1, score2);
            System.out.println("记录总数=" + a);
            //每条记录数
            int c = 8;
            //b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<com.sample.ReportTable> array = FactoryDao.getReportDao().mySubject(nowpage * 8 - 7,
                    nowpage * 8, patient.getPatient_Account(), date1, date2, score1, score2);
            Page mypage = new Page(nowpage, b);
            System.out.println("array.size()=" + array.size());
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            request.getRequestDispatcher("front/jsp/index12.jsp").forward(request, response);

        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }


}
