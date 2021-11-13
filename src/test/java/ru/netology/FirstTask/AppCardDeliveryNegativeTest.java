package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryNegativeTest {

    @BeforeEach
    void setUp() {
        Configuration.headless = true;
    }


    @Test
    void shouldTestAllFieldsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Поле обязательно для заполнения"));
    }


    @Test
    void shouldTestFieldCityEmpty() {
        open("http://localhost:9999");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 4;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFieldDateEmpty() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='date'] .input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void shouldTestFieldNameEmpty() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 4;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestFieldPhoneEmpty() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 4;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestCheckBoxEmpty() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldBe(visible)
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldTestNotValidCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Каза");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldTestNotValidCityEng() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Kazan");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestNotValidCitySpecialSymbol() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань%");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Доставка в выбранный город недоступна"));
    }


    @Test
    void shouldTestNotValidDate() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 2;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='date'] .input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldTestNotValidNameEng() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldBe(visible).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNotValidNameSpecialSymbol() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов&Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNotValidNameDash() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("-Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestAllValidInfoDash() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов--Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void shouldTestShortName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("И");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestLongName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']")
                .setValue("Вдлваожфлвоадфовлаофжлвоалфвалфлвафлвалфолвоафловал " +
                        "флваофлываожлфовалофлываожлфоывлаофвоашщцутимтущфщмцшц" +
                        "уВдлваожфлвоадфовлаофжлвоалфвалфлвафлвалфолвоафловал ф" +
                        "лваофлываожлфовалофлываожлфоывлаофвоашщцутимтущфщмцшцу" +
                        "Вдлваожфлвоадфовлаофжлвоалфвалфлвафлвалфолвоафловал фл" +
                        "ваофлываожлфовалофлываожлфоывлаофвоашщцутимтущфщмцшцуВ" +
                        "длваожфлвоадфовлаофжлвоалфвалфлвафлвалфолвоафловал флв" +
                        "аофлываожлфовалофлываожлфоывлаофвоашщцутимтущфщмцшцуВд" +
                        "лваожфлвоадфовлаофжлвоалфвалфлвафлвалфолвоафловал флва" +
                        "офлываожлфовалофлываожлфоывлаофвоашщцутимтущфщмцшцуВдл" +
                        "ваожфлвоадфовлаофжлвоалфвалфлвафлвалфолвоафловал флвао" +
                        "флываожлфовалофлываожлфоывлаофвоашщцутимтущфщмцшцу");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNotValidPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+7123123456");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestNotValidPhonePlus() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("71231234567+");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestNotValidPhoneWithoutPlus() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
