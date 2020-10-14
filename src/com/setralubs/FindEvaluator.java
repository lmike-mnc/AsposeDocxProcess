package com.setralubs;

import com.aspose.words.IReplacingCallback;
import com.aspose.words.ReplaceAction;
import com.aspose.words.ReplacingArgs;

import java.util.Map;

public class FindEvaluator implements IReplacingCallback {
    final Map<String,String[]> map;
    final Map<String,String> mapSkip;
    public FindEvaluator(Map<String,String[]> mapFind){
        this.map=mapFind;
        this.mapSkip=null;
    }
    public FindEvaluator(Map<String,String[]> mapFind, Map<String,String> mapSkip){
        this.map=mapFind;
        this.mapSkip=mapSkip;
    }
    @Override
    public int replacing(ReplacingArgs replacingArgs) throws Exception {
        String key=replacingArgs.getMatch().group(0);
        String[] values=new String[]{"",""};
        if (key.contains("|")){
            String[] tmp=key.split("\\|");
            key=tmp[0];
            if (tmp.length>2) values[0]=tmp[1];
            values[1]=tmp.length>2?tmp[2]:tmp[1];
        }
        if (!map.containsKey(key))map.put(key, values);
        return ReplaceAction.SKIP;
    }
}
