package backgroud.servlet;

import com.google.gson.Gson;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.Message;
import javabean.Patient;
import javabean.Staff;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AppointmentServlet", urlPatterns = "/AppointmentServlet")

public class AppointmentServlet extends BaseServlet {
    /**
     * 确认方法
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void sureAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        System.out.println("我尝试确认而已罢了");
        if (Myallow.getMyallow().allowed(request, response)) {
            Message m = new Message();
            String myhour = request.getParameter("myhour");
            String mydate = request.getParameter("mydate");
            String useraccount = request.getParameter("account");
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            List<com.sample.Appointment> list = FactoryDao.getCalendarDao().queryDateHour(staff.getStaff_Account(), mydate);
            Patient patient = FactoryDao.getFrontUser().queryByUserAccount(useraccount);

            if (Integer.parseInt(patient.getPurse()) >=
                    Integer.parseInt(staff.getCost())) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getHour().equals(myhour)) {
                        //检查是否被管理员给终止掉
                        if (!"已终止".equals(list.get(i).getAppointment_Stage())) {
                            String acttime = new Date().toLocaleString();
                            boolean flag = FactoryDao.getFrontUser().addappointment(null, null,
                                    "已确认", acttime, staff.getStaff_Name(), mydate, myhour);
                          if (staff.getPurse()==null){
                              staff.setPurse("0");
                          }
                            if (patient.getPurse()==null){
                                patient.setPurse("0");
                            }
                            int mypurse = Integer.parseInt(staff.getCost()) + Integer.parseInt(staff.getPurse());
                            int user = Integer.parseInt(patient.getPurse()) - Integer.parseInt(staff.getCost());
                            boolean flag1 = FactoryDao.getUserDao().addPurse(staff.getStaff_Account(), String.valueOf(mypurse));
                            boolean flag2 = FactoryDao.getFrontUser().addPurse(useraccount, String.valueOf(user));
                            String myinsert = "'" + acttime + "',' 咨询支出','" + staff.getStaff_Name()
                                    + "','支出','" + staff.getCost() + "','" + useraccount + "'";
                            boolean flag3 = FactoryDao.getUserDao().addBuyRecord(myinsert);
                            if (flag && flag1 && flag2 && flag3) {
                                m.setMsg("success");
                            } else {
                                m.setMsg("false");
                            }

                        } else {
                            m.setMsg("updatafalse");
                        }
                    }
                }
            } else {
                String mynewdate = new Date().toLocaleString();
                boolean flag = FactoryDao.getFrontUser().addappointment(null, null,
                        "已终止", mynewdate, staff.getStaff_Name(), mydate, myhour);
                m.setMsg("costfalse");
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
            request.getSession().removeAttribute("staff");
        }

    }

    /**
     * 终止预约方法
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void cancelAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        System.out.println("我尝试终止预约罢了");
        if (Myallow.getMyallow().allowed(request, response)) {
            Message m = new Message();
            String myhour = request.getParameter("myhour");
            String mydate = request.getParameter("mydate");
            String useraccount = request.getParameter("useraccount");
            String staffname = request.getParameter("staffname");
            String mynewdate = new Date().toLocaleString();
            boolean flag = FactoryDao.getFrontUser().addappointment(null, null,
                    "已终止", mynewdate, staffname, mydate, myhour);
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
            request.getSession().removeAttribute("staff");
        }
    }

    /**
     * 查询详情
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myinfo(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            System.out.println("我进来的是个INFO信息");
            getinfo(request);
            request.getRequestDispatcher("background/jsp/index9.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 诊断详情
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void mydiagnosis(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            System.out.println("我进入的是诊断");
            getinfo(request);
            request.getRequestDispatcher("background/jsp/index4.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 管理员端获取信息
     *
     * @param request
     */
    public void mygetinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Myallow.getMyallow().allowed(request, response)) {
            request.getSession().removeAttribute("mygetappointment");
            String myhour = request.getParameter("myhour");
            String mydate = request.getParameter("mydate");
            String staffname = request.getParameter("staffname");
            String useraccount = request.getParameter("useraccount");
            com.sample.Appointment mygetappointment = FactoryDao.
                    getUserDao().myinfo(staffname, mydate, myhour);
            request.setAttribute("mygetappointment", mygetappointment);
            Patient patient = FactoryDao.getFrontUser().queryByUserAccount(useraccount);
            request.setAttribute("patient", patient);
            HttpSession session = request.getSession();
            session.setAttribute("mygetappointment", mygetappointment);
            request.getRequestDispatcher("background/jsp/index9.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }


    /**
     * 提交诊断
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void updatediagnosis(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            com.sample.Appointment MyAppointment = (com.sample.Appointment) request.getSession().getAttribute("mygetappointment");
            String diagnosis = request.getParameter("mytext");
            String newdate = new Date().toLocaleString();
            boolean i = FactoryDao.getUserDao().updateDIAGNOSTIC(diagnosis, newdate, null,
                    staff.getStaff_Account(), MyAppointment.getDataday(), MyAppointment.getHour());
            String mynewdate = new Date().toLocaleString();
            boolean flag = FactoryDao.getFrontUser().addappointment(null, null,
                    "已诊断", mynewdate, staff.getStaff_Name(), MyAppointment.getDataday(), MyAppointment.getHour());
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 封装的一个方法获取相关信息
     *
     * @param request
     */
    private void getinfo(HttpServletRequest request) {
        request.getSession().removeAttribute("mygetappointment");
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        String myhour = request.getParameter("myhour");
        String mydate = request.getParameter("mydate");
        String useraccount = request.getParameter("account");
        com.sample.Appointment mygetappointment = FactoryDao.
                getUserDao().myinfo(staff.getStaff_Name(), mydate, myhour);
        request.setAttribute("mygetappointment", mygetappointment);
        Patient patient = FactoryDao.getFrontUser().queryByUserAccount(useraccount);
        request.setAttribute("patient", patient);
        HttpSession session = request.getSession();
        session.setAttribute("mygetappointment", mygetappointment);

    }


}
