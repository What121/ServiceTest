package com.bestom.servicetest.Service;

import android.os.Binder;

public class MyBinder extends Binder {

    int num;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
