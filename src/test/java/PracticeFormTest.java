import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormTest {

    PracticeFormPage practiceFormPage = new PracticeFormPage();


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();

        Configuration.browser = "chrome";
        Configuration.browserSize = "max";
        Configuration.browserPosition = "10x10";
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout = 70000;
        Configuration.pollingInterval = 200;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    public void positiveTest(){
        practiceFormPage.openPage();
        practiceFormPage.setFirstName("Енот");
        practiceFormPage.setLastName("Енотов");
        practiceFormPage.setDteBirth();
        practiceFormPage.setUserEmail("test@test.ru");
        practiceFormPage.setMailGender();
        practiceFormPage.setUserNumber("1234567890");
        practiceFormPage.setSubject("M","Maths");
        practiceFormPage.setPicture("test_files/picture.jpg");
        practiceFormPage.setHobby();
        practiceFormPage.setAddress("Енотов дом");
        practiceFormPage.setCity("NCR","Delhi");
        practiceFormPage.submitForm();
        Map<String, String> map = practiceFormPage.getSubmissionResult();
        assertThat(map.get("Student Name")).isEqualTo("Енот Енотов");
        assertThat(map.get("Address")).isEqualTo("Енотов дом");
        assertThat(map.get("State and City")).isEqualTo("NCR Delhi");
        assertThat(map.get("Picture")).isEqualTo("picture.jpg");
        assertThat(map.get("Student Email")).isEqualTo("test@test.ru");
        assertThat(map.get("Date of Birth")).isEqualTo("13 July,2025");
        assertThat(map.get("Gender")).isEqualTo("Male");
        assertThat(map.get("Mobile")).isEqualTo("1234567890");
        assertThat(map.get("Subjects")).isEqualTo("Maths");
        assertThat(map.get("Hobbies")).isEqualTo("Sports");
        sleep(5000);
    }
}
