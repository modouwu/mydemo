package com.example.demo.myAnnotation;

import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class TestRunner {
    @MyLog
    public void Run()
    {
        System.out.println("MyController is Run");
    }

    public static Long getTimestampToBind(String time) {

        try {
            // 构建calendar对象
            Calendar cal = Calendar.getInstance();
            // 参数非空判断
            if (!StringUtils.isEmpty(time) && time.indexOf("-") > -1) {
                // 去除前后空格并获取年月日的值
//				time = time.trim();
                String timeStr = time.trim();
                int year = Integer.parseInt(timeStr.substring(0, timeStr.indexOf("-")));
                timeStr = timeStr.substring(timeStr.indexOf("-") + 1);
                int month = Integer.parseInt(timeStr.substring(0, timeStr.indexOf("-")));
                timeStr = timeStr.substring(timeStr.indexOf("-") + 1);
                int date = 0;
                if (timeStr.length() > 2) {
                    date = Integer.parseInt(timeStr.substring(0, timeStr.indexOf(" ")));
                    timeStr = timeStr.substring(timeStr.indexOf(" ") + 1);
                } else {
                    date = Integer.parseInt(timeStr);
                    timeStr = "";
                }
                cal.set(year, month - 1, date);
                // 判断是否有时分秒的值
                if (timeStr.length() >= 2) {
                    int hour = Integer.parseInt(timeStr.substring(0, 2));
                    cal.set(Calendar.HOUR_OF_DAY, hour);
                } else {
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                }
                if (timeStr.length() >= 5) {

                    int minite = Integer.parseInt(timeStr.substring(3, 5));
                    cal.set(Calendar.MINUTE, minite);
                } else {
                    cal.set(Calendar.MINUTE, 0);
                }
                if (timeStr.length() >= 7) {
                    int seconds = Integer.parseInt(timeStr.substring(6));
                    cal.set(Calendar.SECOND, seconds);
                } else {
                    cal.set(Calendar.SECOND, 0);
                }
//				if (time.length() >= 23) {
//					int millisecond = Integer.parseInt(time.substring(20));
//					cal.set(Calendar.MILLISECOND, millisecond);
//				} else {
//					cal.set(Calendar.MILLISECOND, 0);
//				}
                return cal.getTimeInMillis();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static void main(String[] args){
        /*TestRunner a=new TestRunner();
        a.Run();*/
        for(int i=2;i<15;i++){
            System.out.println(i+":  "+Math.log(i)*7);
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        Long next=getTimestampToBind(sdf.format(date));
        System.out.println(next);
    }
}
