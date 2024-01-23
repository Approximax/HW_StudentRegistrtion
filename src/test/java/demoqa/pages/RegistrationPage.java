package demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import demoqa.pages.components.CalendarComponent;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    SelenideElement firstNameInput = $("#firstName"),
                    lastNameInput = $("#lastName"),
                    emailInput = $("#userEmail"),
                    genderChoice = $("#genterWrapper"),
                    userNumberInput = $("#userNumber"),
                    dateOfBirthInput = $("dateOfBirthInput"),
                    subjectsInput = $("#subjectsInput"),
                    hobbiesRadio = $("#hobbiesWrapper"),
                    pictureUpload = $("#uploadPicture"),
                    addressInput = $("#currentAddress"),
                    stateSelect = $ ("#state"),
                    citySelect = $("#city"),
                    submit = $("#submit");


    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderChoice.$(byText("value")).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day,month,year);

        return this;
    }



    /*
    Тут мне кажется было бы правильным запихнуть все доступные в этом поле значения, который сайт дописывает за первой
    буквой в отдельный Enam и потом с помощью switch-case сделать конструкцию перебора за пользовательским вводом. Так
    было бы честнее, а не как сейчас жестко забитое одное единственное значение, но я не нашел в коде страницы всех
    значений этого поля, поэтому оставил так.
     */
    public  RegistrationPage setSubjects() {
        subjectsInput.setValue("a").sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesRadio.$(byText(value)).click();

        return this;
    }

    public RegistrationPage pictureUpload(String pictureName) {
        pictureUpload.uploadFromClasspath(pictureName);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        stateSelect.click();
        stateSelect.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        citySelect.click();
        citySelect.$(byText(value)).click();

        return this;
    }

    public RegistrationPage submit() {
        submit.click();

        return this;
    }
}