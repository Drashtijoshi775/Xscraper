package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void goToUrlAndWait(WebDriver driver, String url) {
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public static void findElementAndClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public static void findElementAndClickWE(WebDriver driver, WebElement we, By locator) {
        WebElement element = we.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public static void handleCaptcha() throws InterruptedException {
        Thread.sleep(10000);  // Adjust the time as needed to handle CAPTCHA manually
    }
}