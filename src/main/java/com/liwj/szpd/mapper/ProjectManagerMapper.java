package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectManager;
import com.liwj.szpd.model.ProjectManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectManagerMapper {
    long countByExample(ProjectManagerExample example);

    int deleteByExample(ProjectManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectManager record);

    int insertSelective(ProjectManager record);

    List<ProjectManager> selectByExample(ProjectManagerExample example);

    ProjectManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectManager record, @Param("example") ProjectManagerExample example);

    int updateByExample(@Param("record") ProjectManager record, @Param("example") ProjectManagerExample example);

    int updateByPrimaryKeySelective(ProjectManager record);

    int updateByPrimaryKey(ProjectManager record);
}