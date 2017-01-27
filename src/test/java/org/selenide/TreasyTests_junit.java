package org.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Created by johnbunky on 27.01.17.
 */
public class TreasyTests_junit {

    private String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";

    @BeforeClass
    public static void openInbox() {
        startMaximized = false;
        Configuration.browser = "chrome";
    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

    @Test
    public void registerWithActivationEmailAndConfirmationLink() {

        // Arrange
        open(BASE_URL);

        // Act
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();;

        // Assert
        $(byText("Congratulations, you have successfully registered to Treasy.")).should(exist);
    }

    private void openConfirmationLink() {
        $("a[rel=\"noreferrer\"]").click();
        switchTo().defaultContent(); // Switch to default window
        sleep(10000);
    }

    private void openActivationEmail() {
        $("body").sendKeys(Keys.CONTROL + "t"); // Open empty tab
        open("treasy.uitest@gmail.com");
        $("#Email").val("treasy.uitest@gmail.com").pressEnter();
        $("#Passwd").val("N3cqNkjF6RvN");
        $("#signIn").click();
        $(".error-msg").waitUntil(disappears, 2000);
        $(byText("One more click to Treasy!")).click();
        $("body").sendKeys(Keys.CONTROL + "\t"); // Switch to previous tab
    }

    private void inputEmailAddress() {
        $("input[type=\"email\"]").val("treasy.uitest@gmail.com");
        $(byText("Request confirmation link")).click();
    }
}
