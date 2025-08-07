import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertTest {
    AlertPage alertPage = new AlertPage();


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
    public void alertTest() {
        alertPage
                .openAlertPage()
                .choseSimpleAlert();
        assertThat(alertPage.catchAlertText()).isEqualTo("You clicked a button");
    }
}
