package ch.treasy.uitest.page;

import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by johnbunky on 27.03.17.
 */
public class JournalEntryPage {

    public static void changePackaging(Packaging newPackaging){
        openEditPage();
        String drugName = newPackaging.getDrugName();
        addPackaging(drugName);
        addChanges();

        $(By.xpath("(.//table[1]//td[2])[4]")).shouldHave(text(drugName)); //check changes on a JournalEntryPage
    }

    public static void changeDose(Integer dose){
        openEditPage();
        addDose(dose);
        addChanges();
        $(By.xpath("(.//table[1]//td[2])[3]")).shouldHave(text(dose.toString())); //check changes on a JournalEntryPage
    }

    public static void changeReasons(Reason[] newReasons){
        openEditPage();
        addReason(newReasons, 0);
        if(newReasons.length > 1){
            for(int j = 1; j <= newReasons.length; j++ ){
                $(By.xpath(".//*[@class='input plus']")).click();
                addReason(newReasons, j);
            }
        }
        addChanges();
        $(By.xpath("(.//table[1]//td[2])[6]")).shouldHave(text(newReasons.toString())); //check changes on a JournalEntryPage
    }

    public static void changeAnimal(SimpleAnimal newAnimal){
        openEditPage();
        addAnimal(newAnimal);
        addChanges();
        $(By.xpath("(.//table[1]//td[2])[1]")).shouldHave(text(newAnimal.getName())); //check changes on a JournalEntryPage
    }

    public static void changeAll(int toChange, Packaging newPackaging, int newDose, SimpleAnimal newAnimal, Reason... newReason) {
        openEditPage();
        String drugName = newPackaging.getDrugName();
        addPackaging(drugName);
        addDose(newDose);
        addReason(newReason, 0);
        addAnimal(newAnimal);
        addChanges();
    }

    private static void addPackaging(String drugName) {
        $(By.xpath("(//input)[2]")).val(drugName.substring(0, 5));
        $(byText(drugName)).should(appear).click();
    }

    private static void addDose(Integer dose) {
        $(By.xpath("(//input)[4]")).val(dose.toString());
    }

    private static void addReason(Reason[] reasons, int j){
        String reasonName = reasons[j].getName();
        $(By.xpath("(//input)[" + (5 + j*2) + "]")).val(reasonName.substring(0, 3));
        $(byText(reasonName)).should(appear).click();
    }

    private static void addAnimal(SimpleAnimal newAnimal) {
        $(By.xpath("(//input)[7]")).clear();
        $(By.xpath("(//input)[7]")).val(newAnimal.getName());
    }

    private static void addChanges() {
        $(By.xpath("//footer")).click();
        $(By.xpath("(//button[2])[2]")).click();
    }

    private static void openEditPage() {
        $(By.xpath(".//footer")).click();
    }


}
