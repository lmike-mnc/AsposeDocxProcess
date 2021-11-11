package com.setralubs;

import com.aspose.words.*;

import java.util.Map;

import static com.setralubs.FindAndReplace.FLD_END;

public class ReplaceEvaluator implements IReplacingCallback {
    final Map<String,String> mapReplace;
    public ReplaceEvaluator(Map<String,String> mapReplace){
        this.mapReplace=mapReplace;
    }
    @Override
    public int replacing(ReplacingArgs replacingArgs) throws Exception {
        String key=replacingArgs.getMatch().group(0).toUpperCase();
        //ignore all after |
        if (key.contains("|")){
            String[] tmp=key.split("\\|");
            key=tmp[0]+FLD_END;;
        }
        if ( mapReplace.containsKey(key) ){
            //builder.insertHtml("<b><font color='red'>James Bond, </font></b><br>Bullet");
            if (mapReplace.get(key).contains("\n")) {
                ////!some bug - it can shift position of the text (to left across some symbols if space present) after replacement
                //will preserve style across line breaks
                ReplacingArgs e = replacingArgs;
                //e.getMatchNode().getNextSibling();
                DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                builder.moveTo(e.getMatchNode());
                builder.write(mapReplace.get(key));
                e.setReplacement("");
            }else {
                replacingArgs.setReplacement(mapReplace.get(key));
            }
            return ReplaceAction.REPLACE;
        }
        return ReplaceAction.SKIP;
    }
}
