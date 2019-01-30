package com.example.java.mypermissions.permissons;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.java.mypermissions.tabview.CardViewActivity;
import com.example.java.mypermissions.R;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    private Button btn_call;
    private Button btn_snackbar;
    private Button btn_Login;
    private TextInputEditText tiet_userName;
    private TextInputEditText tiet_passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        btn_call = findViewById (R.id.btn_call);
        btn_snackbar = findViewById (R.id.btn_snackbar);
        btn_Login = findViewById (R.id.btn_login);
        tiet_userName = findViewById (R.id.tiet_username);
        tiet_passWord = findViewById (R.id.tiet_password);
        Button btnNext = findViewById (R.id.btn_next);


        btn_call.setOnClickListener (v -> MainActivityPermissionsDispatcher.callWithCheck (MainActivity.this));

        btn_snackbar.setOnClickListener (v -> showSnackbar ());

        btn_Login.setOnClickListener (v -> login ());

        btnNext.setOnClickListener (v -> showCardView ());
    }

    private void showCardView() {
        startActivity (new Intent (this, CardViewActivity.class));
    }

    /**
     * 弹出屏幕下方提示
     */
    private void showSnackbar() {
        Snackbar.make (getWindow ().getDecorView (), "标题", Snackbar.LENGTH_LONG)
                .setAction ("点击事件", v -> {
                    Toast.makeText (this, "SnackbarSnackbarSnackbar0:" + 0, Toast.LENGTH_SHORT).show ();
                }).setDuration (Snackbar.LENGTH_LONG).show ();
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
                .setPositiveButton ("知道了", (dialog, which) -> request.proceed ()).show ();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void showDenied() {
        Toast.makeText (this, "用户选择拒绝时的提示", Toast.LENGTH_SHORT).show ();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void showNotAsk() {
        new AlertDialog.Builder (this).setMessage ("该功能需要访问电话的权限，不开启将无法正常工作！")
                .setPositiveButton ("确定", (dialog, which) -> {

                }).show ();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissons, int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissons, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult (this, requestCode, grantResults);
    }


    private void login() {
        String username = tiet_userName.getText ().toString ();
        String password = tiet_passWord.getText ().toString ();
        if (!validatePassWord (username)) {
            tiet_userName.setError ("请输入正确的用户名");
        } else if (!validatePassWord (password)) {
            tiet_passWord.setError ("请输入正确的密码");
        } else {
            Toast.makeText (this, "登录成功" + 0, Toast.LENGTH_SHORT).show ();
        }

    }

    private boolean validatePassWord(String passWord) {
        return passWord.length () > 6;
    }


}
