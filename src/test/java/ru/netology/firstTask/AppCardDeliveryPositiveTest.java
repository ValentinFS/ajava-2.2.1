package ru.netology.firstTask;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryPositiveTest {

    @BeforeEach
    void setUp() {
        Configuration.headless = true;
    }


    public String setDays(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }


    @Test
    void shouldTestAllValidInfo() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        String days = setDays(3);
        $("[data-test-id='date'] [class='input__control']").setValue(days);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification'] .notification__title")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " + setDays(3)));
    }

    @Test
    void shouldTestAllValidInfoDash() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        String days = setDays(3);
        $("[data-test-id='date'] [class='input__control']").setValue(days);
        $("[data-test-id='name'] [class='input__control']").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification'] .notification__title")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " + setDays(3)));
    }


}
