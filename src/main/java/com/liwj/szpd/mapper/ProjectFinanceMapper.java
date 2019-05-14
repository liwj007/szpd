package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectFinance;
import com.liwj.szpd.model.ProjectFinanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFinanceMapper {
    long countByExample(ProjectFinanceExample example);

    int deleteByExample(ProjectFinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFinance record);

    int insertSelective(ProjectFinance record);

    List<ProjectFinance> selectByExample(ProjectFinanceExample example);

    ProjectFinance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFinance record, @Param("example") ProjectFinanceExample example);

    int updateByExample(@Param("record") ProjectFinance record, @Param("example") ProjectFinanceExample example);

    int updateByPrimaryKeySelective(ProjectFinance record);

    int updateByPrimaryKey(ProjectFinance record);
}