package com.github.moneytostr;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class XmlConst {
    public static String CURRENCY_LIST =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"+
                    "<CurrencyList>\n"+
                    " \n"+
                    " <language value=\"UKR\"/>\n"+
                    " <UKR>\n"+
                    " <item value=\"0\" text=\"\u043d\u0443\u043b\u044c\"/>\n"+
                    " <item value=\"1000_10\" text=\"\u0442\u0438\u0441\u044f\u0447,\u043c\u0456\u043b\u044c\u0439\u043e\u043d\u0456\u0432,\u043c\u0456\u043b\u044c\u044f\u0440\u0434\u0456\u0432,\u0442\u0440\u0438\u043b\u044c\u0439\u043e\u043d\u0456\u0432\"/>\n"+
                    " <item value=\"1000_1\" text=\"\u0442\u0438\u0441\u044f\u0447\u0430,\u043c\u0456\u043b\u044c\u0439\u043e\u043d,\u043c\u0456\u043b\u044c\u044f\u0440\u0434,\u0442\u0440\u0438\u043b\u044c\u0439\u043e\u043d\"/>\n"+
                    " <item value=\"1000_234\" text=\"\u0442\u0438\u0441\u044f\u0447\u0456,\u043c\u0456\u043b\u044c\u0439\u043e\u043d\u0430,\u043c\u0456\u043b\u044c\u044f\u0440\u0434\u0430,\u0442\u0440\u0438\u043b\u044c\u0439\u043e\u043d\u0430\"/>\n"+
                    " <item value=\"1000_5\" text=\"\u0442\u0438\u0441\u044f\u0447,\u043c\u0456\u043b\u044c\u0439\u043e\u043d\u0456\u0432,\u043c\u0456\u043b\u044c\u044f\u0440\u0434\u0456\u0432,\u0442\u0440\u0438\u043b\u044c\u0439\u043e\u043d\u0456\u0432\"/>\n"+
                    " <item value=\"10_19\" text=\"\u0434\u0435\u0441\u044f\u0442\u044c,\u043e\u0434\u0438\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0434\u0432\u0430\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0442\u0440\u0438\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0447\u043e\u0442\u0438\u0440\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u043f\u2019\u044f\u0442\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0448i\u0441\u0442\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0441i\u043c\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0432i\u0441i\u043c\u043d\u0430\u0434\u0446\u044f\u0442\u044c,\u0434\u0435\u0432\'\u044f\u0442\u043d\u0430\u0434\u0446\u044f\u0442\u044c\"/>\n"+
                    " <item value=\"1\" text=\"\u043e\u0434\u043d\u0430,\u043e\u0434\u0438\u043d,\u043e\u0434\u0438\u043d,\u043e\u0434\u043d\u0430\"/>\n"+
                    " <item value=\"2\" text=\"\u0434\u0432\u0456,\u0434\u0432\u0430,\u0434\u0432\u0430,\u0434\u0432\u0456\"/>\n"+
                    " <item value=\"3_9\" text=\"\u0442\u0440\u0438,\u0447\u043e\u0442\u0438\u0440\u0438,\u043f\u2019\u044f\u0442\u044c,\u0448\u0456\u0441\u0442\u044c,\u0441\u0456\u043c,\u0432\u0456\u0441\u0456\u043c,\u0434\u0435\u0432\u2019\u044f\u0442\u044c\"/>\n"+
                    " <item value=\"100_900\" text=\"\u0441\u0442\u043e ,\u0434\u0432\u0456\u0441\u0442\u0456 ,\u0442\u0440\u0438\u0441\u0442\u0430 ,\u0447\u043e\u0442\u0438\u0440\u0438\u0441\u0442\u0430 ,\u043f\u2019\u044f\u0442\u0441\u043e\u0442 ,\u0448\u0456\u0441\u0442\u0441\u043e\u0442 ,\u0441\u0456\u043c\u0441\u043e\u0442 ,\u0432\u0456\u0441\u0456\u043c\u0441\u043e\u0442 ,\u0434\u0435\u0432\u2019\u044f\u0442\u0441\u043e\u0442 \"/>\n"+
                    " <item value=\"20_90\" text=\"\u0434\u0432\u0430\u0434\u0446\u044f\u0442\u044c ,\u0442\u0440\u0438\u0434\u0446\u044f\u0442\u044c ,\u0441\u043e\u0440\u043e\u043a ,\u043f\u2019\u044f\u0442\u0434\u0435\u0441\u044f\u0442 ,\u0448\u0456\u0441\u0442\u0434\u0435\u0441\u044f\u0442 ,\u0441\u0456\u043c\u0434\u0435\u0441\u044f\u0442 ,\u0432\u0456\u0441\u0456\u043c\u0434\u0435\u0441\u044f\u0442 ,\u0434\u0435\u0432\u2019\u044f\u043d\u043e\u0441\u0442\u043e \"/>\n"+
                    " <item value=\"pdv\" text=\"\u0432 \u0442.\u0447. \u041f\u0414\u0412 \"/>\n"+
                    " <item value=\"pdv_value\" text=\"20\"/>\n"+
                    " </UKR>\n"+
                    " <RUS>\n"+
                    " <item value=\"0\" text=\"\u043d\u043e\u043b\u044c\"/>\n"+
                    " <item value=\"1000_10\" text=\"\u0442\u044b\u0441\u044f\u0447,\u043c\u0438\u043b\u043b\u0438\u043e\u043d\u043e\u0432,\u043c\u0438\u043b\u043b\u0438\u0430\u0440\u0434\u043e\u0432,\u0442\u0440\u0438\u043b\u043b\u0438\u043e\u043d\u043e\u0432\"/>\n"+
                    " <item value=\"1000_1\" text=\"\u0442\u044b\u0441\u044f\u0447\u0430,\u043c\u0438\u043b\u043b\u0438\u043e\u043d,\u043c\u0438\u043b\u043b\u0438\u0430\u0440\u0434,\u0442\u0440\u0438\u043b\u043b\u0438\u043e\u043d\"/>\n"+
                    " <item value=\"1000_234\" text=\"\u0442\u044b\u0441\u044f\u0447\u0438,\u043c\u0438\u043b\u043b\u0438\u043e\u043d\u0430,\u043c\u0438\u043b\u043b\u0438\u0430\u0440\u0434\u0430,\u0442\u0440\u0438\u043b\u043b\u0438\u043e\u043d\u0430\"/>\n"+
                    " <item value=\"1000_5\" text=\"\u0442\u044b\u0441\u044f\u0447,\u043c\u0438\u043b\u043b\u0438\u043e\u043d\u043e\u0432,\u043c\u0438\u043b\u043b\u0438\u0430\u0440\u0434\u043e\u0432,\u0442\u0440\u0438\u043b\u043b\u0438\u043e\u043d\u043e\u0432\"/>\n"+
                    " <item value=\"10_19\" text=\"\u0434\u0435\u0441\u044f\u0442\u044c,\u043e\u0434\u0438\u043d\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0434\u0432\u0435\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0442\u0440\u0438\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0447\u0435\u0442\u044b\u0440\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u043f\u044f\u0442\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0448\u0435\u0441\u0442\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0441\u0435\u043c\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0432\u043e\u0441\u0435\u043c\u043d\u0430\u0434\u0446\u0430\u0442\u044c,\u0434\u0435\u0432\u044f\u0442\u043d\u0430\u0434\u0446\u0430\u0442\u044c\"/>\n"+
                    " <item value=\"1\" text=\"\u043e\u0434\u043d\u0430,\u043e\u0434\u0438\u043d,\u043e\u0434\u0438\u043d,\u043e\u0434\u043d\u0430\"/>\n"+
                    " <item value=\"2\" text=\"\u0434\u0432\u0435,\u0434\u0432\u0430,\u0434\u0432\u0430,\u0434\u0432\u0435\"/>\n"+
                    " <item value=\"3_9\" text=\"\u0442\u0440\u0438,\u0447\u0435\u0442\u044b\u0440\u0435,\u043f\u044f\u0442\u044c,\u0448\u0435\u0441\u0442\u044c,\u0441\u0435\u043c\u044c,\u0432\u043e\u0441\u0435\u043c\u044c,\u0434\u0435\u0432\u044f\u0442\u044c\"/>\n"+
                    " <item value=\"100_900\" text=\"\u0441\u0442\u043e ,\u0434\u0432\u0435\u0441\u0442\u0438 ,\u0442\u0440\u0438\u0441\u0442\u0430 ,\u0447\u0435\u0442\u044b\u0440\u0435\u0441\u0442\u0430 ,\u043f\u044f\u0442\u044c\u0441\u043e\u0442 ,\u0448\u0435\u0441\u0442\u044c\u0441\u043e\u0442 ,\u0441\u0435\u043c\u044c\u0441\u043e\u0442 ,\u0432\u043e\u0441\u0435\u043c\u044c\u0441\u043e\u0442 ,\u0434\u0435\u0432\u044f\u0442\u044c\u0441\u043e\u0442 \"/>\n"+
                    " <item value=\"20_90\" text=\"\u0434\u0432\u0430\u0434\u0446\u0430\u0442\u044c ,\u0442\u0440\u0438\u0434\u0446\u0430\u0442\u044c ,\u0441\u043e\u0440\u043e\u043a ,\u043f\u044f\u0442\u044c\u0434\u0435\u0441\u044f\u0442 ,\u0448\u0435\u0441\u0442\u044c\u0434\u0435\u0441\u044f\u0442 ,\u0441\u0435\u043c\u044c\u0434\u0435\u0441\u044f\u0442 ,\u0432\u043e\u0441\u0435\u043c\u044c\u0434\u0435\u0441\u044f\u0442 ,\u0434\u0435\u0432\u044f\u043d\u043e\u0441\u0442\u043e \"/>\n"+
                    " <item value=\"pdv\" text=\"\u0432 \u0442.\u0447. \u041d\u0414\u0421 \"/>\n"+
                    " <item value=\"pdv_value\" text=\"18\"/>\n"+
                    " </RUS>\n"+
                    " <ENG>\n"+
                    " <item value=\"0\" text=\"zero\"/>\n"+
                    " <item value=\"1000_10\" text=\"thousand,million,billion,trillion\"/>\n"+
                    " <item value=\"1000_1\" text=\"thousand,million,billion,trillion\"/>\n"+
                    " <item value=\"1000_234\" text=\"thousand,million,billion,trillion\"/>\n"+
                    " <item value=\"1000_5\" text=\"thousand,million,billion,trillion\"/>\n"+
                    " <item value=\"10_19\" text=\"ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen\"/>\n"+
                    " <item value=\"1\" text=\"one,one,one,one\"/>\n"+
                    " <item value=\"2\" text=\"two,two,two,two\"/>\n"+
                    " <item value=\"3_9\" text=\"three,four,five,six,seven,eight,nine\"/>\n"+
                    " <item value=\"100_900\" text=\"one hundred ,two hundred ,three hundred ,four hundred ,five hundred ,six hundred ,seven hundred ,eight hundred ,nine hundred \"/>\n"+
                    " <item value=\"20_90\" text=\"twenty-,thirty-,forty-,fifty-,sixty-,seventy-,eighty-,ninety-\"/>\n"+
                    " <item value=\"pdv\" text=\"including VAT \"/>\n"+
                    " <item value=\"pdv_value\" text=\"10\"/>\n"+
                    " </ENG>\n"+
                    "\n"+
                    " <RUR CurrID=\"810\" CurrName=\"\u0420\u043e\u0441\u0441\u0438\u0439\u0441\u043a\u0438\u0435 \u0440\u0443\u0431\u043b\u0438\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0440\u0443\u0431\u043b\u044c\" RubTwoUnit=\"\u0440\u0443\u0431\u043b\u044f\" RubFiveUnit=\"\u0440\u0443\u0431\u043b\u0435\u0439\" Rubkind=\"M\" RubShortUnit=\"\u0440\u0443\u0431.\"\n"+
                    " KopOneUnit=\"\u043a\u043e\u043f\u0435\u0439\u043a\u0430\" KopTwoUnit=\"\u043a\u043e\u043f\u0435\u0439\u043a\u0438\" KopFiveUnit=\"\u043a\u043e\u043f\u0435\u0435\u043a\" Kopkind=\"F\"\n"+
                    " />\n"+
                    " <UAH CurrID=\"980\" CurrName=\"\u0423\u043a\u0440\u0430\u0438\u043d\u0441\u043a\u0456 \u0433\u0440\u0438\u0432\u043d\u0456\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0433\u0440\u0438\u0432\u043d\u044f\" RubTwoUnit=\"\u0433\u0440\u0438\u0432\u043d\u0438\" RubFiveUnit=\"\u0433\u0440\u0438\u0432\u0435\u043d\u044c\" Rubkind=\"F\" RubShortUnit=\"\u0433\u0440\u043d.\"\n"+
                    " KopOneUnit=\"\u043a\u043e\u043f\u0435\u0439\u043a\u0430\" KopTwoUnit=\"\u043a\u043e\u043f\u0435\u0439\u043a\u0438\" KopFiveUnit=\"\u043a\u043e\u043f\u0435\u0435\u043a\" Kopkind=\"F\"\n"+
                    " />\n"+
                    " <USD CurrID=\"840\" CurrName=\"\u0414\u043e\u043b\u0430\u0440\u0438 \u0421\u0428\u0410\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0434\u043e\u043b\u043b\u0430\u0440\" RubTwoUnit=\"\u0434\u043e\u043b\u043b\u0430\u0440\u0430\" RubFiveUnit=\"\u0434\u043e\u043b\u043b\u0430\u0440\u043e\u0432\" Rubkind=\"M\" RubShortUnit=\"\u0434\u043e\u043b.\"\n"+
                    " KopOneUnit=\"\u0446\u0435\u043d\u0442\" KopTwoUnit=\"\u0446\u0435\u043d\u0430\" KopFiveUnit=\"\u0446\u0435\u043d\u0442\u043e\u0432\" Kopkind=\"M\"\n"+
                    " />\n"+
                    "\n"+
                    " <RUR CurrID=\"810\" CurrName=\"\u0420\u043e\u0441\u0441\u0438\u0439\u0441\u043a\u0438\u0435 \u0440\u0443\u0431\u043b\u0438\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0440\u0443\u0431\u043b\u044c\" RubTwoUnit=\"\u0440\u0443\u0431\u043b\u0456\" RubFiveUnit=\"\u0440\u0443\u0431\u043b\u0456\u0432\" Rubkind=\"M\" RubShortUnit=\"\u0440\u0443\u0431.\"\n"+
                    " KopOneUnit=\"\u043a\u043e\u043f\u0456\u0439\u043a\u0430\" KopTwoUnit=\"\u043a\u043e\u043f\u0456\u0439\u043a\u0438\" KopFiveUnit=\"\u043a\u043e\u043f\u0456\u0439\u043e\u043a\" Kopkind=\"F\"\n"+
                    " /> \n"+
                    " <UAH CurrID=\"980\" CurrName=\"\u0423\u043a\u0440\u0430\u0438\u043d\u0441\u043a\u0456 \u0433\u0440\u0438\u0432\u043d\u0456\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0433\u0440\u0438\u0432\u043d\u044f\" RubTwoUnit=\"\u0433\u0440\u0438\u0432\u043d\u0456\" RubFiveUnit=\"\u0433\u0440\u0438\u0432\u0435\u043d\u044c\" Rubkind=\"F\" RubShortUnit=\"\u0433\u0440\u043d.\"\n"+
                    " KopOneUnit=\"\u043a\u043e\u043f\u0456\u0439\u043a\u0430\" KopTwoUnit=\"\u043a\u043e\u043f\u0456\u0439\u043a\u0438\" KopFiveUnit=\"\u043a\u043e\u043f\u0456\u0439\u043e\u043a\" Kopkind=\"F\"\n"+
                    " />\n"+
                    " <USD CurrID=\"840\" CurrName=\"\u0414\u043e\u043b\u0430\u0440\u0438 \u0421\u0428\u0410\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0434\u043e\u043b\u0430\u0440\" RubTwoUnit=\"\u0434\u043e\u043b\u0430\u0440\u0430\" RubFiveUnit=\"\u0434\u043e\u043b\u0430\u0440\u0456\u0432\" Rubkind=\"M\" RubShortUnit=\"\u0434\u043e\u043b.\"\n"+
                    " KopOneUnit=\"\u0446\u0435\u043d\u0442\" KopTwoUnit=\"\u0446\u0435\u043d\u0430\" KopFiveUnit=\"\u0446\u0435\u043d\u0442\u0456\u0432\" Kopkind=\"M\"\n"+
                    " />\n"+
                    "\n"+
                    " <RUR CurrID=\"810\" CurrName=\"\u0420\u043e\u0441\u0441\u0438\u0439\u0441\u043a\u0438\u0435 \u0440\u0443\u0431\u043b\u0438\" language=\"ENG\"\n"+
                    " RubOneUnit=\"ruble\" RubTwoUnit=\"rubles\" RubFiveUnit=\"rubles\" Rubkind=\"M\" RubShortUnit=\"RUR.\"\n"+
                    " KopOneUnit=\"kopeck\" KopTwoUnit=\"kopecks\" KopFiveUnit=\"kopecks\" Kopkind=\"M\"\n"+
                    " /> \n"+
                    " <UAH CurrID=\"980\" CurrName=\"\u0423\u043a\u0440\u0430\u0438\u043d\u0441\u043a\u0456 \u0433\u0440\u0438\u0432\u043d\u0456\" language=\"ENG\"\n"+
                    " RubOneUnit=\"hryvnia\" RubTwoUnit=\"hryvnias\" RubFiveUnit=\"hryvnias\" Rubkind=\"M\" RubShortUnit=\"UAH.\"\n"+
                    " KopOneUnit=\"kopeck\" KopTwoUnit=\"kopecks\" KopFiveUnit=\"kopecks\" Kopkind=\"M\"\n"+
                    " />\n"+
                    " <USD CurrID=\"840\" CurrName=\"\u0414\u043e\u043b\u0430\u0440\u0438 \u0421\u0428\u0410\" language=\"ENG\"\n"+
                    " RubOneUnit=\"dollar\" RubTwoUnit=\"dollars\" RubFiveUnit=\"dollars\" Rubkind=\"M\" RubShortUnit=\"USD.\"\n"+
                    " KopOneUnit=\"cent\" KopTwoUnit=\"cents\" KopFiveUnit=\"cents\" Kopkind=\"M\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER10 CurrID=\"556\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0434\u0435\u0441\u044f\u0442\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0446\u0435\u043b\u0430\u044f,\" RubTwoUnit=\"\u0446\u0435\u043b\u044b\u0445,\" RubFiveUnit=\"\u0446\u0435\u043b\u044b\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0434\u0435\u0441\u044f\u0442\u0430\u044f \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopTwoUnit=\"\u0434\u0435\u0441\u044f\u0442\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopFiveUnit=\"\u0434\u0435\u0441\u044f\u0442\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER100 CurrID=\"557\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0441\u043e\u0442\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0446\u0435\u043b\u0430\u044f,\" RubTwoUnit=\"\u0446\u0435\u043b\u044b\u0445,\" RubFiveUnit=\"\u0446\u0435\u043b\u044b\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0441\u043e\u0442\u0430\u044f \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopTwoUnit=\"\u0441\u043e\u0442\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopFiveUnit=\"\u0441\u043e\u0442\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER1000 CurrID=\"558\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0442\u0438\u0441\u044f\u0447\u043d\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0446\u0435\u043b\u0430\u044f,\" RubTwoUnit=\"\u0446\u0435\u043b\u044b\u0445,\" RubFiveUnit=\"\u0446\u0435\u043b\u044b\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0442\u044b\u0441\u044f\u0447\u043d\u0430\u044f \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopTwoUnit=\"\u0442\u044b\u0441\u044f\u0447\u043d\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopFiveUnit=\"\u0442\u044b\u0441\u044f\u0447\u043d\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER10000 CurrID=\"559\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0434\u0435\u0441\u044f\u0442\u0438 \u0442\u0438\u0441\u044f\u0447\u043d\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"RUS\"\n"+
                    " RubOneUnit=\"\u0446\u0435\u043b\u0430\u044f,\" RubTwoUnit=\"\u0446\u0435\u043b\u044b\u0445,\" RubFiveUnit=\"\u0446\u0435\u043b\u044b\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0442\u044b\u0441\u044f\u0447\u043d\u0430\u044f \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopTwoUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0442\u044b\u0441\u044f\u0447\u043d\u044b\u0435 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" KopFiveUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0442\u044b\u0441\u044f\u0447\u043d\u044b\u0445 \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER10 CurrID=\"556\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0434\u0435\u0441\u044f\u0442\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0446\u0456\u043b\u0430,\" RubTwoUnit=\"\u0446\u0456\u043b\u0438\u0445,\" RubFiveUnit=\"\u0446\u0456\u043b\u0438\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0434\u0435\u0441\u044f\u0442\u0430 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopTwoUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopFiveUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER100 CurrID=\"557\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0441\u043e\u0442\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0446\u0456\u043b\u0430,\" RubTwoUnit=\"\u0446\u0456\u043b\u0438\u0445,\" RubFiveUnit=\"\u0446\u0456\u043b\u0438\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0441\u043e\u0442\u0430 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopTwoUnit=\"\u0441\u043e\u0442\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopFiveUnit=\"\u0441\u043e\u0442\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER1000 CurrID=\"558\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0442\u0438\u0441\u044f\u0447\u043d\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0446\u0456\u043b\u0430,\" RubTwoUnit=\"\u0446\u0456\u043b\u0438\u0445,\" RubFiveUnit=\"\u0446\u0456\u043b\u0438\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0442\u0438\u0441\u044f\u0447\u043d\u0430 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopTwoUnit=\"\u0442\u0438\u0441\u044f\u0447\u043d\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopFiveUnit=\"\u0442\u0438\u0441\u044f\u0447\u043d\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER10000 CurrID=\"559\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0434\u0435\u0441\u044f\u0442\u0438 \u0442\u0438\u0441\u044f\u0447\u043d\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"UKR\"\n"+
                    " RubOneUnit=\"\u0446\u0456\u043b\u0430,\" RubTwoUnit=\"\u0446\u0456\u043b\u0438\u0445,\" RubFiveUnit=\"\u0446\u0456\u043b\u0438\u0445,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0442\u0438\u0441\u044f\u0447\u043d\u0430 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopTwoUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0442\u0438\u0441\u044f\u0447\u043d\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" KopFiveUnit=\"\u0434\u0435\u0441\u044f\u0442\u0438\u0442\u0438\u0441\u044f\u0447\u043d\u0438\u0445 \u0432\u0456\u0434\u0441\u043e\u0442\u043a\u0430\" Kopkind=\"M\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER10 CurrID=\"560\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0434\u0435\u0441\u044f\u0442\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"ENG\"\n"+
                    " RubOneUnit=\",\" RubTwoUnit=\"integers,\" RubFiveUnit=\"integers,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"tenth of one percent\" KopTwoUnit=\"tenth of one percent\" KopFiveUnit=\"tenth of one percent\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER100 CurrID=\"561\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0441\u043e\u0442\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"ENG\"\n"+
                    " RubOneUnit=\",\" RubTwoUnit=\"integers,\" RubFiveUnit=\"integers,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"hundred percent\" KopTwoUnit=\"hundredth of percent\" KopFiveUnit=\"hundredth of percent\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER1000 CurrID=\"562\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0442\u0438\u0441\u044f\u0447\u043d\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"ENG\"\n"+
                    " RubOneUnit=\",\" RubTwoUnit=\"integers,\" RubFiveUnit=\"integers,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"thousandth of percent\" KopTwoUnit=\"thousandths of percent\" KopFiveUnit=\"thousandths of percent\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    " <PER10000 CurrID=\"563\" CurrName=\"\u0412i\u0434\u0441\u043e\u0442\u043a\u0438 \u0437 \u0434\u0435\u0441\u044f\u0442\u0438 \u0442\u0438\u0441\u044f\u0447\u043d\u0438\u043c\u0438 \u0447\u0430\u0441\u0442\u0438\u043d\u0430\u043c\u0438\" language=\"ENG\"\n"+
                    " RubOneUnit=\",\" RubTwoUnit=\"integers,\" RubFiveUnit=\"integers,\" Rubkind=\"F\"\n"+
                    " KopOneUnit=\"ten percent\" KopTwoUnit=\"ten-percent\" KopFiveUnit=\"ten-percent\" Kopkind=\"F\"\n"+
                    " />\n"+
                    "\n"+
                    "</CurrencyList>\n"+
                    "";
    public static void main(String[] args) throws FileNotFoundException {
         PrintWriter xml= new PrintWriter("currency_list.xml");
         xml.print(CURRENCY_LIST);
         xml.close();
    }
}
