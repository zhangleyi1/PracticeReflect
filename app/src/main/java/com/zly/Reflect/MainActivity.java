package com.zly.Reflect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private TelephonyManager mTm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "zly --> onCreate");
        intData();
    }

    private void intData() {
        try {
            mTm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            //获取public变量值
            Field sField = mTm.getClass().getField("ACTION_PRECISE_CALL_STATE_CHANGED");
            Log.d(TAG, "zly --> name: " + sField.getName() + " values:" + sField.get(MainActivity.this));

            //private static final String TAG = "TelephonyManager";
            Field sField1 = mTm.getClass().getDeclaredField("multiSimConfig");
            //printAllFileds();
            Log.d(TAG, "zly --> name: " + sField1.getName());

            Class clazz = TelephonyManager.class;
            Method method = clazz.getMethod("getSimState", int.class);
            Log.d(TAG, "zly --> simState:" + (int) method.invoke(mTm, 0));


            Class clazz1 = TelephonyManager.class;
            Method method1 = clazz1.getDeclaredMethod("getDefaultPhone");
            Log.d(TAG, "zly --> getDefaultPhone:" + (int) method1.invoke(mTm));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            Log.d(TAG, "zly --> NoSuchFieldException:" + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.d(TAG, "zly --> IllegalAccessException:" + e.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void printAllFileds() {
        Field[] field = mTm.getClass().getDeclaredFields();
        Field f;
        for (int i = 0; i < field.length; i++) {
            f = field[i];
            Log.d(TAG, "zly --> Field Name = " + f.getName());
        }
    }

    private void printAllMethods() {
        Method[] method = mTm.getClass().getDeclaredMethods();
        for (Method m : method) {
            System.out.println("Method Name = " + m.getName());
        }
    }
}
