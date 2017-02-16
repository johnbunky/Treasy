package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 15.02.17.
 */
public class DrugName {
    public WelcomePage inputName(){
        $(By.xpath(".//input[@class='ng-pristine ng-untouched ng-valid']")).val("Meier & Muller Tierarzte");
        $(By.xpath("(//*[@class='recommend'])[1]")).click();
        return page (WelcomePage.class);
    }
}
