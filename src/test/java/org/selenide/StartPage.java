package org.selenide;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Created by johnbunky on 06.02.17.
 */
public class StartPage {

    public WelcomePage inputCredential() {
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();
        return page(WelcomePage.class);
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
