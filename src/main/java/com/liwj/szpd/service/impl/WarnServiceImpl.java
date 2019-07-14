package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.ProjectManagerMapper;
import com.liwj.szpd.mapper.ProjectMapper;
import com.liwj.szpd.mapper.ProjectScheduleMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectFeeService;
import com.liwj.szpd.service.WarnService;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WarnServiceImpl implements WarnService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectManagerMapper projectManagerMapper;

    @Autowired
    private ProjectFeeService projectFeeService;

    @Autowired
    private ProjectScheduleMapper projectScheduleMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public DebtWarnVO getDebtWarning(String token) {
        User user = userMapper.findByToken(token);
        HashSet<Integer> projectIds = getProjectIds(user);

        DebtWarnVO vo = new DebtWarnVO();

        for (Integer projectId : projectIds) {
            FinanceStatisticVO statisticVO = projectFeeService.statisticFinanceInfo(projectId);
            if (statisticVO.getDebt() != 0.0) {
                vo.setProjectNo(vo.getProjectNo() + 1);
                vo.setTotal(vo.getTotal() + statisticVO.getDebt());
                vo.setInvoice(vo.getInvoice() + statisticVO.getStepNoInvoice());
                vo.setAccount(vo.getAccount() + statisticVO.getInvoiceNoAccount());
            }
        }

        return vo;
    }

    private HashSet<Integer> getProjectIds(User user){
        ProjectManagerExample managerExample = new ProjectManagerExample();
        managerExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ProjectManager> projectManagers = projectManagerMapper.selectByExample(managerExample);
        HashSet<Integer> projectIds = new HashSet<>();
        for (ProjectManager manager : projectManagers) {
            Project project = projectMapper.selectByPrimaryKey(manager.getProjectId());
            if (project.getStatus()== Constants.PROJECT_NEW || project.getStatus()==Constants.PROJECT_DOING){
                projectIds.add(manager.getProjectId());
            }
        }
        return projectIds;
    }

    @Override
    public ProcessWarnVO getProcessWarning(String token) {
        User user = userMapper.findByToken(token);
        HashSet<Integer> projectIds = getProjectIds(user);

        ProcessWarnVO vo = new ProcessWarnVO();
        Date now = new Date();

        Calendar cd = Calendar.getInstance();
        cd.setTime(now);
        //获取下个月第一天：
        cd.add(Calendar.MONTH, 1);
        //设置为1号,当前日期既为次月第一天
        cd.set(Calendar.DAY_OF_MONTH, 1);
        Date next = cd.getTime();

        HashSet<Integer> total = new HashSet<>();
        for (Integer projectId : projectIds) {
            ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
            scheduleExample.createCriteria().andProjectIdEqualTo(projectId);
            List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(scheduleExample);
            ProjectSchedule schedule = projectSchedules.get(0);

            if (schedule.getPlanStartUpDate() != null && schedule.getPlanStartUpDate().before(now) && schedule.getActualStartUpDate() == null) {
                vo.setOutDate(vo.getOutDate() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanMiddleDate() != null && schedule.getPlanMiddleDate().before(now) && schedule.getActualMiddleDate() == null) {
                vo.setOutDate(vo.getOutDate() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanPreliminaryResult() != null && schedule.getPlanPreliminaryResult().before(now) && schedule.getActualPreliminaryResult() == null) {
                vo.setOutDate(vo.getOutDate() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanReviewDate() != null && schedule.getPlanReviewDate().before(now) && schedule.getActualReviewDate() == null) {
                vo.setOutDate(vo.getOutDate() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanFinalDate() != null && schedule.getPlanFinalDate().before(now) && schedule.getActualFinalDate() == null) {
                vo.setOutDate(vo.getOutDate() + 1);
                total.add(projectId);
            }

            if (schedule.getPlanStartUpDate() != null && schedule.getActualStartUpDate() == null
                    && schedule.getPlanStartUpDate().after(now) && schedule.getPlanStartUpDate().before(next)) {
                vo.setMonth(vo.getMonth() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanMiddleDate() != null && schedule.getActualMiddleDate() == null
                    && schedule.getPlanMiddleDate().after(now) && schedule.getPlanMiddleDate().before(next)) {
                vo.setMonth(vo.getMonth() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanPreliminaryResult() != null && schedule.getActualPreliminaryResult() == null
                    && schedule.getPlanPreliminaryResult().after(now) && schedule.getPlanPreliminaryResult().before(next)) {
                vo.setMonth(vo.getMonth() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanReviewDate() != null && schedule.getActualReviewDate() == null
                    && schedule.getPlanReviewDate().after(now) && schedule.getPlanReviewDate().before(next)) {
                vo.setMonth(vo.getMonth() + 1);
                total.add(projectId);
            }
            if (schedule.getPlanFinalDate() != null && schedule.getActualFinalDate() == null
                    && schedule.getPlanFinalDate().after(now) && schedule.getPlanFinalDate().before(next)) {
                vo.setMonth(vo.getMonth() + 1);
                total.add(projectId);
            }
        }
        vo.setNo(total.size());
        vo.setTotal(vo.getOutDate()+vo.getMonth());

        return vo;
    }

    @Override
    public List<ProcessWarnDetailVO> getProcessWarningDetail(String token) {
        User user = userMapper.findByToken(token);
        HashSet<Integer> projectIds = getProjectIds(user);

        List<ProcessWarnDetailVO> res = new ArrayList<>();

        Date now = new Date();
        Calendar cd = Calendar.getInstance();
        cd.setTime(now);
        cd.add(Calendar.MONTH, 1);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        Date next = cd.getTime();

        for (Integer projectId: projectIds){
            ProcessWarnDetailVO vo = new ProcessWarnDetailVO();
            boolean flag = false;

            ProjectScheduleExample scheduleExample = new ProjectScheduleExample();
            scheduleExample.createCriteria().andProjectIdEqualTo(projectId);
            List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(scheduleExample);
            ProjectSchedule schedule = projectSchedules.get(0);

            if (schedule.getPlanStartUpDate() != null && schedule.getPlanStartUpDate().before(now) && schedule.getActualStartUpDate() == null) {
                vo.getOutDateSteps().add("项目启动");
                flag=true;
            }
            if (schedule.getPlanMiddleDate() != null && schedule.getPlanMiddleDate().before(now) && schedule.getActualMiddleDate() == null) {
                vo.getOutDateSteps().add("项目中期");
                flag=true;
            }
            if (schedule.getPlanPreliminaryResult() != null && schedule.getPlanPreliminaryResult().before(now) && schedule.getActualPreliminaryResult() == null) {
                vo.getOutDateSteps().add("初步成果");
                flag=true;
            }
            if (schedule.getPlanReviewDate() != null && schedule.getPlanReviewDate().before(now) && schedule.getActualReviewDate() == null) {
                vo.getOutDateSteps().add("评审验收");
                flag=true;
            }
            if (schedule.getPlanFinalDate() != null && schedule.getPlanFinalDate().before(now) && schedule.getActualFinalDate() == null) {
                vo.getOutDateSteps().add("最终成果");
                flag=true;
            }

            if (schedule.getPlanStartUpDate() != null && schedule.getActualStartUpDate() == null
                    && schedule.getPlanStartUpDate().after(now) && schedule.getPlanStartUpDate().before(next)) {
                vo.getMonthSteps().add("项目启动");
                flag=true;
            }
            if (schedule.getPlanMiddleDate() != null && schedule.getActualMiddleDate() == null
                    && schedule.getPlanMiddleDate().after(now) && schedule.getPlanMiddleDate().before(next)) {
                vo.getMonthSteps().add("项目中期");
                flag=true;
            }
            if (schedule.getPlanPreliminaryResult() != null && schedule.getActualPreliminaryResult() == null
                    && schedule.getPlanPreliminaryResult().after(now) && schedule.getPlanPreliminaryResult().before(next)) {
                vo.getMonthSteps().add("初步成果");
                flag=true;
            }
            if (schedule.getPlanReviewDate() != null && schedule.getActualReviewDate() == null
                    && schedule.getPlanReviewDate().after(now) && schedule.getPlanReviewDate().before(next)) {
                vo.getMonthSteps().add("评审验收");
                flag=true;
            }
            if (schedule.getPlanFinalDate() != null && schedule.getActualFinalDate() == null
                    && schedule.getPlanFinalDate().after(now) && schedule.getPlanFinalDate().before(next)) {
                vo.getMonthSteps().add("最终成果");
                flag=true;
            }
            if (flag){
                Project project = projectMapper.selectByPrimaryKey(projectId);
                vo.setId(projectId);
                vo.setName(project.getName());
                res.add(vo);
            }
        }

        return res;
    }

    @Override
    public List<DebtWarnDetailVO> getDebtWarningDetail(String token) {
        User user = userMapper.findByToken(token);
        HashSet<Integer> projectIds = getProjectIds(user);

        List<DebtWarnDetailVO> res = new ArrayList<>();

        for (Integer projectId : projectIds) {
            FinanceStatisticVO statisticVO = projectFeeService.statisticFinanceInfo(projectId);
            if (statisticVO.getDebt() != 0.0) {
                DebtWarnDetailVO vo = new DebtWarnDetailVO();

                vo.setInvoice(statisticVO.getStepNoInvoice());
                vo.setAccount(statisticVO.getInvoiceNoAccount());
                vo.setDebt(statisticVO.getDebt());

                Project project = projectMapper.selectByPrimaryKey(projectId);
                vo.setId(projectId);
                vo.setName(project.getName());
                res.add(vo);
            }
        }

        return res;
    }
}
