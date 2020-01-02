package com.ccg.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;

import com.ccg.aidlserver.SsoAuth;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SsoAuth ssoAuth;
    Button sso_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sso_btn = (Button)findViewById(R.id.sso_btn);
        sso_btn.setOnClickListener((v) -> {
            if(ssoAuth == null) {
                // 建立远程服务
                bindSsoAuthService();
            } else {
                doSsoAuth();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void bindSsoAuthService() {
        Intent intent = new Intent();
        intent.setAction("book.aidl_server.service.SinaSsoAuthService");
        intent.setPackage("com.ccg.aidlserver");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // 建立连接后binder转化为ssoauth
            ssoAuth = SsoAuth.Stub.asInterface(iBinder);
            doSsoAuth();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            ssoAuth = null;
        }
    };

    private void doSsoAuth() {
        try {
            ssoAuth.ssoAuth("ccg", "110707");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
