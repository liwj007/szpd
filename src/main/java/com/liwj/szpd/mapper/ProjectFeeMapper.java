package com.liwj.szpd.mapper;

import com.liwj.szpd.model.ProjectFee;
import com.liwj.szpd.model.ProjectFeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectFeeMapper {
    long countByExample(ProjectFeeExample example);

    int deleteByExample(ProjectFeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFee record);

    int insertSelective(ProjectFee record);

    List<ProjectFee> selectByExample(ProjectFeeExample example);

    ProjectFee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectFee record, @Param("example") ProjectFeeExample example);

    int updateByExample(@Param("record") ProjectFee record, @Param("example") ProjectFeeExample example);

    int updateByPrimaryKeySelective(ProjectFee record);

    int updateByPrimaryKey(ProjectFee record);
}