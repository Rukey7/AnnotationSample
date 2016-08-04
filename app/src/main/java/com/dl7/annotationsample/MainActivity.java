package com.dl7.annotationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.annotation.runtime.ClassInfo;
import com.example.annotation.runtime.FieldInfo;
import com.example.annotation.runtime.MethodInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTvDesc;
    Button mBtnRuntime;
    Button mBtnClass;
    Button mBtnSource;

    boolean mIsOpen = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _findViews();
    }

    private void _findViews() {
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        mBtnRuntime = (Button) findViewById(R.id.btn_runtime);
        mBtnClass = (Button) findViewById(R.id.btn_class);
        mBtnSource = (Button) findViewById(R.id.btn_source);
        mBtnRuntime.setOnClickListener(this);
        mBtnClass.setOnClickListener(this);
        mBtnSource.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_runtime:
                _testRuntimeAnnotation();
                break;
            case R.id.btn_source:
                _testSourceAnnotation();
                break;
        }
    }


    /**
     * 测试运行时注解
     */
    private void _testRuntimeAnnotation() {
        StringBuffer sb = new StringBuffer();
        Class<?> cls = TestRuntimeAnnotation.class;
        Constructor<?>[] constructors = cls.getConstructors();
        // 获取指定类型的注解
        sb.append("Class注解：").append("\n");
        ClassInfo classInfo = cls.getAnnotation(ClassInfo.class);
        if (classInfo != null) {
            sb.append(Modifier.toString(cls.getModifiers())).append(" ")
                    .append(cls.getSimpleName()).append("\n");
            sb.append("注解值: ").append(classInfo.value()).append("\n\n");
        }

        sb.append("Field注解：").append("\n");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            if (fieldInfo != null) {
                sb.append(Modifier.toString(field.getModifiers())).append(" ")
                        .append(field.getType().getSimpleName()).append(" ")
                        .append(field.getName()).append("\n");
                sb.append("注解值: ").append(Arrays.toString(fieldInfo.value())).append("\n\n");
            }
        }

        sb.append("Method注解：").append("\n");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
            if (methodInfo != null) {
                sb.append(Modifier.toString(method.getModifiers())).append(" ")
                        .append(method.getReturnType().getSimpleName()).append(" ")
                        .append(method.getName()).append("\n");
                sb.append("注解值: ").append("\n");
                sb.append("name: ").append(methodInfo.name()).append("\n");
                sb.append("data: ").append(methodInfo.data()).append("\n");
                sb.append("age: ").append(methodInfo.age()).append("\n");
            }
        }

        mTvDesc.setText(sb.toString());
    }

    /**
     * 测试源码注解
     */
    private void _testSourceAnnotation() {
        if (mIsOpen) {
//            TestSourceAnnotation.setStatus(1); 直接设置数值编译器会直接提示错误
            TestSourceAnnotation.setStatus(TestSourceAnnotation.STATUS_CLOSE);
            mIsOpen = false;
        } else {
            TestSourceAnnotation.setStatus(TestSourceAnnotation.STATUS_OPEN);
            mIsOpen = true;
        }

        mTvDesc.setText(TestSourceAnnotation.getStatusDesc());
    }
}
