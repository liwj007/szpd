package com.liwj.szpd.mapper;

import com.liwj.szpd.model.PhoneCodeWeb;
import com.liwj.szpd.model.PhoneCodeWebExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneCodeWebMapper {
    long countByExample(PhoneCodeWebExample example);

    int deleteByExample(PhoneCodeWebExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhoneCodeWeb record);

    int insertSelective(PhoneCodeWeb record);

    List<PhoneCodeWeb> selectByExample(PhoneCodeWebExample example);

    PhoneCodeWeb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhoneCodeWeb record, @Param("example") PhoneCodeWebExample example);

    int updateByExample(@Param("record") PhoneCodeWeb record, @Param("example") PhoneCodeWebExample example);

    int updateByPrimaryKeySelective(PhoneCodeWeb record);

    int updateByPrimaryKey(PhoneCodeWeb record);
}