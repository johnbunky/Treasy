package org.selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 06.02.17.
 */
public class SettingsPage {
    public ListAnimalType openListAnimalType() {
        $(byText("List animal types")).click();
        return page(ListAnimalType.class);
    }
}
