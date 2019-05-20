package com.liwj.szpd.service.impl;

import com.liwj.szpd.form.ProjectDateForm;
import com.liwj.szpd.mapper.*;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectScheduleService;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.vo.ProjectFileVO;
import com.liwj.szpd.vo.ProjectScheduleVO;
import com.liwj.szpd.vo.ScheduleItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectScheduleServiceImpl implements ProjectScheduleService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectScheduleMapper projectScheduleMapper;

    @Autowired
    private ProjectFileMapper projectFileMapper;

    @Autowired
    private ProjectLeaderMapper projectLeaderMapper;

    @Autowired
    private ProjectMapper projectMapper;



    @Override
    public ProjectScheduleVO getScheduleInfo(String token, Integer projectId) {
        ProjectScheduleExample example = new ProjectScheduleExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectSchedule> lst = projectScheduleMapper.selectByExample(example);
        if (lst.size() != 1)
            return null;
        ProjectSchedule schedule = lst.get(0);
        ProjectScheduleVO vo = new ProjectScheduleVO();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        vo.setId(schedule.getId());
        vo.setStartDate(schedule.getStartDate() == null ? null : sdf.format(schedule.getStartDate()));

        List<ScheduleItemVO> scheduleItemVOS = new ArrayList<>();

        //项目启动
        ScheduleItemVO scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_START_STEP);
        scheduleItem.setPlanDate(schedule.getPlanStartUpDate() == null ? null : sdf.format(schedule.getPlanStartUpDate()));
        scheduleItem.setActualDate(schedule.getActualStartUpDate() == null ? null : sdf.format(schedule.getActualStartUpDate()));
        scheduleItem.setFiles(boxFile(projectId, Constants.PROJECT_START_STEP));
        scheduleItem.setStepName("项目启动");
        scheduleItemVOS.add(scheduleItem);

        //项目中期
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_MIDDLE_STEP);
        scheduleItem.setPlanDate(schedule.getPlanMiddleDate() == null ? null : sdf.format(schedule.getPlanMiddleDate()));
        scheduleItem.setActualDate(schedule.getActualMiddleDate() == null ? null : sdf.format(schedule.getActualMiddleDate()));
        scheduleItem.setFiles(boxFile(projectId, Constants.PROJECT_MIDDLE_STEP));
        scheduleItem.setStepName("项目中期");
        scheduleItemVOS.add(scheduleItem);

        //项目初步成果
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_PRELIMINARY_STEP);
        scheduleItem.setPlanDate(schedule.getPlanPreliminaryResult() == null ? null : sdf.format(schedule.getPlanPreliminaryResult()));
        scheduleItem.setActualDate(schedule.getActualPreliminaryResult() == null ? null : sdf.format(schedule.getActualPreliminaryResult()));
        scheduleItem.setFiles(boxFile(projectId, Constants.PROJECT_PRELIMINARY_STEP));
        scheduleItem.setStepName("初步成果");
        scheduleItemVOS.add(scheduleItem);

        //项目评审
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_REVIEW_STEP);
        scheduleItem.setPlanDate(schedule.getPlanReviewDate() == null ? null : sdf.format(schedule.getPlanReviewDate()));
        scheduleItem.setActualDate(schedule.getActualReviewDate() == null ? null : sdf.format(schedule.getActualReviewDate()));
        scheduleItem.setFiles(boxFile(projectId, Constants.PROJECT_REVIEW_STEP));
        scheduleItem.setStepName("评审验收");
        scheduleItemVOS.add(scheduleItem);

        //项目最终
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_FINAL_STEP);
        scheduleItem.setPlanDate(schedule.getPlanFinalDate() == null ? null : sdf.format(schedule.getPlanFinalDate()));
        scheduleItem.setActualDate(schedule.getActualFinalDate() == null ? null : sdf.format(schedule.getActualFinalDate()));
        scheduleItem.setFiles(boxFile(projectId, Constants.PROJECT_FINAL_STEP));
        scheduleItem.setStepName("最终成果");
        scheduleItemVOS.add(scheduleItem);

        vo.setSchedules(scheduleItemVOS);

        User user = userMapper.findByToken(token);
        ProjectLeaderExample leaderExample = new ProjectLeaderExample();
        leaderExample.createCriteria().andProjectIdEqualTo(projectId).andUserIdEqualTo(user.getId());
        long c = projectLeaderMapper.countByExample(leaderExample);
        if (c == 0)
            vo.setEditable(false);
        else
            vo.setEditable(true);

        return vo;
    }

    @Override
    public ScheduleItemVO getScheduleStepInfo(String token, Integer scheduleId, Integer step) {
        ProjectSchedule schedule = projectScheduleMapper.selectByPrimaryKey(scheduleId);
        ScheduleItemVO scheduleItem = new ScheduleItemVO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        switch (step) {
            case Constants.PROJECT_START_STEP:
                scheduleItem.setPlanDate(schedule.getPlanStartUpDate() == null ? null : sdf.format(schedule.getPlanStartUpDate()));
                scheduleItem.setActualDate(schedule.getActualStartUpDate() == null ? null : sdf.format(schedule.getActualStartUpDate()));
                scheduleItem.setFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_START_STEP));
                break;
            case Constants.PROJECT_MIDDLE_STEP:
                scheduleItem.setPlanDate(schedule.getPlanMiddleDate() == null ? null : sdf.format(schedule.getPlanMiddleDate()));
                scheduleItem.setActualDate(schedule.getActualMiddleDate() == null ? null : sdf.format(schedule.getActualMiddleDate()));
                scheduleItem.setFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_MIDDLE_STEP));
                break;
            case Constants.PROJECT_PRELIMINARY_STEP:
                scheduleItem.setPlanDate(schedule.getPlanPreliminaryResult() == null ? null : sdf.format(schedule.getPlanPreliminaryResult()));
                scheduleItem.setActualDate(schedule.getActualPreliminaryResult() == null ? null : sdf.format(schedule.getActualPreliminaryResult()));
                scheduleItem.setFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_PRELIMINARY_STEP));
                break;
            case Constants.PROJECT_REVIEW_STEP:
                scheduleItem.setPlanDate(schedule.getPlanReviewDate() == null ? null : sdf.format(schedule.getPlanReviewDate()));
                scheduleItem.setActualDate(schedule.getActualReviewDate() == null ? null : sdf.format(schedule.getActualReviewDate()));
                scheduleItem.setFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_REVIEW_STEP));
                break;
            case Constants.PROJECT_FINAL_STEP:
                scheduleItem.setPlanDate(schedule.getPlanFinalDate() == null ? null : sdf.format(schedule.getPlanFinalDate()));
                scheduleItem.setActualDate(schedule.getActualFinalDate() == null ? null : sdf.format(schedule.getActualFinalDate()));
                scheduleItem.setFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_FINAL_STEP));
                break;
        }
        return scheduleItem;
    }

    @Override
    public boolean updateStep(String token, Integer scheduleId, Integer step, String actualDate, List<String> files) {
        ProjectSchedule schedule = projectScheduleMapper.selectByPrimaryKey(scheduleId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User user = userMapper.findByToken(token);
        try {
            switch (step) {
                case Constants.PROJECT_START_STEP:
                    schedule.setActualStartUpDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_MIDDLE_STEP:
                    schedule.setActualMiddleDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_PRELIMINARY_STEP:
                    schedule.setActualPreliminaryResult(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_REVIEW_STEP:
                    schedule.setActualReviewDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_FINAL_STEP:
                    schedule.setActualFinalDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
            }
            schedule.setUpdatedTime(new Date());
            projectScheduleMapper.updateByPrimaryKeySelective(schedule);

            ProjectFileExample fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(schedule.getProjectId()).andStepEqualTo(step);
            projectFileMapper.deleteByExample(fileExample);

            for (String file : files) {
                ProjectFile projectFile = new ProjectFile();
                projectFile.setStep(step);
                projectFile.setProjectId(schedule.getProjectId());
                projectFile.setPath(file);
                projectFile.setCreatedTime(new Date());
                projectFile.setCreatedBy(user.getId());
                projectFileMapper.insert(projectFile);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private ArrayList boxFile(Integer projectId, Integer step) {
        ProjectFileExample fileExample = new ProjectFileExample();
        fileExample.createCriteria().andProjectIdEqualTo(projectId).andStepEqualTo(step);
        List<ProjectFile> files = projectFileMapper.selectByExample(fileExample);
        ArrayList<ProjectFileVO> res = new ArrayList<>();
        for (ProjectFile file : files) {
            ProjectFileVO fileVO = new ProjectFileVO();
            fileVO.setId(file.getId());
            fileVO.setUrl(file.getPath());
            res.add(fileVO);
        }
        return res;
    }

    @Override
    public boolean updateDates(String token, ProjectDateForm projectDateForm) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User user = userMapper.findByToken(token);

        Integer projectId = projectDateForm.getProjectId();
        ProjectScheduleExample example = new ProjectScheduleExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(example);
        if (projectSchedules.size() == 0)
            return false;
        ProjectSchedule schedule = projectSchedules.get(0);
        try {
            if (projectDateForm.getStartDate() != null && !projectDateForm.getStartDate().equals("")) {
                schedule.setPlanStartUpDate(dateFormat.parse(projectDateForm.getStartDate()));

            }
            if (projectDateForm.getMiddleDate() != null && !projectDateForm.getMiddleDate().equals("")) {
                schedule.setPlanMiddleDate(dateFormat.parse(projectDateForm.getMiddleDate()));

            }
            if (projectDateForm.getPreliminaryDate() != null && !projectDateForm.getPreliminaryDate().equals("")) {
                schedule.setPlanPreliminaryResult(dateFormat.parse(projectDateForm.getPreliminaryDate()));


            }
            if (projectDateForm.getReviewDate() != null && !projectDateForm.getReviewDate().equals("")) {
                schedule.setPlanReviewDate(dateFormat.parse(projectDateForm.getReviewDate()));

            }
            if (projectDateForm.getFinalDate() != null && !projectDateForm.getFinalDate().equals("")) {
                schedule.setPlanFinalDate(dateFormat.parse(projectDateForm.getFinalDate()));


            }
            schedule.setUpdatedTime(new Date());
            schedule.setUpdatedBy(user.getId());
            projectScheduleMapper.updateByPrimaryKeySelective(schedule);

            Project project = projectMapper.selectByPrimaryKey(projectId);
            if (project.getStatus() == Constants.PROJECT_NEW) {
                project.setStatus(Constants.PROJECT_DOING);
                projectMapper.updateByPrimaryKeySelective(project);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public ProjectDateForm getPlanDates(String token, Integer projectId) {
        ProjectDateForm form = new ProjectDateForm();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProjectScheduleExample example = new ProjectScheduleExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectSchedule> projectSchedules = projectScheduleMapper.selectByExample(example);
        if (projectSchedules.size() == 0)
            return form;
        ProjectSchedule schedule = projectSchedules.get(0);
        form.setProjectId(projectId);

        form.setStartDate(schedule.getPlanStartUpDate() == null ? "" : sdf.format(schedule.getPlanStartUpDate()));
        form.setMiddleDate(schedule.getPlanMiddleDate() == null ? "" : sdf.format(schedule.getPlanMiddleDate()));
        form.setPreliminaryDate(schedule.getPlanPreliminaryResult() == null ? "" : sdf.format(schedule.getPlanPreliminaryResult()));
        form.setReviewDate(schedule.getPlanReviewDate() == null ? "" : sdf.format(schedule.getPlanReviewDate()));
        form.setFinalDate(schedule.getPlanFinalDate() == null ? "" : sdf.format(schedule.getPlanFinalDate()));

        return form;
    }

    @Override
    public boolean updateStartDate(String token, Integer id, String date) {
        User user = userMapper.findByToken(token);
        ProjectSchedule schedule = projectScheduleMapper.selectByPrimaryKey(id);
        schedule.setUpdatedBy(user.getId());
        schedule.setUpdatedTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            schedule.setStartDate(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        projectScheduleMapper.updateByPrimaryKeySelective(schedule);
        return true;
    }

    @Override
    public boolean updatePlanDate(String token, Integer id, String date, String type) {
        User user = userMapper.findByToken(token);
        ProjectSchedule schedule = projectScheduleMapper.selectByPrimaryKey(id);
        schedule.setUpdatedBy(user.getId());
        schedule.setUpdatedTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            switch (type) {
                case "start":
                    schedule.setPlanStartUpDate(dateFormat.parse(date));
                    break;
                case "middle":
                    schedule.setPlanMiddleDate(dateFormat.parse(date));
                    break;
                case "final":
                    schedule.setPlanFinalDate(dateFormat.parse(date));
                    break;
                case "review":
                    schedule.setPlanReviewDate(dateFormat.parse(date));
                    break;
                case "preliminary":
                    schedule.setPlanPreliminaryResult(dateFormat.parse(date));
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    @Override
    public boolean updateActualDate(String token, Integer id, String date, String type) {
        User user = userMapper.findByToken(token);
        ProjectSchedule schedule = projectScheduleMapper.selectByPrimaryKey(id);
        schedule.setUpdatedBy(user.getId());
        schedule.setUpdatedTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            switch (type) {
                case "start":
                    schedule.setActualStartUpDate(dateFormat.parse(date));
                    break;
                case "middle":
                    schedule.setActualMiddleDate(dateFormat.parse(date));
                    break;
                case "final":
                    schedule.setActualFinalDate(dateFormat.parse(date));
                    break;
                case "review":
                    schedule.setActualReviewDate(dateFormat.parse(date));
                    break;
                case "preliminary":
                    schedule.setActualPreliminaryResult(dateFormat.parse(date));
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
