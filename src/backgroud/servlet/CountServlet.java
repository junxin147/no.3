package backgroud.servlet;

import com.google.gson.Gson;
import com.net.DateUtil;
import dao.FactoryDao;
import javabean.MyCount;
import javabean.MydayData;
import javabean.Patient;
import javabean.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CountServlet", urlPatterns = "/CountServlet")

public class CountServlet extends BaseServlet {
    public void myweek(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ParseException {
        Date date = new Date();
        DateUtil dateUtil = new DateUtil();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date mystar = dateUtil.myDayWeekStar(date);
        Date myend = dateUtil.weekend(date);
        String weekstar = ft.format(mystar);
        String weekend = ft.format(myend);
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        List<Patient> list = FactoryDao.getMyCountDao().queryAllUser(weekstar, weekend);
        for (int i = 0; i < list.size(); i++) {
            Date regdate = ft.parse(list.get(i).getPatient_Regtime());
            if (regdate.compareTo(myend) < 0) {

                if (dateUtil.getDiffDays(regdate, myend) == 1) {
                    count7++;
                    System.out.println("我有进来7");
                }
                if (dateUtil.getDiffDays(regdate, myend) == 2) {
                    System.out.println("我有进来6");
                    count6++;
                }
                if (dateUtil.getDiffDays(regdate, myend) == 3) {
                    System.out.println("我有进来5");
                    count5++;
                }
                if (dateUtil.getDiffDays(regdate, myend) == 4) {
                    System.out.println("我有进来4");
                    count4++;
                }
                if (dateUtil.getDiffDays(regdate, myend) == 5) {
                    System.out.println("我有进来3");
                    count3++;
                }

                if (dateUtil.getDiffDays(regdate, myend) == 6) {
                    System.out.println("我有进来2");
                    count2++;
                }
                if (dateUtil.getDiffDays(regdate, myend) == 7) {
                    System.out.println("我有进来1");
                    count1++;
                }
            }
        }
        ArrayList<MydayData> arrayList = new ArrayList<>();
        arrayList.add(new MydayData("周一", count1));
        arrayList.add(new MydayData("周二", count2));
        arrayList.add(new MydayData("周三", count3));
        arrayList.add(new MydayData("周四", count4));
        arrayList.add(new MydayData("周五", count5));
        arrayList.add(new MydayData("周六", count6));
        arrayList.add(new MydayData("周天", count7));
        String sendStr = new Gson().toJson(arrayList);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();

    }

    public void mymonth(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ParseException {

        DateUtil dateUtil = new DateUtil();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date mystar = dateUtil.getBeginDayOfMonth();
        Date thismonthend = dateUtil.getEndDayOfMonth();
        Date myend = dateUtil.getBeginDayOfNextMonth();
        String star = ft.format(mystar);
        String end = ft.format(myend);
        int weekday = dateUtil.getWeek(mystar);
        //默认首周跟首周的周天的差距天数
        int diffyday = 0;
        //1为周天
        if (weekday == 1) {
            diffyday = 1;

        } else if (weekday == 2) { //2为周1
            diffyday = 7;

        } else if (weekday == 3) {   //3为周2
            diffyday = 6;

        } else if (weekday == 4) {    //4为周3
            diffyday = 5;

        } else if (weekday == 5) {  //5为周4
            diffyday = 4;

        } else if (weekday == 6) {    //6为周5
            diffyday = 3;

        } else if (weekday == 7) {//7为周6
            diffyday = 2;
        }
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        System.out.println(diffyday + "diffyday");
        List<Patient> list = FactoryDao.getMyCountDao().queryAllUser(star, end);
        for (int i = 0; i < list.size(); i++) {
            Date regdate = ft.parse(list.get(i).getPatient_Regtime());
            if (regdate.compareTo(myend) < 0) {
                if (dateUtil.getDiffDays(mystar, regdate) >= 0 &&
                        dateUtil.getDiffDays(mystar, regdate) < diffyday) {
                    System.out.println(regdate);
                    System.out.println(dateUtil.getDiffDays(mystar, regdate) + "=1");
                    count1++;
                }
                if (dateUtil.getDiffDays(mystar, regdate) >= diffyday &&
                        dateUtil.getDiffDays(mystar, regdate) < diffyday + 6) {
                    System.out.println(regdate + "好哈");
                    System.out.println(dateUtil.getDiffDays(mystar, regdate) + "=2");
                    count2++;
                }
                if (dateUtil.getDiffDays(mystar, regdate) >= diffyday + 6 &&
                        dateUtil.getDiffDays(mystar, regdate) < diffyday + 13) {
                    System.out.println(regdate);
                    System.out.println(dateUtil.getDiffDays(mystar, regdate) + "=3");
                    count3++;
                }
                if (dateUtil.getDiffDays(mystar, regdate) >= diffyday + 13 &&
                        dateUtil.getDiffDays(mystar, regdate) < diffyday + 20) {
                    System.out.println(regdate);
                    System.out.println(dateUtil.getDiffDays(mystar, regdate) + "=4");
                    count4++;
                }
                if (diffyday < 6) {
                    if (dateUtil.getDiffDays(mystar, regdate) >= diffyday + 20 &&
                            dateUtil.getDiffDays(mystar, regdate) < diffyday + 27) {
                        System.out.println(regdate);
                        count5++;
                        System.out.println(dateUtil.getDiffDays(mystar, regdate) + "=5");
                    }
                    if (dateUtil.getDiffDays(mystar, regdate) >= diffyday + 27) {
                        if (dateUtil.getDiffDays(mystar, myend) > diffyday + 28 + 1) {
                            System.out.println(regdate);
                            System.out.println(dateUtil.getDiffDays(mystar, regdate) + "=6");
                            count6++;
                        }
                    }
                }
            }
        }
        ArrayList<MydayData> arrayList = new ArrayList<>();
        arrayList.add(new MydayData("第一周", count1));
        arrayList.add(new MydayData("第二周", count2));
        arrayList.add(new MydayData("第三周", count3));
        arrayList.add(new MydayData("第四周", count4));
        if (diffyday < 6) {
            arrayList.add(new MydayData("第五周", count5));
            if ((dateUtil.getDiffDays(mystar, myend) - 28 - diffyday - 1) > 0) {
                arrayList.add(new MydayData("第六周", count6));
            }
        }
        String sendStr = new Gson().toJson(arrayList);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();


    }


    public void myyear(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ParseException {
        Date date = new Date();
        DateUtil dateUtil = new DateUtil();
        SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ft2 = new SimpleDateFormat("yyyyMM");
        List<String> list = dateUtil.getNearDate("0");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        Date date1 = dateUtil.getSartDayAnyMonth();
        String star = ft1.format(date1);
        Date enddate = dateUtil.getBeginDayOfNextMonth();
        String end = ft1.format(enddate);
        List<Patient> list1 = FactoryDao.getMyCountDao().queryAllUser(star, end);
        for (int i = 0; i < list1.size(); i++) {
            Date regdate = ft1.parse(list1.get(i).getPatient_Regtime());
            String regdate1 = ft2.format(regdate);
            if (regdate1.equals(list.get(5))) {
                count1++;
            }
            if (regdate1.equals(list.get(4))) {
                count2++;
            }
            if (regdate1.equals(list.get(3))) {
                count3++;
            }
            if (regdate1.equals(list.get(2))) {
                count4++;
            }
            if (regdate1.equals(list.get(1))) {
                count5++;
            }
            if (regdate1.equals(list.get(0))) {
                count6++;
            }


        }
        ArrayList<MydayData> arrayList = new ArrayList<>();
        arrayList.add(new MydayData(list.get(5), count1));
        arrayList.add(new MydayData(list.get(4), count2));
        arrayList.add(new MydayData(list.get(3), count3));
        arrayList.add(new MydayData(list.get(2), count4));
        arrayList.add(new MydayData(list.get(1), count5));
        arrayList.add(new MydayData(list.get(0), count6));
        String sendStr = new Gson().toJson(arrayList);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();
    }








    public void channelCount(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ParseException{
        String data1=request.getParameter("data1");
        String data2=request.getParameter("data2");
        System.out.println(data1);
        List<Staff> myarray = FactoryDao.getMyCountDao().mydoctor();
        ArrayList<MydayData> arrayList = new ArrayList<>();

        for (int i = 0; i <myarray.size() ; i++) {
            String staffname=myarray.get(i).getStaff_Name();
            int count = FactoryDao.getMyCountDao().channelCount(data1, data2, staffname);
            arrayList.add(new MydayData(staffname,count));
        }


        String sendStr = new Gson().toJson(arrayList);
        response.setContentType("text/html; charset =utf-8");
        response.getWriter().write(sendStr);
        response.getWriter().flush();


    }
}
