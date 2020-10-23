# AsposeDocxProcess
## Processing posted json with servlets

Will replace "fields" and tables in the **DOCX** files (it's possible to use old **DOC** format)
Project using [Aspose engine](https://docs.aspose.com/words/java/)

Here are only code and some resources without libraries(dependencies) it's possible to see info in [.idea](./../../tree/master/.idea) files

## /DocxInfo entry point
usage example (assume **Tomcat** on port 8080):
```
curl -X POST -i http://localhost:8080/mso_war_exploded/DocxInfo --data '{"filepath":"C:\\Users\\mike\\Documents\\Development\\Java\\mso\\src\\res\\add4_test1.docx"}'
```

property ___filepath___ is mandatory
by default "field" mark (tags) is \<\>
response is json with properties: ___tables___ (array), ___fields___ (object), ___tablesPath___ (object), **example**:
```json
{
  "tables" : [ "add4_Грузополучатели" ],
  "requestType" : "POST",
  "input-type" : "docx",
  "fields" : {
    "<add4_contrdate1>" : [ "01.01.2020", "дата контракта[01.01.2020]>" ],
    "<add4_basis>" : [ "", "" ],
    "<add4_kpp>" : [ "", "" ],
    "<add4_contrdate2>" : [ "", "" ],
    "<add4_contrnumb1>" : [ "", "" ],
    "<add4_who_position>" : [ "", "" ],
    "<add4_bnkbic>" : [ "", "" ],
    "<add4_shortname>" : [ "", "" ],
    "<add4_whom>" : [ "", "" ],
    "<add4_phone>" : [ "", "" ],
    "<add4_acc>" : [ "", "" ],
    "<add4_ogrn>" : [ "", "" ],
    "<add4_bnkacc>" : [ "", "" ],
    "<add4_postaddr>" : [ "", "" ],
    "<add4_name>" : [ "", "" ],
    "<add4_position>" : [ "", "" ],
    "<add4_bnkname>" : [ "", "" ],
    "<add4_inn>" : [ "", "" ],
    "<add4_legaladdr>" : [ "", "" ],
    "<add4_who>" : [ "", "" ],
    "<add4_fax>" : [ "", "" ],
    "<add4_startdate>" : [ "", "" ],
    "<add4_contrnumb2>" : [ "", "" ]
  }
}
```
___tablesPath___ property will be received when ___extract___ property have been specified in **request**
```curl -X POST -i http://localhost:8080/mso_war_exploded/DocxInfo --data '{**"extract":true**,"filepath":"C:\\Users\\mike\\Documents\\Development\\Java\\mso\\src\\res\\add4_test1.docx"}'
```
**response**
```json
...
  "tablesPath" : {
    "add4_грузополучатели" : "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\temp\\out7712675334286421645.docx"
  },
...
```
## /DocxReplace entry point
**request**
```bash
curl -X POST -i http://localhost:8080/mso_war_exploded/DocxReplace --data '{
"filepath":"C:\\Users\\mike\\Documents\\Development\\Java\\mso\\src\\res\\add4_test.docx"
,"open":true
,"filetype":"pdf"
,"fields":{
  "<ADd4_POSITION>":"Генерала"
  ,"<ADD4_WHOM>":"Кузнецова"
}
,"tablesPath":{
  "add4_Грузополучатели":"C:\\Users\\mike\\Documents\\Development\\Java\\mso\\src\\res\\receivers.docx"
}
}'
```
___"open"___:true - to open result file with according system file type association (awt Desktop is used)
___"filetype"___:"pdf" - is default output format (could be skipped), possible variands limiting by Aspose.Word type support and resticted to pdf docx html by code
___"tablesPath":{___ - path to tables to replace according title

**response**
```json
{
  "filetype" : "pdf",
  "requestType" : "POST",
  "filepath" : "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\temp\\out42412272048788812.pdf",
  "input-type" : "docx",
  "isConverted" : true
}
```
___"filepath"___ result file path
