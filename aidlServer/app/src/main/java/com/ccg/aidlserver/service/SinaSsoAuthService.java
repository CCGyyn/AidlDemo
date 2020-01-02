package com.ccg.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.ccg.aidlserver.SsoAuth;

public class SinaSsoAuthService extends Service {
    public SinaSsoAuthService() {
    }
    SinaSsoImpl mBinder = new SinaSsoImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ssoAuth", "###sso service create");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    class SinaSsoImpl extends SsoAuth.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public void ssoAuth(String userName, String pwd) throws RemoteException {
            Log.d("ssoAuth", "sina customer, login userName=" + userName
                    + ", pwd=" + pwd);

        }
    }
}
