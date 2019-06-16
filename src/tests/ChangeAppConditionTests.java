package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testScreenRotation() {
        String articleTitle = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        driver.rotate(ScreenOrientation.PORTRAIT);

//open Search Wikipedia page with searching input field
        searchPageObject.initSearchInput();
//search "Java" and get to the referenceJava page
        searchPageObject.typeSearchLine(articleTitle);
        articlePageObject.clickByLinkWithText(articleTitle);
        String titleBeforRotation = articlePageObject.getArticleTitle(articleTitle);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = articlePageObject.getArticleTitle(articleTitle);

        assertEquals("Article title have been changed after screen rotation", titleBeforRotation, titleAfterRotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = articlePageObject.getArticleTitle(articleTitle);

        assertEquals("Article title have been changed after screen rotation", titleAfterRotation, titleAfterSecondRotation);
    }
    //        driver.runAppInBackground(2);
}
