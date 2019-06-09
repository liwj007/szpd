package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectFeeService;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ProjectFeeServiceImpl implements ProjectFeeService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectFinanceStepMapper projectFinanceStepMapper;

    @Autowired
    private ProjectTreasurerMapper projectTreasurerMapper;

    @Autowired
    private InvoiceFileMapper invoiceFileMapper;

    @Autowired
    private ProjectScheduleMapper projectScheduleMapper;

    @Autowired
    private ProjectFeeMapper projectFeeMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public FinanceVO getFinanceInfo(Integer projectID) {
        FinanceVO vo = new FinanceVO();

        ProjectFinanceStepExample example = new ProjectFinanceStepExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        example.setOrderByClause("step asc");
        List<ProjectFinanceStep> steps = projectFinanceStepMapper.selectByExample(example);
        List<FinanceStepVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (ProjectFinanceStep step : steps) {
            FinanceStepVO stepVO = new FinanceStepVO();
            stepVO.setId(step.getId());
            stepVO.setStep(step.getStep());
            switch (step.getStep()) {
                case Constants
                        .PROJECT_START_STEP:
                    stepVO.setStepName("项目启动");
                    break;
                case Constants.PROJECT_MIDDLE_STEP:
                    stepVO.setStepName("项目中期");
                    break;
                case Constants.PROJECT_PRELIMINARY_STEP:
                    stepVO.setStepName("初步成果");
                    break;
                case Constants.PROJECT_REVIEW_STEP:
                    stepVO.setStepName("评审验收");
                    break;
                case Constants.PROJECT_FINAL_STEP:
                    stepVO.setStepName("最终成果");
                    break;
            }
            stepVO.setInvoiceMoney(step.getInvoiceMoney() == null ? null : step.getInvoiceMoney().setScale(2).doubleValue());
            stepVO.setInvoicePercent(step.getInvoicePercent());
            stepVO.setInvoiceDate(step.getInvoiceDate() == null ? null : sdf.format(step.getInvoiceDate()));
            stepVO.setAccountMoney(step.getAccountMoney() == null ? null : step.getAccountMoney().setScale(2).doubleValue());
            stepVO.setAccountPercent(step.getAccountPercent());
            stepVO.setAccountDate(step.getAccountDate() == null ? null : sdf.format(step.getAccountDate()));
            voList.add(stepVO);
        }
        vo.setSteps(voList);

        FinanceStatisticVO statisticVO = statisticFinanceInfo(projectID);
        vo.setStatistic(statisticVO);

        return vo;
    }

    @Override
    public FinanceInfoVO getFinanceStepInfo(String token, Integer stepId) {
        ProjectFinanceStep step = projectFinanceStepMapper.selectByPrimaryKey(stepId);
        FinanceStepVO vo = new FinanceStepVO();
        if (step != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            vo.setId(stepId);
            vo.setInvoiceMoney(step.getInvoiceMoney() == null ? null : step.getInvoiceMoney().setScale(2).doubleValue());
            vo.setInvoicePercent(step.getInvoicePercent());
            vo.setInvoiceDate(step.getInvoiceDate() == null ? null : sdf.format(step.getInvoiceDate()));
            vo.setAccountMoney(step.getAccountMoney() == null ? null : step.getAccountMoney().setScale(2).doubleValue());
            vo.setAccountPercent(step.getAccountPercent());
            vo.setAccountDate(step.getAccountDate() == null ? null : sdf.format(step.getAccountDate()));

            vo.setFiles(new ArrayList<>());
            InvoiceFileExample fileExample = new InvoiceFileExample();
            fileExample.createCriteria().andFinanceStepIdEqualTo(stepId);
            List<InvoiceFile> fileList = invoiceFileMapper.selectByExample(fileExample);
            for (InvoiceFile file : fileList) {
                ProjectFileVO invoiceFile = new ProjectFileVO();
                invoiceFile.setId(file.getId());
                invoiceFile.setUrl(file.getPath());
                vo.getFiles().add(invoiceFile);
            }
        }

        FinanceInfoVO infoVO = new FinanceInfoVO();
        infoVO.setInfo(vo);


        User user = userMapper.findByToken(token);
        ProjectTreasurerExample treasurerExample = new ProjectTreasurerExample();
        treasurerExample.createCriteria().andProjectIdEqualTo(step.getProjectId()).andUserIdEqualTo(user.getId());
        long c = projectTreasurerMapper.countByExample(treasurerExample);
        if (c == 0)
            infoVO.setEditable(false);
        else
            infoVO.setEditable(true);

        Project project = projectMapper.selectByPrimaryKey(step.getProjectId());
        infoVO.setTotalFee(project.getTotalFee()==null?0:project.getTotalFee().setScale(2).doubleValue());

        return infoVO;
    }

    @Override
    public boolean update(String token, FinanceStepVO form) {
        User user = userMapper.findByToken(token);

        ProjectFinanceStep financeStep = projectFinanceStepMapper.selectByPrimaryKey(form.getId());
        if (financeStep == null)
            return false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            financeStep.setInvoiceMoney(form.getInvoiceMoney() == null ? null : new BigDecimal(form.getInvoiceMoney()));
            financeStep.setInvoicePercent(form.getInvoicePercent());
            financeStep.setInvoiceDate(form.getInvoiceDate() == null ? null : sdf.parse(form.getInvoiceDate()));

            financeStep.setAccountMoney(form.getAccountMoney() == null ? null : new BigDecimal(form.getAccountMoney()));
            financeStep.setAccountPercent(form.getAccountPercent());
            financeStep.setAccountDate(form.getAccountDate() == null ? null : sdf.parse(form.getAccountDate()));

            financeStep.setUpdateBy(user.getId());
            financeStep.setUpdateTime(new Date());

            projectFinanceStepMapper.updateByPrimaryKeySelective(financeStep);


            InvoiceFileExample fileExample = new InvoiceFileExample();
            fileExample.createCriteria().andFinanceStepIdEqualTo(form.getId());
            invoiceFileMapper.deleteByExample(fileExample);

            for (ProjectFileVO fileVO : form.getFiles()) {
                InvoiceFile invoiceFile = new InvoiceFile();
                invoiceFile.setPath(fileVO.getUrl());
                invoiceFile.setProjectId(financeStep.getProjectId());
                invoiceFile.setStep(financeStep.getStep());
                invoiceFile.setCreateBy(user.getId());
                invoiceFile.setCreateTime(new Date());
                invoiceFile.setFinanceStepId(financeStep.getId());
                invoiceFileMapper.insert(invoiceFile);
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public FinanceStatisticVO statisticFinanceInfo(Integer projectID) {
        FinanceStatisticVO vo = new FinanceStatisticVO();

        ProjectFeeExample feeExample = new ProjectFeeExample();
        feeExample.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectFee> projectFees = projectFeeMapper.selectByExample(feeExample);
        if (projectFees.size() == 0){
            return vo;
        }

        ProjectFee projectFee = projectFees.get(0);

        ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
        scheduleExample.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(scheduleExample);
        ProjectSchedule schedule = projectSchedules.get(0);

        Date now = new Date();

        ProjectFinanceStepExample example = new ProjectFinanceStepExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectFinanceStep> steps = projectFinanceStepMapper.selectByExample(example);
        for (ProjectFinanceStep financeStep : steps) {
            Integer step = financeStep.getStep();

            vo.setInvoice(vo.getInvoice() + (financeStep.getInvoiceMoney() == null ? 0 : financeStep.getInvoiceMoney().setScale(2).doubleValue()));//开票金额
            vo.setAccount(vo.getAccount() + (financeStep.getAccountMoney() == null ? 0 : financeStep.getAccountMoney().setScale(2).doubleValue()));//到账金额
            if (financeStep.getInvoiceMoney() != null) {
                if (financeStep.getAccountMoney()==null){
                    financeStep.setAccountMoney(new BigDecimal(0.0));
                }
                vo.setInvoiceNoAccount(vo.getInvoiceNoAccount() + (financeStep.getInvoiceMoney().setScale(2).doubleValue()-financeStep.getAccountMoney().setScale(2).doubleValue()));//开票未到账金额
            }else{
                boolean flag = false;
                double fee = 0.0;
                switch (step) {
                    case Constants.PROJECT_START_STEP:
                        if (schedule.getActualStartUpDate() != null && now.after(schedule.getActualStartUpDate())) {
                            flag = true;
                            fee = projectFee.getStartPercent() == null ? 0 : projectFee.getStartPercent().setScale(2).doubleValue();
                        }
                        break;
                    case Constants.PROJECT_MIDDLE_STEP:
                        if (schedule.getActualMiddleDate() != null && now.after(schedule.getActualMiddleDate())) {
                            flag = true;
                            fee = projectFee.getMiddlePercent() == null ? 0 : projectFee.getMiddlePercent().setScale(2).doubleValue();
                        }
                        break;
                    case Constants.PROJECT_PRELIMINARY_STEP:
                        if (schedule.getActualPreliminaryResult() != null && now.after(schedule.getActualPreliminaryResult())) {
                            flag = true;
                            fee = projectFee.getPreliminaryResultPercent() == null ? 0 : projectFee.getPreliminaryResultPercent().setScale(2).doubleValue();
                        }
                        break;
                    case Constants.PROJECT_REVIEW_STEP:
                        if (schedule.getActualReviewDate() != null && now.after(schedule.getActualReviewDate())) {
                            flag = true;
                            fee = projectFee.getReviewPercent() == null ? 0 : projectFee.getReviewPercent().setScale(2).doubleValue();
                        }
                        break;
                    case Constants.PROJECT_FINAL_STEP:
                        if (schedule.getActualFinalDate() != null && now.after(schedule.getActualFinalDate())) {
                            flag = true;
                            fee = projectFee.getFinalPercent() == null ? 0 : projectFee.getFinalPercent().setScale(2).doubleValue();
                        }
                        break;
                }
                if (flag) {
                    vo.setStepNoInvoice(vo.getStepNoInvoice() + fee);//基于节点未开票
                }
            }
        }

        vo.setDebt(vo.getStepNoInvoice() + vo.getInvoiceNoAccount());//待催款金额
        return vo;
    }
}
