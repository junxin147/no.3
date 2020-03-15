package front.servlet;

import backgroud.servlet.BaseServlet;
import com.google.gson.Gson;
import com.net.Myallow;
import com.sample.Diagnostic;
import dao.FactoryDao;
import javabean.Message;
import javabean.Patient;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MyAppointmentServlet", urlPatterns = "/MyAppointmentServlet")

public class MyAppointmentServlet extends BaseServlet {
    /**
     * 查询领域相对应的咨询师
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void domainStaff(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String domain = request.getParameter("domain");
            Message m = new Message();
            List<Staff> mylist = FactoryDao.getFrontUser().getDoctor(domain);
            if (mylist.size() > 0) {
                m.setMsg("success");
                m.setMystafflist(mylist);

            } else {
                m.setMsg("false");
            }
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();

        } else {
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.removeAttribute("patient");

        }
    }

    /**
     * 咨询师相关资料
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void staffIfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            request.getSession().removeAttribute("staffifo");
            String name = request.getParameter("name");
            String domain = request.getParameter("domain");
            Message m = new Message();
            Staff staff = FactoryDao.getFrontUser().queryIfo(domain, name);
            if (staff != null) {
                m.setMsg("success");
                HttpSession session = request.getSession();
                session.setAttribute("staffifo", staff);
                m.setMsg("success");
                m.setStaff(staff);
            } else {
                m.setMsg("false");
            }
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        } else {
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.removeAttribute("patient");

        }
    }

    /**
     * 查询相关时间
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void stafftime(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String date = request.getParameter("date");
            Staff staff = (Staff) request.getSession().getAttribute("staffifo");
            List<com.sample.Appointment> staffhour = FactoryDao.getCalendarDao().queryDateHour(staff.getStaff_Account(), date);
            Message m = new Message();
            if (staffhour.size() > 0) {

                m.setMygetlist(staffhour);
                m.setMsg("success");
            } else {
                m.setMsg("false");
            }

            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        } else {
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.removeAttribute("patient");

        }

    }

    /**
     * 检测当前时刻是否被人预约
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myDateHour(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            Message m = new Message();
            Staff staff = (Staff) request.getSession().getAttribute("staffifo");
            String myaccount = staff.getStaff_Account();
            String mydate = request.getParameter("date");
            List<com.sample.Appointment> mygetlist = FactoryDao.getCalendarDao().queryDateHour(myaccount, mydate);
            if (mygetlist.size() > 0) {
                m.setMygetlist(mygetlist);
                m.setMsg("success");
            } else {
                m.setMsg("false");
            }

            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        } else {
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.removeAttribute("patient");

        }
    }

    /**
     * 确定预约
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myAddAppointment(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("我确认提交预约了");
        if (Myallow.getMyallow().allowed(request, response)) {
            Message m = new Message();
            String myhour = request.getParameter("mygethour");
            String textproblem = request.getParameter("mygettext");
            String mydate = request.getParameter("mydate");
            Staff staff = (Staff) request.getSession().getAttribute("staffifo");
            Patient mypatient = (Patient) request.getSession().getAttribute("patient");
            Patient patient = FactoryDao.getFrontUser().queryByUserAccount(mypatient.getPatient_Account());
            boolean flag = false;
         if (patient.getPurse()==null){
             patient.setPurse("0");
         }

            if (Integer.parseInt(patient.getPurse()) >=
                    Integer.parseInt(staff.getCost())) {
                List<com.sample.Appointment> mygetlist = FactoryDao.getCalendarDao().queryDateHour(staff.getStaff_Account(),
                        mydate);
                for (int i = 0; i < mygetlist.size(); i++) {
                    if (mygetlist.get(i).getHour().equals(myhour)) {

                        if (!("未预约".equals(mygetlist.get(i).getAppointment_Stage()) ||
                                "已终止".equals(mygetlist.get(i).getAppointment_Stage()))) {
                            m.setMsg("appointmentfalse");
                            break;
                        } else {
                            flag = FactoryDao.getFrontUser().addappointment(textproblem,
                                    patient.getPatient_Account(), "已预约",
                                    null,
                                    staff.getStaff_Name(), mydate, myhour);
                            com.sample.Appointment myid = FactoryDao.getFrontUser().queryIfo(myhour,
                                    mydate, staff.getStaff_Name());
                            String myinset = "'" + myid.getProblem_Id() + "','null','null','null'";
                            FactoryDao.getFrontUser().addproblem(myinset);
                            m.setMsg("success");
                            break;
                        }
                    }
                }

            } else {
                m.setMsg("appointmentfalse");
            }
            if (flag) {
                m.setMsg("success");
            } else {
                m.setMsg("false");
            }
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        } else {
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.removeAttribute("patient");

        }
    }

    /**
     * 查询相关信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myinfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            request.getSession().removeAttribute("myapAppointment");
            String myhour = request.getParameter("myhour");
            String mydate = request.getParameter("mydate");
            String mystaffname = request.getParameter("mystaffname");
            com.sample.Appointment myapAppointment = FactoryDao.
                    getUserDao().myinfo(mystaffname, mydate, myhour);
            request.setAttribute("myapAppointment", myapAppointment);
            HttpSession session = request.getSession();
            session.setAttribute("myapAppointment", myapAppointment);
            request.getRequestDispatcher("front/jsp/index6.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }

    /**
     * 提交评价
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void addappraise(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            com.sample.Appointment myapAppointment = (com.sample.Appointment) request.getSession().getAttribute("myapAppointment");
            String mytext = request.getParameter("mytext");
            String newdate = new Date().toLocaleString();
            boolean i = FactoryDao.getUserDao().updateDIAGNOSTIC(null, null, mytext,
                    myapAppointment.getStaff_Account(), myapAppointment.getDataday(), myapAppointment.getHour());
            boolean flag = FactoryDao.getFrontUser().addappointment(null, null,
                    "已评价", newdate, myapAppointment.getStaff_name(), myapAppointment.getDataday(), myapAppointment.getHour());
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }

    /**
     * 查看详情
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myappointmentlist(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            request.getSession().removeAttribute("myapAppointment");
            String myhour = request.getParameter("myhour");
            String mydate = request.getParameter("mydate");
            String mystaffname = request.getParameter("mystaffname");
            com.sample.Appointment myapAppointment = FactoryDao.
                    getUserDao().myinfo(mystaffname, mydate, myhour);
            request.setAttribute("myapAppointment", myapAppointment);
            HttpSession session = request.getSession();
            session.setAttribute("myapAppointment", myapAppointment);
            request.getRequestDispatcher("front/jsp/index7.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }

    public void staffinfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            request.getSession().removeAttribute("myapAppointment");
            request.getSession().removeAttribute("diagnostic");
            String myhour = request.getParameter("myhour");
            String mydate = request.getParameter("mydate");
            String mystaffname = request.getParameter("mystaffname");
            com.sample.Appointment myapAppointment = FactoryDao.
                    getUserDao().myinfo(mystaffname, mydate, myhour);
            request.setAttribute("myapAppointment", myapAppointment);
            HttpSession session = request.getSession();
            session.setAttribute("myapAppointment", myapAppointment);
            List<Diagnostic> diagnostic = FactoryDao.getFrontUser().appraiseinfo(mystaffname);
            request.setAttribute("diagnostic", diagnostic);
            request.getRequestDispatcher("front/jsp/index8.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }
}