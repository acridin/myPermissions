package com.example.java.mypermissions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    private Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        btn_call = findViewById (R.id.btn_call);

        btn_call.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                MainActivityPermissionsDispatcher.callWithCheck (MainActivity.this);
            }
        });
    }


    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void call() {
        Intent intent = new Intent (Intent.ACTION_CALL);
        Uri data = Uri.parse ("tel:" + "10080");
        intent.setData (data);
        startActivity (intent);
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showWhy(final PermissionRequest request) {
        new AlertDialog.Builder (this).setMessage ("提示用户为何开启权限")
                .setPositiveButton ("知道了", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed ();
                    }
                }).show ();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void showDenied() {
        Toast.makeText (this, "用户选择拒绝时的提示", Toast.LENGTH_SHORT).show ();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void showNotAsk() {
        new AlertDialog.Builder (this).setMessage ("该功能需要访问电话的权限，不开启将无法正常工作！")
                .setPositiveButton ("确定", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show ();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissons, int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissons, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult (this, requestCode, grantResults);
    }

}
