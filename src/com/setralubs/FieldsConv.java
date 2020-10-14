package com.setralubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FieldsConv {
    List<Map.Entry<String, Object>> list=new ArrayList<Map.Entry<String, Object>>();
    public void setMap(Map<String, Object> map){
        this.list.addAll(map.entrySet());
    }
    public int size(){
        return list.size();
    }

    public String[] getValue(int i){
        Map.Entry<String, Object> entry=list.get(i);

        return entry.getValue() instanceof ArrayList<?> ?
                ((ArrayList<String>)entry.getValue()).toArray(new String[0]):
                (String[]) entry.getValue();
    }

    public String getKey(int i){
        return list.get(i).getKey();
    }
}
