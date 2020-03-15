package backgroud.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.Message;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CalendarServlet", urlPatterns = "/CalendarServlet")

public class CalendarServlet extends BaseServlet {
    /**
     * 日历添加事件
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myAddDate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String myname = request.getParameter("myname");
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            String myaccount = staff.getStaff_Account();
            String mydate = request.getParameter("mydate");
            Message m = new Message();
            com.sample.Scheduling scheduling = FactoryDao.getCalendarDao().myQueryDateID(myaccount, mydate);
            if (scheduling == null) {
                String myinsert = "'" + myaccount + "','" + myname + "','" + mydate + "'";
                boolean flag = FactoryDao.getCalendarDao().addScheduling(myinsert);
                com.sample.Scheduling myscheduling = FactoryDao.getCalendarDao().myQueryDateID(myaccount, mydate);
                String myID = myscheduling.getScheduling_Id();
                for (int i = 9; i < 12; i++) {
                    String myinsert1 = "'" + myID + "','" + i + "','未预约'";
                    boolean flag1 = FactoryDao.getCalendarDao().addAppointment(myinsert1);

                }
                for (int i = 14; i < 17; i++) {
                    String myinsert2 = "'" + myID + "','" + i + "','未预约'";
                    boolean flag2 = FactoryDao.getCalendarDao().addAppointment(myinsert2);
                }
                if (flag == true) {
                    m.setMsg("success");
                } else {
                    m.setMsg("false");
                }
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
            request.getSession().removeAttribute("staff");
            response.getWriter().flush();
        }
    }

    public void myDate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            Message m = new Message();
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            String myaccount = staff.getStaff_Account();
            List<com.sample.Scheduling> list = FactoryDao.getCalendarDao().queryMyAccountDate(myaccount);
            m.setMylist(list);
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
            request.getSession().removeAttribute("staff");
            response.getWriter().flush();
        }


    }

    /**
     * 我的某个日期时间
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
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            String myaccount = staff.getStaff_Account();
            String myname = request.getParameter("myname");
            String mydate = request.getParameter("mydate");
            List<com.sample.Appointment> mygetlist = FactoryDao.getCalendarDao().queryDateHour(myaccount, mydate);
            m.setMygetlist(mygetlist);
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
        }


    }

    /**
     * 删除日程操作
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myDeleteDate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            Message m = new Message();
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            String myaccount = staff.getStaff_Account();
            String myname = request.getParameter("myname");
            String mydate = request.getParameter("mydate");
            List<com.sample.Appointment> mygetlist = FactoryDao.getCalendarDao().queryDateHour(myaccount, mydate);
            for (int i = 0; i < mygetlist.size(); i++) {
                if (!("未预约".equals(mygetlist.get(i).getAppointment_Stage()) ||
                        "已终止".equals(mygetlist.get(i).getAppointment_Stage()))) {
                    m.setMsg("false");
                    break;
                } else {
                    m.setMsg("allow");
                }
            }
            if ("allow".equals(m.getMsg())) {
                //删除某天操作
                //如果boolean flag=false,则出现意外情况，JS提示操作异常
                boolean flag = FactoryDao.getCalendarDao().mydelete(myaccount, mydate);
                boolean flag1 = FactoryDao.getCalendarDao().mydeletescheduling(myaccount, mydate);
                if (flag == true && flag1 == true) {
                    m.setMsg("success");
                } else {
                    m.setMsg("false");
                }
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
            request.getSession().removeAttribute("staff");
            response.getWriter().flush();
        }
    }

    /**
     * 咨询师的确认保存
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myLoad(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Message m = new Message();
        if (Myallow.getMyallow().allowed(request, response)) {
            Staff staff = (Staff) request.getSession().getAttribute("staff");
            String myaccount = staff.getStaff_Account();
            String timelist = request.getParameter("timelist");
            String mydate = request.getParameter("mydate");
            System.out.println(mydate);
            //创建一个Gson对象
            Gson gson = new Gson();
            //创建一个JsonParser
            JsonParser parser = new JsonParser();
            JsonArray jarray = parser.parse(timelist).getAsJsonArray();
            System.out.println(jarray.size());
            boolean flag = FactoryDao.getCalendarDao().myloadhourdelete(myaccount, mydate);
            List<com.sample.Appointment> mylist = FactoryDao.getCalendarDao().queryDateHour(myaccount, mydate);
            com.sample.Scheduling myscheduling = FactoryDao.getCalendarDao().myQueryDateID(myaccount, mydate);
            String myID = myscheduling.getScheduling_Id();
            boolean flag1;
            boolean f = true;
            if (jarray.size() == 0 && mylist.size() == 0) {
                flag1 = FactoryDao.getCalendarDao().mydeletescheduling(myaccount, mydate);
                if (flag1) {
                    m.setMsg("deletesuccess");
                } else {
                    m.setMsg("false");
                }
            } else if (jarray.size() == 1 && mylist.size() == 1) {
                m.setMsg("success");
            } else {
                for (int i = 0; i < jarray.size(); i++) {
                    String myload = jarray.get(i).toString();
                        String myinsert = "'" + myID + "','" + jarray.get(i) + "','未预约'";
                        f = FactoryDao.getCalendarDao().addAppointment(myinsert);
                    }
                    if (f) {
                        m.setMsg("success");
                    } else {
                        m.setMsg("false");
                    }
                }
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
        } else {
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            request.getSession().removeAttribute("staff");
        }
        response.getWriter().flush();

    }

}