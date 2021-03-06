package ch.treasy.uitest.page;

import static ch.treasy.uitest.data.DrugSource.MEIER_MUELLER;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.navigator;

import ch.treasy.uitest.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

class JournalPage {
     void checkElements(List<Treatment> treatments){
            for( int i = treatments.size()-1; i >= 0; i--  ){
                $(By.xpath(".//tr[" + (7-i) + "]/td[2]")).shouldHave(text(treatments.get(i).getSimpleAnimal().getName()));
                $(By.xpath(".//tr[" + (7-i) + "]/td[3]")).shouldHave(text(String.valueOf(treatments.get(i).getReasons().get(0).getName())));
                $(By.xpath(".//tr[" + (7-i) + "]/td[4]")).shouldHave(text(treatments.get(i).getPackaging().getDrugName()));
                $(By.xpath(".//tr[" + (7-i) + "]/td[5]")).shouldHave(text(String.valueOf(treatments.get(i).getDose())));
                $(By.xpath(".//tr[" + (7-i) + "]/td[7]")).shouldHave(text(MEIER_MUELLER.getName())).click();
                checkDetails(treatments, i); // Checking treatment details
            }
    }

    private  void checkDetails(List<Treatment> treatments, int i){
        $(By.xpath("(.//td[2])[1]")).shouldHave(text(treatments.get(i).getSimpleAnimal().getName()));
        $(By.xpath("(.//td[2])[3]")).shouldHave(text(String.valueOf(treatments.get(i).getDose())));
        $(By.xpath("(.//td[2])[4]")).shouldHave(text(treatments.get(i).getPackaging().getDrugName()));
        $(By.xpath("(.//td[2])[5]")).shouldHave(text(MEIER_MUELLER.getName()));
        $(By.xpath("(.//td[2])[6]")).shouldHave(text(String.valueOf(treatments.get(i).getReasons().get(0).getName())));
        navigator.back();
    }

    static void openJournalEntryPage(int toChange){
        $(By.xpath(".//tr[" + (toChange + 2) +"]/td[2]")).click();
    }


    SelenideElement checkNewPackaging(){
        return $(By.xpath(".//tr[2]/td[4]"));
    }

    SelenideElement checkNewDose() {
        return $(By.xpath(".//tr[2]/td[5]"));
    }

    SelenideElement checkNewReason(){
        return $(By.xpath(".//tr[2]/td[3]"));
    }

    SelenideElement checkNewAnimal() { return $(By.xpath(".//tr[2]/td[2]"));
    }
}
