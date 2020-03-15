package backgroud.servlet;

import com.net.Myallow;
import dao.FactoryDao;
import javabean.Page;
import javabean.Patient;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PageServlet", urlPatterns = "/PageServlet")

public class PageServlet extends BaseServlet {
    /**
     * 用户列表的带条件和分页查询
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void userManagement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String username = request.getParameter("username");
            String myselect = request.getParameter("myselect");
            int a = FactoryDao.getUserDao().count(username, myselect);
            //每条记录数
            int c = 5;
            //   b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            System.out.println(pageNoStr + "=pageNoStr");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<Patient> array = FactoryDao.getUserDao().queryAllUser(nowpage * 5 - 4,
                    nowpage * 5, username, myselect);
            Page mypage = new Page(nowpage, b);
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            request.setAttribute("username", username);
            request.setAttribute("myselect", myselect);
            request.getRequestDispatcher("background/jsp/index5.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }


    }

    /**
     * 后台用户列表的带条件和分页查询
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void backUserManagement(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String username = request.getParameter("username");
            String myselect = request.getParameter("myselect");
            String jobtitle = request.getParameter("jobtitle");
            int a = FactoryDao.getUserDao().count(username, myselect, jobtitle);
            //每条记录数
            int c = 5;
            //   b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            System.out.println(pageNoStr + "=pageNoStr");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<Staff> array = FactoryDao.getUserDao().queryAllUser(nowpage * 5 - 4,
                    nowpage * 5, username, myselect, jobtitle);
            Page mypage = new Page(nowpage, b);
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            //数据回显
            request.setAttribute("username", username);
            request.setAttribute("myselect", myselect);
            request.setAttribute("jobtitle", jobtitle);
            request.getRequestDispatcher("background/jsp/index6.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }


    }

    /**
     * 咨询师预约列表带条件查询和，分页
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void appointmentManagement(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {


            String data1 = request.getParameter("data1");
            String data2 = request.getParameter("data2");
            String stage = request.getParameter("stage");
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            String account = staff.getStaff_Account();
            int a = FactoryDao.getUserDao().apointmentCount(data1, data2, stage, account);
            System.out.println("记录总数=" + a);
            //每条记录数
            int c = 5;
            //   b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<com.sample.Appointment> array = FactoryDao.getUserDao().queryAppointment(nowpage * 5 - 4,
                    nowpage * 5, data1, data2, stage, account);
            Page mypage = new Page(nowpage, b);
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            request.setAttribute("data1", data1);
            request.setAttribute("data2", data2);
            request.setAttribute("stage", stage);
            request.getRequestDispatcher("background/jsp/index3.jsp").forward(request, response);

        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 管理员端的预约连表翻页
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void backAppointment(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            System.out.println(" 预约列表+userAppointment");
            String data1 = request.getParameter("data1");
            String data2 = request.getParameter("data2");
            String staffname = request.getParameter("staffname");
            String useraccount = request.getParameter("useraccount");
            int a = FactoryDao.getUserDao().myApointmentCount(data1, data2, staffname, useraccount);
            System.out.println("记录总数=" + a);
            //每条记录数
            int c = 5;
            //b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }

            List<com.sample.Appointment> array = FactoryDao.getUserDao().
                    myqueryAppointment(nowpage * 5 - 4,
                            nowpage * 5, data1, data2, staffname, useraccount);
            Page mypage = new Page(nowpage, b);
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            request.setAttribute("data1", data1);
            request.setAttribute("data2", data2);
            request.setAttribute("staffname", staffname);
            request.setAttribute("useraccount", useraccount);
            request.getRequestDispatcher("background/jsp/index10.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 资金账户管理翻页
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void mypurse(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {


            Staff staff = (Staff) request.getSession().getAttribute("staff");
            int a = FactoryDao.getUserDao().myQueryBuyRecordCount(staff.getStaff_Name(), null);
            System.out.println("记录总数=" + a);
            int c = 5;
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<com.sample.Buyrecord> array = FactoryDao.getUserDao().
                    myQueryBuyRecord(nowpage * 5 - 4,
                            nowpage * 5, staff.getStaff_Name(), null);
            Page mypage = new Page(nowpage, b);
            System.out.println("array.size()=" + array.size());
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            Staff staff1 = FactoryDao.getUserDao().queryByAccount(staff.getStaff_Account());
            request.getSession().setAttribute("staff", staff1);
            request.getRequestDispatcher("background/jsp/index11.jsp").forward(request, response);

        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 题库管理菜单
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void mySubject(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {


            System.out.println("我进入的是题库管理");
            String domain = request.getParameter("domain");
            int a = FactoryDao.getUserDao().mySubjectCount(domain);
            System.out.println("记录总数=" + a);
            //每条记录数
            int c = 5;
            //b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<com.sample.Subject> array = FactoryDao.getUserDao().
                    mySubject(nowpage * 5 - 4,
                            nowpage * 5, domain);
            Page mypage = new Page(nowpage, b);
            System.out.println("array.size()=" + array.size());
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            request.setAttribute("domain", domain);
            request.setAttribute("count", a);

            request.getRequestDispatcher("background/jsp/index12.jsp").forward(request, response);

        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }

    }

    /**
     * 用户报告列表翻页
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myReportliset(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String date1 = request.getParameter("data1");
            String date2 = request.getParameter("data2");
            String score1 = request.getParameter("score1");
            String score2 = request.getParameter("score2");
            int a = FactoryDao.getReportDao().mySubjectCount(null,
                    date1, date2, score1, score2);
            System.out.println("记录总数=" + a);
            //每条记录数
            int c = 5;
            //b设置为总页数
            int b = (a > c) ? (a / c) + 1 : 1;
            int nowpage = 1;
            String pageNoStr = request.getParameter("mypage");
            if (pageNoStr != null) {
                nowpage = Integer.parseInt(pageNoStr);
            }
            List<com.sample.ReportTable> array = FactoryDao.getReportDao().mySubject(nowpage * 5 - 4,
                    nowpage * 5, null, date1, date2, score1, score2);
            Page mypage = new Page(nowpage, b);
            request.setAttribute("page", mypage);
            request.setAttribute("myarray", array);
            request.setAttribute("date1", date1);
            request.setAttribute("date2", date2);
            request.setAttribute("score1", score1);
            request.setAttribute("score2", score2);
            request.getRequestDispatcher("background/jsp/index15.jsp").forward(request, response);

        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

}
