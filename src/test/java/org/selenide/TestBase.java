package org.selenide;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by johnbunky on 06.02.17.
 */
public class TestBase {

    public StartPage openStartPage(){
        StartPage page = open("https://treasy-tst.eu-gb.mybluemix.net", StartPage.class);
        return page;
    }
}
