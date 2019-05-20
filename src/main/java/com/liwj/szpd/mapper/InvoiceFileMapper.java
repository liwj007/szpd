package com.liwj.szpd.mapper;

import com.liwj.szpd.model.InvoiceFile;
import com.liwj.szpd.model.InvoiceFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceFileMapper {
    long countByExample(InvoiceFileExample example);

    int deleteByExample(InvoiceFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvoiceFile record);

    int insertSelective(InvoiceFile record);

    List<InvoiceFile> selectByExample(InvoiceFileExample example);

    InvoiceFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvoiceFile record, @Param("example") InvoiceFileExample example);

    int updateByExample(@Param("record") InvoiceFile record, @Param("example") InvoiceFileExample example);

    int updateByPrimaryKeySelective(InvoiceFile record);

    int updateByPrimaryKey(InvoiceFile record);
}