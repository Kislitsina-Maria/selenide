import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestOderCard {

    //java -jar artifacts/app-card-delivery.jar -port=7777


    @BeforeEach
   void setDate() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    }








    @Test
    void shouldTest() {

        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 4);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Самара");
        $("input[placeholder=\"Дата встречи\"]").clear();
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        $x("//*[contains(text(),\"Встреча успешно забронирована на\")]");
        $x("//*[contains(text(),\"05.11.2022\")]");
    }

    @Test
    void wrongCity() {

        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 4);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Суздаль");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void englishCity() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 4);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Moscow");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void cityWithNumber() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 4);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Москва 2");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Доставка в выбранный город недоступна\")]");
    }

    @Test
    void withoutCity() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 4);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Поле обязательно для заполнения\")]");
    }
    @Test
    void lastDayMeeting() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, -1);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Краснодар");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void todayMeeting() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Пенза");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void tomorrowMeeting() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 1);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Биробиджан");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void twoDaysMeeting() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 2);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Тамбов");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void threeDaysMeeting() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 3);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Кемерово");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Заказ на выбранную дату невозможен\"]");
    }

    @Test
    void nameWithNumber() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Кострома");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Инесса 3");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.\"]");
    }

    @Test
    void englishName() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Салехард");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Inessa Vladimirovna");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.\"]");
    }

    @Test
    void doubleName() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Санкт-Петербург");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Анна-Мария Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        $x("//*[contains(text(),\"Встреча успешно забронирована на\")]");
        $x("//*[contains(text(),\"05.11.2022\")]");
    }

    @Test
    void withoutName() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Киров");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Поле обязательно для заполнения\")]");
    }

    @Test
    void withoutPhone() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Киров");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Поле обязательно для заполнения\")]");
    }

    @Test
    void phoneNumberWithEight() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Киров");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("89340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.\")]");
    }

    @Test
    void phoneNumberMore() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Киров");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("+793402481975");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.\")]");
    }

    @Test
    void phoneNumberLess() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Киров");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("+7934024819");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.\")]");
    }

    @Test
    void withoutAgreement() {
        Calendar calc = GregorianCalendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        calc.add(Calendar.DATE, 5);
        String date = formatter.format(calc.getTime());

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Киров");
        $("input[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(date);
        $("[name=\"name\"]").setValue("Анна Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"button__content\"]").click();

        $("[data-test-id=\"agreement\"].input_invalid").should(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }






}
