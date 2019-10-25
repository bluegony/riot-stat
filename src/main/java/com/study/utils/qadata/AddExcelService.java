//package com.study.utils.qadata;
//
//import com.study.utils.qadata.PurchaseMapper;
//import com.study.utils.qadata.dto.Purchase;
//import com.study.utils.qadata.dto.SimpleSuccessFailDto;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.math.BigDecimal;
//
///**
// * Created on 2019. 1. 11..
// */
//@Slf4j
//@Service
//public class AddExcelService extends ExcelSheetService {
//
//    @Autowired
//    private PurchaseMapper purchaseMapper;
//
//
//    public SimpleSuccessFailDto addExcelData(MultipartFile file) {
//        log.info("asdf");
//        return convertFileToList(file);
//    }
//
//    @Override
//    protected boolean rowAction(DataFormatter formatter, Row row, String sheetName) {
//        log.info("sheetname={}",sheetName);
//        try{
//            if(sheetName.contains("승인")) {
////                return processApprovalData(formatter, row);
//            } else if (sheetName.contains("매입")) {
//                return processPurchaseData(formatter, row);
//            } else if (sheetName.contains("회원")) {
////                return processMemberData(formatter, row);
//            }
//        } catch (Exception e) {
//            log.info("rowAction exception", e);
//            return false;
//        }
//
//        return false;
//
//    }
//
//
//    private boolean processPurchaseData(DataFormatter formatter, Row row) {
//        Purchase purchase = Purchase.builder()
//                .corpCode(formatter.formatCellValue(row.getCell(0)).trim())
//                .prodCode(formatter.formatCellValue(row.getCell(1)).trim())
//                .partnerCardCode(formatter.formatCellValue(row.getCell(2)).trim())
//                .cardNo(formatter.formatCellValue(row.getCell(3)).trim())
//                .approvalNumber(formatter.formatCellValue(row.getCell(4)).trim())
//                .approvalDate(formatter.formatCellValue(row.getCell(5)).trim())
//                .approvalTime(formatter.formatCellValue(row.getCell(6)).trim())
//                .purchaseDate(formatter.formatCellValue(row.getCell(7)).trim())
//                .noApprovalPurchase(formatter.formatCellValue(row.getCell(8)).trim())
//                .abroadUse(formatter.formatCellValue(row.getCell(9)).trim())
//                .payAmount(new BigDecimal(formatter.formatCellValue(row.getCell(10)).trim()))
//                .merchantName(formatter.formatCellValue(row.getCell(11)).trim())
//                .trType(formatter.formatCellValue(row.getCell(12)).trim())
//                .build();
//
//        try {
//            log.info("merge purchase data", purchase.toString());
//            purchaseMapper.merge(purchase);
//            return true;
//        } catch(Exception e) {
//            log.info("purchase insert error", e);
//        }
//        return false;
//    }
//
//    private String getCellData( int cellNum) {
//        return _formatter.formatCellValue(_row.getCell(cellNum)).trim();
//
//    }
//
//
//}
