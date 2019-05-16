package com.study.utils.qadata;

import com.study.utils.qadata.dto.SimpleSuccessFailDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by 1000773 on 2019. 1. 18..
 */
@Slf4j
public abstract class ExcelSheetService {

    protected DataFormatter _formatter = new DataFormatter();
    protected Row _row;

    public SimpleSuccessFailDto convertFileToList(MultipartFile file) {

        int success = 0;
        int fail = 0;
        if (!file.isEmpty()) {
            try(InputStream inputStream = file.getInputStream()) {

                Workbook workbook = null;
                workbook = new XSSFWorkbook(new BufferedInputStream(inputStream));

                //Get the number of sheets in the xlsx file
                int numberOfSheets = workbook.getNumberOfSheets();

                log.info("first sheet name:{}, sheet count:{}",workbook.getSheetName(0),numberOfSheets);

                DataFormatter formatter = new DataFormatter();

                //loop through each of the sheets
                for (int i = 0; i < numberOfSheets; i++) {

                    //Get the nth sheet from the workbook
                    Sheet sheet = workbook.getSheetAt(i);

                    //every sheet has rows, iterate over them
                    Iterator<Row> rowIterator = sheet.iterator();

                    boolean isFirstRow = true;
                    while (rowIterator.hasNext()) {
                        //Get the row object
                        Row row = rowIterator.next();
                        _row =row;

                        if (!isFirstRow) {
                            // 아래 부분을 공통으로 분리합시다..
                            if(rowAction(formatter, row, sheet.getSheetName())==true)
                                success ++;
                            else
                                fail ++;
                        }
                        isFirstRow = false;
                    }
                } //end of sheets for loop

            } catch(RuntimeException e) {
                throw e;
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException("target insert fail");
            }
            return new SimpleSuccessFailDto(success, fail);
        } else {
            throw new RuntimeException("File is empty");
        }
    }

    protected abstract boolean rowAction(DataFormatter formatter, Row row, String SheetName);
}
