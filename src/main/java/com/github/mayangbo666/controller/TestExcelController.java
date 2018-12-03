package com.github.mayangbo666.controller;

import com.github.mayangbo666.entity.User;
import com.github.mayangbo666.service.TestExcelService;
import com.github.mayangbo666.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/excel")
public class TestExcelController {

    @Autowired
    private TestExcelService testExcelService;


    private static final Logger logger = LoggerFactory.getLogger(TestExcelController.class);


    @RequestMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        if (!ExcelUtils.validateExcel(file.getOriginalFilename())) {
            logger.error("文件必须是excel!");
            return null;
        }
        long size = file.getSize();
        if (file.getOriginalFilename() == null || file.getOriginalFilename().equals("") || size == 0) {
            logger.error("文件不能为空!");
            return null;
//            System.out.println("文件不能为空");
//            return "poi";
        }

        Workbook workbook;
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xlsx".equals(fileType)){
//            workbook = new XSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if (".xls".equals(fileType)){
//            workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            workbook = new HSSFWorkbook(file.getInputStream());
        }else {
            logger.error("excel文件格式错误!");
            return null;
        }

//        int numberOfSheets = workbook.getNumberOfSheets();//获得有多少sheet
//        HSSFSheet sheet = workbook.getSheetAt(0);//默认只有一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();//获得sheet有多少行
        //遍历行
        for (int j = 0; j < rows; j++) {
            if (j == 0) {
                continue;//标题行(省略)
            }
//            HSSFRow row = sheet.getRow(j);
            Row row = sheet.getRow(j);
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
//                HSSFCell cell = row.getCell(k);
                Cell cell = row.getCell(k);
                logger.info(cell.toString());
            }
        }
        return "testExcel";
    }

    //生成user表excel
    @RequestMapping("/downUserExcel")
    public void downUserExcel(HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook, sheet);
        List<User> rows = new ArrayList<>();//userService.getAll();
        rows.add(new User("1", "小明", "牛逼", new Date()));
        rows.add(new User("2", "中明", "牛2逼", new Date()));
        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (User user : rows) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getLoginCode());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getPassword());
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(user.getCreateTime());
            cell.setCellStyle(style);
            rowNum++;
        }
        String fileName = "导出excel例子.xls";
        //生成excel文件
        buildExcelFile(fileName, workbook);
        //浏览器下载excel
        buildExcelDocument(fileName, workbook, response);
    }

    //创建表头
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(3, 17 * 256);
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("显示名");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);
    }

    //生成excel文件
    protected void buildExcelFile(String filename, HSSFWorkbook workbook) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
