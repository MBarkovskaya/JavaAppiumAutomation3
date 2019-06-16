package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private static final String
            LINEAR_ANSWERS_TO_SYNC_NOTIFICATION = "//*[@class='android.widget.LinearLayout']/*[@text='{SUBSTRING}']",
            LINK_NAME = "//*[@class='android.view.ViewGroup']/*[@text='%s']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return LINEAR_ANSWERS_TO_SYNC_NOTIFICATION.replace("{SUBSTRING}", substring);
    }

    public void giveAnswerToTheNotificationSync(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXPath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void checkListTitle(String folderDescriptionItemTitle) {
        this.checkTitleText("text", folderDescriptionItemTitle,
                By.xpath("//*[@class='android.view.ViewGroup'][2]/*[@class='android.widget.TextView'][2]"));
    }

    public void clickByLinkWithText(String linkText) {
        this.waitForElementAndClick(By.xpath(String.format(LINK_NAME, linkText)), "Broken " + linkText + "link", 5);
    }
}
