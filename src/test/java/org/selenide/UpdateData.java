package org.selenide;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.selenide.StaticData.*;

/**
 * Created by johnbunky on 15.02.17.
 */
public class UpdateData {
    public void intutData(){
        $(By.xpath(".//input[@ng-if][1]")).val(TVD_NUMBER);
        $(By.xpath(".//input[@ng-if][2]")).val(OPERATION_NAME);
        $(By.xpath(".//input[@ng-if][3]")).val(ADDRESS_LINE1);
        $(By.xpath(".//input[@ng-if][4]")).val(ADDRES_LINE2);
        $(By.xpath(".//input[@ng-if][5]")).val(ZIP);
        $(By.xpath(".//input[@ng-if][6]")).val(CITY);
        $(By.xpath("(//*[@class='recommend'])[2]")).click();
    }
}
