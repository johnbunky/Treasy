package org.selenide;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
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

    private static final long FIVE_SECONDS = 5000;

   private static final long TWO_SECONDS = 2000;

    public static String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";

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
        
        // Wait for UI to load the dynamic parts
        sleep(FIVE_SECONDS);

        // Act
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();
    }

    @Test
    public void registerWithActivationEmailAndConfirmationLink() {

        // Assert
       switchTo().window("qikCloud - Treasy");
        $(byText("Anmeldung erfolgreich")).should(exist);
    }

    @Test
    public void checkSettingsListAnimalType() {

       switchTo().window("Treasy");

        // Act
        openSettingPage();
        openListAnimalType();

        // Assert
        $(byText("Piglet")).should(exist);
        $(byText("Weaner")).should(exist);
        $(byText("Porker")).should(exist);
        $(byText("Sow")).should(exist);
    }

    @Test
    public void  checkDeletingFromListAnimalType(){

       switchTo().window("Treasy");

        // Act
        openSettingPage();
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
        $(byText("List animal types")).click();
    }

    private void openSettingPage() {
        $(By.xpath("//*[@id='menu']")).click();
        $(byText("Einstellungen")).click();
        sleep(2000);
    }

    private void openConfirmationLink() {
        $(byText("https://treasy-tst.eu-gb.")).click();
        switchTo().window("Treasy");
        $(byText("Schliessen")).click();
        $(byText("Start")).click();
    }

    private void openActivationEmail() {
       // Wait for activation email to arrive
       sleep(FIVE_SECONDS);
       
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
