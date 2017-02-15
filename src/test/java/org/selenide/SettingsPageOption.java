package org.selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by johnbunky on 06.02.17.
 */
public class SettingsPageOption {
    public ListAnimalType openSettinsPageOption(String text) {
        $(byText(text)).click();
        return page(ListAnimalType.class);
    }
}
