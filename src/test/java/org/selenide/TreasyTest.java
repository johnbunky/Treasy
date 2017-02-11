package org.selenide;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by johnbunky on 27.01.17.
 */
public class TreasyTest {

    private static String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
    String Drug = "Herkunft" + format.format(date); // Create an unique string

    @BeforeClass
    public static void setup() {
        startMaximized = false;
        browser = "chrome";
        holdBrowserOpen = true;

    }

    @After
    public void logout() {
        closeWebDriver();
    }

    @Before
    public  void registration(){

        // Arrange
        open(BASE_URL);

        // Act
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();
    }

    @Test
    public void registerWithActivationEmailAndConfirmationLink() {

        // Assert
        $(byText("Anmeldung erfolgreich")).should(exist);
        switchTo().window("Treasy");
        $(byText("Sie haben sich erfolgreich für Treasy registriert.")).waitUntil(appears, 100000).should(exist);

    }

    @Test
    public void checkSettingsListAnimalType() {

        // Arrange
        openWelcomePage();

        // Act
        openMenuOptionPage("Einstellungen");
        openSettinsPageOption("Liste der Tierarten");

        // Assert
        $(byText("Mastschwein")).should(exist);
        $(byText("Jager")).should(exist);
        $(byText("Muttersau")).should(exist);
        $(byText("Eber")).should(exist);
    }

    @Test
    public void  checkDeletingFromListAnimalType(){

        // Arrange
        openWelcomePage();

        // Act
        openMenuOptionPage("Einstellungen");
        openSettinsPageOption("Liste der Tierarten");
        //checkItemExist("Ferkel");
        deletItem("Ferkel");

        // Assert
        $(byText("Ferkel")).should(not(visible));
    }

    @Test
    public void checkSettingsDrugSourceInput(){

        // Arrange
        openWelcomePage();

        // Act
        openMenuOptionPage("Einstellungen");
        openSettinsPageOption("Arzneimittelherkunft erfassen");
        inputNewDrug();
        openSettinsPageOption("Liste der Arzneimittelherkünfte");

        // Assert
        $(byText(Drug)).should(exist);
    }

    private void inputNewDrug() {
        $(By.xpath("//*[@placeholder='Name der Arzneimittelherkunft']")).val(Drug);
        $(By.xpath("//*[@class='recommend']")).click();
    }

    private void openWelcomePage() {
        switchTo().window("Treasy");
        $(byText("Start")).waitUntil(appears, 100000);
        open(BASE_URL + "/#/welcome");
    }

    private void checkItemExist(String ferkel){
        if(!$(byText(ferkel)).exists()){
            refresh();
            $(byText("Tierart erfassen")).click();
            $(By.xpath("//*[@class='down']")).click();
            $(byText(ferkel)).click();
            $(By.xpath("//*[@class='recommend']")).click();
        }
    }

    private void deletItem(String ferkel) {
        $(By.xpath("//div[child::span[text()='Ferkel']]/*/button[@class='deleteItem']")).click();
        openSettinsPageOption("Liste der Tierarten");
    }

    private void openSettinsPageOption(String elementText) {
        $(byText(elementText)).click();
    }

    private void openMenuOptionPage(String elementText) {
        $(By.xpath("//*[@id='menu']")).click();
        $(byText(elementText)).click();
    }

    private void openConfirmationLink() {
        $(byText("https://treasy-tst.eu-gb.")).click();
        switchTo().window("qikCloud - Treasy");
    }

    private void openActivationEmail() {
        executeJavaScript("window.open('https://mail.google.com');"); // Open a new tab
        switchTo().window("Gmail");
        $("#Email").val("treasy.uitest@gmail.com").pressEnter();
        $("#Passwd").val("N3cqNkjF6RvN");
        $("#signIn").click();
        $(".error-msg").waitUntil(disappears, 2000);
        $(byText("TST: Noch ein Klick bis Treasy!")).click();
    }

    private void inputEmailAddress() {
        $("input[type=\"email\"]").val("treasy.uitest@gmail.com");
        $(byText("Bestätigungslink anfordern")).click();
    }
}
