package org.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by johnbunky on 06.02.17.
 */
public class WelcomePage {

    public  SelenideElement popupRegistration(){
        switchTo().window("qikCloud - Treasy");
        return $(byText("Anmeldung erfolgreich"));
    }

    public SelenideElement successRegistration(){
        return $(byText("Sie haben sich erfolgreich für Treasy registriert.")).waitUntil(appears, 100000);
    }

    public SettingsPageOption openMenuOptionPage(String settings) {
        $(By.xpath("//*[@id='menu']")).click();
        $(byText(settings)).click();
        return page(SettingsPageOption.class);
    }
}
