package org.selenide;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by johnbunky on 06.02.17.
 */
public class ListAnimalType {
    public ElementsCollection collectionListAnimalTypes(){
        return $$(By.xpath("//section[@class='dialogue']/div//span"));
    }
}
