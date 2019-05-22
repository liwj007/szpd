package com.liwj.szpd.controller;

import com.liwj.szpd.service.WarnService;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.vo.DebtWarnDetailVO;
import com.liwj.szpd.vo.DebtWarnVO;
import com.liwj.szpd.vo.ProcessWarnDetailVO;
import com.liwj.szpd.vo.ProcessWarnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/warn")
public class WarnController {
    @Autowired
    private WarnService warnService;

    @RequestMapping(value = "/debt", method = RequestMethod.GET)
    public JsonResult getDebtWarning(@RequestHeader(value = "token") String token) {
        DebtWarnVO vo = warnService.getDebtWarning(token);

        return JsonResult.renderSuccess(vo);
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public JsonResult getProcessWarning(@RequestHeader(value = "token") String token) {
        ProcessWarnVO vo = warnService.getProcessWarning(token);

        return JsonResult.renderSuccess(vo);
    }

    @RequestMapping(value = "/process_detail", method = RequestMethod.GET)
    public JsonResult getProcessWarningDetail(@RequestHeader(value = "token") String token) {
        List<ProcessWarnDetailVO> res = warnService.getProcessWarningDetail(token);

        return JsonResult.renderSuccess(res);
    }

    @RequestMapping(value = "/debt_detail", method = RequestMethod.GET)
    public JsonResult getDebtWarningDetail(@RequestHeader(value = "token") String token) {
        List<DebtWarnDetailVO> res = warnService.getDebtWarningDetail(token);

        return JsonResult.renderSuccess(res);
    }
}
