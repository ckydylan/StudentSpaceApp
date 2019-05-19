package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.studentspaceapp.R;

public class ChangeInfoActivity extends Activity {
    ImageView imgHead;
    TextView showIpText;
    EditText editTextIp;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        imgHead = findViewById(R.id.head_img);
        showIpText = findViewById(R.id.tv_showIp);
        editTextIp = findViewById(R.id.ed_ip);

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

}
