package demoqa.tests;

import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import org.junit.jupiter.api.Test;


public class StudentRegistrationTest  extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();
    @Test
    void minimumDataRegistration() {

        registrationPage.openPage()
                .setFirstName("Andrew")
                .setLastName("Doe")
                .setGender("Male")
                .setUserNumber("1234567891")
                .submit();

        resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", "Andrew Doe")
                .checkTableValue("Gender", "Male")
                .checkTableValue("Mobile", "1234567891");

    }

    @Test
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Peter")
                .setLastName("")
                .setGender("Other")
                .setUserNumber("88005553535")
                .submit();

        resultTableComponent.checkAbsence();
    }
}