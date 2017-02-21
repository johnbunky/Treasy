package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 15.02.17.
 */
public class UpdateData {
    public void intutData(){
        $(By.xpath(".//input[@ng-if][1]")).val("1234567");
        $(By.xpath(".//input[@ng-if][2]")).val("Musterhof");
        $(By.xpath(".//input[@ng-if][3]")).val("Peter Master");
        $(By.xpath(".//input[@ng-if][4]")).val("Musterstrasse 1");
        $(By.xpath(".//input[@ng-if][5]")).val("1000");
        $(By.xpath(".//input[@ng-if][6]")).val("Musterlingen");
        $(By.xpath("(//*[@class='recommend'])[2]")).click();
    }
}
