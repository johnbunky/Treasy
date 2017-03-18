package ch.treasy.uitest.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import ch.treasy.uitest.data.DrugSource;
import ch.treasy.uitest.data.Farm;

public class SettingsPage {

   public SettingsPage inputFarm(Farm farm) {
      $(By.xpath("//*[section[@class='settings']][4]")).click();
      $(By.xpath(".//input[@ng-if][1]")).val(farm.getTvdNumber());
      $(By.xpath(".//input[@ng-if][2]")).val(farm.getName());
      $(By.xpath(".//input[@ng-if][3]")).val(farm.getAddress1());
      $(By.xpath(".//input[@ng-if][4]")).val(farm.getAddress2());
      $(By.xpath(".//input[@ng-if][5]")).val(farm.getZip());
      $(By.xpath(".//input[@ng-if][6]")).val(farm.getCity());
      $(By.xpath("(//*[@class='recommend'])[2]")).click();
      return this;
   }

   public SettingsPage inputDrugSource(DrugSource source) {
      $(By.xpath("//*[section[@class='settings']][9]")).click();
      $(By.xpath(".//input[@class='ng-pristine ng-untouched ng-valid']")).val(source.getName());
      $(By.xpath("(//*[@class='recommend'])[1]")).click();
      return this;
   }

}
