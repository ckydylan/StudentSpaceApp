package com.example.studentspaceapp.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.fargment.HomeFragment;
import com.example.studentspaceapp.fargment.MyFragment;
import com.example.studentspaceapp.fargment.ToolsFragment;
import com.example.studentspaceapp.utils.BottomBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(HomeFragment.class,
                        "首页",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(ToolsFragment.class,
                        "工具",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(MyFragment.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();
    }


}
