import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class TestOderCard {

    @Test
    void shouldTest() {

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Москва");



//        driver.findElement(By.cssSelector("[data-test-id='city']")).sendKeys("Москва");
//        driver.findElement(By.cssSelector("data-test-id=\"date\"")).sendKeys("05.11.2022");
//        driver.findElement(By.cssSelector("[data-test-id=\"name\"]")).sendKeys("Инесса Рюриковна");
//        driver.findElement(By.cssSelector("[data-test-id=\"phone\"]")).sendKeys("+79340248197");
//        driver.findElement(By.className("checkbox__control")).click();
//        driver.findElement(By.className("button__content")).click();
//
//        String actual = driver.findElement(By.className("notification__icon")).getText().trim().;
//        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
//
//        Assertions.assertEquals(expected, actual);


    }
}
