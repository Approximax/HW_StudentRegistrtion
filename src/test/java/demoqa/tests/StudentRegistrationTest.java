package demoqa.tests;

import com.github.javafaker.Faker;
import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import demoqa.utils.RandomUtils;
import org.junit.jupiter.api.Test;


public class StudentRegistrationTest  extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();
    RandomUtils randomUtils = new RandomUtils();

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = randomUtils.getRandomGender();
    String phoneNumber = faker.phoneNumber().cellPhone();

    @Test
    void minimumDataRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submit();

        resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber);

    }

    @Test
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName("")
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submit();

        resultTableComponent.checkAbsence();
    }

    @Test
    void fullDataRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth("15", "March", "2013")
                .setSubjects()
                .setHobbies("Music")
                .pictureUpload("testPicture.png")
                .setAddress("st. Test 15")
                .setState("NCR")
                .setCity("Noida")
                .submit();

        resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", email)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber)
                .checkTableValue("Date of Birth", "15 March,2013")
                .checkTableValue("Subjects", "Accounting")
                .checkTableValue("Hobbies", "Music")
                .checkTableValue("Picture", "testPicture.png")
                .checkTableValue("Address", "st. Test 15")
                .checkTableValue("State and City", "NCR Noida");

    }
}