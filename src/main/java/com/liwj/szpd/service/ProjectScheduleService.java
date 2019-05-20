package com.liwj.szpd.service;

import com.liwj.szpd.form.ProjectDateForm;
import com.liwj.szpd.vo.ProjectScheduleVO;
import com.liwj.szpd.vo.ScheduleItemVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectScheduleService {
    boolean updateStartDate(String token,Integer id, String date);

    boolean updatePlanDate(String token, Integer id, String date, String type);

    boolean updateActualDate(String token, Integer id, String date, String type);


    ProjectScheduleVO getScheduleInfo(String token, Integer projectId);

    ScheduleItemVO getScheduleStepInfo(String token, Integer scheduleId, Integer step);

    boolean updateStep(String token, Integer scheduleId, Integer step,  String actualDate, List<String> files);

    boolean updateDates(String token, ProjectDateForm projectDateForm);

    ProjectDateForm getPlanDates(String token, Integer projectId);
}
