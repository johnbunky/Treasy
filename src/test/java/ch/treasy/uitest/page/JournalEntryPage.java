package ch.treasy.uitest.page;

import ch.treasy.uitest.data.Packaging;
import ch.treasy.uitest.data.Reason;
import ch.treasy.uitest.data.SimpleAnimal;
import com.codeborne.selenide.Selenide;
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
        $(By.xpath("(.//td[2])[4]")).shouldHave(text(drugName)); // Check changes on a JournalEntryPage
    }

    public static void changeDose(Integer dose){
        openEditPage();
        addDose(dose);
        addChanges();
        $(By.xpath("(.//td[2])[3]")).shouldHave(text(dose.toString())); // Check changes on a JournalEntryPage
    }

    public static void changeReasons(Reason... newReasons){
        openEditPage();
        addReason(0, newReasons);
        if(newReasons.length > 1) {
            for (int j = 1; j < newReasons.length; j++) {
                $(By.xpath(".//*[@class='input plus']")).click();
                addReason(j, newReasons);
            }
        }
        addChanges();
        if (newReasons.length == 1) {
            $(By.xpath("(.//td[2])[6]")).shouldHave(text(newReasons[0].getName())); // Check changes on a JournalEntryPage
        }
        if(newReasons.length > 1) {
            $(By.xpath("(.//td[2])[6]")).shouldHave(text(newReasons[1].getName() + "\n" + newReasons[0].getName()));
        }
    }

    public static void changeAnimal(SimpleAnimal newAnimal){
        openEditPage();
        addAnimal(newAnimal);
        addChanges();
        $(By.xpath("(.//td[2])[1]")).shouldHave(text(newAnimal.getName())); // Check changes on a JournalEntryPage
    }

    public static void changeAll(int toChange, Packaging newPackaging, int newDose, SimpleAnimal newAnimal, Reason... newReason) {
        openEditPage();
        String drugName = newPackaging.getDrugName();
        addPackaging(drugName);
        addDose(newDose);
        addReason(0, newReason);
        addAnimal(newAnimal);
        addAnimalType(newAnimal);
        addChanges();
    }

    private static void addPackaging(String drugName) {
        $(By.xpath("(//input)[2]")).val(drugName.substring(0, 5));
        $(byText(drugName)).should(appear).click();
    }

    private static void addDose(Integer dose) {
        $(By.xpath("//*[@name=\"treatment.dose\"]")).val(dose.toString());
    }

    private static void addReason(int j, Reason... reasons){
        String reasonName = reasons[j].getName();
        $(By.xpath("(//*[@placeholder=\"Grund auswählen\"]//input[1])[" + (j + 1) +"]")).val(reasonName.substring(0, 3));
        $(byText(reasonName)).should(appear).click();
    }

    private static void addAnimal(SimpleAnimal newAnimal) {
        $(By.xpath("(//input)[7]")).clear();
        $(By.xpath("(//input)[7]")).val(newAnimal.getName());
    }

    private static void addAnimalType(SimpleAnimal newAnimal) {
        $(By.xpath("(//*[@placeholder=\"Tierart wählen\"])[2]")).click();
        Selenide.sleep(100);
        $(byText(newAnimal.getType().getName())).should(appear).click();
    }

    private static void addChanges() {
        $(By.xpath("//footer")).click();
        $(By.xpath("(//button[2])[2]")).click();
    }

    private static void openEditPage() {
        $(By.xpath(".//footer")).click();
    }
    
}
