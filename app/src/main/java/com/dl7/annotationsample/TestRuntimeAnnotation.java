package com.dl7.annotationsample;

import com.example.annotation.runtime.ClassInfo;
import com.example.annotation.runtime.FieldInfo;
import com.example.annotation.runtime.MethodInfo;

/**
 * Created by long on 2016/6/30.
 * 测试运行时注解
 */
@ClassInfo("Test Class")
public class TestRuntimeAnnotation {

    @FieldInfo(value = {1, 2})
    public String fieldInfo = "FiledInfo";

    @FieldInfo(value = {10086})
    public int i = 100;

    @MethodInfo(name = "BlueBird", data = "Big")
    public static String getMethodInfo() {
        return TestRuntimeAnnotation.class.getSimpleName();
    }
}
