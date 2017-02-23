package org.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by johnbunky on 21.02.17.
 */
public class JournalPage {
    public static  SelenideElement tableColumn(String xpathExpression){
        return  $(By.xpath(xpathExpression));
    }
}
