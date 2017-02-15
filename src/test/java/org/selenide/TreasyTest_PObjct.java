package org.selenide;

import com.codeborne.selenide.ElementsCollection;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Created by johnbunky on 15.02.17.
 */
public class TreasyTest_PObjct extends StartPage{

    StartPage startPage = open("https://treasy-tst.eu-gb.mybluemix.net", StartPage.class);
    WelcomePage welcomePage = startPage.inputCredential();

    @BeforeClass
    public static void setup(){
        startMaximized = false;
        browser = "chrome";
        holdBrowserOpen = true;
    }

    @After
    public  void logout(){
        closeWebDriver();
    }

    @Test
    public void registerWithActivationEmailAndConfirmationLinkTest() {

        // Assert
        welcomePage.successRegistration().should(exist);
    }

    @Test
    public void settingsListAnimalTypeTest(){

        // Arrange
        ElementsCollection collectionListAnimalTypes = welcomePage

                // Act
                .openMenuOptionPage("Einstellungen")
                .openSettinsPageOption("Liste der Tierarten")
                .collectionListAnimalTypes();

        // Assert
        collectionListAnimalTypes.findBy(text("Ferkel")).should(exist);
        collectionListAnimalTypes.findBy(text("Muttersau")).should(exist);
        collectionListAnimalTypes.findBy(text("Jager")).should(exist);
        collectionListAnimalTypes.findBy(text("Mastschwein")).should(exist);
    }

    @Test
    public void checkGroupAddTreatmentTest(){

        // Arrange
        welcomePage

                // Act
                .openMenuOptionPage("Einstellungen")
                .openUpdateDataOption("//*[section[@class='settings']][4]")
                .intutData();
    }
}
