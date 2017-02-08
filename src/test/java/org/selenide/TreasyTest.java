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

/**
 * Created by johnbunky on 27.01.17.
 */
public class TreasyTest {

    private static String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";

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
    }

    @Test
    public void checkSettingsListAnimalType() {

        // Arrange
        openWelcomePage();

        // Act
        openMenuOptionPage("Einstellungen");
        openListAnimalType();

        // Assert
        $(byText("Mastschwein")).should(exist);
        $(byText("Jager")).should(exist);
        $(byText("Muttersau")).should(exist);
        $(byText("Eber")).should(exist);
    }

    private void openWelcomePage() {
        switchTo().window("Treasy");
        $(byText("Start")).waitUntil(appears, 100000);
        open(BASE_URL + "/#/welcome");
    }

    @Test
    public void  checkDeletingFromListAnimalType(){

        // Arrange
        switchTo().window("Treasy");

        // Act
        openMenuOptionPage("Einstellungen");
        openListAnimalType();
        checkItemExist();
        deletItem();

        // Assert
        $(byText("Boar")).should(not(visible));
    }

    private void checkItemExist(){
        if(!$(byText("Boar")).exists()){
            refresh();
            $(byText("Register animal type")).click();
            $(By.xpath("//*[@class='down']")).click();
            $(byText("Boar")).click();
            $(byText("Save")).click();
        }
    }

    private void deletItem() {
        $(byText("Boar")).$(By.xpath("//*[@class='deleteItem']")).click();
        openListAnimalType();
    }

    private void openListAnimalType() {
        $(byText("Liste der Tierarten")).click();
    }

    private void openMenuOptionPage(String einstellungen) {
        $(By.xpath("//*[@id='menu']")).click();
        $(byText(einstellungen)).click();
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
