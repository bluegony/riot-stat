package com.web.controller;


import com.web.dto.CardIssueProcessRequest;
import com.web.requestprocessing.ServiceHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2016-02-20.
 */
@Slf4j
@Controller
@RequestMapping(value = "/v1/member")
public class MemberController {

    @Autowired
    private ServiceHolder serviceHolder;

    @RequestMapping(value = "/cardIssueProcess", method = RequestMethod.POST)
    public @ResponseBody Object cardIssueProcess(HttpServletRequest httpRequest, @RequestBody CardIssueProcessRequest request) {
        log.debug(request.toString());
        return request.processRequest(httpRequest, serviceHolder);
    }




}

