package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            ONBOARDING_SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_RESULTS_LIST = "//*[@resource-id='org.wikipedia:id/search_results_list']",
            SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.id(ONBOARDING_SKIP_BUTTON), "Cannot find and click on SKIP button", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find list of searching results");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INIT_ELEMENT), searchLine, "Cannot find and type into search input", 5);
    }

    public WebElement waitForSearchResultsListElement() {
        return this.waitForElementPresent(By.xpath(SEARCH_RESULTS_LIST), "Cannot find list of searching results");
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click on cancel button", 0);
    }

    public boolean waitForSearchResultsListIsNotPresent() {
        return this.waitForElementNotPresent(By.xpath(SEARCH_RESULTS_LIST),
                "The list of searching results is still on the page", 5);
    }
}