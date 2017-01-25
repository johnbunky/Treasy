package org.selenide;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



/**
 * Created by johnbunky on 19.01.17.
 */
public class TreasyTests {

    private String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net/#/start";

    @BeforeClass
    public static void openInbox() {

        startMaximized = false;

    }

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

    @Test
    public void registerWithActivationEmailAndConfirmationLink(){

        //arrange
        open(BASE_URL);
        //act
        inputEmailAddress();
        openActivationEmail();
        openConfirmationLink();
        switchTo().defaultContent();
        open(BASE_URL);
        //assert
        $("input[placeholder=\"Select drug\"]").shouldBe();
        $("*[placeholder=\"Dose\"]").shouldBe();
        $("input[placeholder=\"Animal name\"]").shouldBe();
        $("input[placeholder=\"Select animal type\"]").shouldBe();
        $(byText("Save")).shouldBe();

    }

    private void openConfirmationLink() {
        $("a[rel=\"noreferrer\"]").click();
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
        $("input[type=\"email\"]").val("treasy.uitest@gmail.com");
        $(byText("Request confirmation link")).click();
    }

}
