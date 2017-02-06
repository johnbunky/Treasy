package org.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Created by johnbunky on 06.02.17.
 */
public class WelcomePage {

    public SelenideElement successRegistration(){
        return $(byText("Congratulations, you have successfully registered to Treasy."));
    }

    public SettingsPage openSettingPage() {
        $(By.xpath("//*[@id='menu']")).click();
        $(byText("Settings")).click();
        sleep(2000);
        return page(SettingsPage.class);
    }
}
