package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 16.02.17.
 */
public class RegisterSupply {
    public NewSupply openInputSupplyPage(){
        $(By.xpath("//footer")).click();
        return page(NewSupply.class);
    }
}
