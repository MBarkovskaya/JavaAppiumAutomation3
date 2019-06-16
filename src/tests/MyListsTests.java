package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.ReadingListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveTwoArticle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        ReadingListPageObject readingListPageObject = new ReadingListPageObject(driver);

        String firstArticleText = "Java";
        String secondArticleText = "Java (programming language)";
        String folderName = "newFolder";
        String folderDescriptionItemTitle = "1 article";

//open Search Wikipedia page with searching input field
        searchPageObject.initSearchInput();
//search "Java" and get to the referenceJava page
        searchPageObject.typeSearchLine(firstArticleText);
        articlePageObject.clickByLinkWithText(firstArticleText);
        //create new reading list and to get back to the page with "Java" searching
        articlePageObject.newReadingListCreation(folderName);
        articlePageObject.backToThePreviousPage();
//open the "Java (programming language)" article and add the article to created reading list
        articlePageObject.clickByLinkWithText(secondArticleText);
        articlePageObject.openArticleMenu();
        articlePageObject.clickByLinkWithText(folderName);
//remove one article from the reading list
        articlePageObject.openMyListsPage();
        myListsPageObject.giveAnswerToTheNotificationSync("No thanks");
        myListsPageObject.clickByLinkWithText(folderName);
        readingListPageObject.removeElementBySwipe(secondArticleText);
//open the reading list and make sure another article is in the reading list
        readingListPageObject.clickOnBackToMyListsPage();
        myListsPageObject.checkListTitle(folderDescriptionItemTitle);
        myListsPageObject.clickByLinkWithText(folderName);
        String articleTitleInFolder = readingListPageObject.getArticleNameWebElementFromReadingList(firstArticleText).getAttribute("text");
//open another article link from the list and make sure the article title into the reading list is equal to the article title opened by reference
        readingListPageObject.clickByLinkWithText(firstArticleText);
        String articleTitleOpenedByReference = articlePageObject.getArticleTitle(firstArticleText);

        assertEquals(articleTitleInFolder, articleTitleOpenedByReference);
    }
//
//    Виталий Котов, [10.06.19 15:16]
//    WebElement title_element = waitForTitleElement();
//
//        if (Platform.getInstance().isAndroid()){
//        return title_element.getAttribute("text");
//    } else if (Platform.getInstance().isIOS()) {
//        return title_element.getAttribute("name");
//    } else {
//        return title_element.getText();
//    }

//    private int getAmountOfElements(By locator) {
//        List elements = driver.findElements(locator);
//        return elements.size();
//    }
}
