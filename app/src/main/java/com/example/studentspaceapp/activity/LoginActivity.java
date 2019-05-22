package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.bean.User;
import com.example.studentspaceapp.utils.EditTextClearTools;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {
    EditText et_userName;
    EditText et_password;
    Button btn_login;
    Button btn_register;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "ced8af6e245a60297c9c4df97b675bb1");
//        query();
        init();
        sureIsLogin();


        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        et_userName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        et_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});


        btn_login.setOnClickListener(v -> {
            if (et_userName.getText().toString().equals("")) {
                Toast.makeText(this, "用户名不得为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (et_password.getText().toString().equals("")) {
                Toast.makeText(this, "密码不得为空", Toast.LENGTH_SHORT).show();
                return;
            }
            loginByPhone();
            login(btn_login);
            fetchUserInfo(btn_login);
        });

        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });


}

    private void init() {
        EditText userName = findViewById(R.id.et_userName);
        EditText password = findViewById(R.id.et_password);
        ImageView unameClear = findViewById(R.id.iv_unameClear);
        ImageView pwdClear = findViewById(R.id.iv_pwdClear);

        EditTextClearTools.addClearListener(userName, unameClear);
        EditTextClearTools.addClearListener(password, pwdClear);
    }



    private void login(View view) {
        user = new User();
        user.setUsername(et_userName.getText().toString());
        user.setPassword(et_password.getText().toString());
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    Snackbar.make(view, "登录成功：" + user.getUsername(), Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(view, "登录失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    /**
     * 同步控制台数据到缓存中
     * @param view
     */
    private void fetchUserInfo(final View view) {
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                User myUser = BmobUser.getCurrentUser(User.class);
                if (e == null) {
                    Snackbar.make(view, "更新用户本地缓存信息成功："+myUser.getUsername()+"-"+myUser.getAge(), Snackbar.LENGTH_LONG).show();
                    Log.e("bmob","更新用户本地缓存信息成功："+myUser.getUsername()+"-"+myUser.getAge());
                } else {
                    Log.e("error",e.getMessage());
                    Snackbar.make(view, "更新用户本地缓存信息失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    public void sureIsLogin(){
        if (BmobUser.isLogin()) {
            User myUser = BmobUser.getCurrentUser(User.class);
            Log.e("bmob","已经登录："+ myUser.getUsername());
//            Snackbar.make(view, "已经登录：" + myUser.getUsername(), Snackbar.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
//            Snackbar.make(view, "尚未登录", Snackbar.LENGTH_LONG).show();
            Log.e("bmob","尚未登录：");
        }
    }

    /**
     * 手机号码密码登录
     */
    private void loginByPhone(){
        BmobUser.loginByAccount(et_userName.getText().toString(), et_password.getText().toString(), new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user!=null){
                    if (e == null) {
                        Toast.makeText(LoginActivity.this, "短信验证成功", Toast.LENGTH_SHORT).show();
                    } else {
//                        mTvInfo.append("短信登录失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                    }
                }
            }
        });
    }


}
