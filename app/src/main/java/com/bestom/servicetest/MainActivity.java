package com.bestom.servicetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.bestom.servicetest.Service.MyBinder;
import com.bestom.servicetest.Service.MyService;
import com.bestom.servicetest.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Context mContext;
    Activity mActivity;
    Intent serviceIntent;

    MyHandler mMyHandler= new MyHandler();
    ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            Log.d(TAG, "ServiceConnected: componentName"+componentName.toString());
//            MyBinder myBinder= (MyBinder) iBinder;
            IMyAidlInterface iMyAidlInterface = MyService.IMyService.Stub.asInterface(iBinder);
            try {
                int num = iMyAidlInterface.getNUM();
                Log.d(TAG, "ServiceConnected: iMyAidlInterface"+num);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");

        }
    };

    public class MyHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //start local service
                    startService(serviceIntent);
                    break;
                case -1:
                    //stop local service
                    stopService(serviceIntent);
                    break;
                case 2:
                    //bind local service
                    bindService(serviceIntent,mServiceConnection , BIND_AUTO_CREATE);
                    break;
                case -2:
                    //unbind local service
                    unbindService(mServiceConnection);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        mActivity= this;
        serviceIntent = new Intent(mContext, MyService.class);

        localService();
        remoteService();
    }

    private void localService(){
//        Message message=new Message();
//        mMyHandler.sendEmptyMessage(1);
//        message.what=-1;
//        mMyHandler.sendMessageDelayed(message,10*1000);
        mMyHandler.sendEmptyMessage(2);
        Message un=new Message();
        un.what=-2;
        mMyHandler.sendMessageDelayed(un,20*1000);
    }


    private void remoteService(){

    }


}
