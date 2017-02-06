package org.selenide;

import static com.codeborne.selenide.Selenide.*;
import static org.selenide.TreasyTest.BASE_URL;

/**
 * Created by johnbunky on 06.02.17.
 */
public class TestBase {

    public StartPage openStartPage(){
        StartPage page = open(BASE_URL, StartPage.class);
        return page;
    }
}
