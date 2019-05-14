package com.liwj.szpd.mapper;

import com.liwj.szpd.model.RolePermit;
import com.liwj.szpd.model.RolePermitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermitMapper {
    long countByExample(RolePermitExample example);

    int deleteByExample(RolePermitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermit record);

    int insertSelective(RolePermit record);

    List<RolePermit> selectByExample(RolePermitExample example);

    RolePermit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermit record, @Param("example") RolePermitExample example);

    int updateByExample(@Param("record") RolePermit record, @Param("example") RolePermitExample example);

    int updateByPrimaryKeySelective(RolePermit record);

    int updateByPrimaryKey(RolePermit record);
}