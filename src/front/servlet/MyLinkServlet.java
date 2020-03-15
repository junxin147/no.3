package front.servlet;

import backgroud.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "MyLinkServlet",urlPatterns = "/MyLinkServlet")

public class MyLinkServlet extends BaseServlet {
    public void loginLink(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        response.sendRedirect(contextPath+"/front/jsp/index2.jsp");
    }
    public void regLink(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        response.sendRedirect(contextPath+"/front/jsp/index1.jsp");
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        request.getSession().removeAttribute("patient");
        response.sendRedirect(contextPath+"/front/jsp/index2.jsp");
    }

    public void myapp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        request.getRequestDispatcher("front/jsp/index5.jsp").forward(request,response);

    }
}
