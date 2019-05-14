package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectSchedule;
import com.liwj.szpd.model.ProjectScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectScheduleMapper {
    long countByExample(ProjectScheduleExample example);

    int deleteByExample(ProjectScheduleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectSchedule record);

    int insertSelective(ProjectSchedule record);

    List<ProjectSchedule> selectByExample(ProjectScheduleExample example);

    ProjectSchedule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectSchedule record, @Param("example") ProjectScheduleExample example);

    int updateByExample(@Param("record") ProjectSchedule record, @Param("example") ProjectScheduleExample example);

    int updateByPrimaryKeySelective(ProjectSchedule record);

    int updateByPrimaryKey(ProjectSchedule record);
}