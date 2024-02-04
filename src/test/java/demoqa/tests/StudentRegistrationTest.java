package demoqa.tests;

import com.github.javafaker.Faker;
import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import demoqa.utils.RandomUtils;
import org.junit.jupiter.api.Test;


public class StudentRegistrationTest  extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    Faker faker = new Faker();

    RandomUtils randomUtils = new RandomUtils();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = randomUtils.getRandomGender(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            address = faker.address().streetAddress(),
            state = randomUtils.getRandomState(),
            city = randomUtils.getRandomCity(state),
            hobby = randomUtils.getRandomHobby(),
            day = randomUtils.getRandomDay(),
            month = randomUtils.getRandomMonth(),
            year = randomUtils.getRandomYear();

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
                .setDateOfBirth(day, month, year)
                .setSubjects()
                .setHobbies(hobby)
                .pictureUpload("testPicture.png")
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", email)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber)
                .checkTableValue("Date of Birth", day + " " + month + "," + year)
                .checkTableValue("Subjects", "Accounting")
                .checkTableValue("Hobbies", hobby)
                .checkTableValue("Picture", "testPicture.png")
                .checkTableValue("Address", address)
                .checkTableValue("State and City", state + " " + city);

    }
}