package com.setralubs;

import com.aspose.words.*;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FindAndReplace {
    static String FLD_START="<";
    static String FLD_END=">";
    static String fieldRegex = "("+FLD_START+".+?"+FLD_END+")";
    final com.aspose.words.License license;
    static final String DATA_DIR = "res/";

    public FindAndReplace() throws Exception {
        license = new com.aspose.words.License();
        license.setLicense("Aspose.Total.Java.lic");
    }

    /**
     * @param doc Aspose document
     * @param regex search expression (ex "\<.+\>" angle bracket for fields tag)
     * @param map fields name with values to replace
     * @throws Exception replace exception
     */
    void replace(Document doc, String regex, Map<String,String> map) throws Exception {
        FindReplaceOptions options = new FindReplaceOptions();
        options.setReplacingCallback(new ReplaceEvaluator(map));

        doc.getRange().replace(Pattern.compile(regex), "", options);
        //String outName= DATA_DIR + "Range.ReplaceWithEvaluator_Out.docx";
        //doc.save(outName);
        //Runtime.getRuntime().exec("gio open "+outName);
    }
/*
    void getFields(Document doc, String regex, Map<String,String[]> map) throws Exception {
        FindReplaceOptions options = new FindReplaceOptions();
        options.setReplacingCallback(new FindEvaluator(map));
        doc.getRange().replace(Pattern.compile(regex), "", options);

    }
*/
    /**
     *
     * @param doc Aspose document
     * @param regex search expression (ex "\<.+\>" angle bracket for fields tag)
     * @return map as name->[default value,comments]
     * @throws Exception replace exception
     */
    Map<String,String[]> getFields(Document doc, String regex) throws Exception {
        FindReplaceOptions options = new FindReplaceOptions();
        Map<String,String[]> map= new CaseInsensitiveMap<>();
        options.setReplacingCallback(new FindEvaluator(map));
        doc.getRange().replace(Pattern.compile(regex), "", options);
        return map;
    }
    public void tablesProcess(Document doc) throws Exception {
        // Table manipulations
        // Get the first table in the document.
        Table firstTable = (Table) doc.getChild(NodeType.TABLE, 0, true);
        @SuppressWarnings("unchecked")
        NodeCollection<Table> tables=doc.getChildNodes(NodeType.TABLE, true);
        for(Table tbl:tables){
            System.out.println(tbl.getTitle());
            //tbl.getRows().clear();
        }
// We will split the table at the third row (inclusive).
        Row row = firstTable.getRows().get(1);

// Create a new container for the split table.
        Table table = (Table) firstTable.deepClone(false);

// Insert the container after the original.
        firstTable.getParentNode().insertAfter(table, firstTable);

// Add a buffer paragraph to ensure the tables stay apart.
        firstTable.getParentNode().insertAfter(new Paragraph(doc), firstTable);

        Row currentRow;
        //System.out.println(row.getText());
        do {
            currentRow = firstTable.getLastRow();
            table.prependChild(currentRow);
        } while (currentRow != row);
        replaceRows(firstTable,table);
        //firstTable.remove();
    }

    /**
     * @param doc Aspose Document
     * @return tables title names array
     */
    public List<String> getTablesNames(Document doc){
        List<String> lst= new ArrayList<>();
        @SuppressWarnings("unchecked")
        NodeCollection<Table> tables=doc.getChildNodes(NodeType.TABLE, true);
        for(Table tbl:tables){
            if (!tbl.getTitle().isEmpty())lst.add(tbl.getTitle());
        }
        return lst;
    }

    /**
     * @param doc Aspose Document
     * @return map as table title, table object
     */
    public Map<String,Table> getTablesNamesMap(Document doc){
        Map<String,Table> map=new CaseInsensitiveMap<>();
        @SuppressWarnings("unchecked")
        NodeCollection<Table> tables=doc.getChildNodes(NodeType.TABLE, true);
        int i=0;
        for(Table tbl:tables){
            String name=tbl.getTitle();
            if (!name.isEmpty()){
                if(map.containsKey(name))name+="+"+i;
                map.put(name, tbl);
            }
            i++;
        }
        return map;
    }

    /**
     * @param doc Aspose Document
     * @return extended table objects array
     */
    public List<WordTable> getTables(Document doc){
        List<WordTable> ret= new ArrayList<>();
        @SuppressWarnings("unchecked")
        NodeCollection<Table> tables=doc.getChildNodes(NodeType.TABLE, true);
        for(Table tbl:tables){
            ret.add(new WordTable(tbl.getTitle(), tbl));
        }
        return ret;
    }

    public void replaceRows(Table firstTable, Table secondTable) {
        firstTable.getRows().clear();
        while (secondTable.hasChildNodes())
            firstTable.getRows().add(secondTable.getFirstRow());
        //replace cell text
        //firstTable.getLastRow().getLastCell().getRange().replace(Pattern.compile(".*"),"new Text");
    }
    public void replaceRowsText(Table firstTable, Table secondTable) throws Exception {
/*
        for (Cell cell : (Iterable<Cell>) firstTable.getChildNodes(NodeType.CELL, true))
            cell.getRange().delete();// removeAllChildren();
*/
        RowCollection rows=secondTable.getRows();
        int cnt=rows.getCount();
        Row lastRow=firstTable.getLastRow();
        Row appendRow=(Row)lastRow.deepClone(true);
        lastRow.getParentNode().insertAfter(appendRow,lastRow);
        int i=0;
        for(Row row:firstTable.getRows()){
            if (i>=cnt) break;
            CellCollection cells=rows.get(i).getCells();
            int c=cells.getCount();
            int j=0;
            for(Cell cell:row.getCells()){
                cell.getRange().replace(".*",j<c?cells.get(j).getText():"");
                j++;
            }
            i++;
        }
    }

    /**
     * @param insertAfterNode Aspose Document Node
     * @param srcDoc Aspose Document to be appended
     */
    //https://docs.aspose.com/display/wordsjava/How+to++Insert+a+Document+into+another+Document
    public void insertDocument(Node insertAfterNode, Document srcDoc) {
        // Make sure that the node is either a paragraph or table.
        if ((insertAfterNode.getNodeType() != NodeType.PARAGRAPH) & (insertAfterNode.getNodeType() != NodeType.TABLE))
            throw new IllegalArgumentException("The destination node should be either a paragraph or table.");

        // We will be inserting into the parent of the destination paragraph.
        @SuppressWarnings( "rawtypes")
        CompositeNode dstStory = insertAfterNode.getParentNode();

        // This object will be translating styles and lists during the import.
        NodeImporter importer = new NodeImporter(srcDoc, insertAfterNode.getDocument(), ImportFormatMode.KEEP_SOURCE_FORMATTING);

        // Loop through all sections in the source document.
        for (Section srcSection : srcDoc.getSections()) {
            // Loop through all block level nodes (paragraphs and tables) in the body of the section.
            for (Node srcNode : srcSection.getBody()) {
                // Let's skip the node if it is a last empty paragraph in a section.
                if (srcNode.getNodeType() == (NodeType.PARAGRAPH)) {
                    Paragraph para = (Paragraph) srcNode;
                    if (para.isEndOfSection() && !para.hasChildNodes())
                        continue;
                }

                // This creates a clone of the node, suitable for insertion into the destination document.
                Node newNode = importer.importNode(srcNode, true);

                // Insert new node after the reference node.
                dstStory.insertAfter(newNode, insertAfterNode);
                insertAfterNode = newNode;
            }
        }
    }

    /**
     * @param name table title to search
     * @param doc Aspose Document
     * @return table object according title
     */
    public Table getTableByName(String name, Document doc){
        Table ret=null;
        @SuppressWarnings("unchecked")
        NodeCollection<Table> tables=doc.getChildNodes(NodeType.TABLE, true);
        for(Table tbl:tables) {
            String title = tbl.getTitle();
            if (!title.isEmpty() && title.equalsIgnoreCase(name)) {
                ret=tbl;
                break;
            }
        }
        return ret;
    }

     Document extractTable(Table table) throws Exception {
        Document outDoc=new Document();//outTmp.getAbsolutePath());
        NodeImporter importer = new NodeImporter(table.getDocument(), outDoc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        Node newNode=importer.importNode(table,true);
        outDoc.getFirstSection().getBody().appendChild(newNode);
        return outDoc;
    }

    public Document replaceWtables(InputStream isTarget, Map<String,InputStream> mapSources, Map<String,String> mapFields) throws Exception {
        Document docTarget=new Document(isTarget);
        if(mapSources!=null && !mapSources.isEmpty()){
            final Map <String,Table> mapTables=getTablesNamesMap(docTarget);
            mapSources.entrySet().stream()
                    .filter(e->mapTables.containsKey(e.getKey()))
                    .forEach(e->{
                                Table tbl2replace=mapTables.get(e.getKey());
                                try {
                                    //first table in source doc
                                    //Table tblSource= (Table) (new Document(e.getValue())).getChildNodes(NodeType.TABLE, true).get(0);
                                    Document doc=new Document(e.getValue());
                                    System.out.println("replacing table: "+e.getKey());
                                    insertDocument(tbl2replace,doc);
                                    tbl2replace.remove();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                    );
        }
        if (mapFields!=null){
            replace(docTarget,fieldRegex, mapFields);
        }
        return docTarget;
    }

    public static void main(String[] args) throws Exception {
        Map<String,String> map= new HashMap<>();
        //<ADD4_POSITION> <ADD4_WHOM>
        map.put("<ADD4_POSITION>","Генерала");
        map.put("<ADD4_WHOM>","Кузнецова");
        //File tmpDocx=File.createTempFile("out",".docx");
        File tmp=File.createTempFile("out",".pdf");
        String outName=tmp.getAbsolutePath();//DATA_DIR + "out.pdf";
        FindAndReplace obj=new FindAndReplace();

        File outTmp=File.createTempFile("out",".docx");
        Document srcDoc=new Document(obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "add4_test.docx"));
        //get first table
        Document outDoc=obj.extractTable(
                obj.getTables(srcDoc).get(0).table
        );
        outDoc.save(outTmp.getAbsolutePath());
        Desktop.getDesktop().open(outTmp);
        if(true)return;

        //Workbook workbook = new Workbook(obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "server-proposal.xlsx"));
        //workbook.save(tmpDocx.getAbsolutePath(), SaveFormat.DOCX);
        //Desktop.getDesktop().open(tmpDocx);
        Map<String,InputStream> mapSource=new HashMap<>();
        mapSource.put("add4_Грузополучатели"
                ,obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "receivers.doc"));
        Document docTarget=obj.replaceWtables(
                obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "add4_test.docx")
            ,mapSource
            ,map);
        docTarget.save(outName);
        System.out.println("saved to: "+outName);
        System.out.println(System.getProperty("os.name"));
        Desktop.getDesktop().open(tmp);
        if(true)return;
        //target document
        Document doc = new Document(obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "add4_test.docx"));
        System.out.println(obj.getTablesNames(doc));
        //System.exit(0);
/*
        Sender sender = new Sender();
        sender.setName("LINQ Reporting Engine");
        sender.setMessage("Hello World");

        // Create a Reporting Engine.
        ReportingEngine engine = new ReportingEngine();

        // Execute the build report.
        engine.buildReport(doc, sender, "sender");
        obj.tablesProcess(doc);
        obj.getTablesNamesMap(doc);
*/
        //source document to insert
        Document doc1=new Document(obj.getClass().getClassLoader().getResourceAsStream(DATA_DIR + "receivers.doc"));
        //table node to replace
        Table tbl1=obj.getTables(doc).get(0).table;
        //table replace with
        Table tbl2=obj.getTables(doc1).get(0).table;
        //obj.replaceRowsText(tbl1, tbl2);
        obj.insertDocument(tbl1,doc1);
        //remove first table
        tbl1.remove();
        // save
        //String outName=DATA_DIR + "out.docx";
        doc.save(outName);
        System.out.println("saved to: "+outName);
        System.out.println(System.getProperty("os.name"));
        Desktop.getDesktop().open(tmp);
/*
        System.exit(0);

        obj.getFields(new Document(DATA_DIR + "SpA_SupAgreement.docx"), "(<.+?>)");
        Map<String,String> map=new HashMap<String, String>();
        map.put("<SPA_POSITION>","Генерал");
        map.put("<SPA_WHOM>","Кузнецов");
        obj.replace(new Document(DATA_DIR + "SpA_SupAgreement.docx"), "(<.+?>)", map);
*/
    }

}
