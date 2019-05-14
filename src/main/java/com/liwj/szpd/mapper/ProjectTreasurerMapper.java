package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectTreasurer;
import com.liwj.szpd.model.ProjectTreasurerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectTreasurerMapper {
    long countByExample(ProjectTreasurerExample example);

    int deleteByExample(ProjectTreasurerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectTreasurer record);

    int insertSelective(ProjectTreasurer record);

    List<ProjectTreasurer> selectByExample(ProjectTreasurerExample example);

    ProjectTreasurer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectTreasurer record, @Param("example") ProjectTreasurerExample example);

    int updateByExample(@Param("record") ProjectTreasurer record, @Param("example") ProjectTreasurerExample example);

    int updateByPrimaryKeySelective(ProjectTreasurer record);

    int updateByPrimaryKey(ProjectTreasurer record);
}