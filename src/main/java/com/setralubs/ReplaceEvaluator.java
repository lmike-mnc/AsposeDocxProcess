package com.setralubs;

import com.aspose.words.IReplacingCallback;
import com.aspose.words.ReplaceAction;
import com.aspose.words.ReplacingArgs;

import java.util.Map;

import static com.setralubs.FindAndReplace.FLD_END;

public class ReplaceEvaluator implements IReplacingCallback {
    final Map<String,String> mapReplace;
    public ReplaceEvaluator(Map<String,String> mapReplace){
        this.mapReplace=mapReplace;
    }
    @Override
    public int replacing(ReplacingArgs replacingArgs) {
        String key=replacingArgs.getMatch().group(0).toUpperCase();
        //ignore all after |
        if (key.contains("|")){
            String[] tmp=key.split("\\|");
            key=tmp[0]+FLD_END;;
        }
        if ( mapReplace.containsKey(key) ){
            replacingArgs.setReplacement(mapReplace.get(key));
            return ReplaceAction.REPLACE;
        }
        return ReplaceAction.SKIP;
    }
}
