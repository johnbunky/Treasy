package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 06.02.17.
 */
public class SettingsPage {
    public ListAnimalType openSettinsPageOption(String text) {
        $(byText(text)).click();
        return page(ListAnimalType.class);
    }

    public UpdateData openUpdateDataOption(String xpathExpression) {
        $(By.xpath(xpathExpression)).click();
        return page(UpdateData.class);
    }

    public static DrugName registerDrugNameOption(String xpathExpression) {
        $(By.xpath(xpathExpression)).click();
        return page(DrugName.class);
    }


}
