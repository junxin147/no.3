package com.net;

import dao.Connectionutil;
import dao.FactoryDao;
import javabean.Patient;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Myallow {
    private static Myallow myallow;

    public Myallow() {

    }
    /**
     * 懒汉式单例模式
     */
    public static synchronized Myallow getMyallow() {
        if (myallow == null) {
            myallow = new Myallow();
        }
        return myallow;
    }

    /**
     * 判断状态是否允许操作
     */
    public  boolean allowed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = true;
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        Patient patient = (Patient) request.getSession().getAttribute("patient");
        if (staff != null) {
            Staff mystaff = FactoryDao.getUserDao().queryByAccount(staff.getStaff_Account());

            if (mystaff.getStaff_Stage() != null) {
                if ("2".equals(mystaff.getStaff_Stage()) || "3".equals(mystaff.getStaff_Stage())) {
                    flag = false;
                    return flag;
                }
            }
        }
        if (patient != null) {
            Patient mypatient = FactoryDao.getFrontUser().queryByUserAccount(patient.getPatient_Account());
            if ("2".equals(mypatient.getPatient_Stage()) || "3".equals(mypatient.getPatient_Stage())) {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    public  void staffremoveSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("staff");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<script language='javascript'>alert('账号异常，请联系后台管理员');" +
                "    parent.parent.window.location.replace('background/jsp/index1.jsp');    </script>");
    }

    public  void userremoveSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("patient");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<script language='javascript'>alert('\"账号异常，请联系后台管理员');" +
                "   parent.parent.window.location.replace('front/jsp/index2.jsp');                      </script>");

    }

}
