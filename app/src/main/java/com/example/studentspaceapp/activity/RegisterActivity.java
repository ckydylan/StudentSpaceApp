package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class RegisterActivity extends Activity {
    EditText et_userName;
    EditText et_password;
    EditText et_password2;
    EditText et_phone;
    EditText et_sure_phone;
    Button btn_register;
    Button btn_send_phone;
    Boolean flag = true;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        et_phone = findViewById(R.id.et_phone);
        btn_register = findViewById(R.id.btn_register);
        btn_send_phone = findViewById(R.id.btn_send_phone);
        et_sure_phone = findViewById(R.id.et_sure_phone);



        btn_send_phone.setOnClickListener(v->{
            BmobSMS.requestSMSCode(et_phone.getText().toString(), "hooliSDK", new QueryListener<Integer>() {
                @Override
                public void done(Integer smsId, BmobException e) {
                    if (e == null) {
                        Toast.makeText(RegisterActivity.this, "\"发送验证码成功，短信ID：\" + smsId", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "发送验证码失败：" + e.getErrorCode() + "-" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
//            Toast.makeText(this, "FUCK YOU，别想消耗我次数", Toast.LENGTH_LONG).show();
        });

        btn_register.setOnClickListener(v->{
            User user = new User();
            user.setUsername(et_userName.getText().toString());
            user.setPassword(et_password.getText().toString());
            if (et_userName.getText().toString().equals("")){
                Toast.makeText(this, "用户名不得为空", Toast.LENGTH_SHORT).show();
            }else if (et_password.getText().toString().equals("")) {
                Toast.makeText(this, "密码不得为空", Toast.LENGTH_SHORT).show();
            }else if (et_password.length()<6){
                Toast.makeText(RegisterActivity.this, "密码必须大于6位", Toast.LENGTH_SHORT).show();
            }else if (!et_password.getText().toString().equals(et_password2.getText().toString())){
                Toast.makeText(this, "两次密码不同", Toast.LENGTH_SHORT).show();
            }else if (et_phone.getText().toString().equals("")){
                Toast.makeText(this, "电话号码不得为空", Toast.LENGTH_SHORT).show();
            }else if (et_sure_phone.getText().toString().equals("")){
                Toast.makeText(this, "验证码不为空", Toast.LENGTH_SHORT).show();
            }else {
                signUp();
            }
        });



    }
    private void signUp() {
        final User user = new User();
        user.setUsername(et_userName.getText().toString());
        user.setPassword(et_password.getText().toString());
        user.setCountry("china");
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    verifyCode();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败,用户名以存在，请重新输入用户名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verifyCode(){
        BmobSMS.verifySmsCode(et_phone.getText().toString(), et_sure_phone.getText().toString(), new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if (e == null) {
                    Log.e("bmob", "验证码验证成功，您可以在此时进行绑定操作：");
                    Toast.makeText(RegisterActivity.this, "验证码验证成功，您可以在此时进行绑定操作！", Toast.LENGTH_SHORT).show();
                    User user = BmobUser.getCurrentUser(User.class);
                    user.setMobilePhoneNumber(et_phone.getText().toString());
                    Log.e("bmob", et_phone.getText().toString());
                    user.setMobilePhoneNumberVerified(true);
                    user.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "绑定手机号码失败：" + e.getErrorCode() + "-" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Log.e("bmob",et_phone.getText().toString());
                    Log.e("bmob","验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage());
                    Toast.makeText(RegisterActivity.this, "验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    flag = false;
                }
            }
        });

    }
}
