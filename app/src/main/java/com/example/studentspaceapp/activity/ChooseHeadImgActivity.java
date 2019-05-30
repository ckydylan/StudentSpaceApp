package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseHeadImgActivity extends Activity {
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    int icno[] = {R.drawable.thor, R.drawable.doc, R.drawable.logi, R.drawable.cap, R.drawable.thor, R.drawable.doc, R.drawable.logi, R.drawable.cap, R.drawable.thor};
    String name[] = {"雷神", "博士", "洛基", "美队", "雷神", "博士", "洛基", "美队", "雷神"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_head_img);

        gridView = findViewById(R.id.gridview);

        initData();

        String[] from = {"img", "text"};
        int[] to = {R.id.img, R.id.text};
        adapter = new SimpleAdapter(this, dataList, R.layout.gridview_item, from, to);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putInt("img", icno[position]);
            intent.putExtras(bundle);
            setResult(ChangeInfoActivity.REQUESTION, intent);
            Toast.makeText(this, "头像更换成功", Toast.LENGTH_SHORT).show();
            finish();

        });
    }

    void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
    }

}
