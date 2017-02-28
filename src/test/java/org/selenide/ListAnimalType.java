package org.selenide;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by johnbunky on 06.02.17.
 */
public class ListAnimalType {
    public static ElementsCollection collectionListAnimalTypes(){
        return $$(By.xpath("//section[@class='dialogue']/div//span"));
    }

    public static void deleteItem(){
        $(By.xpath("(//button[@class='deleteItem'])[1]")).click();
    }
}
