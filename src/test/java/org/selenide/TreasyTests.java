package org.selenide;
import com.codeborne.selenide.SelenideElement;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.junit.Before;
import org.openqa.selenium.By;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


/**
 * Created by johnbunky on 19.01.17.
 */
public class TreasyTests {

    @Test
    public void registerWithActivationEmailAndConfirmationLink(){

        open("https://treasy-tst.eu-gb.mybluemix.net/#/start");
        inputEmailAddress();
        openActivationEmail();
        $(withText("https://treasy-tst.eu-gb.mybluemix.net/v1/user/confirm/")).shouldBe();
    }

    private void openActivationEmail() {
        open("treasy.uitest@gmail.com");
        $("#Email").val("treasy.uitest@gmail.com").pressEnter();
        $("#Passwd").val("N3cqNkjF6RvN");
        $("#signIn").click();
        $(".error-msg").waitUntil(disappears, 2000);
        $(byText("One more click to Treasy!")).click();
    }

    private void inputEmailAddress() {
        $("input[type=\"email\"]").setValue("treasy.uitest@gmail.com");
        $(byText("Request confirmation link")).click();
    }

}
