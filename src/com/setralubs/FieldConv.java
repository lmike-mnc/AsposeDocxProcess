package com.setralubs;

import java.util.ArrayList;
import java.util.Map.Entry;

public class FieldConv {
    Entry<String, Object> entry;
    public void setEntry(Entry<String, Object> e){
        entry=e;
    }
    public String getKey(){
        return entry.getKey();
    }
    public String[] getValue(){
        //System.out.println("value class: "+entry.getValue().getClass().getCanonicalName());
        return entry.getValue() instanceof ArrayList ?
                ((ArrayList<String>)entry.getValue()).toArray(new String[0]):
                (String[]) entry.getValue();
    }
}
