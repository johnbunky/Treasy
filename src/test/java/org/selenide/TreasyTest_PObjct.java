package org.selenide;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.selenide.StaticData.ADDRESS_LINE1;
import static org.selenide.StaticData.ADDRES_LINE2;
import static org.selenide.StaticData.CITY;
import static org.selenide.StaticData.DATA_FORMAT;
import static org.selenide.StaticData.DOSE;
import static org.selenide.StaticData.DRUG;
import static org.selenide.StaticData.OPERATION_NAME;
import static org.selenide.StaticData.ORIGIN;
import static org.selenide.StaticData.REASON;
import static org.selenide.StaticData.TVD_NUMBER;
import static org.selenide.StaticData.ZIP;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.ElementsCollection;

/**
 * Created by johnbunky on 15.02.17.
 */
public class TreasyTest_PObjct extends StartPage{

    StartPage startPage = open(Settings.getUrl(), StartPage.class);
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
        WelcomePage.popupRegistration().should(exist);
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
    public void deleteListAnimalItemTest(){

        // Arrange
        welcomePage

                // Act
                .openMenuOptionPage("Einstellungen")
                .openSettinsPageOption("Liste der Tierarten")
                .collectionListAnimalTypes().shouldHave(size(5));
                ListAnimalType.deleteItem();
                SettingsPage.openSettinsPageOption("Liste der Tierarten")

                        // Assert
                        .collectionListAnimalTypes().shouldHave(size(4));
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

        String treatmentRowPath = "//*[@id='content']/ng-view/section/table/tbody/tr[2]";
        
        JournalPage.tableColumn(treatmentRowPath + "/td[2]").shouldHave(text("" + DATA_FORMAT + " ABC "));
        JournalPage.tableColumn(treatmentRowPath + "/td[3]/ul/li").shouldHave(text(REASON));
        JournalPage.tableColumn(treatmentRowPath + "/td[4]").shouldHave(text(" " + DRUG + " "));
        JournalPage.tableColumn(treatmentRowPath + "/td[5]").shouldHave(text(" " + DOSE + " ml "));
        JournalPage.tableColumn(treatmentRowPath + "/td[7]").shouldHave(text(" " + ORIGIN + " "));
    }
}
