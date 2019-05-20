package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegisterActivity extends Activity {
    EditText et_userName;
    EditText et_password;
    EditText et_password2;
    Button btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        btn_register = findViewById(R.id.btn_register);



        btn_register.setOnClickListener(v->{
            User user = new User();
            user.setUserName(et_userName.getText().toString());
            user.setPassword(et_password.getText().toString());
            if (et_userName.getText().toString().equals("")){
                Toast.makeText(this, "用户名不得为空", Toast.LENGTH_SHORT).show();
            }else if (et_password.getText().toString().equals("")){
                Toast.makeText(this, "密码不得为空", Toast.LENGTH_SHORT).show();
            }else if (!et_password.getText().toString().equals(et_password2.getText().toString())){
                Toast.makeText(this, "两次密码不同", Toast.LENGTH_SHORT).show();
            }else {
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Log.e("sss", "添加数据成功，返回objectId为：" + s);
                        } else {
                            Log.e("sss", "创建数据失败：" + e.getMessage());
                        }
                    }
                });
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        });
    }
}
