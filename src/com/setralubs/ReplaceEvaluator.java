package com.setralubs;

import com.aspose.words.IReplacingCallback;
import com.aspose.words.ReplaceAction;
import com.aspose.words.ReplacingArgs;

import java.util.Map;

public class ReplaceEvaluator implements IReplacingCallback {
    final Map<String,String> mapReplace;
    public ReplaceEvaluator(Map<String,String> mapReplace){
        this.mapReplace=mapReplace;
    }
    @Override
    public int replacing(ReplacingArgs replacingArgs) throws Exception {
        String key=replacingArgs.getMatch().group(0).toUpperCase();
        if ( mapReplace.containsKey(key) ){
            replacingArgs.setReplacement(mapReplace.get(key));
            return ReplaceAction.REPLACE;
        }
        return ReplaceAction.SKIP;
    }
}
