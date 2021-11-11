//https://javadev.github.io/moneytostr-russian/
 /*
  * $Id$
  *
  * Copyright 2014 Valentyn Kolesnikov
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */
 package com.github.moneytostr;

import java.util.Objects;

/**
  * Converts numbers to symbols.
  *
  * @author Valentyn Kolesnikov
  * @version $Revision$ $Date$
  */
 public class MoneyToStr {
     private static final int INDEX_3 = 3;
     private static final int INDEX_2 = 2;
     private static final int INDEX_1 = 1;
     private static final int INDEX_0 = 0;
     private static final org.w3c.dom.Document xmlDoc;
     private static final int NUM0 = 0;
     private static final int NUM1 = 1;
     private static final int NUM2 = 2;
     private static final int NUM3 = 3;
     private static final int NUM4 = 4;
     private static final int NUM5 = 5;
     private static final int NUM6 = 6;
     private static final int NUM7 = 7;
     private static final int NUM8 = 8;
     private static final int NUM9 = 9;
     private static final int NUM10 = 10;
     private static final int NUM11 = 11;
     private static final int NUM14 = 14;
     private static final int NUM100 = 100;
     private static final int NUM1000 = 1000;
     private static final int NUM10000 = 10000;
     private final java.util.Map<String, String[]> messages = new java.util.LinkedHashMap<>();
     private final String rubOneUnit;
     private final String rubTwoUnit;
     private final String rubFiveUnit;
     private final String rubkind;
     private final String kopOneUnit;
     private final String kopTwoUnit;
     private final String kopFiveUnit;
     private final String kopkind;
     private final String rubShortUnit;
     private final Currency currency;
     private final Language language;
     private final Pennies pennies;
     private final static String xml="/currency_list.xml";

     //static final String CURRENCY_LIST_F=new Scanner(Objects.requireNonNull(MoneyToStr.class.getResourceAsStream(xml)), "UTF-8").useDelimiter("\\A").next();
     static {
//         Const.CURRENCY_LIST=CURRENCY_LIST_F;
         javax.xml.parsers.DocumentBuilderFactory docFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
         try {
             javax.xml.parsers.DocumentBuilder xmlDocBuilder = docFactory.newDocumentBuilder();
             xmlDoc = xmlDocBuilder.parse(Objects.requireNonNull(MoneyToStr.class.getResourceAsStream(xml)), "UTF-8");
                     //new java.io.ByteArrayInputStream(Const.CURRENCY_LIST.getBytes(StandardCharsets.UTF_8)));
         } catch (Exception ex) {
             throw new UnsupportedOperationException(ex);
         }
     }

     /** Currency. */
     @SuppressWarnings("unused")
     public enum Currency {
         /**.*/
         RUR,
         /**.*/
         UAH,
         /**.*/
         USD,
         /**.*/
         PER10,
         /**.*/
         PER100,
         /**.*/
         PER1000,
         /**.*/
         PER10000
     }

     /** Language. */
     @SuppressWarnings("unused")
     public enum Language {
         /**.*/
         RUS,
         /**.*/
         UKR,
         /**.*/
         ENG
     }

     /** Pennies. */
     @SuppressWarnings("unused")
     public enum Pennies {
         /**.*/
         NUMBER,
         /**.*/
         TEXT
     }

     /**
      * Inits class with currency. Usage: MoneyToStr moneyToStr = new MoneyToStr(
      *     MoneyToStr.Currency.UAH, MoneyToStr.Language.UKR, MoneyToStr.Pennies.NUMBER);
      * Definition for currency is placed into currlist.xml
      *
      * @param currency the currency (UAH, RUR, USD)
      * @param language the language (UKR, RUS, ENG)
      * @param pennies the pennies (NUMBER, TEXT)
      */
     public MoneyToStr(Currency currency, Language language, Pennies pennies) {
         if (currency == null) {
             throw new IllegalArgumentException("currency is null");
         }
         if (language == null) {
             throw new IllegalArgumentException("language is null");
         }
         if (pennies == null) {
             throw new IllegalArgumentException("pennies is null");
         }
         this.currency = currency;
         this.language = language;
         this.pennies = pennies;
         String theISOstr = currency.name();
         org.w3c.dom.Element languageElement = (org.w3c.dom.Element)
                 (xmlDoc.getElementsByTagName(language.name())).item(0);
         org.w3c.dom.NodeList items = languageElement.getElementsByTagName("item");
         for (int index = 0; index < items.getLength(); index += 1) {
             org.w3c.dom.Element languageItem = (org.w3c.dom.Element) items.item(index);
             messages.put(languageItem.getAttribute("value"), languageItem.getAttribute("text").split(","));
         }
         org.w3c.dom.NodeList theISOElements = xmlDoc.getElementsByTagName(theISOstr);
         org.w3c.dom.Element theISOElement = null;
         for (int index = 0; index < theISOElements.getLength(); index += 1) {
             if (((org.w3c.dom.Element) theISOElements.item(index)).getAttribute("language").equals(language.name())) {
                 theISOElement = (org.w3c.dom.Element) theISOElements.item(index);
                 break;
             }
         }
         rubOneUnit = Objects.requireNonNull(theISOElement).getAttribute("RubOneUnit");
         rubTwoUnit = theISOElement.getAttribute("RubTwoUnit");
         rubFiveUnit = theISOElement.getAttribute("RubFiveUnit");
         kopOneUnit = theISOElement.getAttribute("KopOneUnit");
         kopTwoUnit = theISOElement.getAttribute("KopTwoUnit");
         kopFiveUnit = theISOElement.getAttribute("KopFiveUnit");
         rubkind = theISOElement.getAttribute("Rubkind");
         kopkind = theISOElement.getAttribute("Kopkind");
         rubShortUnit = theISOElement.hasAttribute("RubShortUnit") ? theISOElement.getAttribute("RubShortUnit") : "";
     }

     /**
      * Converts percent to string.
      * @param amount the amount of percent
      * @param lang the language (RUS, UKR)
      * @return the string of percent
      */
     @SuppressWarnings("unused")
     public static String percentToStr(Double amount, Language lang) {
         return percentToStr(amount, lang, Pennies.TEXT);
     }

     /**
      * Converts percent to string.
      * @param amount the amount of percent
      * @param lang the language (RUS, UKR, ENG)
      * @param pennies the pennies (NUMBER, TEXT)
      * @return the string of percent
      */
     public static String percentToStr(Double amount, Language lang, Pennies pennies) {
         if (amount == null) {
             throw new IllegalArgumentException("amount is null");
         }
         if (lang == null) {
             throw new IllegalArgumentException("language is null");
         }
         if (pennies == null) {
             throw new IllegalArgumentException("pennies is null");
         }
         Long intPart = amount.longValue();
         long fractPart;
         String result;
         if (amount.floatValue() == amount.intValue()) {
             result = new MoneyToStr(Currency.PER10, lang, pennies).convert(amount.longValue(), 0L);
         } else if (Double.valueOf(amount * NUM10).floatValue() == Double.valueOf(amount * NUM10).intValue()) {
             fractPart = Math.round((amount - intPart) * NUM10);
             result = new MoneyToStr(Currency.PER10, lang, pennies).convert(intPart, fractPart);
         } else if (Double.valueOf(amount * NUM100).floatValue() == Double.valueOf(amount * NUM100).intValue()) {
             fractPart = Math.round((amount - intPart) * NUM100);
             result = new MoneyToStr(Currency.PER100, lang, pennies).convert(intPart, fractPart);
         } else if (Double.valueOf(amount * NUM1000).floatValue() == Double.valueOf(amount * NUM1000).intValue()) {
             fractPart = Math.round((amount - intPart) * NUM1000);
             result = new MoneyToStr(Currency.PER1000, lang, pennies).convert(intPart, fractPart);
         } else {
             fractPart = Math.round((amount - intPart) * NUM10000);
             result = new MoneyToStr(Currency.PER10000, lang, pennies).convert(intPart, fractPart);
         }
         return result;
     }

     /**
      * Converts double value to the text description.
      *
      * @param theMoney
      *            the amount of money in format major.minor
      * @return the string description of money value
      */
     public String convert(Double theMoney) {
         if (theMoney == null) {
             throw new IllegalArgumentException("theMoney is null");
         }
         Long intPart = theMoney.longValue();
         long fractPart = Math.round((theMoney - intPart) * NUM100);
         if (currency == Currency.PER1000) {
             fractPart = Math.round((theMoney - intPart) * NUM1000);
         }
         return convert(intPart, fractPart);
     }

    public String convert(Long theMoney) {
        return convert(theMoney, null);
    }

     /**
      * Converts number to currency. Usage: MoneyToStr moneyToStr = new MoneyToStr("UAH"); String result =
      * moneyToStr.convert(123D); Expected: result = сто двадцять три гривні 00 копійок
      *
      * @param theMoney
      *            the amount of money major currency
      * @param theKopeiki
      *            the amount of money minor currency
      * @return the string description of money value
      */
     public String convert(Long theMoney, Long theKopeiki) {
         if (theMoney == null) {
             throw new IllegalArgumentException("theMoney is null");
         }
/*
         if (theKopeiki == null) {
             throw new IllegalArgumentException("theKopeiki is null");
         }
*/
         StringBuilder money2str = new StringBuilder();
         long triadNum = 0L;
         long theTriad;

         long intPart = theMoney;
         if (intPart == 0) {
             money2str.append(messages.get("0")[0]).append(" ");
         }
         do {
             theTriad = intPart % NUM1000;
             money2str.insert(0, triad2Word(theTriad, triadNum, rubkind));
             if (triadNum == 0) {
                 long range10 = (theTriad % NUM100) / NUM10;
                 long range = theTriad % NUM10;
                 if (range10 == NUM1) {
                     money2str.append(rubFiveUnit);
                 } else {
                     switch ((byte) range) {
                         case NUM1:
                             money2str.append(rubOneUnit);
                             break;
                         case NUM2:
                         case NUM3:
                         case NUM4:
                             money2str.append(rubTwoUnit);
                             break;
                         default:
                             money2str.append(rubFiveUnit);
                             break;
                     }
                 }
             }
             intPart = intPart / NUM1000;
             triadNum++;
         } while (intPart > 0);

         if (theKopeiki!=null) {
             if (pennies == Pennies.TEXT) {
                 money2str.append(language == Language.ENG ? " and " : " ").append(theKopeiki == 0 ? messages.get("0")[0] + " " : triad2Word(theKopeiki, 0L, kopkind));
             } else {
                 money2str.append(" ").append(theKopeiki < 10 ? "0" + theKopeiki : theKopeiki).append(" ");
             }
             if (theKopeiki >= NUM11 && theKopeiki <= NUM14) {
                 money2str.append(kopFiveUnit);
             } else {
                 switch ((byte) (theKopeiki % NUM10)) {
                     case NUM1:
                         money2str.append(kopOneUnit);
                         break;
                     case NUM2:
                     case NUM3:
                     case NUM4:
                         money2str.append(kopTwoUnit);
                         break;
                     default:
                         money2str.append(kopFiveUnit);
                         break;
                 }
             }
         }
         return money2str.toString().trim();
     }

     private String triad2Word(Long triad, Long triadNum, String kind) {
         StringBuilder triadWord = new StringBuilder(NUM100);

         if (triad == 0) {
             return "";
         }

         long range = check1(triad, triadWord);
         if (language == Language.ENG && triadWord.length() > 0 && triad % NUM10 == 0) {
             triadWord.deleteCharAt(triadWord.length() - 1);
             triadWord.append(" ");
         }

         long range10 = range;
         range = triad % NUM10;
         check2(triadNum, kind, triadWord, triad, range10);
         switch (triadNum.byteValue()) {
             case NUM0:
                 break;
             case NUM1:
             case NUM2:
             case NUM3:
             case NUM4:
                 if (range10 == NUM1) {
                     triadWord.append(messages.get("1000_10")[triadNum.byteValue() - 1]).append(" ");
                 } else {
                     switch ((byte) range) {
                         case NUM1:
                             triadWord.append(messages.get("1000_1")[triadNum.byteValue() - 1]).append(" ");
                             break;
                         case NUM2:
                         case NUM3:
                         case NUM4:
                             triadWord.append(messages.get("1000_234")[triadNum.byteValue() - 1]).append(" ");
                             break;
                         default:
                             triadWord.append(messages.get("1000_5")[triadNum.byteValue() - 1]).append(" ");
                             break;
                     }
                 }
                 break;
             default:
                 triadWord.append("??? ");
                 break;
         }
         return triadWord.toString();
     }

     /**
      * @param triadNum the triad num
      * @param kind the kind
      * @param triadWord the triad word
      * @param triad the triad
      * @param range10 the range 10
      */
     private void check2(Long triadNum, String kind, StringBuilder triadWord, Long triad, Long range10) {
         long range = triad % NUM10;
         if (range10 == 1) {
             triadWord.append(messages.get("10_19")[(byte) range]).append(" ");
         } else {
             switch ((byte) range) {
                 case NUM1:
                     if (triadNum == NUM1) {
                         triadWord.append(messages.get("1")[INDEX_0]).append(" ");
                     } else if (triadNum == NUM2 || triadNum == NUM3 || triadNum == NUM4) {
                         triadWord.append(messages.get("1")[INDEX_1]).append(" ");
                     } else if ("M".equals(kind)) {
                         triadWord.append(messages.get("1")[INDEX_2]).append(" ");
                     } else if ("F".equals(kind)) {
                         triadWord.append(messages.get("1")[INDEX_3]).append(" ");
                     }
                     break;
                 case NUM2:
                     if (triadNum == NUM1) {
                         triadWord.append(messages.get("2")[INDEX_0]).append(" ");
                     } else if (triadNum == NUM2 || triadNum == NUM3 || triadNum == NUM4) {
                         triadWord.append(messages.get("2")[INDEX_1]).append(" ");
                     } else if ("M".equals(kind)) {
                         triadWord.append(messages.get("2")[INDEX_2]).append(" ");
                     } else if ("F".equals(kind)) {
                         triadWord.append(messages.get("2")[INDEX_3]).append(" ");
                     }
                     break;
                 case NUM3:
                 case NUM4:
                 case NUM5:
                 case NUM6:
                 case NUM7:
                 case NUM8:
                 case NUM9:
                     triadWord.append(concat(new String[]{"", "", ""}, messages.get("3_9"))[(byte)range]).append(" ");
                     break;
                 default:
                     break;
             }
         }
     }

     /**
      * @param triad the triad
      * @param triadWord the triad word
      * @return the range
      */
     private Long check1(Long triad, StringBuilder triadWord) {
         long range = triad / NUM100;
         triadWord.append(concat(new String[] {""}, messages.get("100_900"))[(byte) range]);

         range = (triad % NUM100) / NUM10;
         triadWord.append(concat(new String[] {"", ""}, messages.get("20_90"))[(byte) range]);
         return range;
     }

     private <T> T[] concat(T[] first, T[] second) {
         T[] result = java.util.Arrays.copyOf(first, first.length + second.length);
         System.arraycopy(second, 0, result, first.length, second.length);
         return result;
     }

     public static void main(String[] args) {
         String amount = "123.25";
         String language = "ENG";
         String currency = "USD";
         String pennies = "TEXT";
         if (args.length == 0) {
             System.out.println("Usage: java -jar moneytostr.jar --amount=123.25 --language=rus|ukr|eng --currency=rur|uah|usd --pennies=text|number");
         } else {
             for (String arg : args) {
                 if (arg.startsWith("--amount=")) {
                     amount = arg.substring(9).trim().replace(",", ".");
                 } else if (arg.startsWith("--language=")) {
                     language = arg.substring(11).trim().toUpperCase();
                 } else if (arg.startsWith("--currency=")) {
                     currency = arg.substring(11).trim().toUpperCase();
                 } else if (arg.startsWith("--pennies=")) {
                     pennies = arg.substring(10).trim().toUpperCase();
                 }
             }
             MoneyToStr obj=new MoneyToStr(Currency.valueOf(currency), Language.valueOf(language), Pennies.valueOf(pennies));
             System.out.println(obj.convert(Double.valueOf(amount)));
             System.out.println(obj.convert(Double.valueOf(amount).longValue()));

         }
     }

     @SuppressWarnings("unused")
     public java.util.Map<String, String[]> getMessages() {
         return messages;
     }

     @SuppressWarnings("unused")
     public String getRubShortUnit() {
         return rubShortUnit;
     }

     @SuppressWarnings("unused")
     public Language getLanguage() {
         return language;
     }
 }
