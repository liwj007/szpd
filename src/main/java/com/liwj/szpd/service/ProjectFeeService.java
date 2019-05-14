package com.liwj.szpd.service;

import com.liwj.szpd.form.ProjectFinanceForm;

public interface ProjectFeeService {


    ProjectFinanceForm getFinanceForm(String token, Integer projectID);

    boolean updateFee(String token, Integer id, String invoice, String arrival, String noArrival, String stepArrival, String debt);
}
