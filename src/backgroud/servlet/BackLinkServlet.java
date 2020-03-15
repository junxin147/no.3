package backgroud.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="BackLinkServlet",urlPatterns = "/BackLinkServlet")

public class BackLinkServlet extends  BaseServlet {

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        request.getSession().removeAttribute("staff");
        response.sendRedirect(contextPath+"/background/jsp/index1.jsp");

    }

    public void addnew(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        request.getRequestDispatcher("background/jsp/index7.jsp").forward(request,response);

    }


    public void linkthirteen(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String contextPath=this.getServletContext().getContextPath();
        request.getRequestDispatcher("background/jsp/index13.jsp").forward(request,response);

    }
    }
