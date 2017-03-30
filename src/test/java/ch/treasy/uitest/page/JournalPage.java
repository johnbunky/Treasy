package ch.treasy.uitest.page;

import static ch.treasy.uitest.data.DrugSource.MEIER_MUELLER;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.navigator;
import static com.codeborne.selenide.Selenide.page;

import ch.treasy.uitest.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;


/**
 * Created by johnbunky on 25.03.17.
 */
public class JournalPage {
    public boolean checkElements(List<Treatment> treatments){
            for( int i = treatments.size()-1; i <= 0; i--  ){
                $(By.xpath(".//tr[" + (7-i) + "]/td[2]")).shouldHave(text(treatments.get(i).getSimpleAnimal().getName()));
                $(By.xpath(".//tr[" + (7-i) + "]/td[3]")).shouldHave(text(String.valueOf(treatments.get(i).getReasons().get(0).getName())));
                $(By.xpath(".//tr[" + (7-i) + "]/td[4]")).shouldHave(text(treatments.get(i).getPackaging().getDrugName()));
                $(By.xpath(".//tr[" + (7-i) + "]/td[5]")).shouldHave(text(String.valueOf(treatments.get(i).getDose())));
                $(By.xpath(".//tr[" + (7-i) + "]/td[7]")).shouldHave(Condition.attribute(MEIER_MUELLER.getName())).click();
                checkDetails(treatments, i); // checking treatment details
            }
        return true;
    }

    private  void checkDetails(List<Treatment> treatments, int i){
        $(By.xpath("(.//table[1]//td[2])[1]")).shouldHave(text(treatments.get(i).getSimpleAnimal().getName()));
        $(By.xpath("(.//table[1]//td[2])[3]")).shouldHave(text(String.valueOf(treatments.get(i).getDose())));
        $(By.xpath("(.//table[1]//td[2])[4]")).shouldHave(text(treatments.get(i).getPackaging().getDrugName()));
        //$(By.xpath("(.//table[1]//td[2])[5]")).shouldHave(Condition.attribute(MEIER_MUELLER.getName())); // Space before and after text
        $(By.xpath("(.//table[1]//td[2])[6]")).shouldHave(text(String.valueOf(treatments.get(i).getReasons().get(0).getName())));
        navigator.back();
    }

    public SelenideElement checkNewPackaging(int toChange){
        return $(By.xpath(".//tr[2]/td[4]"));
    }

    public SelenideElement checkNewDose(int toChange) {
        return $(By.xpath(".//tr[2]/td[5]"));
    }

    public SelenideElement checkNewReason(){
        return $(By.xpath(".//tr[2]/td[3]"));
    }
}
