import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VSCodeTest extends AbstractEndToEndTest {

    @Test
    void testMyPage(WebDriver driver) {
        driver.navigate().to("https://github.com/microsoft/vscode-java-test/issues/661");
        WebElement stateElement = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".State")));
        assertEquals("Closed", stateElement.getText());
    }

}
