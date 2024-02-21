package demoqa.tests;

import com.github.javafaker.Faker;
import demoqa.config.ProjectConfig;
import demoqa.pages.RegistrationPage;
import demoqa.pages.components.ResultTableComponent;
import demoqa.utils.RandomUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;


public class StudentRegistrationTest extends TestBase{

    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    Faker faker = new Faker();

    RandomUtils randomUtils = new RandomUtils();

    ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class);

    String firstName = projectConfig.firstName(),
            lastName = projectConfig.lastName(),
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
    @DisplayName("Минимально допустимый набор данных для регистрации нового студента")
    @Severity(SeverityLevel.BLOCKER)
    void minimumDataRegistrationTest() {

        step("Открываем сайт c проверяемой формой", () -> registrationPage.openPage());

        step("Убираем баннер, если он есть", () -> {
            registrationPage.bannerRemove();
        });

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

        step("Убираем баннер, если он есть", () -> {
            registrationPage.bannerRemove();
        });

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

        step("Убираем баннер, если он есть", () -> {
            registrationPage.bannerRemove();
        });

        step("Заполнение формы регистрации полным набором данных", () -> registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(day, month, year)
                .setSubjects(subject)
                .setHobbies(hobby)
                .pictureUpload("img/testPicture.png")
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
                .checkTableValue("Picture", "img/testPicture.png")
                .checkTableValue("Address", address)
                .checkTableValue("State and City", state + " " + city));
    }
}