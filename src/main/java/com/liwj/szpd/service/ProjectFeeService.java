package com.liwj.szpd.service;

import com.liwj.szpd.vo.FinanceInfoVO;
import com.liwj.szpd.vo.FinanceStepVO;
import com.liwj.szpd.vo.FinanceVO;

import java.util.List;

public interface ProjectFeeService {


    FinanceVO getFinanceInfo(Integer projectID);

    FinanceInfoVO getFinanceStepInfo(String token,Integer stepId);

    boolean update(String token, FinanceStepVO form);
}
