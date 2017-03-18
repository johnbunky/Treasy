package ch.treasy.uitest.page;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import ch.treasy.uitest.Duration;
import ch.treasy.uitest.Stock;
import ch.treasy.uitest.data.Packaging;

public class StockPage {

   public StockPage registerStock(Packaging packaging, Integer alternativeContent) {

      $(By.xpath("//footer")).click();
      $(By.xpath("(//input)[1]")).val(packaging.getSwissmedicNumber());
      $(byText(packaging.getFullName())).waitUntil(appears, Duration.TWENTY_SECONDS.getMillis()).click();

      if (alternativeContent != null) {
         $(By.xpath("(//input)[6]")).val(alternativeContent.toString());
      }
      $(By.xpath("//footer")).click();
      $(By.xpath("//button[2]")).click();

      return this;
   }

   public void registerStock(Stock stock) {
      registerStock(stock.getPackaging(), stock.getAlternativeContent());
   }
}
