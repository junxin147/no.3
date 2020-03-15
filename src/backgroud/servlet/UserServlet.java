package backgroud.servlet;

import com.google.gson.Gson;
import dao.FactoryDao;
import javabean.Message;
import javabean.MyMenu;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    /**
     * 后台登录
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String account = request.getParameter("username");
        String pass = request.getParameter("password");
        String accounttype = request.getParameter("gettype");
        Staff staff = FactoryDao.getUserDao().queryByAccount(account);
        Message m = new Message();
        if (staff != null) {
            if (pass.equals(staff.getStaff_Pwd()) && accounttype.equals(staff.getRole_Id())) {
                if (!("2".equals(staff.getStaff_Stage()) || "3".equals(staff.getStaff_Stage()))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("staff", staff);
                    m.setMsg("success");
                } else {
                    m.setMsg("errorFalse");

                }
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
    }

    public void returnlogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //查询登录后的菜单列表
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        HashMap<String, Vector<MyMenu>> getMenu = FactoryDao.getMenuDao().queryByMenu(staff.getRole_Id(), "可用");
        request.setAttribute("getMenu", getMenu);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("background/jsp/index2.jsp").forward(request,response);
    }

    /**
     * AJAX验证后台账号是否注册过
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void checkAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String account = request.getParameter("account");
        String mytype = request.getParameter("mytype");
        System.out.println(account);
        System.out.println(mytype);
        Message m = new Message();
        Staff staff = FactoryDao.getUserDao().queryByAccount(account);

        if (staff == null) {
            m.setMsg("success");
        } else {
            m.setMsg("false");
        }
        String sendStr = new Gson().toJson(m);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();
    }

}
