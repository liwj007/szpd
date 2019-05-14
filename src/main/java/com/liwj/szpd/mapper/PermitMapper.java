package com.liwj.szpd.mapper;

import com.liwj.szpd.model.Permit;
import com.liwj.szpd.model.PermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermitMapper {
    long countByExample(PermitExample example);

    int deleteByExample(PermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permit record);

    int insertSelective(Permit record);

    List<Permit> selectByExample(PermitExample example);

    Permit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permit record, @Param("example") PermitExample example);

    int updateByExample(@Param("record") Permit record, @Param("example") PermitExample example);

    int updateByPrimaryKeySelective(Permit record);

    int updateByPrimaryKey(Permit record);
}