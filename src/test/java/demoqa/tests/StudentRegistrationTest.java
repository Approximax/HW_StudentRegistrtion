package demoqa.tests;

import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import org.junit.jupiter.api.Test;


public class StudentRegistrationTest  extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();
    @Test
    void minimumDataRegistrationTest() {

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

    @Test
    void fullDataRegistrationTest() {

        registrationPage.openPage()
                .setFirstName("Andrew")
                .setLastName("Doe")
                .setUserEmail("qwerty@abc.com")
                .setGender("Other")
                .setUserNumber("1234567891")
                .setDateOfBirth("15", "March", "2013")
                .setSubjects()
                .setHobbies("Music")
                .pictureUpload("testPicture.png")
                .setAddress("st. Test 15")
                .setState("NCR")
                .setCity("Noida")
                .submit();

        resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", "Andrew Doe")
                .checkTableValue("Student Email", "qwerty@abc.com")
                .checkTableValue("Gender", "Other")
                .checkTableValue("Mobile", "1234567891")
                .checkTableValue("Date of Birth", "15 March,2013")
                .checkTableValue("Subjects", "Accounting")
                .checkTableValue("Hobbies", "Music")
                .checkTableValue("Picture", "testPicture.png")
                .checkTableValue("Address", "st. Test 15")
                .checkTableValue("State and City", "NCR Noida");

    }
}