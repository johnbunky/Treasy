package org.selenide;

import org.openqa.selenium.By;
import static org.selenide.StaticData.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by johnbunky on 15.02.17.
 */
public class DrugName {
    public void inputDrugName(){
        $(By.xpath(".//input[@class='ng-pristine ng-untouched ng-valid']")).val(ORIGIN);
        $(By.xpath("(//*[@class='recommend'])[1]")).click();
    }
}
