package com.cashmeoutside.mybudget;

import android.app.Application;

import com.cashmeoutside.mybudget.entities.MyObjectBox;

import io.objectbox.BoxStore;


public class MyBudgetApplication extends Application {
    protected static MyBudgetApplication mInstance;
    private BoxStore mBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public static BoxStore getBoxStore() {
        return mInstance.mBoxStore;
    }
}
