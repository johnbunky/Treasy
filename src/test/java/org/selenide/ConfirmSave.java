package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 16.02.17.
 */
public class ConfirmSave {
    public TreatPage saveSingleTreatment(){
        $(By.xpath("//button[3]")).click();
        return page (TreatPage.class);
    }
}
