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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        scheduleItem.setcFiles(boxFile(projectId, Constants.PROJECT_START_STEP, Constants.FILE_CHECK));
        scheduleItem.setpFiles(boxFile(projectId, Constants.PROJECT_START_STEP, Constants.FILE_MEETING_PHOTO));
        scheduleItem.setmFiles(boxFile(projectId, Constants.PROJECT_START_STEP, Constants.FILE_MEETING_SUMMARY));
        scheduleItem.setStepName("合同签订");
        scheduleItemVOS.add(scheduleItem);

        //项目中期
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_MIDDLE_STEP);
        scheduleItem.setPlanDate(schedule.getPlanMiddleDate() == null ? null : sdf.format(schedule.getPlanMiddleDate()));
        scheduleItem.setActualDate(schedule.getActualMiddleDate() == null ? null : sdf.format(schedule.getActualMiddleDate()));
        scheduleItem.setcFiles(boxFile(projectId, Constants.PROJECT_MIDDLE_STEP, Constants.FILE_CHECK));
        scheduleItem.setpFiles(boxFile(projectId, Constants.PROJECT_MIDDLE_STEP, Constants.FILE_MEETING_PHOTO));
        scheduleItem.setmFiles(boxFile(projectId, Constants.PROJECT_MIDDLE_STEP, Constants.FILE_MEETING_SUMMARY));
        scheduleItem.setStepName("中间成果");
        scheduleItemVOS.add(scheduleItem);

        //项目初步成果
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_PRELIMINARY_STEP);
        scheduleItem.setPlanDate(schedule.getPlanPreliminaryResult() == null ? null : sdf.format(schedule.getPlanPreliminaryResult()));
        scheduleItem.setActualDate(schedule.getActualPreliminaryResult() == null ? null : sdf.format(schedule.getActualPreliminaryResult()));
        scheduleItem.setcFiles(boxFile(projectId, Constants.PROJECT_PRELIMINARY_STEP, Constants.FILE_CHECK));
        scheduleItem.setpFiles(boxFile(projectId, Constants.PROJECT_PRELIMINARY_STEP, Constants.FILE_MEETING_PHOTO));
        scheduleItem.setmFiles(boxFile(projectId, Constants.PROJECT_PRELIMINARY_STEP, Constants.FILE_MEETING_SUMMARY));
        scheduleItem.setStepName("论证评审");
        scheduleItemVOS.add(scheduleItem);

        //项目评审
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_REVIEW_STEP);
        scheduleItem.setPlanDate(schedule.getPlanReviewDate() == null ? null : sdf.format(schedule.getPlanReviewDate()));
        scheduleItem.setActualDate(schedule.getActualReviewDate() == null ? null : sdf.format(schedule.getActualReviewDate()));
        scheduleItem.setcFiles(boxFile(projectId, Constants.PROJECT_REVIEW_STEP, Constants.FILE_CHECK));
        scheduleItem.setpFiles(boxFile(projectId, Constants.PROJECT_REVIEW_STEP, Constants.FILE_MEETING_PHOTO));
        scheduleItem.setmFiles(boxFile(projectId, Constants.PROJECT_REVIEW_STEP, Constants.FILE_MEETING_SUMMARY));

        scheduleItem.setStepName("最终成果");
        scheduleItemVOS.add(scheduleItem);

        //项目最终
        scheduleItem = new ScheduleItemVO();
        scheduleItem.setStep(Constants.PROJECT_FINAL_STEP);
        scheduleItem.setPlanDate(schedule.getPlanFinalDate() == null ? null : sdf.format(schedule.getPlanFinalDate()));
        scheduleItem.setActualDate(schedule.getActualFinalDate() == null ? null : sdf.format(schedule.getActualFinalDate()));
        scheduleItem.setcFiles(boxFile(projectId, Constants.PROJECT_FINAL_STEP, Constants.FILE_CHECK));
        scheduleItem.setpFiles(boxFile(projectId, Constants.PROJECT_FINAL_STEP, Constants.FILE_MEETING_PHOTO));
        scheduleItem.setmFiles(boxFile(projectId, Constants.PROJECT_FINAL_STEP, Constants.FILE_MEETING_SUMMARY));
        scheduleItem.setStepName("报批通过");
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
    public double getProjectProgress(Integer projectId) {
        ProjectScheduleExample example = new ProjectScheduleExample();
        example.createCriteria().andProjectIdEqualTo(projectId);
        List<ProjectSchedule> lst = projectScheduleMapper.selectByExample(example);
        if (lst.size() != 1)
            return 0;
        ProjectSchedule schedule = lst.get(0);

        int total = 0;
        int finish = 0;

        if (schedule.getPlanStartUpDate() != null) {
            total++;
            if (schedule.getActualStartUpDate() != null) {
                finish++;
            }
        }
        if (schedule.getPlanMiddleDate() != null) {
            total++;
            if (schedule.getActualMiddleDate() != null) {
                finish++;
            }
        }
        if (schedule.getPlanPreliminaryResult() != null) {
            total++;
            if (schedule.getActualPreliminaryResult() != null) {
                finish++;
            }
        }
        if (schedule.getPlanReviewDate() != null) {
            total++;
            if (schedule.getActualReviewDate() != null) {
                finish++;
            }
        }
        if (schedule.getPlanFinalDate() != null) {
            total++;
            if (schedule.getActualFinalDate() != null) {
                finish++;
            }
        }
        BigDecimal bd = total == 0.0 ? new BigDecimal(0.0) : new BigDecimal((double) finish / total);

        return bd.setScale(4, RoundingMode.HALF_UP).doubleValue();
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
                scheduleItem.setcFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_START_STEP, Constants.FILE_CHECK));
                scheduleItem.setpFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_START_STEP, Constants.FILE_MEETING_PHOTO));
                scheduleItem.setmFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_START_STEP, Constants.FILE_MEETING_SUMMARY));
                break;
            case Constants.PROJECT_MIDDLE_STEP:
                scheduleItem.setPlanDate(schedule.getPlanMiddleDate() == null ? null : sdf.format(schedule.getPlanMiddleDate()));
                scheduleItem.setActualDate(schedule.getActualMiddleDate() == null ? null : sdf.format(schedule.getActualMiddleDate()));
                scheduleItem.setcFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_MIDDLE_STEP, Constants.FILE_CHECK));
                scheduleItem.setpFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_MIDDLE_STEP, Constants.FILE_MEETING_PHOTO));
                scheduleItem.setmFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_MIDDLE_STEP, Constants.FILE_MEETING_SUMMARY));
                break;
            case Constants.PROJECT_PRELIMINARY_STEP:
                scheduleItem.setPlanDate(schedule.getPlanPreliminaryResult() == null ? null : sdf.format(schedule.getPlanPreliminaryResult()));
                scheduleItem.setActualDate(schedule.getActualPreliminaryResult() == null ? null : sdf.format(schedule.getActualPreliminaryResult()));
                scheduleItem.setcFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_PRELIMINARY_STEP, Constants.FILE_CHECK));
                scheduleItem.setpFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_PRELIMINARY_STEP, Constants.FILE_MEETING_PHOTO));
                scheduleItem.setmFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_PRELIMINARY_STEP, Constants.FILE_MEETING_SUMMARY));
                break;
            case Constants.PROJECT_REVIEW_STEP:
                scheduleItem.setPlanDate(schedule.getPlanReviewDate() == null ? null : sdf.format(schedule.getPlanReviewDate()));
                scheduleItem.setActualDate(schedule.getActualReviewDate() == null ? null : sdf.format(schedule.getActualReviewDate()));
                scheduleItem.setcFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_REVIEW_STEP, Constants.FILE_CHECK));
                scheduleItem.setpFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_REVIEW_STEP, Constants.FILE_MEETING_PHOTO));
                scheduleItem.setmFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_REVIEW_STEP, Constants.FILE_MEETING_SUMMARY));
                break;
            case Constants.PROJECT_FINAL_STEP:
                scheduleItem.setPlanDate(schedule.getPlanFinalDate() == null ? null : sdf.format(schedule.getPlanFinalDate()));
                scheduleItem.setActualDate(schedule.getActualFinalDate() == null ? null : sdf.format(schedule.getActualFinalDate()));
                scheduleItem.setcFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_FINAL_STEP, Constants.FILE_CHECK));
                scheduleItem.setpFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_FINAL_STEP, Constants.FILE_MEETING_PHOTO));
                scheduleItem.setmFiles(boxFile(schedule.getProjectId(), Constants.PROJECT_FINAL_STEP, Constants.FILE_MEETING_SUMMARY));
                break;
        }
        return scheduleItem;
    }

    @Override
    public boolean updateStep(String token, Integer scheduleId, Integer step, String actualDate, List<String> cFiles, List<String> pFiles, List<String> mFiles) {
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

//            ProjectFileExample fileExample = new ProjectFileExample();
//            fileExample.createCriteria().andProjectIdEqualTo(schedule.getProjectId()).andStepEqualTo(step);
//            projectFileMapper.deleteByExample(fileExample);

            ProjectFileExample fileExample = new ProjectFileExample();
            fileExample.createCriteria().andProjectIdEqualTo(schedule.getProjectId()).andStepEqualTo(step);
            List<ProjectFile> currentFiles = projectFileMapper.selectByExample(fileExample);
            List<String> currentFilePath = new ArrayList<>();
            for (ProjectFile file : currentFiles) {
                currentFilePath.add(file.getPath());
            }

            for (String file : cFiles) {
                if (!currentFilePath.contains(file)) {
                    ProjectFile projectFile = new ProjectFile();
                    projectFile.setStep(step);
                    projectFile.setProjectId(schedule.getProjectId());
                    projectFile.setPath(file);
                    projectFile.setCreatedTime(new Date());
                    projectFile.setCreatedBy(user.getId());
                    projectFile.setCategory(Constants.FILE_CHECK);
                    projectFileMapper.insert(projectFile);
                } else {
                    currentFilePath.remove(file);
                }
            }


            for (String file : pFiles) {
                if (!currentFilePath.contains(file)) {
                    ProjectFile projectFile = new ProjectFile();
                    projectFile.setStep(step);
                    projectFile.setProjectId(schedule.getProjectId());
                    projectFile.setPath(file);
                    projectFile.setCreatedTime(new Date());
                    projectFile.setCreatedBy(user.getId());
                    projectFile.setCategory(Constants.FILE_MEETING_PHOTO);
                    projectFileMapper.insert(projectFile);
                } else {
                    currentFilePath.remove(file);
                }
            }


            for (String file : mFiles) {
                if (!currentFilePath.contains(file)) {
                    ProjectFile projectFile = new ProjectFile();
                    projectFile.setStep(step);
                    projectFile.setProjectId(schedule.getProjectId());
                    projectFile.setPath(file);
                    projectFile.setCreatedTime(new Date());
                    projectFile.setCreatedBy(user.getId());
                    projectFile.setCategory(Constants.FILE_MEETING_SUMMARY);
                    projectFileMapper.insert(projectFile);
                } else {
                    currentFilePath.remove(file);
                }
            }
            ProjectFileExample delFileExample = new ProjectFileExample();
            delFileExample.createCriteria().andPathIn(currentFilePath);
            projectFileMapper.deleteByExample(delFileExample);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private ArrayList boxFile(Integer projectId, Integer step, Integer category) {
        ProjectFileExample fileExample = new ProjectFileExample();
        fileExample.createCriteria().andProjectIdEqualTo(projectId).andStepEqualTo(step).andCategoryEqualTo(category);
        List<ProjectFile> files = projectFileMapper.selectByExample(fileExample);
        ArrayList<ProjectFileVO> res = new ArrayList<>();
        for (ProjectFile file : files) {
            ProjectFileVO fileVO = new ProjectFileVO();
            fileVO.setId(file.getId());
            fileVO.setUrl(file.getPath());

            String extension = file.getPath().substring(file.getPath().lastIndexOf(".") + 1);
            if ("pdf".equals(extension)){
                fileVO.setType("pdf");
            }else{
                fileVO.setType("img");
            }

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
