package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        MY_LIST_FOLDER_ELEMENT_TPL,
        MY_LISTS_FOR_EXAMPLE;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    private static String getMyListFolderElement(String name_of_folder)
    {
        return MY_LIST_FOLDER_ELEMENT_TPL.replace("{FOLDER}", name_of_folder);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page!",
                30
        );
    }

    public String getArticleTitle()
    {
        WebElement title_element = this.waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {   if(Platform.getInstance().isAndroid()) {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                40
        );
    } else {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                40
        );
    }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        By by = this.getLocatorByString(MY_LISTS_FOR_EXAMPLE);
        if(driver.findElements(by).size() != 0) {
            this.waitForElementAndClick(
                    ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find 'Got it' tip overlay",
                    5
            );

            this.waitForElementAndClear(
                    MY_LIST_NAME_INPUT,
                    "Cannot find input to set name of articles folder",
                    5
            );

            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    name_of_folder,
                    "Cannot put text into articles folder input",
                    5
            );

            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot press OK button",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    getMyListFolderElement(name_of_folder),
                    "Cannot find and click the folder in MyList with name: " + name_of_folder,
                    15
            );
        }
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                15
        );
    }

    public void addArticleToExistingFolderInMyLists(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                getMyListFolderElement(name_of_folder),
                "Cannot find and click the folder in MyList with name: " + name_of_folder,
                15
        );
    }

    public void addArticleToMySaved()
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
                );
    }
}