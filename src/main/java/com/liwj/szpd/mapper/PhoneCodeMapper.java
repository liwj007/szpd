package com.liwj.szpd.mapper;

import com.liwj.szpd.model.PhoneCode;
import com.liwj.szpd.model.PhoneCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneCodeMapper {
    long countByExample(PhoneCodeExample example);

    int deleteByExample(PhoneCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhoneCode record);

    int insertSelective(PhoneCode record);

    List<PhoneCode> selectByExample(PhoneCodeExample example);

    PhoneCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhoneCode record, @Param("example") PhoneCodeExample example);

    int updateByExample(@Param("record") PhoneCode record, @Param("example") PhoneCodeExample example);

    int updateByPrimaryKeySelective(PhoneCode record);

    int updateByPrimaryKey(PhoneCode record);

    PhoneCode findByPhone(String phone);

}