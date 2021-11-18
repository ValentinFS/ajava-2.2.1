package ru.netology.secondTask;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryPositiveTest {

//    @BeforeEach
//    void setUp() {
//        Configuration.headless = true;
//    }

    public String setDays(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    @Test
    void shouldTestPopupCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Ка");
        $(byText("Казань")).click();
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
    void shouldTestCalendar() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        $$("button").filter(appear).first().click();
        LocalDate currentData = LocalDate.now();
        LocalDate nextDate = LocalDate.now().plusDays(7);
        if (currentData.getMonth() != nextDate.getMonth()) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }
        $$("td.calendar__day").find(Condition.text(String.valueOf(nextDate.getDayOfMonth()))).click();
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification'] .notification__title")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на " +
                        nextDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    }


}
