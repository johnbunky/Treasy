package org.selenide;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by johnbunky on 23.02.17.
 */
public class StaticData {

    static long TEN_SECONDS = 10000;

    static Date date = new Date();
    static SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
    static String DATA_FORMAT =  format.format(date); // Create an unique string

    static String TVD_NUMBER = "1234567";
    static String OPERATION_NAME = "Musterhof";
    static String ADDRESS_LINE1 = "Peter Master";
    static String ADDRES_LINE2 = "Musterstrasse 1";
    static String ZIP = "1000";
    static String CITY = "Musterlingen";

    static String REASON = "Geburtshilfe";
    static String DOSE = "2";
    static String DRUG = "Dinolytic 5 mg/ml";
    static String ORIGIN = "Meier & Muller Tierarzte";

}
