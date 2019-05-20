package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.bean.User;
import com.example.studentspaceapp.utils.EditTextClearTools;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {
    EditText et_userName;
    EditText et_password;
    Button btn_login;
    Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "ced8af6e245a60297c9c4df97b675bb1");

        User user = new User();
        user.setUserName("123");
        user.setPassword("123");
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

        init();

        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        et_userName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        et_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});


        btn_login.setOnClickListener(v -> {
            queryData();
        });

        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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


    public static User getUser(String key, String jsonString) {

        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject userObject = jsonObject.getJSONObject("user");
            user.setUserName(userObject.getString("username"));
            user.setPassword(userObject.getString("password"));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return user;
    }

    public void queryData() {
        BmobQuery query = new BmobQuery("Student");
        query.addWhereEqualTo("username", et_userName.getText());
        query.setLimit(2);
        query.order("createdAt");
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                Log.e("ary",ary.toString());
                for (int i = 0; i < ary.length(); i++) {
                    try {
                        JSONObject job = ary.getJSONObject(i);
                        LoginActivity.this.getPassword(job);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }

    public void getPassword(JSONObject json) throws JSONException {
        String makeSurePassword = json.getString("password");
        if (et_password.getText().toString().equals(makeSurePassword) ) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (et_userName.getText().toString().equals("")) {
            Toast.makeText(this, "用户名不得为空", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().equals("")) {
            Toast.makeText(this, "密码不得为空", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "密码错误,请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

}
