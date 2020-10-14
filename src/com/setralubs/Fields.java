package com.setralubs;

import java.util.HashMap;
import java.util.Map;

public class Fields {
    Map<String, FieldData> map=null;

/*
    public Fields(List<FieldData> list) {
        this.list = list;
    }
*/
    public Fields() {}

    public Map<String, FieldData> getMap() {
        return map;
    }

    public void setMap(Map<String, FieldData> map) {
        this.map = map;
    }
    public Fields add(String name, FieldData field){
        if (map==null)map=new HashMap<String, FieldData>();
        map.put(name, field);
        return this;
    }
    public String toString(){
        return String.valueOf(map);
    }
}
