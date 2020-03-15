package front.servlet;

import backgroud.servlet.BaseServlet;
import com.google.gson.Gson;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.Message;
import javabean.MyMenu;
import javabean.Patient;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "FrontUserServlet", urlPatterns = "/FrontUserServlet")
public class FrontUserServlet extends BaseServlet {
    /**检查账号是否存在
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void checkAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String account = request.getParameter("name");
        Message m = new Message();
        Patient patient = FactoryDao.getFrontUser().queryByUserAccount(account);
        if (patient == null) {
            m.setMsg("success");
        } else {
            m.setMsg("false");
        }
        String sendStr = new Gson().toJson(m);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();
    }

    /**登录验证
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void login(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        String account = request.getParameter("username");
        String pass = request.getParameter("password");
        Message m = new Message();
        Patient patient = FactoryDao.getFrontUser().queryByUserAccount(account);
        if (patient != null) {
            if (pass.equals(patient.getPatient_Pwd())) {
                if (!"2".equals(patient.getPatient_Stage()) ||
                        "3".equals(patient.getPatient_Stage())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("patient", patient);
                    m.setMsg("success");
                }else{
                    m.setMsg("errorFalse");
                }

            } else {
                m.setMsg("false");
            }
        } else {
            m.setMsg("false");
        }
        System.out.println(m.getMsg());
        String sendStr = new Gson().toJson(m);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();

    }

    /**登录验证成功跳转
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void returnlogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        System.out.println("我有跳转");
       Patient patient= (Patient) request.getSession().getAttribute("patient");
        request.setAttribute("patient", patient);
        List<com.sample.Menu> getMenu = FactoryDao.getMenuDao().queryMenu(patient.getRole_Id(),"可用");
        System.out.println(getMenu.size());
        request.setAttribute("getMenu", getMenu);
        request.getRequestDispatcher("front/jsp/index4.jsp").forward(request,response);
    }

    /**注册
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void registered(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        String account = request.getParameter("account");
        String myname = request.getParameter("myname");
        String pwd = request.getParameter("pwd");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String sexy = request.getParameter("sexy");
        String mydate = new Date().toLocaleString();
        String myinsert = "'" + account + "','" + myname + "','" + pwd + "','" + sexy + "','" + age + "','" + phone + "','2','" + mydate + ",'1'";
        boolean flag = FactoryDao.getFrontUser().addpatient(myinsert);
        String contextPath = this.getServletContext().getContextPath();
        if (flag == true) {
            HttpSession session = request.getSession();
            session.setAttribute("regsuccess", "注册成功，现在你可以开始进行登录了");
            response.sendRedirect(contextPath + "/front/jsp/index2.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("regfalse", "注册失败，请重新填写检查你的信息");
            response.sendRedirect(contextPath + "/front/jsp/index1.jsp");
        }
    }

    /**用户充值方法
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void recharge(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException{
        if (Myallow.getMyallow().allowed(request,response)) {
            Patient patient = (Patient) request.getSession().getAttribute("patient");
           if (patient.getPurse()==null){
               patient.setPurse("0");
           }
            String mymoney = request.getParameter("money");
            System.out.println(mymoney + "mymoney");
            Message m = new Message();
            String acttime = new Date().toLocaleString();
            String myinsert = "'" + acttime + "','充值','" + "用户"
                    + "','收入','" + mymoney + "','" + patient.getPatient_Account() + "'";
            boolean flag = FactoryDao.getUserDao().addBuyRecord(myinsert);
            int updataNum = Integer.parseInt(patient.getPurse()) + Integer.parseInt(mymoney);
            boolean flag1 = FactoryDao.getFrontUser().addPurse(patient.getPatient_Account(), String.valueOf(updataNum));
            if (flag && flag1) {
                m.setMsg("success");
                Patient patient1 = FactoryDao.getFrontUser().queryByUserAccount(patient.getPatient_Account());
                request.getSession().setAttribute("patient", patient1);
            } else {
                m.setMsg("false");
            }
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        }else{
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
}
