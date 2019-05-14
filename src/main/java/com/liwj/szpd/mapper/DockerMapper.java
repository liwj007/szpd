package com.liwj.szpd.mapper;

import com.liwj.szpd.model.Docker;
import com.liwj.szpd.model.DockerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DockerMapper {
    long countByExample(DockerExample example);

    int deleteByExample(DockerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Docker record);

    int insertSelective(Docker record);

    List<Docker> selectByExample(DockerExample example);

    Docker selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Docker record, @Param("example") DockerExample example);

    int updateByExample(@Param("record") Docker record, @Param("example") DockerExample example);

    int updateByPrimaryKeySelective(Docker record);

    int updateByPrimaryKey(Docker record);
}