package com.ccwl.manager.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandle {
    public static List<ArrayList<Object>> loadExcel(String[] HEAD, CommonsMultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        List<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
        if (fileName.endsWith("xlsx")) {
            File newFile=new File(fileName);
            file.transferTo(newFile);

            FileInputStream in = new FileInputStream(newFile);

            Workbook wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheet("Sheet1");

            for (int i = 0; i < HEAD.length; i++) {
                if (!HEAD[i].equals(sheet.getRow(0).getCell(i).toString())){
                    System.out.println("校验不通过");
                    return null;
                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                ArrayList<Object> row = new ArrayList<Object>();
                for (int j = 0; j < HEAD.length; j++) {
                    if (sheet.getRow(i) == null){
                        break;
                    }
                    Cell cell = sheet.getRow(i).getCell(j);
                    row.add(cell.toString());
                }
                result.add(row);
            }

            newFile.delete();
            System.out.println(result);
            return result;
        }else {
            System.out.println("文件格式错误，请检查后缀名是否为“.xlsx");
            return null;
        }



    }
}
