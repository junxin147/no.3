package backgroud.servlet;

import com.google.gson.Gson;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserTbActServlet", urlPatterns = "/UserTbActServlet")
public class UserTbActServlet extends BaseServlet {
    ///PsychologicalConsultationPlatform/UserTbActServlet?methodName=myStage
    public void myStage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //account:myaccount,stage:mynode
        if (Myallow.getMyallow().allowed(request, response)) {
            String account = request.getParameter("account");
            String stageid = request.getParameter("stage");
            Message m = new Message();
            boolean flag = FactoryDao.getUserDao().changeStage(stageid, account);
            if (flag == true) {
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
            request.getSession().removeAttribute("staff");
        }
    }

    public void myreset(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //account:myaccount
        if (Myallow.getMyallow().allowed(request, response)) {

            String account = request.getParameter("account");
            String resetPwd = "123456";
            Message m = new Message();

            boolean flag = FactoryDao.getUserDao().resetPwd(account, resetPwd);
            if (flag == true) {
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
            request.getSession().removeAttribute("staff");
        }
    }

}