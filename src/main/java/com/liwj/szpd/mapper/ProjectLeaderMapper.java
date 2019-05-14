package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectLeader;
import com.liwj.szpd.model.ProjectLeaderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectLeaderMapper {
    long countByExample(ProjectLeaderExample example);

    int deleteByExample(ProjectLeaderExample example);

    int insert(ProjectLeader record);

    int insertSelective(ProjectLeader record);

    List<ProjectLeader> selectByExample(ProjectLeaderExample example);

    int updateByExampleSelective(@Param("record") ProjectLeader record, @Param("example") ProjectLeaderExample example);

    int updateByExample(@Param("record") ProjectLeader record, @Param("example") ProjectLeaderExample example);
}