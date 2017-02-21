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

    public static SettingsPage openMenuOptionPage(String settings) {
        $(By.xpath("//*[@id='menu']")).waitUntil(appears, 5000).click();
        $(byText(settings)).click();
        return page(SettingsPage.class);
    }

    public static SupplyPage openMedicalSuppliesPage(String supplies) {
        //open("https://treasy-tst.eu-gb.mybluemix.net/#/newStock");
        $(By.xpath("//*[@id='menu']")).waitUntil(appears, 5000).click();
        $(byText(supplies)).click();
        return page(SupplyPage.class);
    }

    public static JournalPage openJournalPage(String journal) {
        $(By.xpath("//*[@id='menu']")).waitUntil(appears, 5000).click();
        $(byText(journal)).click();
        return page(JournalPage.class);
    }
}
