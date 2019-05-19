package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.utils.EditTextClearTools;

public class LoginActivity extends Activity {
    EditText et_userName;
    EditText et_password;
    Button btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        et_userName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        et_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});

        btn_login.setOnClickListener(v->{
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        });

        btn_register.setOnClickListener(v->{
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);

        });

        /*new Thread(){
            public void run(){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Log.d("调试","111");
                    String url = "jdbc:mysql://localhost:3306/TEST";
                    Log.d("调试","222");
                    Connection conn = DriverManager.getConnection(url, "root", "12345678");
                    Log.d("调试","333");
                    if(conn!=null){
                        Log.d("调试","连接成功");
                        Statement stmt = conn.createStatement();
                        String sql = "select * from user";
                        ResultSet rs = stmt.executeQuery(sql);
                    }else{
                        Log.d("调试","连接失败");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/




    }

    private void init() {
        EditText userName = findViewById(R.id.et_userName);
        EditText password = findViewById(R.id.et_password);
        ImageView unameClear = findViewById(R.id.iv_unameClear);
        ImageView pwdClear = findViewById(R.id.iv_pwdClear);

        EditTextClearTools.addClearListener(userName, unameClear);
        EditTextClearTools.addClearListener(password, pwdClear);
    }
}
