package demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import demoqa.utils.Attach;
import demoqa.utils.RandomUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static io.qameta.allure.Allure.step;


public class StudentRegistrationTest {

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

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    @Test
    @Tag("minimumTest")
    @DisplayName("Минимально допустимый набор данных для регистрации нового студента")
    @Severity(SeverityLevel.BLOCKER)
    void minimumDataRegistrationTest() {

        step("Открываем сайт c проверяемой формой", () -> registrationPage.openPage());

        step("Устанавливаем минимально необходимые для регистрации тестовые данные студента", () -> registrationPage.setFirstName(firstName)
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
    @DisplayName("Отсутствие регистрации нового студента при неправильном заполнении формы")
    @Severity(SeverityLevel.BLOCKER)
    void negativeRegistrationTest() {

        step("Открываем сайт c проверяемой формой", () -> registrationPage.openPage());

        step("Некорректно заполняем форму регистрации тестовыми данными", () ->registrationPage.setFirstName(firstName)
                        .setLastName("")
                        .setGender(gender)
                        .setUserNumber(phoneNumber)
                        .submit());

        step("Проверяем, что таблица с данными студента не появилась, после некорректной регистрации",
                () -> resultTableComponent.checkAbsence());
    }

    @Test
    @Tag("fullTest")
    @DisplayName("Успешная регистрация с полным набором данных нового студента")
    @Severity(SeverityLevel.BLOCKER)
    void fullDataRegistrationTest() {

        step("Открываем сайт c проверяемой формой", () -> registrationPage.openPage());

        step("Заполнение формы регистрации полным набором данных", () -> registrationPage.setFirstName(firstName)
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
                .submit());

        step("Проверяем корректность заполненных данных студента после регистрации в форме", () -> resultTableComponent.checkAppearance()
                .checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", email)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", phoneNumber)
                .checkTableValue("Date of Birth", day + " " + month + "," + year)
                .checkTableValue("Subjects", subject)
                .checkTableValue("Hobbies", hobby)
                .checkTableValue("Picture", "testPicture.png")
                .checkTableValue("Address", address)
                .checkTableValue("State and City", state + " " + city));
    }
}