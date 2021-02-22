package com.zhanglubin.multipledatasourcesdynamic.common.annotation;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

//    String name() default TargetDataSource.Caijinginfo;

//    public static String Caijinginfo = "dataSource1";

//    public static String Jrjgmen = "dataSource2";

//    public static String Jrjg = "dataSource3";

//    public static String Dujiagaojian = "dataSource4";

    String name() default TargetDataSource.datasourcemyself;

    public static String datasourcemyself = "datasourcemyself";

    public static String datasourcework = "datasourcework";


}

