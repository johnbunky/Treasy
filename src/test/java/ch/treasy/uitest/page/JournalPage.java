package ch.treasy.uitest.page;

import static ch.treasy.uitest.data.DrugSource.MEIER_MUELLER;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import ch.treasy.uitest.*;
import com.codeborne.selenide.Condition;
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
                $(By.xpath(".//tr[" + (7-i) + "]/td[7]")).shouldHave(Condition.attribute(MEIER_MUELLER.getName()));
            }
        return true;
    }
}
