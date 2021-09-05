package com.hongkai.utils;

import com.hongkai.enums.ExcelName;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {
    // 导出时excel的扩展名
    public static final String EXTENSION_NAME = ".xlsx";
    // 03版excel扩展名
    public static final String XLS = ".xls";
    // 07版excel扩展名
    public static final String XLSX = ".xlsx";

    private static Logger logger = Logger.getLogger(ExcelUtils.class);


    /**
     * 获取单元格内容
     *
     * @param cell
     * @return
     */
    public static String getContent(Cell cell) {
        String cellValue = null;
        if(cell==null){
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            // 数字
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            // 字符串
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            // Boolean
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            // 空值
            case BLANK:
                cellValue = "";
                break;
            // 错误
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean fileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    /**
     * 判断sheet是否存在
     * @param filePath
     * @param sheetName
     * @return
     */
    public static boolean sheetExist(String filePath, String sheetName) {
        boolean flag = false;
        File file = new File(filePath);
        if (file.exists()) {
            try {
                // 得到文件的资源输入流
                InputStream input = new FileInputStream(file);
                // 得到处理excel的Workbook对象
                Workbook workbook = ExcelUtils.getWorkbookByExtensionName(input, filePath);
                if (workbook.getSheet(sheetName) != null) {
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * 创建有titels的excel
     * @param fileOutPath
     * @param titels
     */
    public static void createExcel(String fileOutPath, ArrayList<String> titels) throws Exception {
        Workbook workbook = null;
        int index = fileOutPath.lastIndexOf(".");
        String suffix = fileOutPath.substring(index);
        logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> filePath指定的文件扩展名为 = " + suffix);
        if (ExcelUtils.XLS.equals(suffix)) {
            workbook = new HSSFWorkbook();
        } else if (ExcelUtils.XLSX.equals(suffix)) {
            workbook = new XSSFWorkbook();
        } else {
            logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> 异常信息：filePath指定的文件扩展名不是excel文件格式(只能是.xls和.xlsx格式)");
            throw new RuntimeException("异常信息：filePath指定的文件扩展名不是excel文件格式(只能是.xls和.xlsx格式)");
        }
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row=sheet.createRow(0);
        for (int i = 0; i < titels.size(); i++) {
            Cell cell=row.createCell(i);
            cell.setCellValue(titels.get(i));
        }
        FileOutputStream out=new FileOutputStream(fileOutPath);
        workbook.write(out);

        out.close();
    }

    /**
     * 向指定excel中追加内容
     * @param fileOutPath
     * @param sheetName
     * @param object
     * @throws Exception
     */
    public void writeToExcel(String fileOutPath,String sheetName,Object object) throws Exception {
        Workbook workbook = null;
        File file = new File(fileOutPath);
        InputStream input = new FileInputStream(file);
        int index = fileOutPath.lastIndexOf(".");
        String suffix = fileOutPath.substring(index);
        logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> filePath指定的文件扩展名为 = " + suffix);
        if (ExcelUtils.XLS.equals(suffix)) {
            workbook = new HSSFWorkbook(input);
        } else if (ExcelUtils.XLSX.equals(suffix)) {
            workbook = new XSSFWorkbook(input);
        } else {
            logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> 异常信息：filePath指定的文件扩展名不是excel文件格式(只能是.xls和.xlsx格式)");
            throw new RuntimeException("异常信息：filePath指定的文件扩展名不是excel文件格式(只能是.xls和.xlsx格式)");
        }

        Sheet sheet = workbook.createSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        int cloumnCount=sheet.getRow(0).getLastCellNum();

        Row row=sheet.createRow(rowCount+1);
        Class objClass=object.getClass();
        Row titleRow=sheet.getRow(0);
        if(titleRow!=null){
            for (int i = 0; i < cloumnCount; i++) {
                String title=getContent(titleRow.getCell(i));
                String bigTitle=Character.toUpperCase(title.charAt(0))+title.substring(1);
                String methodName="get"+bigTitle;
                Method method=objClass.getDeclaredMethod(methodName);
                Object value=method.invoke(object);
                Cell cell=row.createCell(i);
                cell.setCellValue(value==null?"null":value.toString());
            }
        }
        FileOutputStream out=new FileOutputStream(fileOutPath);
        workbook.write(out);
        out.close();
    }

    /**
     * 读取excel
     *
     * @param filePath 导入的excel文件所在的绝对路径
     * @param startRow 开始解析的行数
     * @param startCol 开始解析的列数
     * @param sheetNum 开始解析的sheet序号，如果不指定，默认传值为-1，则会解析所有sheet
     * @return
     */
    public static List<List<Map<String, Object>>> readExcel(String filePath, int startRow, int startCol, int sheetNum) {
        logger.info("========================= ExcelUtils.java ->> importExcel()从Excel表格中获取数据 ->> 开始 =========================");

        // 用于存储最终整个Excel表格的数据
        List<List<Map<String, Object>>> resultList = new ArrayList<>();

        // 得到指定路径的文件File对象
        File file = new File(filePath);
        // 如果不存在
        if (!file.exists()) {
            logger.info("ExcelUtils.java ->> importExcel() ->> 错误操作：要读取Excel文件在指定路径(" + filePath + ")下找不到");
            throw new RuntimeException("错误操作：要读取Excel文件在指定路径(" + filePath + ")下找不到");
        }

        InputStream input = null;
        Workbook workbook = null;
        try {
            // 得到文件的资源输入流
            input = new FileInputStream(file);
            // 得到处理excel的Workbook对象
            workbook = ExcelUtils.getWorkbookByExtensionName(input, filePath);
            // 创建一个公式计算器，用于计算并得到Excel中的公式结果
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // 得到Excel表格中sheet的数量
            int totalSheetNum = workbook.getNumberOfSheets();
            logger.info("ExcelUtils.java ->> importExcel() ->> 用户指定解析的Sheet表格序号sheetNum = " + sheetNum);
            Sheet sheet = null;
            if (sheetNum == -1) {
                // 循环遍历sheet
                for (int m = 0; m < totalSheetNum; m++) {
                    logger.info("ExcelUtils.java ->> importExcel() ->> 开始解析第" + (m + 1) + "个Sheet表格");
                    // 获取每一个sheet
                    sheet = workbook.getSheetAt(m);
                    // 保存Sheet中的数据到List集合中
                    List<Map<String, Object>> sheetList = ExcelUtils.getDataBySheet(sheet, startRow, startCol, formulaEvaluator);
                    // 保存存储有每个sheet数据的List到结果集List中去
                    resultList.add(sheetList);
                }
            } else if (sheetNum > 0 && (sheetNum - 1) < totalSheetNum) {
                logger.info("ExcelUtils.java ->> importExcel() ->> 开始解析第" + sheetNum + "个Sheet表格");
                // 获取指定sheet序号的sheet表格
                sheet = workbook.getSheetAt((sheetNum - 1));
                // 保存Sheet中的数据到List集合中
                List<Map<String, Object>> sheetList = ExcelUtils.getDataBySheet2(sheet, startRow, startCol, formulaEvaluator);

                resultList.add(sheetList);
            } else {
                logger.info("ExcelUtils.java ->> importExcel() ->> 该Excel表格只有" + totalSheetNum + "个Sheet表，而用户指定解析的Sheet表序号为" + sheetNum + "，不在范围内");
                throw new RuntimeException("异常信息：该Excel表格只有" + totalSheetNum + "个Sheet表，而用户指定解析的Sheet表序号为" + sheetNum + "，不在范围内");
            }

        } catch (Exception e) {
            logger.info("ExcelUtils.java ->> importExcel() ->> 异常信息：" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                    logger.info("ExcelUtils.java ->> importExcel() ->> 关闭Workbook资源");
                }
                if (input != null) {
                    input.close();
                    logger.info("ExcelUtils.java ->> importExcel() ->> 关闭InputStream资源");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("========================= ExcelUtils.java ->> importExcel()从Excel表格中获取数据 ->> 结束 =========================");
        }

        return resultList;
    }


    /**
     * 根据文件扩展名(.xls或.xlsx)获取对应的Workbook类型（HSSFWorkbook或XSSFWorkbook）
     *
     * @param input    关联excel文件的资源输入流
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbookByExtensionName(InputStream input, String filePath) throws Exception {
        logger.info("========================= ExcelUtils.java ->> getWorkbookByExtensionName()根据excel文件后缀(.xls或.xlsx)获取Workbook的方法 ->> 开始 =========================");

        if (filePath == null || "".equals(filePath) || filePath.trim().isEmpty()) {
            logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> 异常信息：excel文件路径不能为空");
            throw new RuntimeException("异常信息：excel文件路径不能为空");
        }

        int index = filePath.lastIndexOf(".");
        if (index == -1) {
            logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> 异常信息：filePath指定的文件不带有扩展名，不属于文件类型");
            throw new RuntimeException("异常信息：filePath指定的文件不带有扩展名，不属于文件类型");
        }

        Workbook wk = null;

        String suffix = filePath.substring(index);
        logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> filePath指定的文件扩展名为 = " + suffix);
        if (ExcelUtils.XLS.equals(suffix)) {
            wk = new HSSFWorkbook(input);
        } else if (ExcelUtils.XLSX.equals(suffix)) {
            wk = new XSSFWorkbook(input);
        } else {
            logger.info("ExcelUtils.java ->> getWorkbookByExtensionName() ->> 异常信息：filePath指定的文件扩展名不是excel文件格式(只能是.xls和.xlsx格式)");
            throw new RuntimeException("异常信息：filePath指定的文件扩展名不是excel文件格式(只能是.xls和.xlsx格式)");
        }

        logger.info("========================= ExcelUtils.java ->> getWorkbookByExtensionName()根据excel文件后缀(.xls或.xlsx)获取Workbook的方法 ->> 结束 =========================");
        return wk;
    }


    /**
     * 获取sheet中的数据并返回一个List集合
     *
     * @param sheet            Sheet对象
     * @param startRow         开始解析的行数
     * @param startCol         开始解析的列数
     * @param formulaEvaluator 公式计算器实例
     * @return
     * @throws Exception
     */
    private static List<Map<String, Object>> getDataBySheet(Sheet sheet, int startRow, int startCol, FormulaEvaluator formulaEvaluator) throws Exception {

        logger.info("========================= ExcelUtils.java ->> getDataBySheet()从Sheet表格中获取数据 ->> 开始 =========================");

        List<Map<String, Object>> sheetList = new ArrayList<>();

        /*
            Sheet中的getPhysicalNumberOfRows()和getLastRowNum()区别：
                > getPhysicalNumberOfRows()：获取的是物理行数，即会跳过空行的情况。
                > getLastRowNum()：获取的是最后一行的行编号（编号从0开始）。
         */
        // 得到表格中总共的行数，会比实际的行数小1
        int totalRowNum = sheet.getLastRowNum() + 1;
        logger.info("ExcelUtils.java ->> getDataBySheet() ->> 当前Sheet表格中总行数totalRowNum = " + totalRowNum);

        // 循环当前表格中所有行
        for (int i = (startRow - 1); i < totalRowNum; i++) {
            // 得到Row行对象
            Row row = sheet.getRow(i);
            if (row == null || row.toString().trim().isEmpty()
                    || "".equals(row.toString()) || "null".equals(row.toString())) {
                logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的内容为空，因此解析下一行");
                continue;
            }

            /*
                Row中的getPhysicalNumberOfCells()和getLastCellNum()区别：
                    > getPhysicalNumberOfCells()：获取的是物理列数，即会跳过空列的情况。
                    > getLastCellNum()：获取的是最后一列的列编号（编号从0开始）。
             */
            // 得到当前行中所有的单元格数量
            int totalCellNum = row.getLastCellNum();
            logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的总列数totalCellNum = " + totalCellNum);
            // 创建Map集合用于存储当前行中所有的单元格数据
            Map<String, Object> rowMap = new HashMap<>();
            // 循环当前行中所有单元格
            for (int j = (startCol - 1); j < totalCellNum; j++) {
                // 得到Cell列对象
                Cell cell = row.getCell(j);

                // 如果等于空
                if (cell == null || cell.toString().trim().isEmpty()
                        || "".equals(cell.toString()) || "null".equals(cell.toString())) {
                    rowMap.put("Row" + (i + 1) + "-Col" + (j + 1), "");
                    logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的第" + (j + 1) + "列" + "的单元格的内容为空，因此解析下一个单元格");
                    continue;
                }

                // 进行公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                // 其余数据类型，根据官方文档，完全可以忽略：http://poi.apache.org/spreadsheet/eval.html
                CellValue cellValue = formulaEvaluator.evaluate(cell);

                // 得到对应单元格的内容
                String result = ExcelUtils.getCellResultByCellType(cell, cellValue, formulaEvaluator);
                logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的第" + (j + 1) + "列的单元格内容 = " + result);
                // 保存到Map集合
                rowMap.put("Row" + (i + 1) + "-Col" + (j + 1), result);
            }
            // 将每个行对象保存到List中
            sheetList.add(rowMap);
        }

        logger.info("========================= ExcelUtils.java ->> getDataBySheet()从Sheet表格中获取数据 ->> 结束 =========================");

        return sheetList;
    }

    /**
     * 获取sheet中的数据并返回一个List集合
     *
     * @param sheet            Sheet对象
     * @param startRow         开始解析的行数
     * @param startCol         开始解析的列数
     * @param formulaEvaluator 公式计算器实例
     * @return
     * @throws Exception
     */
    private static List<Map<String, Object>> getDataBySheet2(Sheet sheet, int startRow, int startCol, FormulaEvaluator formulaEvaluator) throws Exception {

        logger.info("========================= ExcelUtils.java ->> getDataBySheet()从Sheet表格中获取数据 ->> 开始 =========================");

        List<Map<String, Object>> sheetList = new ArrayList<>();

        /*
            Sheet中的getPhysicalNumberOfRows()和getLastRowNum()区别：
                > getPhysicalNumberOfRows()：获取的是物理行数，即会跳过空行的情况。
                > getLastRowNum()：获取的是最后一行的行编号（编号从0开始）。
         */
        // 得到表格中总共的行数，会比实际的行数小1
        int totalRowNum = sheet.getLastRowNum() + 1;
        logger.info("ExcelUtils.java ->> getDataBySheet() ->> 当前Sheet表格中总行数totalRowNum = " + totalRowNum);

        Row titleRow=sheet.getRow(0);
        int totalCellNum = titleRow.getLastCellNum();
        // 循环当前表格中所有行
        for (int i = startRow+1; i < totalRowNum; i++) {
            // 得到Row行对象
            Row row = sheet.getRow(i);
            if (rowNull(row)) {
                logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的内容为空，因此解析下一行");
                continue;
            }

            /*
                Row中的getPhysicalNumberOfCells()和getLastCellNum()区别：
                    > getPhysicalNumberOfCells()：获取的是物理列数，即会跳过空列的情况。
                    > getLastCellNum()：获取的是最后一列的列编号（编号从0开始）。
             */
            logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的总列数totalCellNum = " + totalCellNum);
            // 创建Map集合用于存储当前行中所有的单元格数据
            Map<String, Object> rowMap = new HashMap<>();
            // 循环当前行中所有单元格
            for (int j = startCol; j < totalCellNum; j++) {

                Cell titleCell=titleRow.getCell(j);
                if(titleCell == null || titleCell.toString().trim().isEmpty()
                        || "".equals(titleCell.toString()) || "null".equals(titleCell.toString())){
                    logger.info("第"  + j + "列表头为空，不记录");
                    continue;
                }
                // 得到Cell列对象
                Cell cell = row.getCell(j);
                String result =getContent(cell);
                logger.info("ExcelUtils.java ->> getDataBySheet() ->> 第" + (i + 1) + "行的第" + (j + 1) + "列的单元格内容 = " + result);
                // 保存到Map集合
                rowMap.put(getContent(titleCell), result);
            }
            // 将每个行对象保存到List中
            sheetList.add(rowMap);
        }

        logger.info("========================= ExcelUtils.java ->> getDataBySheet()从Sheet表格中获取数据 ->> 结束 =========================");

        return sheetList;
    }


    //判断行为空
    private static boolean rowNull(Row row) {
        Iterator<Cell> cellItr = row.iterator();
        while (cellItr.hasNext()) {
            Cell c = cellItr.next();
            if (!getContent(c).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据单元格数据类型获取单元格的值
     *
     * @param cell             Cell单元格类型
     * @param cellValue        CellValue单元格值类型
     * @param formulaEvaluator 公式计算器
     * @return
     */
    private static String getCellResultByCellType(Cell cell, CellValue cellValue, FormulaEvaluator formulaEvaluator) {

        String result = null;
        CellType cellTypeEnum = cellValue.getCellTypeEnum();

        if (cellTypeEnum == CellType.NUMERIC) {
            // 判断当前单元格是否为日期格式
            if (DateUtil.isCellDateFormatted(cell)) {
                logger.info("ExcelUtils.java ->> getCellResultByCellType() ->> 当前单元格类型为数值型中的日期类型");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                result = sdf.format(cell.getDateCellValue());
            } else {
                logger.info("ExcelUtils.java ->> getCellResultByCellType() ->> 当前单元格类型为数值型中的整数型");
                DataFormatter dataFormatter = new DataFormatter();
                result = dataFormatter.formatCellValue(cell, formulaEvaluator);
            }
        } else if (cellTypeEnum == CellType.STRING) {
            logger.info("ExcelUtils.java ->> getCellResultByCellType() ->> 当前单元格类型为字符串");
            result = cellValue.getStringValue();
        } else if (cellTypeEnum == CellType.BOOLEAN) {
            logger.info("ExcelUtils.java ->> getCellResultByCellType() ->> 当前单元格类型为布尔类型");
            result = String.valueOf(cellValue.getBooleanValue());
        }

        return result;
    }




    /*
     * 上面是Excel导入功能
     * ====================================================================================================================================
     * 下面是Excel导出功能
     */


    /**
     * 导出数据到Excel
     *
     * @param title    Excel表格中sheet的名称以及大标题行的标题
     * @param rowName  小标题行的标题
     * @param dataList 主体数据
     * @param out      输出流
     * @throws Exception
     */
    public static void exportExcel(String title, String[] rowName, List<Object[]> dataList, OutputStream out) {
        logger.info("========================= ExcelUtils.java ->> exportExcel()导出数据到Excel中 ->> 开始 =========================");

        XSSFWorkbook workbook = null;
        try {
            /*
                1，创建工作簿对象，然后创建大标题行，并设置标题
             */
            // 创建工作簿对象
            workbook = new XSSFWorkbook();
            // 创建一个表格对象
            XSSFSheet sheet = workbook.createSheet("Sheet1");

            String mark = "title";
            // 定义大标题行的样式
            XSSFCellStyle titleCellStyle = ExcelUtils.getCellStyle(workbook, mark);
            // 如果参数title不等空，则设置Sheet表格的大标题
            if (!"null".equals(title) && title != null
                    && !"".equals(title) && !title.trim().isEmpty()) {
                // 创建表格大标题行
                XSSFRow titleRow = sheet.createRow(0);
                // 创建表格大标题行的第一个单元格
                XSSFCell titleCell = titleRow.createCell(0);

                // 定义大标题行的宽度和高度（合并单元格）
                sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
                // 设置大标题行的单元格样式
                titleCell.setCellStyle(titleCellStyle);
                // 设置大标题行的单元格名称
                titleCell.setCellValue(title);
                logger.info("ExcelUtils.java ->> exportExcel() ->> 导出到Excel中Sheet表格的标题title = " + title);
            }

            /*
                2，创建小标题行并设置标题
             */
            // 定义所需列数 = 参数数组长度
            int columnNum = rowName.length;
            logger.info("ExcelUtils.java ->> exportExcel() ->> 导出到Excel中Sheet表格的标题列数columnNum = " + columnNum);
            // 创建小标题行，由于0行和1行用作大标题行，所以小标题行从2开始
            XSSFRow subTitleRow = sheet.createRow(2);

            // 将列头设置到sheet的单元格中
            for (int i = 0; i < columnNum; i++) {
                // 创建小标题行的单元格
                XSSFCell subTitleCell = subTitleRow.createCell(i);
                // 设置单元格的单元格类型
                subTitleCell.setCellType(CellType.STRING);
                // 使用数组中的数据作为单元格的文本来创建小标题
                XSSFRichTextString text = new XSSFRichTextString(rowName[i]);
                // 设置小标题单元格的样式
                subTitleCell.setCellStyle(titleCellStyle);
                // 设置文本到小标题单元格中
                subTitleCell.setCellValue(text);
                logger.info("ExcelUtils.java ->> exportExcel() ->> 设置小标题行的第" + (i + 1) + "列的标题为 = " + text);
            }


            /*
                3，开始循环主体数据，并设置到sheet表格中
             */
            logger.info("ExcelUtils.java ->> exportExcel() ->> 要导入到Excel表格中的数据行数 = " + dataList.size());
            mark = "";
            // 定义普通行的样式
            XSSFCellStyle style = ExcelUtils.getCellStyle(workbook, mark);
            // 循环遍历参数主体数据
            for (int i = 0; i < dataList.size(); i++) {
                // 遍历每个Object数组
                Object[] objArr = dataList.get(i);
                // 创建当前要填充数据的行对象
                XSSFRow currentRow = sheet.createRow(i + 3);
                // 循环遍历Object数组
                for (int j = 0; j < objArr.length; j++) {

                    XSSFCell cell = null;
                    // 如果是每行的第一个单元格
                    if (j == 0) {
                        // 创建单元格，且设置单元格类型为数值型
                        cell = currentRow.createCell(j, CellType.NUMERIC);
                        cell.setCellValue(i + 1);
                        // 设置单元格的样式
                        cell.setCellStyle(titleCellStyle);
                        logger.info("ExcelUtils.java ->> exportExcel() ->> 每行的第一个单元格作为行序号标记，当前序号为 = " + (i + 1));
                    } else {
                        // 创建单元格，且设置单元格类型为字符串型
                        cell = currentRow.createCell(j, CellType.STRING);
                        // 如果数组中的内容不为空
                        if (objArr[j] != null && !"".equals(objArr[j])) {
                            // 设置到单元格中
                            cell.setCellValue(objArr[j].toString());
                        }
                        // 设置单元格的样式
                        cell.setCellStyle(style);
                        logger.info("ExcelUtils.java ->> exportExcel() ->> 第" + (i + 1) + "行的第" + (j + 1) + "个单元格的内容为 = " + (i + 1));
                    }
                }
            }

            // 让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                //
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    XSSFRow currentRow;
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        XSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellTypeEnum() == CellType.STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                // 设置列宽
                if (colNum == 0) { // 如果是首列
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else { // 否则
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }

            workbook.write(out);
        } catch (Exception e) {
            logger.info("ExcelUtils.java ->> exportExcel() ->> 异常信息：" + e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                    logger.info("ExcelUtils.java ->> exportExcel() ->> 关闭Workbook资源");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("========================= ExcelUtils.java ->> exportExcel()导出数据到Excel中 ->> 结束 =========================");
        }
    }

    /**
     * 得到单元格样式
     *
     * @param workbook XSSFWorkbook类型对象
     * @param mark     标记，当且仅当为title时获取标题行的样式，否则为普通单元格样式
     * @return
     */
    private static XSSFCellStyle getCellStyle(XSSFWorkbook workbook, String mark) {

        // 创建字体对象
        XSSFFont font = workbook.createFont();

        // 创建单元格样式对象
        XSSFCellStyle cellStyle = workbook.createCellStyle();

        if ("title".equals(mark)) {
            // 设置字体的大小
            font.setFontHeightInPoints((short) 16);
            // 设置字体加粗
            font.setBold(true);
            // 设置字体高度
//            font.setFontHeight((short) 240);
        } else {
            // 设置字体的大小
            font.setFontHeightInPoints((short) 12);
            // 设置字体加粗
            font.setBold(false);

            // 设置单元格左边框
            cellStyle.setBorderLeft(BorderStyle.THIN);
            // 设置单元格左边框颜色
            cellStyle.setLeftBorderColor(new XSSFColor());
        }

        // 设置单元格的边框样式
        ExcelUtils.setCellCommonStyle(cellStyle, font);

        return cellStyle;
    }

    /**
     * 得到单元格样式
     *
     * @param cell 单元格对象，从模板中获取
     * @return
     */
    private static CellStyle getCellStyle(Cell cell) {
        return cell.getCellStyle();
    }

    /**
     * 设置单元格的边框样式
     *
     * @param cellStyle XSSFCellStyle单元格样式对象
     * @param font      XSSFFont字体对象
     */
    private static void setCellCommonStyle(XSSFCellStyle cellStyle, XSSFFont font) {
        // 设置字体的格式
        font.setFontName("等线");
        // 设置单元格上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        // 设置单元格上边框颜色
        cellStyle.setTopBorderColor(new XSSFColor());
        // 设置单元格右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        // 设置单元格右边框颜色
        cellStyle.setRightBorderColor(new XSSFColor());
        // 设置单元格底边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 设置单元格底边框颜色
        cellStyle.setBottomBorderColor(new XSSFColor());

        // 在单元格样式用应用设置的字体
        cellStyle.setFont(font);
        // 设置是否自动换行
        cellStyle.setWrapText(false);
        // 设置水平对齐的样式为居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直对齐的样式为居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }



    public static String exportBom(Map<String,Object> map) throws Exception {
        //模板的路径，这个在自己的项目中很容易弄错，相对位置一定要写对啊
        String psth = ClassLoader.getSystemResource("excelModel").getPath()+"/model.xlsx";
        InputStream in =new FileInputStream(psth);
        XSSFWorkbook workbook = new XSSFWorkbook(in);

        XSSFSheet sheet = workbook.getSheetAt(0);
        CellStyle cellStyle=getCellStyle(sheet.getRow(1).getCell(1));

        String sampleIdString=map.get(ExcelName.sampleId.getEngName()).toString();
        Cell sampleId=sheet.getRow(1).getCell(2);
        sampleId.setCellValue(sampleIdString);
        sampleId.setCellStyle(cellStyle);

        String chinaNameString=map.get(ExcelName.chinaName.getEngName()).toString();
        Cell chinaName=sheet.getRow(2).getCell(2);
        chinaName.setCellValue(chinaNameString);
        chinaName.setCellStyle(cellStyle);

        String latiNameString=map.get(ExcelName.latiName.getEngName()).toString();
        Cell latiName=sheet.getRow(3).getCell(2);
        latiName.setCellValue(latiNameString);
        latiName.setCellStyle(cellStyle);

        String sampleGoneString=map.get(ExcelName.sampleGone.getEngName()).toString();
        Cell sampleGone=sheet.getRow(4).getCell(4);
        sampleGone.setCellValue(sampleGoneString);
        sampleGone.setCellStyle(cellStyle);

        String proLocationString=map.get(ExcelName.proLocation.getEngName()).toString();
        Cell proLocation=sheet.getRow(5).getCell(2);
        proLocation.setCellValue(proLocationString);
        proLocation.setCellStyle(cellStyle);

        String sampleTypeString=map.get(ExcelName.sampleType.getEngName()).toString();
        Cell sampleType=sheet.getRow(6).getCell(2);
        sampleType.setCellValue(sampleTypeString);
        sampleType.setCellStyle(cellStyle);

        String categoryString=map.get(ExcelName.category.getEngName()).toString();
        Cell category=sheet.getRow(7).getCell(2);
        category.setCellValue(categoryString);
        category.setCellStyle(cellStyle);

        String sampleSourceString=map.get(ExcelName.sampleSource.getEngName()).toString();
        Cell sampleSource=sheet.getRow(8).getCell(2);
        sampleSource.setCellValue(sampleSourceString);
        sampleSource.setCellStyle(cellStyle);

        String sampleStatusString=map.get(ExcelName.sampleStatus.getEngName()).toString();
        Cell sampleStatus=sheet.getRow(9).getCell(2);
        sampleStatus.setCellValue(sampleStatusString);
        sampleStatus.setCellStyle(cellStyle);

        String sampleCountString=map.get(ExcelName.sampleCount.getEngName()).toString();
        Cell sampleCount=sheet.getRow(9).getCell(4);
        sampleCount.setCellValue(sampleCountString);
        sampleCount.setCellStyle(cellStyle);

        String origNumberString=map.get(ExcelName.origNumber.getEngName()).toString();
        Cell origNumber=sheet.getRow(9).getCell(7);
        origNumber.setCellValue(origNumberString);
        origNumber.setCellStyle(cellStyle);

        String protectLevelString=map.get(ExcelName.protectLevel.getEngName()).toString();
        Cell protectLevel=sheet.getRow(10).getCell(2);
        protectLevel.setCellValue(protectLevelString);
        protectLevel.setCellStyle(cellStyle);

        String pictureNumberString=map.get(ExcelName.pictureNumber.getEngName()).toString();
        Cell pictureNumber=sheet.getRow(10).getCell(4);
        pictureNumber.setCellValue(pictureNumberString);
        pictureNumber.setCellStyle(cellStyle);

        String enterDateString=map.get(ExcelName.enterDate.getEngName()).toString();
        Cell enterDate=sheet.getRow(10).getCell(7);
        enterDate.setCellValue(enterDateString);
        enterDate.setCellStyle(cellStyle);

        String sampleDescString=map.get(ExcelName.sampleDesc.getEngName()).toString();
        Cell sampleDesc=sheet.getRow(11).getCell(2);
        sampleDesc.setCellValue(sampleDescString);
        sampleDesc.setCellStyle(cellStyle);

        String exploreCompanyString=map.get(ExcelName.exploreCompany.getEngName()).toString();
        Cell exploreCompany=sheet.getRow(19).getCell(2);
        exploreCompany.setCellValue(exploreCompanyString);
        exploreCompany.setCellStyle(cellStyle);

        String exploreReasonString=map.get(ExcelName.exploreReason.getEngName()).toString();
        Cell exploreReason=sheet.getRow(19).getCell(5);
        exploreReason.setCellValue(exploreReasonString);
        exploreReason.setCellStyle(cellStyle);

        String exploreDateString=map.get(ExcelName.exploreDate.getEngName()).toString();
        Cell exploreDate=sheet.getRow(20).getCell(5);
        exploreDate.setCellValue(exploreDateString);
        exploreDate.setCellStyle(cellStyle);

        String remarkString=map.get(ExcelName.remark.getEngName()).toString();
        Cell remark=sheet.getRow(21).getCell(2);
        remark.setCellValue(remarkString);
        remark.setCellStyle(cellStyle);

        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        String picturePath=map.get(ExcelName.picturePaths.getEngName()).toString().split(";")[0];
        BufferedImage bufferImg = ImageIO.read(new File(picturePath));
        ImageIO.write(bufferImg, "jpg", byteArrayOut);
        byte[] imgtypes = byteArrayOut.toByteArray();
        int puctureIndex = workbook.addPicture(imgtypes, HSSFWorkbook.PICTURE_TYPE_JPEG);
        //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        XSSFDrawing  patriarch = sheet.createDrawingPatriarch();
        //anchor主要用于设置图片的属性
        XSSFClientAnchor anchor = new XSSFClientAnchor(9525*3, 9525*3, -9525*1, -9525*1,(short)5, 1, (short) 9, 9);
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        //插入图片
        patriarch.createPicture(anchor,puctureIndex);

        writeExcel(workbook, "D:\\DataBaseForNiHeWan\\export\\"+sampleIdString+".xlsx");
        return "D:\\DataBaseForNiHeWan\\export\\"+sampleIdString+".xlsx";
    }


    private static void writeExcel(Workbook work, String fileName) throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            work.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private static Cell setCellStyleWithStyleAndValue(CellStyle style, Cell cell, String value) {
        cell.setCellStyle(style);
        cell.setCellValue(value);
        return cell;
    }

//    private static Cell setCellStyleWithStyleAndValue(CellStyle style, Cell cell, Double value) {
//        cell.setCellStyle(style);
//        cell.setCellValue(value);
//        return cell;
//    }
//
//    private static Cell setCellStyleWithValue(Cell cell, String value) {
//        cell.setCellStyle(cellstyle);
//        cell.setCellValue(value);
//        return cell;
//    }
//
//
//    private static Cell setCellStyleWithStyleAndValue(CellStyle style, Cell cell, RichTextString value) {
//        cell.setCellStyle(style);
//        cell.setCellValue(value);
//        return cell;
//    }
//
//    private static Cell setCellStyleWithValue(Cell cell, int value) {
//        cell.setCellStyle(cellstyle);
//        cell.setCellValue(value);
//        return cell;
//    }
//
//    private static Cell setCellStyleWithValue(Cell cell, double value) {
//        cell.setCellStyle(cellstyle);
//        cell.setCellValue(value);
//        return cell;
//    }

//    private static XSSFCellStyle createCellStyle(Workbook wb) {
//        cellstyle = (XSSFCellStyle) wb.createCellStyle();
//        cellstyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
////        cellstyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
////        cellstyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
////        cellstyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
////        cellstyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
//        cellstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//        return cellstyle;
//    }
}
