package com.setralubs;

import com.aspose.words.IReplacingCallback;
import com.aspose.words.ReplaceAction;
import com.aspose.words.ReplacingArgs;

import java.util.Map;

import static com.setralubs.FindAndReplace.FLD_END;

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

            key=tmp[0]+FLD_END;
            //there are format <fld_name|fld_value|fld_description[fld_example]>
            //or <fld_name|fld_value>
            if (tmp.length>1) {
                //apply default value
                values[0] = tmp[1];
                //apply comments
                values[1] = tmp.length > 2 ?
                        //end with extra FLD_END sign
                        (tmp[2].endsWith(FLD_END) ? tmp[2].substring(0, tmp[2].length() - 1)
                                : tmp[2])
                        : tmp[1];
            }
        }
        //fill map with new
        if (!map.containsKey(key))map.put(key, values);
        //do nothing (only find)
        return ReplaceAction.SKIP;
    }
}
