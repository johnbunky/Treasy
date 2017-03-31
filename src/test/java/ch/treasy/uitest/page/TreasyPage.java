package ch.treasy.uitest.page;

//import static ch.treasy.uitest.page.JournalPage.openJournalEntryPage;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import java.util.ArrayList;
import java.util.List;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;

import ch.treasy.uitest.Duration;
import ch.treasy.uitest.Settings;
import ch.treasy.uitest.Stock;
import ch.treasy.uitest.Treatment;
import ch.treasy.uitest.data.DrugSource;
import ch.treasy.uitest.data.Farm;
import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;
import ch.treasy.uitest.data.User;

public class TreasyPage {

   public TreasyPage register(User user) {
      Selenide.open(Settings.getServerUrl());
      inputEmailAddress(user);
      openActivationEmail(user);
      openConfirmationLink();
      openWelcomePage();
      return this;
   }

   private void openConfirmationLink() {
      $(byText(Settings.getConfirmationLinkSearchString())).waitUntil(appears, Duration.TEN_SECONDS.getMillis()).click();
      switchTo().window("qikCloud - Treasy");
   }

   private void openActivationEmail(User user) {
      executeJavaScript("window.open('https://mail.google.com');"); // Open a new tab
      switchTo().window("Gmail");
      $("#Email").val(user.getMailAddress()).pressEnter();
      $("#Passwd").val(user.getMailPassword());
      $("#signIn").click();
      $(".error-msg").waitUntil(disappears, Duration.TEN_SECONDS.getMillis());
      $(byText(Settings.getEnvId() + ": Noch ein Klick bis Treasy!")).click();
   }

   private void inputEmailAddress(User user) {
      $("input[type=\"email\"]").val(user.getNewMailAddress());
      $(byText("Bestätigungslink anfordern")).waitUntil(appears, Duration.TEN_SECONDS.getMillis()).click();
   }

   private void openWelcomePage() {
      switchTo().window("Treasy");
      $(byText("Start")).waitUntil(appears, Duration.TEN_SECONDS.getMillis());
   }

   public TreasyPage treat(Treatment treatment) {
      openTreatmentPage().inputTreatment(treatment);
      return this;
   }

   public TreasyPage registerStock(Stock stock) {
      openStockPage().registerStock(stock);
      return this;
   }

   public TreasyPage baseData(Farm farm, DrugSource source) {
      openSettingsPage().inputFarm(farm).inputDrugSource(source);
      return this;
   }

   private StockPage openStockPage() {
      $(By.xpath("//*[@id='menu']")).waitUntil(appears, Duration.TEN_SECONDS.getMillis()).click();
      $(byText("Vorrat")).click();
      return page(StockPage.class);
   }

   private SettingsPage openSettingsPage() {
      $(By.xpath("//*[@id='menu']")).waitUntil(appears, Duration.TEN_SECONDS.getMillis()).click();
      $(byText("Einstellungen")).click();
      return page(SettingsPage.class);
   }

   private TreatmentPage openTreatmentPage() {
      $(By.xpath("//*[@id='menu']")).waitUntil(appears, Duration.TEN_SECONDS.getMillis()).click();
      $(byText("Behandeln")).click();
      return page(TreatmentPage.class);
   }

   private JournalPage openJournalPage() {
      $(By.xpath("//*[@id='menu']")).waitUntil(appears, Duration.TEN_SECONDS.getMillis()).click();
      $(byText("Journal")).click();
      return page(JournalPage.class);
   }

   public void treat(List<Treatment> treatments) {
      for (Treatment treatment : treatments) {
         treat(treatment);
      }
   }

   public void registerStock(List<Stock> stockList) {
      for (Stock stock : stockList) {
         registerStock(stock);
      }
   }

   /**
    * Check if treatments exist in journal and in treatment details
    * 
    * @param treatments
    *           The treatments to check
    * @return true of the treatments in the journal and details are Ok.
    */
   public boolean check(List<Treatment> treatments) {

      return  openJournalPage().checkElements(treatments);

   }

   /**
    * Change the packaging of the treatment in the specified position.
    * 
    * @param treatments
    *           The actual treatments
    * @param toChange
    *           The position to change.
    * @param newPackaging
    *           The new packaging.
    * @return the new treatments.
    */
   public SelenideElement change(List<Treatment> treatments, int toChange, Packaging newPackaging) {

                JournalPage.openJournalEntryPage(toChange);
                JournalEntryPage.changePackaging(newPackaging);

      return openJournalPage().checkNewPackaging();
   }

   /**
    * Change the packaging of the treatment in the specified position.
    * 
    * @param treatments
    *           The actual treatments
    * @param toChange
    *           The position to change.
    * @param newDose
    *           The new dose.
    * @return the new treatments.
    */
   public SelenideElement change(List<Treatment> treatments, int toChange, int newDose) {

      JournalPage.openJournalEntryPage(toChange);
      JournalEntryPage.changeDose(newDose);

      return openJournalPage().checkNewDose();
   }

   /**
    * Change the packaging of the treatment in the specified position.
    * 
    * @param treatments
    *           The actual treatments
    * @param toChange
    *           The position to change.
    * @param newReasons
    *           The new reasons.
    * @return the new treatments.
    */
   public SelenideElement change(List<Treatment> treatments, int toChange, Reason... newReasons) {

         JournalPage.openJournalEntryPage(toChange);
         JournalEntryPage.changeReasons(newReasons);

      return openJournalPage().checkNewReason();
   }

   /**
    * Change the animal of the treatment in the specified position.
    * 
    * @param treatments
    *           The actual treatments
    * @param toChange
    *           The position to change.
    * @param newAnimal
    *           The new animal.
    * @return the new treatments.
    */
   public SelenideElement change(List<Treatment> treatments, int toChange, SimpleAnimal newAnimal) {

      JournalPage.openJournalEntryPage(toChange);
      JournalEntryPage.changeAnimal(newAnimal);

      return openJournalPage().checkNewAnimal();
   }

   /**
    * Change the treatment in the specified position.
    * 
    * @param treatments
    *           The actual treatments
    * @param toChange
    *           The position to change.
    * @param newPackaging
    *           The new packaging.
    * @param newDose
    *           The new dose.
    * @param newAnimal
    *           The new animal.
    * @param newReason
    *           The new reason.
    * @return the new treatments.
    */
   public boolean change(List<Treatment> treatments,
                         int toChange, Packaging newPackaging, int newDose, SimpleAnimal newAnimal, Reason newReason) {

      JournalPage.openJournalEntryPage(toChange);
      JournalEntryPage.changeAll(toChange, newPackaging, newDose, newAnimal, newReason);
      List<Treatment> testTreatments = new ArrayList<Treatment>();
      testTreatments.add(new Treatment(newPackaging, newDose, newReason, newAnimal));

      return openJournalPage().checkElements(testTreatments);
   }
}
