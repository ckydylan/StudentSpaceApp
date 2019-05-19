package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassManageActivity extends Activity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manage);

        ListView lv_show_text = findViewById(R.id.lv_show_text);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this
                , getData()
                , R.layout.list_show_class_manager
                , new String[]{"image", "title", "info"}
                , new int[]{R.id.my_image, R.id.tv_title, R.id.tv_info});
        lv_show_text.setAdapter(simpleAdapter);
        lv_show_text.setOnItemClickListener(this);
    }

    private ArrayList<Map<String, Object>> getData() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;
        map = new HashMap<>();
        map.put("image", R.drawable.class_java);
        map.put("title", "Java");
        map.put("info", "我是Java");
        list.add(map);

        map = new HashMap<>();
        map.put("image", R.drawable.class_android);
        map.put("title", "Android");
        map.put("info", "我是Android");
        list.add(map);

        map = new HashMap<>();
        map.put("image", R.drawable.class_python);
        map.put("title", "Python");
        map.put("info", "我是Python");
        list.add(map);

        map = new HashMap<>();
        map.put("image", R.drawable.class_mysql);
        map.put("title", "Mysql");
        map.put("info", "我是Mysql");
        list.add(map);

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ClassManageActivity.this, getData().get(position) + "onClick", Toast.LENGTH_SHORT).show();
    }
}
