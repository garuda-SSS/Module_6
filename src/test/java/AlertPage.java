import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AlertPage {
    private SelenideElement simpleAlertBtn = $("#alertButton"); //Первая кнопка в алертах

    public AlertPage openAlertPage() {
        open("/alerts");
        return this;
    }

    public AlertPage choseSimpleAlert() {
        simpleAlertBtn.click();
        return this;
    }

    public String catchAlertText() {
        return Selenide.switchTo().alert().getText();
    }
}