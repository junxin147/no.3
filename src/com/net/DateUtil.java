package com.net;

import java.util.*;

public class DateUtil {


    public DateUtil() {

    }

    //获取下月的开始时间
    public Date getBeginDayOfNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth(), 1);
        return calendar.getTime();
    }


    //获取过去6个月的开始时间包括现在
    public Date getSartDayAnyMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getBeforeSixMonth(), 1);
        return calendar.getTime();
    }


    //获取本月的开始时间
    public Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return calendar.getTime();
    }

    //获取本月的结束时间
    public Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return (calendar.getTime());
    }

    //获取今年是哪一年
    public Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(Calendar.YEAR));
    }

    //获取本月是哪一月
    public int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(Calendar.MONTH) + 1;
    }
    //获取前6个月首月是哪一月
    public int getBeforeSixMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(Calendar.MONTH) -5;
    }



    //两个日期相减得到的天数
    public int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    public Date myDayWeekStar(Date date) {
        //获取本周开始时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(dayofweek + "dayofweek");
        if (dayofweek == 0) {
            dayofweek += 7;
        }

        cal.add(Calendar.DATE, 1 - dayofweek);
        Date mycal = cal.getTime();
        return mycal;
    }

    public Date weekend(Date date) {
        //获取本周结束时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDayWeekStar(date));
        cal.add(Calendar.DAY_OF_WEEK, 7);
        Date weekEndSta = cal.getTime();
        return weekEndSta;
    }

    //当天周几
    public int getWeek(Date date) {
        String week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return weekday;
    }

    public List<String> getNearDate(String isYear) {
        List<String> resultList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        if ("0".equals(isYear)) {
            int MONTH_SIZE = 6;
            //获取当前月
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String aResult = "";
            if (month < 10) {
                aResult = year + "0" + month;
            } else {
                aResult = "" + year + month;
            }
            resultList.add(aResult);
            //获取之前五个月
            for (int i = 0; i < MONTH_SIZE - 1; i++) {
                month = month - 1;
                if (0 == month) {
                    month = 12;
                    year = year - 1;
                }
                if (month < 10) {
                    aResult = year + "0" + month;
                } else {
                    aResult = "" + year + month;
                }
                resultList.add(aResult);
            }
        } else {
            //近两年
            int nowYear = cal.get(Calendar.YEAR);
            int lastYear = nowYear - 1;
            resultList.add(String.valueOf(nowYear));
            resultList.add(String.valueOf(lastYear));
        }

        return resultList;
    }

    public  int getDaysOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
