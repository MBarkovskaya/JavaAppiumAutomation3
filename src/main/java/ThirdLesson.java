import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class ThirdLesson {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/hdd/Projects_Mariya/study/JavaAppiumAutomation/apks/org.wikipedia_10280_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

//        driver.rotate(ScreenOrientation.LANDSCAPE);

//        driver.runAppInBackground(2);

    @Test
    public void saveTwoArticleTest() {
        String firstArticleText = "Java";
        String secondArticleText = "Java (programming language)";
        String folderName = "newFolder";
        String folderDescriptionItemTitle = "1 article, 4.16 MB";

//open Search Wikipedia page with searching input field
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find SKIP button", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Cannot find search input", 5);
//search "Java" and get to the referenceJava page
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), firstArticleText, "Cannot find search input", 5);
        waitForElementAndClick(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", firstArticleText)), "Broken " + firstArticleText + "link", 5);
//create new reading list and to get back to the page with "Java" searching
        newReadingListCreation("newFolder");
        backToThePreviousPage();
//open the "Java (programming language)" article and add the article to created reading list
        waitForElementAndClick(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", secondArticleText)), "Broken " + secondArticleText + "link", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"), "Article menu bookmark doesn't work", 5);
        waitForElementAndClick(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", folderName)), "Broken " + folderName + "link", 5);
//remove one article from the reading list
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"), "Cannot open the menu", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']"), "The action overflow reading lists doesn't work", 5);
        waitForElementAndClick(By.xpath("//*[@class='android.widget.LinearLayout']/*[@text='No thanks']"), "There isn't the notification 'Sync reading lists'", 10);
        waitForElementAndClick(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", folderName)), "Cannot open the new reading list", 5);
        swipeElementToLeft(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", secondArticleText)), "The saved article was not found");
//open the reading list and make sure another article is in the reading list
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/reading_list_toolbar']/*[@class='android.widget.ImageButton']"), "Cannot get back to the My list", 5);
        checkTitleText("text", folderDescriptionItemTitle,
                By.xpath("//*[@class='android.view.ViewGroup'][2]/*[@class='android.widget.TextView'][2]"));
        waitForElementAndClick(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", folderName)), "Cannot open the reading list", 5);
        waitForElementAndClick(By.xpath("//*[@class='android.view.ViewGroup']/*[@resource-id='org.wikipedia:id/item_reading_list_statistical_description']"),
                "Cannot open the reading list", 5);

//open another article link from the list and make sure the article title into the reading list is equal to the article title opened by reference
        String articleTitleInFolder = waitForElementPresent(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", firstArticleText)),
                "Cannot find " + firstArticleText + "textReference on the page").getAttribute("text");
        waitForElementAndClick(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", firstArticleText)), "Cannot open the reference", 5);
        WebElement articleTitleOpenedByReference = waitForElementPresent(By.xpath("(//android.view.View[@content-desc='Java'])[1]"),
                "Cannot find " + firstArticleText + " title on the page", 10);
        Assert.assertEquals(articleTitleInFolder, articleTitleOpenedByReference.getAttribute("content-desc"));
    }


    private WebElement waitForElementPresent(By locator, String erMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(erMsg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitForElementPresent(By locator, String erMsg) {
        return waitForElementPresent(locator, erMsg, 5);
    }

    private WebElement waitForElementAndClick(By locator, String erMsg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, erMsg, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By locator, String value, String erMsg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, erMsg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By locator, String erMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(erMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action.press(x, startY).waitAction(timeOfSwipe).moveTo(x, endY).release().perform();
    }

    protected void swipeElementToLeft(By locator, String errMsg) {
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

    private void newReadingListCreation(String readingListName) {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"), "Article menu bookmark doesn't work", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Got it')]"), "There isn't the notification 'Add articles to a list for reading later...'", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"), "Create button doesn't work", 5);
        waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"), readingListName, "Cannot find text input", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'OK')]"), "The OK button is absent", 5);
    }

    private void backToThePreviousPage() {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"), "Cannot open the manu", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_back']"), "The action overflow back doesn't work", 5);
    }

    private void checkTitleText(String attributeName, String textTitle, By locator) {
        WebElement titleElement = waitForElementPresent(locator, "Cannot find " + textTitle + "title on the page");
        String searchingTitle = titleElement.getAttribute(attributeName);

        Assert.assertEquals("We see unexpected title", textTitle, searchingTitle);
    }
}
