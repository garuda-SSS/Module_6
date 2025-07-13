import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private SelenideElement formBtn = $(".show #item-0"); //Кнопка для открытия формы
    private SelenideElement firstName = $("#firstName"); //Поле с именем
    private SelenideElement lastName = $("#lastName"); //Поле с фамилией
    private SelenideElement userEmail = $("#userEmail"); //Поле с почтой
    private SelenideElement gender = $(".custom-radio:first-of-type"); //Радиобаттон для пола Male
    private SelenideElement userNumber = $("#userNumber"); //Поле для телефона
    private ElementsCollection rows = $$("table.table-dark tbody tr"); //Все строки таблицы результатов
    private SelenideElement sudmitBtn = $("#submit"); //Кнопка подтверждения
    private SelenideElement subject = $("#subjectsInput");//Выбор предмета
    private SelenideElement picture = $("#uploadPicture");//Выбор файла
    private SelenideElement address = $("textarea.form-control");//Ввод адреса
    private SelenideElement hobby1 = $("label[for='hobbies-checkbox-1']");//Ввод адреса
    private ElementsCollection forCity = $$("#stateCity-wrapper .col-md-4 .css-1wa3eu0-placeholder"); //Костыль
    private SelenideElement dateBirth = $("#dateOfBirthInput");//Вызов календаря
    private SelenideElement day13 = $(".react-datepicker__day--013");//Костыль




    public void openPage(){
        open("/forms");
        formBtn.shouldBe(visible, enabled).click();
    }

    public void setFirstName(String value){
        firstName.setValue(value);
    }

    public void setUserEmail(String value){
        userEmail.setValue(value);
    }

    public void setDteBirth(){
        dateBirth.click();
        day13.click();
    }

    public void setLastName(String value){
        lastName.setValue(value);
    }

    public void setMailGender(){
        gender.click();
    }

    public void setUserNumber(String value){
        userNumber.setValue(value);
    }

    public void setSubject(String letter,String sub){
        subject.setValue(letter);
        $(byText(sub)).click();
    }

    public void setPicture(String file){
        picture.uploadFromClasspath(file);
    }

    public void setHobby(){
        hobby1.click();
    }

    public void setAddress(String value){
        address.setValue(value);
    }

    public void setCity(String state,String city){
        forCity.get(0).click();
        $(byText(state)).click();
        forCity.get(0).click();
        $(byText(city)).click();
    }

    public void submitForm(){
        sudmitBtn.click();
    }

    public Map<String,String> getSubmissionResult(){
        Map<String, String> tableData = new HashMap<>();
        for (SelenideElement row : rows) {
            SelenideElement label = row.$("td", 0);
            SelenideElement value = row.$("td", 1);
            tableData.put(label.text(), value.text());
        }
        return tableData;
    }
}
