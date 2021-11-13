package ru.netology.SecondTask;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryPositiveTest {

    int maxLengthMonth;

//    @BeforeEach
//    void setUp() {
//        Configuration.headless = true;
//    }

    @Test
    void shouldTestPopupCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Ка");
        $(byText("Казань")).click();
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 3;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification'] .notification__title")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='notification'] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на"));
    }

    @Test
    void shouldTestCalendar() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("Казань");
        $$("button").filter(appear).first().click();
        Thread.sleep(5000);
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        $("[data-day='1637355600000']").shouldBe(visible).click();

        $("[data-test-id='date'] .input__control");
        Thread.sleep(5000);

        System.out.println(dayOfMonth);
    }

//
//        if (month == 11) {
//            maxLengthMonth = 30;
//        }
//        if (dayOfMonth>maxLengthMonth){
//            month=month+1;
//            dayOfMonth=dayOfMonth-maxLengthMonth;
//        }
//
//        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
//
//        $("[data-test-id='name'] [class='input__control']").setValue("Иванов Иван");
//        $("[data-test-id='phone'] [class='input__control']").setValue("+71231234567");
//        $(".checkbox__box").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id='notification'] .notification__title")
//                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно!"));
//        $("[data-test-id='notification'] .notification__content")
//                .shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно забронирована на"));
//    }


}
