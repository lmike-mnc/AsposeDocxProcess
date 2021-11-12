package com.setralubs;

import com.aspose.words.*;

import java.util.Map;
import java.util.regex.Pattern;

import static com.setralubs.FindAndReplace.FLD_END;

public class ReplaceEvaluator implements IReplacingCallback {
    private static final String TO_WORDS_SIGN = "FULL";
    final Map<String,String> mapReplace;
    public ReplaceEvaluator(Map<String,String> mapReplace){
        this.mapReplace=mapReplace;
    }
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
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
            String value=mapReplace.get(key);
            //multiline processing
            if (value.contains("\n")) {
                ////!some bug - it can shift position of the text (to left across some symbols if space present) after replacement
                //will preserve style across line breaks
                ReplacingArgs e = replacingArgs;
                //e.getMatchNode().getNextSibling();
                DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                builder.moveTo(e.getMatchNode());
                builder.write(value);
                e.setReplacement("");
            }else {
                if (key.toUpperCase().endsWith(TO_WORDS_SIGN+FLD_END) && isNumeric(value))
                    value=InWords.convert(Double.valueOf(value).longValue());
                replacingArgs.setReplacement(value);
            }
            return ReplaceAction.REPLACE;
        }
        return ReplaceAction.SKIP;
    }
}
