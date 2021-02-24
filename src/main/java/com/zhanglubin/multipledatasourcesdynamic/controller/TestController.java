package com.zhanglubin.multipledatasourcesdynamic.controller;

import com.zhanglubin.multipledatasourcesdynamic.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhanglubin
 * @date 2021/2/22
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("my")
    public String TestMyDataSources() {
        String s = testService.testMyDataSources();
        System.out.println(s);
        return s;
    }

    @RequestMapping("work")
    public String TestWorkDataSources() {
        String s = testService.testWorkDataSources();
        System.out.println(s);
        return s;
    }

    @RequestMapping("my2")
    public void TestMyDataSources2() {
        testService.testMyDataSources2();
    }

    @RequestMapping("work2")
    public void TestWorkDataSources2() {
        testService.testWorkDataSources2();
    }
}
