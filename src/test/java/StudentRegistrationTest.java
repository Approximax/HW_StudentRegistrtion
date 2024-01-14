import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
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
//        $("#gender-ratio-3").click();
        $x("//label[]").selectRadio("Other");
        $("#userNumber").setValue("+12345678");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__day--025").click();
        $("#hobbies-checkbox-2").click();
        $("#upoadPicture").uploadFromClasspath("testPicture.png");
        $("#currentAddress").setValue("st.Test");
        $("#state").click();


    }
}