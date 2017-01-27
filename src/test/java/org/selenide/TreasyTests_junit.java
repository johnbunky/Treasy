package org.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
        openConfirmationLink();

        // Assert
        $(byText("Congratulations, you have successfully registered to Treasy.")).should(exist);
    }

    private void openConfirmationLink() {
        $(byText("https://treasy-tst.eu-gb.")).click();
        switchTo().window(0);
        sleep(10000);
    }

    private void openActivationEmail() {
        executeJavaScript("window.open('https://mail.google.com');");
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
