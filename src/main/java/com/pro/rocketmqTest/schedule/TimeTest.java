package com.pro.rocketmqTest.schedule;

import java.util.Calendar;
import java.util.Date;

public class TimeTest {
    public static void main(String[] args) {
     String s =   getTimeDifference(1590228345618L,1590228465620L);
        System.out.println(s);
        System.out.println("==");
    }

    public static String getTimeDifference(long start,long end){
        // long timeDifference =  end - start;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(new Date(start));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date(end));
        int dayDiffer = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
        int hourDiffer = endCalendar.get(Calendar.HOUR_OF_DAY) - startCalendar.get(Calendar.HOUR_OF_DAY);
        int minuteDiffer = endCalendar.get(Calendar.MINUTE) - startCalendar.get(Calendar.MINUTE);
        int secondDiffer = endCalendar.get(Calendar.SECOND) - startCalendar.get(Calendar.SECOND);
        StringBuffer result = new StringBuffer();
        if(dayDiffer>0){
            result.append(dayDiffer+"d ");
        }
        if(hourDiffer>0){
            result.append(hourDiffer+"h ");
        }
        if(minuteDiffer>0){
            result.append(minuteDiffer+"m ");
        }
        if(secondDiffer>0){
            result.append(secondDiffer+"s");
        }
        return result.toString();
    }
}
