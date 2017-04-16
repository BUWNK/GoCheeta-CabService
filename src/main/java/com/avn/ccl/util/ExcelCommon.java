/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author : Roshen Dilshan
 * @Document : ExcelCommon
 * @Date : Sep 8, 2015 10:23:10 AM
 */
public class ExcelCommon {

    /**
     * gets XSSFWorkbook and return XSSFCellStyle cell object, witch formated to
     * bold font.
     *
     * @param workbook
     * @return XSSFCellStyle
     */
    public static XSSFCellStyle getFontBoldedCell(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    /**
     * gets XSSFWorkbook and return XSSFCellStyle cell object, witch formated to
     * underlined bold font.
     *
     * @param workbook
     * @return XSSFCellStyle
     */
    public static XSSFCellStyle getFontBoldedUnderlinedCell(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setUnderline(XSSFFont.U_SINGLE);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    /**
     * gets XSSFWorkbook and return XSSFCellStyle cell object, witch formated to
     * thin border(top, right, left, bottom) and bold font.
     *
     * @param workbook
     * @return XSSFCellStyle
     */
    public static XSSFCellStyle getColumnHeadeCell(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        return style;
    }

    /**
     * gets XSSFWorkbook and return XSSFCellStyle cell object, witch formated to
     * thin border(top, right, left, bottom).
     *
     * @param workbook
     * @return XSSFCellStyle
     */
    public static XSSFCellStyle getRowColumnCell(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        return style;
    }

    /**
     * return XSSFCellSytyle witch contains the alignment according to the
     * parameter value ' short alignment ' contain and if XSSFCellStyle
     * parameter is null then it create new style with the alignment that came
     * with the alignment parameter. Parameter cellStyel is not mandatory and if
     * cellStyle not null then it clone and set the alignment. Both workbook and
     * alignment is mandatory.
     *
     * @param workbook
     * @param cellStyle
     * @param alignment
     * @return XSSFCellStyle
     */
    public static XSSFCellStyle getAligneCell(XSSFWorkbook workbook, XSSFCellStyle cellStyle, short alignment) {
        XSSFCellStyle style = workbook.createCellStyle();
        if (cellStyle != null) {
            style.cloneStyleFrom(cellStyle);
        }
        style.setAlignment(alignment);
        return style;
    }

    public static void applyStyleToColumnRange(Row row, int from, int to, XSSFCellStyle cellStyle) {
        for (int i = from; i < to; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
        }
    }

}
