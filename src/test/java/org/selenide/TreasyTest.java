package org.selenide;

import org.junit.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Created by johnbunky on 27.01.17.
 */
public class TreasyTest {

    public static String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";

    @BeforeClass
    public static void openInbox() {
        startMaximized = false;
        browser = "chrome";
        holdBrowserOpen = true;
    }

    @After
    public void logout() {
        closeWebDriver();
    }

    @Before
    public void registerWithActivationEmailAndConfirmationLink() {

        // Arrange
        open(BASE_URL);

        // Act
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();

        // Assert
        $(byText("Congratulations, you have successfully registered to Treasy.")).should(exist);
    }

    @Test
    public void checkSettingsListAnimalType() {

        // Arrange
        open(BASE_URL + "/#/welcome");

        // Act
        openSettingPage();
        openListAnimalType();

        // Assert
        $(byText("Piglet")).should(exist);
        $(byText("Weaner")).should(exist);
        $(byText("Porker")).should(exist);
        $(byText("Sow")).should(exist);
        $(byText("Boar")).should(exist);
    }

    @Test
    public void  checkDeletingFromListAnimalType(){

        // Arrange
        open(BASE_URL + "/#/welcome");

        // Act
        openSettingPage();
        openListAnimalType();
        deletItem();

        // Assert
        $(byText("Boar")).should(not(visible));
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
        $(byText("Settings")).click();
        sleep(2000);
    }

    private void openConfirmationLink() {
        $(byText("https://treasy-tst.eu-gb.")).click();
        switchTo().window(0);
        sleep(60000);
    }

    private void openActivationEmail() {
        executeJavaScript("window.open('https://mail.google.com');"); // Open a new tab
        switchTo().window(1);
        $("#Email").val("treasy.uitest@gmail.com").pressEnter();
        $("#Passwd").val("N3cqNkjF6RvN");
        $("#signIn").click();
        $(".error-msg").waitUntil(disappears, 2000);
        $(byText("TST: One more click to Treasy!")).click();
    }

    private void inputEmailAddress() {
        $("input[type=\"email\"]").val("treasy.uitest@gmail.com");
        $(byText("Request confirmation link")).click();
    }
}
