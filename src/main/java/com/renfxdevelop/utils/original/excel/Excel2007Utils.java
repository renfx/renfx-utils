package com.renfxdevelop.utils.original.excel;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by renfx on 2016/6/24.
 */
public class Excel2007Utils {
    private Workbook workbook;

    public static Excel2007Utils install(){
        return new Excel2007Utils();
    }
    public Excel2007Utils() {
        this(100);
    }

    public Excel2007Utils(int bufferSize) {
        this.workbook = new SXSSFWorkbook(bufferSize);
    }

    private CellStyle createTitleCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private CellStyle createColTitleCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.ROYAL_BLUE.getIndex());
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.GENERAL);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    private CellStyle createDefaultCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);//单元格自动换行
        return cellStyle;
    }

    /**
     * @param cell
     * @param format
     */
    private void setCellDataFormat(XSSFCell cell, String format) {
        XSSFCellStyle cellStyle = cell.getCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(format));
        cell.setCellStyle(cellStyle);
    }

    private void setDataRecordCell(Cell cell, Object data) throws Exception {
        if(data==null){
            cell.setCellValue("");
        }else if (data instanceof String) {
            cell.setCellValue(data==null?"":String.valueOf(data));
        } else if (data instanceof Double) {
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        } else if (data instanceof Integer) {
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        } else if (data instanceof Long) {
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        } else if (data instanceof Float) {
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        } else if (data instanceof Boolean) {
            cell.setCellValue((Boolean) data);
        } else if (data instanceof Date) {
            cell.setCellValue((Date) data);
        } else {
            cell.setCellValue(String.valueOf(data));
        }
    }

    private void mergedRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 导出
     *
     * @param response
     * @param fileName    文件名
     * @param title       标题
     * @param collection  数据集合 Collection<Map>
     * @param columnNames 列头
     * @throws Exception
     */
    public void exportByList(HttpServletResponse response, String fileName, String title, Collection<? extends Map> collection, Collection<String> columnNames) throws Exception {
        Optional.ofNullable(response).orElseThrow(()->new NullPointerException("response is null"));
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1") + ".xlsx");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型
        OutputStream os = response.getOutputStream();
        exportByListCollectionMap(os, collection, title, columnNames);
    }

    /**
     * 导出 Collection<Map>里的Map的key作为列头
     *
     * @param response
     * @param fileName   文件名
     * @param title      标题
     * @param collection 数据集合 Collection<Map>
     * @throws Exception
     */
    public void exportByList(HttpServletResponse response, String fileName, String title, Collection<? extends Map> collection) throws Exception {
        Optional.ofNullable(response).orElseThrow(()->new NullPointerException("response is null"));
        fileName = Optional.ofNullable(fileName).orElse("");
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1") + ".xlsx");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型
        OutputStream os = response.getOutputStream();
        exportByList(os, collection, title);
    }

    /**
     * 用LIST<Map>里的Map的key作为列头
     *
     * @param os         流
     * @param collection 数据集合 Collection<Map>
     * @param title      标题
     * @throws Exception
     */
    public void exportByList(OutputStream os, Collection<? extends Map> collection, String title) throws Exception {
        Map<String, Object> map1 = Optional.ofNullable(collection).map(x->x.stream().findAny().get()).orElse(null);
        Set<String> columnNames = Optional.ofNullable(map1).map(x->x.keySet()).orElse(null);
        exportByListCollectionMap(os, collection, title, columnNames);
    }

    public void exportByListCollectionMap(OutputStream os, Collection<? extends Map> collection, String title, Collection<String> columnNames) throws Exception {
        Optional.ofNullable(os).orElseThrow(()->new NullPointerException("os is null"));
        collection = Optional.ofNullable(collection).orElse(new ArrayList<>());
        columnNames = Optional.ofNullable(columnNames).orElse(new ArrayList<>());
        Sheet sheet = workbook.createSheet("Sheet");
        int rowIndex = 0;
        if (title!=null && !"".equals(title)) {
            //标题
            Row titleRow = sheet.createRow(rowIndex);
            titleRow.setHeight((short) 800);
            Cell titleCell = titleRow.createCell(0);
            mergedRegion(sheet, 0, 0, 0, columnNames.size() - 1);
            titleCell.setCellStyle(createTitleCellStyle());
            titleCell.setCellValue(title);
            rowIndex++;
        }
        //设置列头名
        Row colTitleRow = sheet.createRow(rowIndex);
        colTitleRow.setHeight((short) 600);
        int colIndex = 0;
        CellStyle cellStyle = createColTitleCellStyle();
//        Map<String,Integer> maxColMap = new HashMap<>();
        for (String columnName : columnNames) {
            int columnNameBytesLength = columnName.getBytes().length;
            sheet.setColumnWidth(colIndex, columnNameBytesLength * 450);
//            maxColMap.put(columnName, columnNameBytesLength);
            Cell cell = colTitleRow.createCell(colIndex);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnName);
            colIndex++;
        }
        CellStyle defaultCellStyle = createDefaultCellStyle();
        rowIndex++;
        if (collection.size() > 0) {
            int i = 0;
            for (Map<String, Object> map : collection) {
                Row row = sheet.createRow(i + rowIndex);
                row.setHeight((short) 400);
                int cellIndex = 0;
                for (String key : columnNames) {
                    Object value = map.get(key);
//                    String stringValue = value==null?"":String.valueOf(value);
//                    int columnNameBytesLength = stringValue.getBytes().length;
//                    if(columnNameBytesLength>maxColMap.get(key)){//当前列比最大列宽
//                        maxColMap.put(key,columnNameBytesLength);
//                    }
//                    sheet.setColumnWidth(cellIndex, maxColMap.get(key) * 300);//列宽自适应

                    Cell cell = row.createCell(cellIndex);
                    cell.setCellStyle(defaultCellStyle);
                    setDataRecordCell(cell, value);
                    cellIndex++;
                }
                i++;
            }
        }
        workbook.write(os);
        os.close();
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public static void test(Object obje) {
        System.out.println(obje.getClass().getSimpleName());
    }
}
