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


@WebServlet(name="BackUserTbActServlet",urlPatterns = "/BackUserTbActServlet")

public class BackUserTbActServlet extends BaseServlet {

    ///PsychologicalConsultationPlatform/BackUserTbActServlet?methodName=myStage

    /**修改状态
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myStage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //account:myaccount,stage:mynode
        if (Myallow.getMyallow().allowed(request,response)){
            String account = request.getParameter("account");
            String stageid = request.getParameter("stage");
            Message m = new Message();
            boolean flag= FactoryDao.getUserDao().changeStaffStage(stageid,account);
            if (flag==true){
                m.setMsg("success");
            }else{
                m.setMsg("false");
            }
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        }else{
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg()+"G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.getSession().removeAttribute("staff");
        }

    }

    /**重置密码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myreset(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //account:myaccount
        if (Myallow.getMyallow().allowed(request,response)){
            String account = request.getParameter("account");
            String resetPwd="123456";
            Message m = new Message();

            boolean flag= FactoryDao.getUserDao().resetStaffPwd(account,resetPwd);
            if (flag==true){
                m.setMsg("success");
            }else{
                m.setMsg("false");
            }
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        }else{
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg()+"G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.getSession().removeAttribute("staff");
        }


    }

    /**
     * 后台添加账户
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void registered(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        if (Myallow.getMyallow().allowed(request,response)){
            String role_id = request.getParameter("role_id");
        String account = request.getParameter("account");
        String myname = request.getParameter("myname");
        String pwd = request.getParameter("pwd");
        String school = request.getParameter("school");
        String staff_job = request.getParameter("staff_job");
        String domain = request.getParameter("domain");
        String background = request.getParameter("background");
        String resume = request.getParameter("resume");
        String mycost = request.getParameter("mycost");
        String sexy = request.getParameter("sexy");
        String myinsert = "'" + role_id + "','"  + account + "','" + myname + "','" + pwd
                + "','" + school + "','" + staff_job + "','"
                + domain + "','" + background + "','"
                 + resume + "','" + mycost + "','"
                 + sexy + "',"+"'1'";
        boolean flag = FactoryDao.getUserDao().addBackUser(myinsert);
        if (flag==true){
            System.out.println("添加完毕");
        }else{
            System.out.println("失败了,GG");
        }
        }else{
            Myallow.getMyallow().staffremoveSession(request,response);
        }
    }

}


