package com.zhanglubin.multipledatasourcesdynamic.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhanglubin
 * @date 2021/2/19
 */
@Mapper
public interface TestDataSourcesMapper {

    String getUsername();

    String getClientName();
}
