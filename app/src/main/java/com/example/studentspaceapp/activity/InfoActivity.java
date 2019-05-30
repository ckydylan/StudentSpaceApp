package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoActivity extends Activity {
    ListView lv_show_info;
    Map<String,String> map;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        lv_show_info = findViewById(R.id.lv_show_info);
        SimpleAdapter adapter = new SimpleAdapter(InfoActivity.this, getData()
                , R.layout.list_show_info
                , new String[]{"name", "age","email","address"}
                , new int[]{R.id.tv_name, R.id.tv_age,R.id.tv_email,R.id.tv_address});
        lv_show_info.setAdapter(adapter);
    }

    private ArrayList<Map<String, String>> getData() {
        ArrayList<Map<String, String>> list = new ArrayList<>();

        String[] name = {"陈科宇","陈科宇2","陈科宇3","陈科宇4","陈科宇5","陈科宇6","陈科宇7"};
        String[] age = {"20","21","22","23","24","25","26"};
        String[] email = {"123@qq.com","123@qq.com","123@qq.com","123@qq.com","123@qq.com","123@qq.com","123@qq.com"};
        String[] address = {"江苏省常州市","江苏省常州市","江苏省常州市","江苏省常州市","江苏省常州市","江苏省常州市","江苏省常州市"};
        for (int i = 0; i<name.length;i++){
            map = new HashMap<>();
            map.put("name",name[i]);
            map.put("age",age[i]);
            map.put("email",email[i]);
            map.put("address",address[i]);
            list.add(map);
        }
        return list;
    }
}
