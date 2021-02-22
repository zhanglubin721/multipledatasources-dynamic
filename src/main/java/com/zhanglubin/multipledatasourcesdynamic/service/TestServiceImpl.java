package com.zhanglubin.multipledatasourcesdynamic.service;

import com.zhanglubin.multipledatasourcesdynamic.common.annotation.TargetDataSource;
import com.zhanglubin.multipledatasourcesdynamic.mapper.TestDataSourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhanglubin
 * @date 2021/2/22
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDataSourcesMapper testDataSourcesMapper;

    @Override
    @TargetDataSource(name = "datasourcemyself")
    public String testMyDataSources(){
        return testDataSourcesMapper.getUsername();
    }

    @Override
    @TargetDataSource(name = "datasourcework")
    public String testWorkDataSources(){
        return testDataSourcesMapper.getClientName();
    }


}
