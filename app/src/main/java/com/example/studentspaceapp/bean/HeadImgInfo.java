package com.example.studentspaceapp.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class HeadImgInfo implements Serializable {
    private List<Map<String, Object>> dataList;

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }
}
