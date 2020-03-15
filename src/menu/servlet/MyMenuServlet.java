package menu.servlet;

import backgroud.servlet.BaseServlet;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.MyMenu;
import javabean.Page;
import javabean.Patient;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "MyMenuServlet",urlPatterns = "/MyMenuServlet")
public class MyMenuServlet extends BaseServlet {
 public void userManagement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
  System.out.println("用户管理界面");
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
  String username = "";
  String myselect = "";
  int a = FactoryDao.getUserDao().count(username,myselect);
  //每条记录数
  int c = 5;
  //   b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  System.out.println(pageNoStr + "=pageNoStr");
   List<Patient> array = FactoryDao.getUserDao().queryAllUser(nowpage * 5 - 4,
          nowpage * 5,username,myselect);
  Page mypage = new Page(nowpage, b);
 request.setAttribute("page",mypage);
 request.setAttribute("myarray",array);
  request.setAttribute("username",username);
  request.setAttribute("myselect",myselect);
  request.getRequestDispatcher("background/jsp/index5.jsp").forward(request, response);
}

public void backuserManagement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
 System.out.println("我是后台用户管理界面");
 String username = "";
 String myselect = "";
 String jobtitle="";
 int a = FactoryDao.getUserDao().count(username,myselect,jobtitle);
 System.out.println("记录总数="+a);
 //每条记录数
 int c = 5;
 //   b设置为总页数
 int b = (a > c) ? (a / c) + 1 : 1;
 int nowpage = 1;
 String pageNoStr = request.getParameter("mypage");
 System.out.println(pageNoStr + "=pageNoStr");
 List<Staff> array = FactoryDao.getUserDao().queryAllUser(nowpage * 5 - 4,
         nowpage * 5,username,myselect,jobtitle);
  Page mypage = new Page(nowpage, b);
  request.setAttribute("page",mypage);
 request.setAttribute("myarray",array);
 request.setAttribute("username",username);
 request.setAttribute("myselect",myselect);
 request.setAttribute("jobtitle",jobtitle);
 request.getRequestDispatcher("background/jsp/index6.jsp").forward(request, response);

}

 public void appointment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  System.out.println(" 预约时间设置");
  request.getRequestDispatcher("background/jsp/index8.jsp").forward(request, response);
 }

 public void appointmentManagement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  System.out.println(" 预约管理设置");
//String data1, String data2, String stage, String account
  String data1 = "";
  String data2 = "";
  String stage="";
  Staff staff = (Staff) request.getSession().getAttribute("staff");
  String account=staff.getStaff_Account();
  int a=FactoryDao.getUserDao().apointmentCount(data1,data2,stage,account);
  System.out.println("记录总数="+a);
  //每条记录数
  int c = 5;
  //   b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  List<com.sample.Appointment> array = FactoryDao.getUserDao().queryAppointment(nowpage * 5 - 4,
          nowpage * 5, data1, data2, stage, account);
  Page mypage = new Page(nowpage, b);
  request.setAttribute("page",mypage);
  request.setAttribute("myarray",array);
  request.setAttribute("data1",data1);
  request.setAttribute("data2",data2);
  request.setAttribute("stage",stage);
  request.getRequestDispatcher("background/jsp/index3.jsp").forward(request, response);
 }

 /**用户的预约列表
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void userAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  System.out.println(" 预约列表+userAppointment");
  Patient patient = (Patient) request.getSession().getAttribute("patient");
   int a= FactoryDao.getFrontUser().count(patient.getPatient_Account());
  System.out.println("记录总数="+a);
  //每条记录数
  int c = 5;
  //b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  List<com.sample.Appointment> array = FactoryDao.getFrontUser().queryAllAppointment(nowpage * 5 - 4,
          nowpage * 5, patient.getPatient_Account());
  Page mypage = new Page(nowpage, b);
  request.setAttribute("page",mypage);
  request.setAttribute("myarray",array);
  request.getRequestDispatcher("front/jsp/index3.jsp").forward(request, response);
 }

 /**后台管理预约列表
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void backAppointment(HttpServletRequest request,
                             HttpServletResponse response)
         throws IOException, ServletException{
  System.out.println(" 预约列表+userAppointment");
  String data1="";
  String data2="";
  String staffname="";
  String useraccount="";
  int a= FactoryDao.getUserDao().myApointmentCount(data1,data2,staffname,useraccount);
  System.out.println("记录总数="+a);
  //每条记录数
  int c = 5;
  //b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  List<com.sample.Appointment> array = FactoryDao.getUserDao().
          myqueryAppointment(nowpage * 5 - 4,
                  nowpage * 5,data1,data2,staffname,useraccount);
  Page mypage = new Page(nowpage, b);
  request.setAttribute("page",mypage);
  request.setAttribute("myarray",array);
  request.setAttribute("data1",data1);
  request.setAttribute("data2",data2);
  request.setAttribute("staffname",staffname);
  request.setAttribute("useraccount",useraccount);
  request.getRequestDispatcher("background/jsp/index10.jsp").forward(request, response);
 }

 /**后台资金账户
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void staffMoneyRecord(HttpServletRequest request,
                             HttpServletResponse response)
         throws IOException, ServletException{
  System.out.println("我进入的是管理员的资金账号");
  Staff staff=(Staff)request.getSession().getAttribute("staff");
  int a=FactoryDao.getUserDao().myQueryBuyRecordCount(staff.getStaff_Name(),null);
  System.out.println("记录总数="+a);
  //每条记录数
  int c = 5;
  //b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  List<com.sample.Buyrecord> array = FactoryDao.getUserDao().
          myQueryBuyRecord(nowpage * 5 - 4,
                  nowpage * 5,staff.getStaff_Name(),null);
  Page mypage = new Page(nowpage, b);
  System.out.println("array.size()="+array.size());
  request.setAttribute("page",mypage);
  request.setAttribute("myarray",array);
  Staff staff1=FactoryDao.getUserDao().queryByAccount(staff.getStaff_Account());
  request.getSession().setAttribute("staff", staff1);
  request.getRequestDispatcher("background/jsp/index11.jsp").forward(request, response);

 }

 /**用户的资金账户
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void mypurse(HttpServletRequest request,
                              HttpServletResponse response)
         throws IOException, ServletException{
  System.out.println("我进入的是用户的的资金账号");
  Patient patient=(Patient)request.getSession().getAttribute("patient");
  int a=FactoryDao.getUserDao().myQueryBuyRecordCount(null,patient.getPatient_Account());
  System.out.println("记录总数="+a);
  //每条记录数
  int c = 5;
  //b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  List<com.sample.Buyrecord> array = FactoryDao.getUserDao().
          myQueryBuyRecord(nowpage * 5 - 4,
                  nowpage * 5,null,patient.getPatient_Account());
  Page mypage = new Page(nowpage, b);
  System.out.println("array.size()="+array.size());
  request.setAttribute("page",mypage);
  request.setAttribute("myarray",array);
  Patient patient1=FactoryDao.getFrontUser().queryByUserAccount(patient.getPatient_Account());
  request.getSession().setAttribute("patient", patient1);
  request.getRequestDispatcher("front/jsp/index9.jsp").forward(request, response);

 }

 /**题库管理菜单
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void mySubject(HttpServletRequest request,
                     HttpServletResponse response)
         throws IOException, ServletException{
  System.out.println("我进入的是题库管理");
  String domain="";
  int a=FactoryDao.getUserDao().mySubjectCount(domain);
  System.out.println("记录总数="+a);
  //每条记录数
  int c = 5;
  //b设置为总页数
  int b = (a > c) ? (a / c) + 1 : 1;
  int nowpage = 1;
  String pageNoStr = request.getParameter("mypage");
  List<com.sample.Subject> array = FactoryDao.getUserDao().
          mySubject(nowpage * 5 - 4,
                  nowpage * 5,domain);
  Page mypage = new Page(nowpage, b);
  System.out.println("array.size()="+array.size());
  request.setAttribute("page",mypage);
  request.setAttribute("myarray",array);
  request.setAttribute("domain",domain);
  request.setAttribute("count",a);

  request.getRequestDispatcher("background/jsp/index12.jsp").forward(request, response);
 }

 /**开始评估菜单
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void assess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  System.out.println("在线评估");
  request.getRequestDispatcher("front/jsp/index10.jsp").forward(request, response);
 }

 /**我的报告菜单
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void myReport(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  System.out.println("评估报告");
  if (Myallow.getMyallow().allowed(request,response)){
   Patient patient=(Patient)request.getSession().getAttribute("patient") ;
   String date1="";
   String date2="";
   String score1="";
   String score2="";
   int a=FactoryDao.getReportDao().mySubjectCount(patient.getPatient_Account(),
           date1,date2,score1,score2);
   System.out.println("记录总数="+a);
   //每条记录数
   int c = 8;
   //b设置为总页数
   int b = (a > c) ? (a / c) + 1 : 1;
   int nowpage = 1;
   String pageNoStr = request.getParameter("mypage");
   List<com.sample.ReportTable> array = FactoryDao.getReportDao().mySubject(nowpage * 8 - 7,
           nowpage * 8, patient.getPatient_Account(), date1, date2, score1, score2);
   Page mypage = new Page(nowpage, b);
   System.out.println("array.size()="+array.size());
   request.setAttribute("page",mypage);
   request.setAttribute("myarray",array);
   request.getRequestDispatcher("front/jsp/index12.jsp").forward(request, response);

  }else{
   Myallow.getMyallow().userremoveSession(request,response);
  }
 }

 /**用户评估列表菜单（管理员）
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void myReportliset(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  if (Myallow.getMyallow().allowed(request,response)){
   String date1="";
   String date2="";
   String score1="";
   String score2="";
   int a=FactoryDao.getReportDao().mySubjectCount(null,
           date1,date2,score1,score2);
   System.out.println("记录总数="+a);
   //每条记录数
   int c = 5;
   //b设置为总页数
   int b = (a > c) ? (a / c) + 1 : 1;
   int nowpage = 1;
   String pageNoStr = request.getParameter("mypage");
   List<com.sample.ReportTable> array = FactoryDao.getReportDao().mySubject(nowpage * 5 - 4,
           nowpage * 5, null, date1, date2, score1, score2);
   Page mypage = new Page(nowpage, b);
   request.setAttribute("page",mypage);
   request.setAttribute("date1",date1);
   request.setAttribute("date2",date2);
   request.setAttribute("score1",score1);
   request.setAttribute("score2",score2);
   request.setAttribute("myarray",array);
   request.getRequestDispatcher("background/jsp/index15.jsp").forward(request, response);


  }else{
   Myallow.getMyallow().staffremoveSession(request,response);
  }
 }

 /**用户统计
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void userCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  request.getRequestDispatcher("background/jsp/index17.jsp").forward(request,response);
 }

 /**渠道量统计
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void channelCount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  request.getRequestDispatcher("background/jsp/index18.jsp").forward(request,response);
 }

 /**权限分配菜单
  *
  * @param request
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void menuList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
  HashMap<String, Vector<MyMenu>> menuAvailableMap = FactoryDao.getMenuDao().queryByMenu("0", "可用");
  HashMap<String, Vector<MyMenu>> diableMap = FactoryDao.getMenuDao().queryByMenu("0", "不可用");
  request.setAttribute("list1",menuAvailableMap);
  request.setAttribute("list2",diableMap);
  request.getRequestDispatcher("background/jsp/index19.jsp").forward(request,response);
 }


}
