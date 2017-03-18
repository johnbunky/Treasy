package ch.treasy.uitest.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;

import ch.treasy.uitest.Treatment;
import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;

public class TreatmentPage {

   public void inputTreatment(Treatment treatment) {
      inputTreatment(treatment.getPackaging(), treatment.getDose(), treatment.getReason(), treatment.getSimpleAnimal());
   }
   
   public void inputTreatment(Packaging packaging, Integer dose, Reason reason, SimpleAnimal simpleAnimal) {

      String drugName = packaging.getDrugName();
      String reasonName = reason.getName();

      $(By.xpath("(//input)[1]")).val(drugName.substring(0, 5));
      $(byText(drugName)).should(appear).click(); // Select reason

      $(By.xpath("(//input)[3]")).val(dose.toString());

      $(By.xpath("(//input)[4]")).val(reasonName.substring(0, 3));
      $(byText(reasonName)).should(appear).click(); // Select reason
      
      $(By.xpath("(//input)[6]")).val(simpleAnimal.getName());
      $(By.xpath("(//input)[7]")).click();
      // Add a little delay otherwise the animal type will randomly not be set 
      Selenide.sleep(100);
      $(byText(simpleAnimal.getType().getName())).should(appear).click(); // Select animal type

      $(By.xpath("//footer")).click();
      $(By.xpath("(//button[2])[2]")).click();
   }
}
