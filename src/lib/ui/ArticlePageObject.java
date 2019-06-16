package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private static final String LINK_NAME = "//*[@class='android.view.ViewGroup']/*[@text='%s']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleTitle(String articleName) {
        return this.waitForElementPresent(By.xpath("(//android.view.View[@content-desc='Java'])[1]"),
                "Cannot find " + articleName + " title on the page", 10).getAttribute("name");
    }

    public void clickByLinkWithText(String linkText) {
        this.waitForElementAndClick(By.xpath(String.format(LINK_NAME, linkText)), "Broken " + linkText + "link", 5);
    }

    public void newReadingListCreation(String readingListName) {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"), "Article menu bookmark doesn't work", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Got it')]"), "There isn't the notification 'Add articles to a list for reading later...'", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"), "Create button doesn't work", 5);
        waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"), readingListName, "Cannot find text input", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'OK')]"), "The OK button is absent", 5);
    }

    public void backToThePreviousPage() {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"), "Cannot open the manu", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_back']"), "The action overflow back doesn't work", 5);
    }

    public void openArticleMenu() {
        this.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"), "Article menu bookmark doesn't work", 5);
    }

    public void openMyListsPage() {
        this.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"), "Cannot open the menu", 5);
        this.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_reading_lists']"), "The action overflow reading lists doesn't work", 5);
    }

    public void assertThatElementPresentOnThePage(String articleTitle) {
        this.assertElementPresent(By.xpath("(//android.view.View[@content-desc='Java'])[1]"), "We've not found the article title with name \"" + articleTitle + "\"");
    }
}
