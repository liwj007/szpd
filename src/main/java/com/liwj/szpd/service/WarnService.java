package com.liwj.szpd.service;

import com.liwj.szpd.vo.DebtWarnDetailVO;
import com.liwj.szpd.vo.DebtWarnVO;
import com.liwj.szpd.vo.ProcessWarnDetailVO;
import com.liwj.szpd.vo.ProcessWarnVO;

import java.util.List;

public interface WarnService {

    DebtWarnVO getDebtWarning(String token);

    ProcessWarnVO getProcessWarning(String token);

    List<ProcessWarnDetailVO> getProcessWarningDetail(String token);

    List<DebtWarnDetailVO> getDebtWarningDetail(String token);
}
