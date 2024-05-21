package com.hd.ibrarybase.utils;

import java.util.Calendar;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/12/28
 **/
public class WeekUtils {

    public static String getWeek(int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, week);
        int day = calendar.get(Calendar.DAY_OF_WEEK); // 获取对应的周几
        String dayOfWeekString = "";
        switch (day) {
            case Calendar.SUNDAY:
                dayOfWeekString = "周日";
                break;
            case Calendar.MONDAY:
                dayOfWeekString = "周一";
                break;
            case Calendar.TUESDAY:
                dayOfWeekString = "周二";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeekString = "周三";
                break;
            case Calendar.THURSDAY:
                dayOfWeekString = "周四";
                break;
            case Calendar.FRIDAY:
                dayOfWeekString = "周五";
                break;
            case Calendar.SATURDAY:
                dayOfWeekString = "周六";
                break;
        }
        return dayOfWeekString;
    }


}
