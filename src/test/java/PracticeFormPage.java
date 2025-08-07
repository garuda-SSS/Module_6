import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private SelenideElement firstName = $("#firstName"); //Поле с именем
    private SelenideElement lastName = $("#lastName"); //Поле с фамилией
    private SelenideElement userEmail = $("#userEmail"); //Поле с почтой
    private SelenideElement userNumber = $("#userNumber"); //Поле для телефона
    private ElementsCollection rows = $$("table.table-dark tbody tr"); //Все строки таблицы результатов
    private SelenideElement sudmitBtn = $("#submit"); //Кнопка подтверждения
    private SelenideElement subject = $("#subjectsInput");//Выбор предмета
    private SelenideElement picture = $("#uploadPicture");//Выбор файла
    private SelenideElement address = $("textarea.form-control");//Ввод адреса
    private SelenideElement stateField = $("#state > div > div.css-1hwfws3"); //Выбор штата
    private SelenideElement cityField = $("#city > div > div.css-1hwfws3"); //Выбор штата
    private SelenideElement dateBirth = $("#dateOfBirthInput");//Открыть календарь
    private final String colorOfRedFrame = "rgb(220, 53, 69)";
    private final Map<String, String> hobbyData = Map.of(
            "Sports", "1",
            "Reading", "2",
            "Music", "3"
    );

    private boolean checkColor(String expectedColor, SelenideElement elem) {
        try {
            elem.shouldHave(cssValue("border-color", expectedColor), Duration.ofSeconds(5));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public boolean firstNameCheckFrame() {
        return checkColor(colorOfRedFrame, firstName);
    }

    public boolean lastNameCheckFrame() {
        return checkColor(colorOfRedFrame, lastName);
    }

    public boolean numberCheckFrame() {
        return checkColor(colorOfRedFrame, userNumber);
    }

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public PracticeFormPage setUserEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public PracticeFormPage setDateBirth(String monthName, String yearNumber, String dayNumber) {
        dateBirth.click();
        $(".react-datepicker__year-select").shouldBe(clickable).click();
        $(byText(yearNumber)).shouldBe(clickable).click();
        $(".react-datepicker__month-select").shouldBe(clickable).click();
        $(byText(monthName)).shouldBe(clickable).click();
        $("[aria-label*='" + monthName + " " + dayNumber + "']").shouldBe(clickable).click();
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public PracticeFormPage setMailGender(String gender) {
        $("[value=" + gender + "] + label").click();
        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumber.setValue(value);
        return this;
    }

    public PracticeFormPage setSubject(String letter, String sub) {
        subject.setValue(letter);
        $(byText(sub)).click();
        return this;
    }

    public PracticeFormPage setPicture(String file) {
        picture.uploadFromClasspath(file);
        return this;
    }

    public PracticeFormPage setHobby(String hobbyType) {
        $("label[for='hobbies-checkbox-" + hobbyData.get(hobbyType) + "']").click();
        return this;
    }

    public PracticeFormPage setAddress(String value) {
        address.setValue(value);
        return this;
    }

    public PracticeFormPage setState(String state) {
        stateField.scrollTo().click();
        $(byText(state)).click();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        cityField.click();
        $(byText(city)).click();
        return this;
    }

    public PracticeFormPage submitForm() {
        sudmitBtn.scrollTo().click();
        return this;
    }

    public Map<String, String> getSubmissionResult() {
        Map<String, String> tableData = new HashMap<>();
        for (SelenideElement row : rows) {
            SelenideElement label = row.$("td", 0);
            SelenideElement value = row.$("td", 1);
            tableData.put(label.text(), value.text());
        }
        return tableData;
    }
}
