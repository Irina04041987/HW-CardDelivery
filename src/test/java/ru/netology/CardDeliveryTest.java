package ru.netology;

import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldSubmitRequestWithDateAsString() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        LocalDate date = LocalDate.now().plusDays(5);
        String dateForTest = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("input[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[placeholder=\"Дата встречи\"]").val(dateForTest);
        $("[data-test-id=name] input").setValue("Рябова Екатерина");
        $("[data-test-id=phone] input").setValue("+79129487713");
        $("[data-test-id=agreement]").click();
        $$("button[type=\"button\"]").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
        $("div.notification__content").shouldHave(text(dateForTest));
    }

}
