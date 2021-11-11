package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryNegativeTest {

    @Test
    void shouldTestAllFieldsEmpty() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id='city'] [class='input__control']").setValue("");
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth() + 5;
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id='date'] [class='input__control']").setValue(dayOfMonth + "." + month + "." + year);
        $("[data-test-id='name'] [class='input__control']").setValue("");
        $("[data-test-id='phone'] [class='input__control']").setValue("");
//        $(".checkbox__box").click();
        $(withText("Забронировать")).click();
        Thread.sleep(25000);
//        $(".icon_size_s").shouldBe(visible, Duration.ofSeconds(15)).click();
    }

}
