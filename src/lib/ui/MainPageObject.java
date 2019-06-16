package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(By locator, String erMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(erMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementPresent(By locator, String erMsg) {
        return waitForElementPresent(locator, erMsg, 5);
    }

    public WebElement waitForElementAndClick(By locator, String erMsg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, erMsg, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By locator, String value, String erMsg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, erMsg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By locator, String erMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(erMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    public void swipeElementToLeft(By locator, String errMsg) {
        WebElement element = waitForElementPresent(locator, errMsg, 10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(150)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    public void checkTitleText(String attributeName, String textTitle, By locator) {
        WebElement titleElement = waitForElementPresent(locator, "Cannot find " + textTitle + "title on the page");
        String searchingTitle = titleElement.getAttribute(attributeName).substring(0, 9);

        Assert.assertEquals("We see unexpected title", textTitle, searchingTitle);
    }

    public WebElement assertElementPresent(By locator, String errMsg) {
        try {
            WebElement element = driver.findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
            String defaultMessage = "An element '" + locator.toString() + "' supposed to be present.";
            throw new AssertionError(defaultMessage + " " + errMsg);
        }
    }
}
