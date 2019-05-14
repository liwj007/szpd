package com.liwj.szpd.controller;

import com.liwj.szpd.form.ProjectFinanceForm;
import com.liwj.szpd.service.ProjectFeeService;
import com.liwj.szpd.utils.ErrorCode;
import com.liwj.szpd.utils.JsonResult;
import com.liwj.szpd.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fee")
public class FeeController {
    @Autowired
    private ProjectFeeService projectFeeService;

    @RequestMapping(value = "/update_fee", method = RequestMethod.POST)
    public JsonResult updateFee(@RequestParam(value = "token") String token,
                                @RequestParam(value = "id") Integer id,
                                @RequestParam(value = "invoice") String invoice,
                                @RequestParam(value = "arrival") String arrival,
                                @RequestParam(value = "noArrival") String noArrival,
                                @RequestParam(value = "stepArrival") String stepArrival,
                                @RequestParam(value = "debt") String debt) {
        if (invoice != null && ("".equals(invoice) || "null".equals(invoice)))
            invoice = null;
        if (arrival != null && ("".equals(arrival) || "null".equals(arrival)))
            arrival = null;
        if (noArrival != null && ("".equals(noArrival) || "null".equals(noArrival)))
            noArrival = null;
        if (stepArrival != null && ("".equals(stepArrival) || "null".equals(stepArrival)))
            stepArrival = null;
        if (debt != null && ("".equals(debt) || "null".equals(debt)))
            debt = null;
        boolean flag = projectFeeService.updateFee(token, id, invoice, arrival, noArrival, stepArrival, debt);
        if (flag) {
            return JsonResult.renderSuccess();
        } else {
            return JsonResult.renderFail();
        }
    }


    @RequestMapping(value = "/finance_form", method = RequestMethod.GET)
    public JsonResult getFinanceForm(@RequestParam(value = "token") String token,
                                     @RequestParam(value = "pid") Integer projectID) {

        ProjectFinanceForm form = projectFeeService.getFinanceForm(token, projectID);

        return JsonResult.renderSuccess(form);
    }
}
