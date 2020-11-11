package com.setralubs;

import com.aspose.words.Table;

public class WordTable {
    final String name;
    final com.aspose.words.Table table;

    public WordTable(String name, Table table){
        this.name=name;
        this.table=table;
    }
}
