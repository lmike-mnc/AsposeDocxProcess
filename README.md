# AsposeDocxProcess
## Processing posted json with servlets

Will replace "fields" and tables in the **DOCX** files (it's possible to use old **DOC** format)
Project using [Aspose engine](https://docs.aspose.com/words/java/)

Here are only code and some resources without libraries(dependencies) it's possible to see info in [.idea] files

## /DocxInfo entry point
usage example (assume **Tomcat** on port 8080):
>curl -X POST -i http://localhost:8080/mso_war_exploded/DocxInfo --data '{"filepath":"C:\\Users\\mike\\Documents\\Development\\Java\\mso\\src\\res\\add4_test1.docx"}'

property ___filepath___ is mandatory
by default "field" mark (tags) is \<\>
response is json with properties: ___tables___ (array), ___fields___ (object), ___tablesPath___ (object), **example**:
>{
>  "tables" : [ "add4_Грузополучатели" ],
>  "requestType" : "POST",
>  "input-type" : "docx",
>  "fields" : {
>    "<add4_contrdate1>" : [ "01.01.2020", "дата контракта[01.01.2020]>" ],
>    "<add4_basis>" : [ "", "" ],
>    "<add4_kpp>" : [ "", "" ],
>    "<add4_contrdate2>" : [ "", "" ],
>    "<add4_contrnumb1>" : [ "", "" ],
>    "<add4_who_position>" : [ "", "" ],
>    "<add4_bnkbic>" : [ "", "" ],
>    "<add4_shortname>" : [ "", "" ],
>    "<add4_whom>" : [ "", "" ],
>    "<add4_phone>" : [ "", "" ],
>    "<add4_acc>" : [ "", "" ],
>    "<add4_ogrn>" : [ "", "" ],
>    "<add4_bnkacc>" : [ "", "" ],
>    "<add4_postaddr>" : [ "", "" ],
>    "<add4_name>" : [ "", "" ],
>    "<add4_position>" : [ "", "" ],
>    "<add4_bnkname>" : [ "", "" ],
>    "<add4_inn>" : [ "", "" ],
>    "<add4_legaladdr>" : [ "", "" ],
>    "<add4_who>" : [ "", "" ],
>    "<add4_fax>" : [ "", "" ],
>    "<add4_startdate>" : [ "", "" ],
>    "<add4_contrnumb2>" : [ "", "" ]
>  }
>}

___tablesPath___ property will be received when ___extract___ property have been specified in **request**
>curl -X POST -i http://localhost:8080/mso_war_exploded/DocxInfo --data '{**"extract":true**,"filepath":"C:\\Users\\mike\\Documents\\Development\\Java\\mso\\src\\res\\add4_test1.docx"}'

**response**
>...
>  "tablesPath" : {
>    "add4_грузополучатели" : "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\temp\\out7712675334286421645.docx"
>  },
>...

## /DocxReplace entry point
...
