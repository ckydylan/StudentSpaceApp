package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.studentspaceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicActivity extends Activity {
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        ListView lv_show = findViewById(R.id.lv_show);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this
                , getData()
                , R.layout.list_show_music
                , new String[]{"image", "title", "author","rightImg"}
                , new int[]{R.id.my_images, R.id.tv_titles, R.id.tv_authors,R.id.img_right});
        lv_show.setAdapter(simpleAdapter);
        lv_show.setOnItemClickListener((parent, view, position, id)->{
            startActivity(new Intent(MusicActivity.this,ShowMusicActivity.class));
        });
    }

    private ArrayList<Map<String, Object>> getData() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        map = new HashMap<>();
        map.put("image", R.mipmap.music_img1);
        map.put("title","陪我长大");
        map.put("author","火箭少女101段奥娟");
        map.put("rightImg",R.mipmap.music_img_right);
        list.add(map);

        map = new HashMap<>();
        map.put("image",R.mipmap.music_img2);
        map.put("title","身骑白马");
        map.put("author","徐佳莹");
        map.put("rightImg",R.mipmap.music_img_right);
        list.add(map);

        map = new HashMap<>();
        map.put("image",R.mipmap.music_img3);
        map.put("title","倒数");
        map.put("author","G.E.M.邓紫棋");
        map.put("rightImg",R.mipmap.music_img_right);
        list.add(map);

        map = new HashMap<>();
        map.put("image",R.mipmap.music_img4);
        map.put("title","某某");
        map.put("author","孟凡明");
        map.put("rightImg",R.mipmap.music_img_right);
        list.add(map);

        map = new HashMap<>();
        map.put("image",R.mipmap.music_img5);
        map.put("title","爱殇");
        map.put("author","小时/龚骏");
        map.put("rightImg",R.mipmap.music_img_right);
        list.add(map);

        return list;
    }
}
