package demoqa.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationTest  extends BaseTest{

    @Test
    void studentRegistrationTest() {

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