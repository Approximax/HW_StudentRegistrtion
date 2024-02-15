package demoqa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import demoqa.utils.RandomUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;


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
            year = randomUtils.getRandomYear(),
            subject = randomUtils.getRandomSubject();

    @Test
    @Tag("minimumTest")
    void minimumDataRegistrationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем сайт c проверяемой формой", () -> registrationPage.openPage());

        step("Устанавливаем тестовые данные студента", () -> registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submit());

        step("Проверяем корректность заполненных данных студента после регистрации в форме", () -> resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber));
    }

    @Test
    @Tag("negativeTest")
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
    @Tag("fullTest")
    void fullDataRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(day, month, year)
                .setSubjects(subject)
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
                .checkTableValue("Subjects", subject)
                .checkTableValue("Hobbies", hobby)
                .checkTableValue("Picture", "testPicture.png")
                .checkTableValue("Address", address)
                .checkTableValue("State and City", state + " " + city);

    }
}