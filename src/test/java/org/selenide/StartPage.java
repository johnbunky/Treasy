package org.selenide;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.selenide.StaticData.DATA_FORMAT;
import static org.selenide.StaticData.TEN_SECONDS;

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
      $(byText(Settings.getConfirmationLinkSearchString())).waitUntil(appears, TEN_SECONDS).click();
      switchTo().window("qikCloud - Treasy");
    }

    private void openActivationEmail() {
        executeJavaScript("window.open('https://mail.google.com');"); // Open a new tab
        switchTo().window("Gmail");
        $("#Email").val("treasy.uitest@gmail.com").pressEnter();
        $("#Passwd").val("N3cqNkjF6RvN");
        $("#signIn").click();
        $(".error-msg").waitUntil(disappears, TEN_SECONDS);
        $(byText(Settings.getEnvId() + ": Noch ein Klick bis Treasy!")).click();
    }

    private void inputEmailAddress() {
        $("input[type=\"email\"]").val("treasy.uitest+" + DATA_FORMAT + "@gmail.com");
        $(byText("Bestätigungslink anfordern")).waitUntil(appears, TEN_SECONDS).click();
    }

    private void openWelcomePage() {
        switchTo().window("Treasy");
        $(byText("Start")).waitUntil(appears, TEN_SECONDS);
    }
}
