package front.servlet;

import backgroud.servlet.BaseServlet;
import com.net.Myallow;
import com.sample.ReportTable;
import dao.FactoryDao;
import javabean.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserSubjectServlet", urlPatterns = "/UserSubjectServlet")

public class UserSubjectServlet extends BaseServlet {
    /**
     * 查询是否余额足够，并随机给5道题
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void mysubject(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Patient patient = (Patient) request.getSession().getAttribute("patient");
        if (Myallow.getMyallow().allowed(request, response)) {
            //评测价格为100元
            int testCost = 100;
            Patient patient1 = FactoryDao.getFrontUser().queryByUserAccount(patient.getPatient_Account());
            request.getSession().setAttribute("patient", patient1);
            if (patient.getPurse()==null){
                patient.setPurse("0");
            }
            System.out.println(patient1.getPurse());

            if (Integer.parseInt(patient1.getPurse()) > testCost) {
                String domain = request.getParameter("domain");
                List<com.sample.Subject> mylist = FactoryDao.getSubjectDao().randomSubject(domain);
                request.setAttribute("myarray", mylist);
                request.getSession().setAttribute("domain", domain);
                request.getRequestDispatcher("front/jsp/index11.jsp").forward(request, response);

            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("<script language='javascript'>alert('你的余额不足，请先充值');" +
                        " parent.window.location.reload();</script>");
            }
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }

    }

    public void mySubmit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request, response)) {
            String no_1_score = request.getParameter("1");
            String no_2_score = request.getParameter("2");
            String no_3_score = request.getParameter("3");
            String no_4_score = request.getParameter("4");
            String no_5_score = request.getParameter("5");
            String domain = (String) request.getSession().getAttribute("domain");
            int myScore = Integer.parseInt(no_1_score) + Integer.parseInt(no_2_score) + Integer.parseInt(no_3_score) +
                    Integer.parseInt(no_4_score) + Integer.parseInt(no_5_score);
            String result = "";
            String resulttext = "";
            Patient patient = (Patient) request.getSession().getAttribute("patient");
            //设置咨询一次评测花100元
            int mymoney = 100;
            String myacttime = new Date().toLocaleString();
            String myinsert = "'" + myacttime + "','评测支出','" + "系统"
                    + "','支出','" + mymoney + "','" + patient.getPatient_Account() + "'";
            boolean flag = FactoryDao.getUserDao().addBuyRecord(myinsert);
            int updataNum = Integer.parseInt(patient.getPurse()) - mymoney;
            boolean flag1 = FactoryDao.getFrontUser().addPurse(patient.getPatient_Account(), String.valueOf(updataNum));


            if (myScore <= 20 && myScore > 12) {
                if ("家庭关系".equals(domain)) {
                    result = "家庭关系优秀";
                    resulttext = "xxxxxxxxxxxxx";
                }
                if ("运动心理学".equals(domain)) {
                    result = "运动优秀";
                    resulttext = "xxxxxxxxxxxxx";
                }
                if ("教育心理学".equals(domain)) {
                    result = "教育优秀";
                    resulttext = "xxxxxxxxxxxxx";
                }

            }
            if (myScore <= 12 && myScore > 8) {
                if ("家庭关系".equals(domain)) {
                    result = "家庭关系良好";
                    resulttext = "xxxxxxxxxxxxx";
                }
                if ("运动心理学".equals(domain)) {
                    result = "运动良好";
                    resulttext = "xxxxxxxxxxxxx";
                }
                if ("教育心理学".equals(domain)) {
                    result = "教育良好";
                    resulttext = "xxxxxxxxxxxxx";
                }
            }
            if (myScore <= 8) {
                if ("家庭关系".equals(domain)) {
                    result = "家庭关系一般";
                    resulttext = "xxxxxxxxxxxxx";
                }
                if ("运动心理学".equals(domain)) {
                    result = "运动一般";
                    resulttext = "xxxxxxxxxxxxx";
                }
                if ("教育心理学".equals(domain)) {
                    result = "教育一般";
                    resulttext = "xxxxxxxxxxxxx";
                }
            }
            Date acttime = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            String mytiame = ft.format(acttime);
            String myinsert1 = "'" + result + "','" + resulttext + "','" + patient.getPatient_Account() +
                    "','" + mytiame + "','" + domain + "','" + myScore + "'";
            boolean flag2 = FactoryDao.getSubjectDao().reportInsert(myinsert1);
            com.sample.ReportTable reportTable = new ReportTable();
            reportTable.setResult(result);
            reportTable.setResult_Text(resulttext);
            request.setAttribute("reportTable", reportTable);
            request.getRequestDispatcher("front/jsp/index13.jsp").forward(request, response);
        } else {
            Myallow.getMyallow().userremoveSession(request, response);
        }
    }


}
