package com.study.controller;

import com.study.utils.qadata.AddExcelService;
import com.study.utils.qadata.dto.SimpleSuccessFailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 1000773 on 2019. 5. 15..
 */
@Slf4j
@Controller
@RequestMapping(value = "/v1/admin/qa")
public class QadataController {

    @Autowired
    private Environment env;
    @Autowired
    AddExcelService addExcelService;

    @RequestMapping(value = "/addExcel", method = RequestMethod.POST)
    @ResponseBody
    public SimpleSuccessFailDto addExcel(HttpServletRequest request, @RequestPart("file") MultipartFile file) {

        boolean isProd = env.getActiveProfiles()[0].equals("prod1");
        if(isProd)
            return null;

        return addExcelService.addExcelData(file);
    }
}
