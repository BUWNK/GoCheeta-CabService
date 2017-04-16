/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util;

import com.avn.ccl.util.varlist.CommonVarList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.text.NumberFormat;

/**
 * @Author : Roshen Dilshan
 * @Document : Common
 * @Date : Jul 4, 2015 10:54:35 AM
 */
public class Common {

    public static Date getStartingTimeofDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getEndingTimeofDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getStartingDateTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getEndingDateTimeOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        date = calendar.getTime();
        return date;
    }

    public static Date getDateFromString(String format, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

    public static Timestamp getTimestampFromDateAndTime(String format, String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date consDate = sdf.parse(datetime);
        return new Timestamp(consDate.getTime());
    }

    public static String getStringFormatDate(String format, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * return String value if parameter contain null or empty then it returns 0
     * else it return the value contain in the parameter.
     *
     * @param value
     * @return String
     */
    public static String replaceEmtyorNullToZero(String value) {
        String returnValue = "0";
        if (value != null && !value.trim().isEmpty()) {
            returnValue = value;
        }
        return returnValue;
    }

    /**
     * gets String value as a parameter and return related String value. If
     * String is 'null' or empty then it returns String "--" value.
     *
     * @param str
     * @return String
     */
    public static String replaceEmptyorNullString(String str) {
        if (str == null || str.trim().isEmpty()) {
            str = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE;
        }
        return str;
    }

    /**
     * gets Object value as a parameter and return the related String value. If
     * Object is 'null' or empty then it returns String "--" value.
     *
     * @param object
     * @return String
     */
    public static String replaceEmptyorNullObjectString(Object object) {
        String value = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE;
        if (object != null && !(object.toString()).trim().isEmpty()) {
            value = object.toString();
        }
        return value;
    }

    /**
     * gets String value as a parameter and return related String value. If
     * String is 'null' or empty then it returns String "All" value.
     *
     * @param string
     * @return String
     */
    public static String replaceEmptyorNullStringToAll(String string) {
        String value = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_ALL;
        if (string != null && !string.trim().isEmpty()) {
            value = string;
        }
        return value;
    }

    /**
     * gets String value as a parameter and return related String value. If
     * String is 'null' or empty then it returns String "N/A" value.
     *
     * @param string
     * @return String
     */
    public static String replaceEmptyorNullStringToNA(String string) {
        String value = CommonVarList.EMPTY_OR_NULL_REPLACE_VALUE_NA;
        if (string != null && !string.trim().isEmpty()) {
            value = string;
        }
        return value;
    }

    public static ByteArrayOutputStream zipFiles(File[] listFiles) throws Exception {
        byte[] buffer;
        ByteArrayOutputStream outputStream = null;
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(outputStream);
            for (File file : listFiles) {
                buffer = new byte[(int) file.length()];
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(buffer, 0, (int) file.length());
                ZipEntry ze = new ZipEntry(file.getName());

                zipOutputStream.putNextEntry(ze);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
        return outputStream;
    }

    public static ByteArrayOutputStream zipFiles(List<File> listFiles) throws Exception {
        byte[] buffer;
        ByteArrayOutputStream outputStream = null;
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(outputStream);
            for (File file : listFiles) {
                buffer = new byte[(int) file.length()];
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(buffer, 0, (int) file.length());
                ZipEntry ze = new ZipEntry(file.getName());

                zipOutputStream.putNextEntry(ze);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
        return outputStream;
    }

    public static String getStringFormatDateandTime(String format, Timestamp date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String getFirstCharcterCapital(String name) {

        String text = name.toLowerCase();
        String capitalize = text.substring(0, 1).toUpperCase();
        String Name = capitalize + text.substring(1);

        return Name;
    }

    public static String sum(long value) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        return nf.format(value).trim();
    }

    public static Timestamp getTimestampFromDateTime24(Date date) throws ParseException {
        //2014-08-12 11:23
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date consDate = sdf.parse(sdf.format(date.getTime()));
        return new Timestamp(consDate.getTime());
    }

    public static String getMonthAndDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        String formattedDate = null;

        formattedDate = sdf.format(date).toUpperCase() + " " + date.getDate();

        return formattedDate;
    }
}
