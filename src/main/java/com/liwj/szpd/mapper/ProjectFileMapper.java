package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectFile;
import com.liwj.szpd.model.ProjectFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFileMapper {
    long countByExample(ProjectFileExample example);

    int deleteByExample(ProjectFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFile record);

    int insertSelective(ProjectFile record);

    List<ProjectFile> selectByExample(ProjectFileExample example);

    ProjectFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFile record, @Param("example") ProjectFileExample example);

    int updateByExample(@Param("record") ProjectFile record, @Param("example") ProjectFileExample example);

    int updateByPrimaryKeySelective(ProjectFile record);

    int updateByPrimaryKey(ProjectFile record);
}