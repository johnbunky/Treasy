package org.selenide;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.selenide.TreasyTest_PObjct.dateFormat;

/**
 * Created by johnbunky on 06.02.17.
 */
public class StartPage {


    public WelcomePage inputCredential() {
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();
        openWelcomePage();
        return page(WelcomePage.class);
    }

    private void openConfirmationLink() {
        $(byText("https://treasy-tst.eu-gb.")).waitUntil(appears, 5000).click();
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
        $("input[type=\"email\"]").val("treasy.uitest+" + dateFormat + "@gmail.com");
        $(byText("Bestätigungslink anfordern")).waitUntil(appears, 5000).click();
    }

    private void openWelcomePage() {
        switchTo().window("Treasy");
        $(byText("Start")).waitUntil(appears, 100000);
    }
}
