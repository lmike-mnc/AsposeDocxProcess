package com.setralubs;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Locale;

public class InWords {
    public static void main(String[]args){
        RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
        System.out.println(nf.format(1234567));
        System.out.println(nf.format(2350001));
    }
}
