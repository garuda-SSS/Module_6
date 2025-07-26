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
    private SelenideElement gender1 = $(".custom-radio:nth-of-type(1)");//Радиобаттон для пола Male
    private SelenideElement gender2 = $(".custom-radio:nth-of-type(2)");//Радиобаттон для пола Female
    private SelenideElement gender3 = $(".custom-radio:nth-of-type(3)");//Радиобаттон для пола Other
    private SelenideElement userNumber = $("#userNumber"); //Поле для телефона
    private ElementsCollection rows = $$("table.table-dark tbody tr"); //Все строки таблицы результатов
    private SelenideElement sudmitBtn = $("#submit"); //Кнопка подтверждения
    private SelenideElement subject = $("#subjectsInput");//Выбор предмета
    private SelenideElement picture = $("#uploadPicture");//Выбор файла
    private SelenideElement address = $("textarea.form-control");//Ввод адреса
    private SelenideElement hobbySports = $("label[for='hobbies-checkbox-1']");//Хобби - спорт
    private SelenideElement hobbyReading = $("label[for='hobbies-checkbox-2']");//Хобби - чтение
    private SelenideElement hobbyMusic = $("label[for='hobbies-checkbox-3']");//Хобби - музыка
    private ElementsCollection forCity = $$("#stateCity-wrapper .col-md-4 .css-1wa3eu0-placeholder"); //Костыль
    private SelenideElement dateBirth = $("#dateOfBirthInput");//Открыть календарь
    private final String colorOfRedFrame = "rgb(220, 53, 69)";


    private boolean checkColor(String expectedColor, SelenideElement elem) {
        try {
            elem.shouldHave(cssValue("border-color", expectedColor), Duration.ofSeconds(5));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public boolean firstNameCheck() {
        return checkColor(colorOfRedFrame, firstName);
    }

    public boolean lastNameCheck() {
        return checkColor(colorOfRedFrame, lastName);
    }

    public boolean numberCheck() {
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
        switch (gender) {
            case "Male":
                gender1.click();
                break;
            case "Female":
                gender2.click();
                break;
            case "Other":
                gender3.click();
                break;
            default:
                gender1.click();
        }
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
        switch (hobbyType) {
            case "Sports":
                hobbySports.click();
                break;
            case "Reading":
                hobbyReading.click();
                break;
            case "Music":
                hobbyMusic.click();
                break;
            default:
                gender1.click();
        }
        return this;
    }

    public PracticeFormPage setAddress(String value) {
        address.setValue(value);
        return this;
    }

    public PracticeFormPage setState(String state) {
        forCity.get(0).scrollTo().click();
        $(byText(state)).click();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        forCity.get(0).click();
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
