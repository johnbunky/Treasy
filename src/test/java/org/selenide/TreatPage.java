package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 16.02.17.
 */
public class TreatPage {
    public void addTreatment(){
        $(By.xpath("(//input)[3]")).val("2");
        $(By.xpath("(//input)[4]")).val("Geb");
        $(By.xpath("(//span)[2]")).waitUntil(appear, 5000).click();
        $(By.xpath("(//input)[6]")).val("1234 ABC");
        $(By.xpath("(//input)[7]")).click();
        $(By.xpath("(//span)[7]")).waitUntil(appear, 5000).click();
        $(By.xpath("//footer")).click();
        $(By.xpath("(//button[2])[2]")).click();
        //return page(TreatPage.class);
    }
}
