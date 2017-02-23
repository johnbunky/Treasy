package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.selenide.StaticData.*;

/**
 * Created by johnbunky on 16.02.17.
 */
public class TreatPage {
    public void addTreatment(){
        $(By.xpath("(//input)[3]")).val("2");
        $(By.xpath("(//input)[4]")).val("Geb");
        $(byText(REASON)).waitUntil(appear, TEN_SECONDS).click();  // Select reason
        $(By.xpath("(//input)[6]")).val(DATA_FORMAT + " ABC");
        $(By.xpath("(//input)[7]")).click();
        $(By.xpath("(//div)[18]")).waitUntil(appear, TEN_SECONDS).click(); // Select animal type
        $(By.xpath("//footer")).click();
        $(By.xpath("(//button[2])[2]")).click();
    }
}
