package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;

import java.util.HashMap;

public class ChangeInfoActivity extends Activity {
    ImageView imgHead;
    TextView showIpText;
    EditText editTextIp;
    Spinner spinner_local;
    String[] data = new String[]{"江苏","安徽","浙江","上海"};
    final static int REQUESTION = 100;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        imgHead = findViewById(R.id.head_img);
        showIpText = findViewById(R.id.tv_showIp);
        editTextIp = findViewById(R.id.ed_ip);
        spinner_local = findViewById(R.id.spinner_local);
        ArrayAdapter arrayAdapter = new ArrayAdapter(ChangeInfoActivity.this,android.R.layout.simple_spinner_item,data);
        spinner_local.setAdapter(arrayAdapter);

        imgHead.setOnClickListener(v->{
            Intent intent = new Intent(ChangeInfoActivity.this,ChooseHeadImgActivity.class);
            startActivityForResult(intent,100);
            startActivity(intent);
        });

        //限制edittext输入9个字符
        editTextIp.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});

        editTextIp.setOnKeyListener((v,keyCode,event)->{
            switch (event.getAction()){
                case KeyEvent.ACTION_UP:
                    String ip = editTextIp.getText().toString();
                    String newIp = "";
                    int size = ((ip.length())%3 == 0) ? ((ip.length())/3):((ip.length())/3 + 1);
                    for(int i=0;i<size ;i++){
                        int endIndex = (i+1)*3;
                        if((i+1)==size){
                            endIndex = ip.length();
                        }
                        if(i==0){
                            newIp += ip.substring(i,endIndex);
                        }else{
                            newIp += ","+ip.substring(i*3, endIndex);
                        }
                    }
                    showIpText.setText(newIp);
                    break;
                case KeyEvent.ACTION_DOWN:
                    break;
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==REQUESTION && resultCode == REQUESTION){
            Bundle bundle = data.getExtras();
            int  imgResult = bundle.getInt("img");
            imgHead.setImageResource(imgResult);
        }
    }
}
