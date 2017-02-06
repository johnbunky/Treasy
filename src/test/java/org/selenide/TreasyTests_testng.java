package org.selenide;

import com.codeborne.selenide.ElementsCollection;
import  org.testng.annotations.*;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


/**
 * Created by johnbunky on 19.01.17.
 */
public class TreasyTests_testng extends TestBase{

    //private  String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";
    @BeforeClass
    public  void setup(){
        startMaximized = false;
        holdBrowserOpen = true;
        browser = "chrome";
    }

    @AfterClass
    public static void logout(){
        closeWebDriver();
    }

    @Test
    public void registerWithActivationEmailAndConfirmationLink() {
        openStartPage().inputCredential()

        //Assertj
                .successRegistration().should(exist);
    }

    @Test
    public void checkSettingsListAnimalType(){
         ElementsCollection listAnimalType = openStartPage()
                 .inputCredential()
                 .openSettingPage()
                 .openListAnimalType()
                 .listAnimalType();

        // Assert
        listAnimalType.findBy(text("Piglet")).should(exist);
        listAnimalType.findBy(text("Weaner")).should(exist);
        listAnimalType.findBy(text("Porker")).should(exist);
        listAnimalType.findBy(text("Sow")).should(exist);
    }
}
