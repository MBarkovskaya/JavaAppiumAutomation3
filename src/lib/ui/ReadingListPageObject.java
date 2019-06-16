package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReadingListPageObject extends MainPageObject {

    private static final String
            LINK_NAME = "//*[@class='android.view.ViewGroup']/*[@text='%s']",
            BACK_TO_MY_LISTS_BUTTON = "//*[@resource-id='org.wikipedia:id/reading_list_toolbar']/*[@class='android.widget.ImageButton']";

    public ReadingListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void removeElementBySwipe(String linkedText) {
        this.swipeElementToLeft(By.xpath(String.format(LINK_NAME, linkedText)), "The saved article was not found");
    }

    public void clickOnBackToMyListsPage() {
        this.waitForElementAndClick(By.xpath(BACK_TO_MY_LISTS_BUTTON), "Cannot get back to the My list", 5);
    }

    public WebElement getArticleNameWebElementFromReadingList(String articleName){
        return this.waitForElementPresent(By.xpath(String.format("//*[@class='android.view.ViewGroup']/*[@text='%s']", articleName)),
                "Cannot find " + articleName + "textReference on the page");
    }

    public void clickByLinkWithText(String linkText) {
        this.waitForElementAndClick(By.xpath(String.format(LINK_NAME, linkText)), "Broken " + linkText + "link", 5);
    }
}
