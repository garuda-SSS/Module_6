import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormTest {
    private final String firstNameValue = "Енот";
    private final String secondNameValue = "Енотов";
    private final String mailValue = "test@test.ru";
    private final String numberValue = "1234567890";
    private final String genderValue = "Male";
    private final String hobbyValue = "Sports";
    private final String subjectValue = "Maths";
    private final String letterSubjectValue = subjectValue.substring(0, 1);
    private final String pictureAddressValue = "test_files/picture.jpg";
    private final String userAddressValue = "Енотов дом";
    private final String stateValue = "NCR";
    private final String cityValue = "Delhi";

    private final String dayValue = "23";
    private final String monthValue = "May";
    private final String yearValue = "2024";


    PracticeFormPage practiceFormPage = new PracticeFormPage();


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 70000;
        Configuration.pollingInterval = 200;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    public void positiveTest() {
        practiceFormPage
                .openPage()
                .setFirstName(firstNameValue)
                .setLastName(secondNameValue)
                .setDateBirth(monthValue, yearValue, dayValue)
                .setUserEmail(mailValue)
                .setMailGender(genderValue)
                .setUserNumber(numberValue)
                .setSubject(letterSubjectValue, subjectValue)
                .setPicture(pictureAddressValue)
                .setHobby(hobbyValue)
                .setAddress(userAddressValue)
                .setState(stateValue)
                .setCity(cityValue)
                .submitForm();
        Map<String, String> map = practiceFormPage.getSubmissionResult();
        assertThat(map.get("Student Name")).isEqualTo(firstNameValue + " " + secondNameValue);
        assertThat(map.get("Address")).isEqualTo(userAddressValue);
        assertThat(map.get("State and City")).isEqualTo(stateValue + " " + cityValue);
        assertThat(map.get("Picture")).isEqualTo(pictureAddressValue.substring(pictureAddressValue.lastIndexOf('/') + 1));
        assertThat(map.get("Student Email")).isEqualTo(mailValue);
        assertThat(map.get("Date of Birth")).isEqualTo(dayValue + " " + monthValue + "," + yearValue);
        assertThat(map.get("Gender")).isEqualTo(genderValue);
        assertThat(map.get("Mobile")).isEqualTo(numberValue);
        assertThat(map.get("Subjects")).isEqualTo(subjectValue);
        assertThat(map.get("Hobbies")).isEqualTo(hobbyValue);
    }

    @Test
    public void negativeTest() {
        practiceFormPage
                .openPage()
                .submitForm();
        assertThat(practiceFormPage.firstNameCheck()).isTrue();
        assertThat(practiceFormPage.lastNameCheck()).isTrue();
        assertThat(practiceFormPage.numberCheck()).isTrue();
    }
}


