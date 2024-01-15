import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @Test
    void studentRegistrationTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("David");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("asd@qwe.ty");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("1234567891");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__day--025").click();
        $("#subjectsInput").setValue("a").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("testPicture.png");
        $("#currentAddress").setValue("st.Test");
        $("#state").click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Noida")).click();
        $("#submit").click();

        $(".modal-open").shouldBe(visible);
        $(".table-responsive").shouldHave(text("David Doe"));
        $(".table-responsive").shouldHave(text("asd@qwe.ty"));
        $(".table-responsive").shouldHave(text("Other"));
        $(".table-responsive").shouldHave(text("1234567891"));
        $(".table-responsive").shouldHave(text("25 January"));
        $(".table-responsive").shouldHave(text("Accounting"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("testPicture.png"));
        $(".table-responsive").shouldHave(text("st.Test"));
        $(".table-responsive").shouldHave(text("NCR Noida"));
    }
}