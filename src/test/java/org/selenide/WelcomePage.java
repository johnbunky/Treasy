package org.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static org.selenide.StaticData.*;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by johnbunky on 06.02.17.
 */
public class WelcomePage {

    public static SelenideElement popupRegistration(){
        switchTo().window("qikCloud - Treasy");
        return $(byText("Anmeldung erfolgreich"));
    }

    public SelenideElement successRegistration(){
        return $(byText("Sie haben sich erfolgreich für Treasy registriert.")).waitUntil(appears, TEN_SECONDS);
    }

    public static SettingsPage openMenuOptionPage(String settings) {
        $(By.xpath("//*[@id='menu']")).waitUntil(appears, TEN_SECONDS).click();
        $(byText(settings)).click();
        return page(SettingsPage.class);
    }

    public static SupplyPage openMedicalSuppliesPage(String supplies) {
        $(By.xpath("//*[@id='menu']")).waitUntil(appears, TEN_SECONDS).click();
        $(byText(supplies)).click();
        return page(SupplyPage.class);
    }

    public static JournalPage openJournalPage(String journal) {
        $(By.xpath("//*[@id='menu']")).waitUntil(appears, TEN_SECONDS).click();
        $(byText(journal)).click();
        return page(JournalPage.class);
    }
}
