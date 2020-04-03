package com.bestom.servicetest.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.bestom.servicetest.aidl.IMyAidlInterface;

public class MyService extends Service {
    private static final String TAG = "MyService";

    IMyService mIMyService=new IMyService();

    @Override
    public void onCreate() {
        //service 创建 只会执行一次
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        for (int i=0;i<10;i++) Log.d(TAG, "onStartCommand: "+i);;

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        MyBinder myBinder=new MyBinder();
        myBinder.setNum(20);
//        return myBinder;
        mIMyService.setNUM(10);
        return new IMyService();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        
        
        super.onDestroy();
    }

    public class IMyService extends IMyAidlInterface.Stub{
        int i=0;

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getNUM() throws RemoteException {
            return i;
        }

        public void setNUM(int i){
            this.i=i;
        }
    }

}
