/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util;

import com.avn.ccl.util.varlist.CommonVarList;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @Author : Office Isuru
 * @Document : DateTime
 * @Date : Jul 26, 2015 3:28:13 PM
 */
public class DateTime {

    public static String getDateString(Timestamp getdate) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(getdate);
        return date;
    }

    public static String getDateString2(Timestamp getdate) {

        String date = new SimpleDateFormat("yy-MM-dd").format(getdate);
        return date;
    }

    public static String getTimeString(Timestamp gettime) {
        String time = new SimpleDateFormat("hh:mm a").format(gettime);
        return time;
    }

    public static String getTimeFromTimestamp(Timestamp gettime) {
        String time = new SimpleDateFormat("HH:mm").format(gettime);
        return time;
    }
    public static String getDateTimeWithoutSeconds(Timestamp gettime) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(gettime);
        return time;
    }

    public static Timestamp getTimestampFromDateAndTime(String date, String time) throws ParseException {
        String concateDateTime = date + " " + time; //2014-08-12 11:23
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date consDate = sdf.parse(concateDateTime);
        return new Timestamp(consDate.getTime());
    }

    public static Timestamp getTimestampFromDateTime(String datetime) throws ParseException {
        //2014-08-12 11:23
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        Date consDate = sdf.parse(datetime);
        return new Timestamp(consDate.getTime());
    }

    public static Timestamp getTimestampFromDateAndTime(Date date, Date time) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd);
        SimpleDateFormat timeFormat = new SimpleDateFormat(CommonVarList.TIME_FORMAT_hh_mm_a);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(CommonVarList.DATE_FORMAT_yyyy_MM_dd_hh_mm_a);
        return new Timestamp(dateTimeFormat.parse(dateFormat.format(date) + " " + timeFormat.format(time)).getTime());
    }

}
