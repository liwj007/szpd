package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectFinanceStep;
import com.liwj.szpd.model.ProjectFinanceStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFinanceStepMapper {
    long countByExample(ProjectFinanceStepExample example);

    int deleteByExample(ProjectFinanceStepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFinanceStep record);

    int insertSelective(ProjectFinanceStep record);

    List<ProjectFinanceStep> selectByExample(ProjectFinanceStepExample example);

    ProjectFinanceStep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFinanceStep record, @Param("example") ProjectFinanceStepExample example);

    int updateByExample(@Param("record") ProjectFinanceStep record, @Param("example") ProjectFinanceStepExample example);

    int updateByPrimaryKeySelective(ProjectFinanceStep record);

    int updateByPrimaryKey(ProjectFinanceStep record);
}