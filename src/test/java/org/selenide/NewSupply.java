package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 16.02.17.
 */
public class NewSupply {
    public RegisterSupply inputSupply(){
        $(By.xpath("(//input)[1]")).val("40063.021");
        $(byText("Dinolytic 5 mg/ml (50 ml, 40063.021)")).waitUntil(appears, 5000).click();
        $(By.xpath("(//input)[6]")).val("50");
        $(By.xpath("//footer")).click();
        return page (RegisterSupply.class);
    }
}
