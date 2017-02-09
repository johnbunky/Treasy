package ch.treasy;

import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public abstract class AbstractTest {

   private static final long FIFE_MINUTES = 300000;
   
   private static final long THIRTY_SECONDS = 30000;
   
   private static final long FIFTEEN_SECONDS = 15000;

   private static final long FIVE_SECONDS = 5000;

   private static final long TWO_SECONDS = 2000;

   public static String BASE_URL = "https://treasy-tst.eu-gb.mybluemix.net";

   private static final String EMAIL_ADDRESS = "treasy.uitest@gmail.com";

   private static final String EMAIL_PASSWORD = "N3cqNkjF6RvN";

   @BeforeClass
   public static void setup() {

      startMaximized = false;
      browser = "chrome";
      holdBrowserOpen = true;

      // Arrange
      open(BASE_URL);

      // Wait for UI to load the dynamic parts
      sleep(FIVE_SECONDS);

      // Request confirmation email
      $("input[type=\"email\"]").val(EMAIL_ADDRESS);
      $(byText("Bestätigungslink anfordern")).click();

      // Wait for activation email to arrive
      sleep(TWO_SECONDS);

      // Confirm registration
      executeJavaScript("window.open('https://mail.google.com');"); // Open a new tab
      switchTo().window("Gmail");
      $("#Email").val(EMAIL_ADDRESS).pressEnter();
      $("#Passwd").val(EMAIL_PASSWORD);
      $("#signIn").click();
      $(".error-msg").waitUntil(disappears, FIFTEEN_SECONDS);
      $(byText("TST: Noch ein Klick bis Treasy!")).click();
      $(byText("https://treasy-tst.eu-gb.")).click();

      // Check if registration was successful
      switchTo().window("qikCloud - Treasy");
      $(byText("Anmeldung erfolgreich")).should(exist);

      // Navigate to settings
      switchTo().window("Treasy");
      if ($(byText("Schliessen")).isDisplayed()) {
         $(byText("Schliessen")).click();
      }
      $(byText("Start")).waitUntil(exist, FIFE_MINUTES);
      $(byText("Start")).click();

   }

   @AfterClass
   public static void teardown() {
      closeWebDriver();
   }
}
