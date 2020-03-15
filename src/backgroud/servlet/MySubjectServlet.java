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

@WebServlet(name = "MySubjectServlet", urlPatterns = "/MySubjectServlet")

public class MySubjectServlet extends BaseServlet {
    /**
     * 删除题目
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            String subject_Id = request.getParameter("subject_Id");
            Message m = new Message();
            boolean flag = FactoryDao.getSubjectDao().deleteSubject(subject_Id);
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
     * 获取准备要修改的题目信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void updataInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {

            request.getSession().removeAttribute("subject");
            String subject_Id = request.getParameter("subject_Id");

            com.sample.Subject subject = FactoryDao.getSubjectDao().subjectInfo(subject_Id);
            request.setAttribute("subject", subject);
            request.getSession().setAttribute("subject", subject);
            request.getRequestDispatcher("background/jsp/index14.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 实现修改数据操作
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myUpdata(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            com.sample.Subject subjecj = (com.sample.Subject) request.getSession().getAttribute("subject");
            String optionA = request.getParameter("a");
            String optionB = request.getParameter("b");
            String optionC = request.getParameter("c");
            String optionD = request.getParameter("d");
            String domain = request.getParameter("domain");
            String mytext = request.getParameter("mytext");
            boolean flag = FactoryDao.getSubjectDao().updataInfo(subjecj.getSubject_Id(),
                    mytext, domain, optionA, optionB, optionC, optionD);

        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }
    }

    /**
     * 实现 新增题目功能
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myInsert(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String optionA = request.getParameter("a");
            String optionB = request.getParameter("b");
            String optionC = request.getParameter("c");
            String optionD = request.getParameter("d");
            String domain = request.getParameter("domain");
            String mytext = request.getParameter("mytext");
        String myinsert="'"+mytext+"','"+domain+"','"+optionA+"','"+optionB+"','"+
                optionC+"','"+optionD+"'";
        boolean flag=FactoryDao.getSubjectDao().myInsert(myinsert);

        } else {
            Myallow.getMyallow().staffremoveSession(request, response);
        }

    }

    /**获取评测报告
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void myReport(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        if (Myallow.getMyallow().allowed(request,response)){
            String myID=request.getParameter("myid");
            System.out.println(myID);
            com.sample.ReportTable reportTable= FactoryDao.getReportDao().queryById(myID);
            request.setAttribute("reportTable",reportTable);
            request.getRequestDispatcher("background/jsp/index16.jsp").forward(request,response);
        }else{
            Myallow.getMyallow().staffremoveSession(request,response);
        }
    }
}
