package com.liwj.szpd.service.impl;

import com.liwj.szpd.mapper.ProjectFileMapper;
import com.liwj.szpd.mapper.ProjectLeaderMapper;
import com.liwj.szpd.mapper.ProjectScheduleMapper;
import com.liwj.szpd.mapper.UserMapper;
import com.liwj.szpd.model.*;
import com.liwj.szpd.service.ProjectScheduleService;
import com.liwj.szpd.utils.Constants;
import com.liwj.szpd.vo.ProjectFileVO;
import com.liwj.szpd.vo.ProjectScheduleVO;
import com.liwj.szpd.vo.ScheduleItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        if (c==0)
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
    public boolean updateStep(String token, Integer scheduleId, Integer step, String planDate, String actualDate, List<String> files) {
        ProjectSchedule schedule = projectScheduleMapper.selectByPrimaryKey(scheduleId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User user = userMapper.findByToken(token);
        try {
            switch (step) {
                case Constants.PROJECT_START_STEP:
                    schedule.setPlanStartUpDate(planDate == null ? null : dateFormat.parse(planDate));
                    schedule.setActualStartUpDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_MIDDLE_STEP:
                    schedule.setPlanMiddleDate(planDate == null ? null : dateFormat.parse(planDate));
                    schedule.setActualMiddleDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_PRELIMINARY_STEP:
                    schedule.setPlanPreliminaryResult(planDate == null ? null : dateFormat.parse(planDate));
                    schedule.setActualPreliminaryResult(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_REVIEW_STEP:
                    schedule.setPlanReviewDate(planDate == null ? null : dateFormat.parse(planDate));
                    schedule.setActualReviewDate(actualDate == null ? null : dateFormat.parse(actualDate));
                    break;
                case Constants.PROJECT_FINAL_STEP:
                    schedule.setPlanFinalDate(planDate == null ? null : dateFormat.parse(planDate));
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
    public List<String> getUploads(Integer projectID) {
        ProjectFileExample example = new ProjectFileExample();
        example.createCriteria().andProjectIdEqualTo(projectID);
        List<ProjectFile> files = projectFileMapper.selectByExample(example);
        List<String> res = new ArrayList<>();
        for (ProjectFile file : files) {
            res.add(file.getPath());
        }
        return res;
    }

    @Override
    public String uploadFile(String token, MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path;
            String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;

            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {

                    String trueFileName = generateRandomFilename();


                    // 项目在容器中实际发布运行的根路径
                    String realPath = "/Users/liwj/Documents/uploads/";

                    // 设置存放图片文件的路径
                    path = realPath + trueFileName;
                    try {
                        file.transferTo(new File(path));
                        return path;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
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


    private String twoNumbers(int number) {
        String _number = number + "";
        if (_number.length() < 2) {
            _number = "0" + _number;
        }
        return _number;
    }

    private String generateRandomFilename() {
        String fourRandom = "";
        //产生4位的随机数(不足4位前加零)
        int randomNum = (int) (Math.random() * 10000);
        fourRandom = randomNum + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = fourRandom + "0";
        }
        StringBuilder sb = new StringBuilder("");

        Calendar calendar = Calendar.getInstance();

        sb.append(calendar.get(Calendar.YEAR))
                .append(twoNumbers(calendar.get(Calendar.MONTH) + 1))
                .append(twoNumbers(calendar.get(Calendar.DAY_OF_MONTH)))
                .append(twoNumbers(calendar.get(Calendar.HOUR)))
                .append(twoNumbers(calendar.get(Calendar.MINUTE)))
                .append(twoNumbers(calendar.get(Calendar.SECOND)))
                .append(fourRandom);
        return sb.toString();
    }
}
