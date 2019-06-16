package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testAssertTitle() {
        String articleTitle = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
//open Search Wikipedia page with searching input field
        searchPageObject.initSearchInput();
//search "Java" and get to the referenceJava page
        searchPageObject.typeSearchLine(articleTitle);
        articlePageObject.clickByLinkWithText(articleTitle);
        articlePageObject.assertThatElementPresentOnThePage(articleTitle);
    }

}
