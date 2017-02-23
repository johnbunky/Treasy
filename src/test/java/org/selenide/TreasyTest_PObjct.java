package org.selenide;

import com.codeborne.selenide.ElementsCollection;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.selenide.StaticData.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
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
        //startMaximized = false;
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
    public void addTreatmentTest(){

        // Arrange
        welcomePage

                // Act
                .openMenuOptionPage("Einstellungen")
                .openUpdateDataOption("//*[section[@class='settings']][4]")
                .intutData();
                SettingsPage.registerDrugNameOption("//*[section[@class='settings']][9]")
                .inputDrugName();
                WelcomePage.openMedicalSuppliesPage("Vorrat")
                .openInputSupplyPage()
                .inputSupply()
                .saveSingleTreatment()
                .addTreatment();
                WelcomePage.openJournalPage("Journal");

        // Assert
        JournalPage.tableColumn("//h2[3]").shouldHave(text(TVD_NUMBER + ", " + OPERATION_NAME +
                ", " + ADDRESS_LINE1 + ", " + ADDRES_LINE2 + ", " + ZIP + " " + CITY));
        JournalPage.tableColumn("(//td)[9]").shouldHave(text("" + DATA_FORMAT + " ABC "));
        JournalPage.tableColumn("(//ul/li)[8]").shouldHave(text(REASON));
        JournalPage.tableColumn("(//td)[11]").shouldHave(text(" " + DRUG + " "));
        JournalPage.tableColumn("(//td)[12]").shouldHave(text(" " + DOSE + " ml "));
        JournalPage.tableColumn("(//td)[16]").shouldHave(text(" " + ORIGIN + " "));
    }
}
