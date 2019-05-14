package com.liwj.szpd.service.impl;

import com.liwj.szpd.form.ProjectFinanceForm;
import com.liwj.szpd.mapper.ProjectFinanceMapper;
import com.liwj.szpd.mapper.ProjectTreasurerMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ProjectFeeServiceImpl implements ProjectFeeService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectFinanceMapper projectFinanceMapper;

    @Autowired
    private ProjectTreasurerMapper projectTreasurerMapper;

    @Override
    public boolean updateFee(String token, Integer id, String invoice, String arrival, String noArrival, String stepArrival, String debt) {
        User user = userMapper.findByToken(token);
        ProjectFinance finance = projectFinanceMapper.selectByPrimaryKey(id);
        finance.setUpdatedTime(new Date());
        finance.setUpdatedBy(user.getId());

        finance.setInvoice(invoice == null ? null : new BigDecimal(invoice));
        finance.setArrival(arrival == null ? null : new BigDecimal(arrival));
        finance.setNoArrival(noArrival == null ? null : new BigDecimal(noArrival));
        finance.setStepArrival(stepArrival == null ? null : new BigDecimal(stepArrival));
        finance.setDebt(debt == null ? null : new BigDecimal(debt));
        projectFinanceMapper.updateByPrimaryKeySelective(finance);
        return true;
    }


    @Override
    public ProjectFinanceForm getFinanceForm(String token, Integer projectID) {
        ProjectFinanceExample example = new ProjectFinanceExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectFinance> lst = projectFinanceMapper.selectByExample(example);
        if (lst.size() != 1)
            return null;
        ProjectFinance finance = lst.get(0);
        ProjectFinanceForm form = new ProjectFinanceForm();
        form.setId(finance.getId());
        form.setInvoice(finance.getInvoice() == null ? null : finance.getInvoice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        form.setArrival(finance.getArrival() == null ? null : finance.getArrival().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        form.setNoArrival(finance.getNoArrival() == null ? null : finance.getNoArrival().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        form.setStepArrival(finance.getStepArrival() == null ? null : finance.getStepArrival().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        form.setDebt(finance.getDebt() == null ? null : finance.getDebt().setScale(2, BigDecimal.ROUND_HALF_UP).toString());

        User user = userMapper.findByToken(token);
        ProjectTreasurerExample treasurerExample = new ProjectTreasurerExample();
        treasurerExample.createCriteria().andUserIdEqualTo(user.getId()).andProjectIdEqualTo(projectID);
        long c = projectTreasurerMapper.countByExample(treasurerExample);
        if (c==0)
            form.setEditable(false);
        else
            form.setEditable(true);

        return form;
    }
}
