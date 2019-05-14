package com.liwj.szpd.service;

import com.liwj.szpd.vo.ProjectScheduleVO;
import com.liwj.szpd.vo.ScheduleItemVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectScheduleService {
    boolean updateStartDate(String token,Integer id, String date);

    boolean updatePlanDate(String token, Integer id, String date, String type);

    boolean updateActualDate(String token, Integer id, String date, String type);

    String uploadFile(String token, MultipartFile file);

    List<String> getUploads(Integer projectID);

    ProjectScheduleVO getScheduleInfo(String token, Integer projectId);

    ScheduleItemVO getScheduleStepInfo(String token, Integer scheduleId, Integer step);

    boolean updateStep(String token, Integer scheduleId, Integer step, String planDate, String actualDate, List<String> files);
}
