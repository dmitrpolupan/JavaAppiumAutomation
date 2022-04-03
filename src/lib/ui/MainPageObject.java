package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class MainPageObject
{
    protected AppiumDriver _driver;

    public MainPageObject(AppiumDriver driver)
    {
        _driver = driver;
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String errorMessage)
    {
        return waitForElementPresent(by, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage);
        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String expectedText, String errorMessage)
    {
        WebElement element = waitForElementPresent(by, "Cannot find element for test", 5);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(errorMessage, expectedText, elementText);
    }

    public int getCountOfWebElements(By by, String errorMessage, long timeoutInSeconds)
    {
        List<WebElement> list = _driver.findElements(by);
        return list.size();
    }

    public boolean isElementDisplayed(By by)
    {
        boolean flag = true;
        try
        {
            _driver.findElement(by);
        }
        catch(Exception e)
        {
            flag = false;
        }

        return flag;
    }

    public List<String> getArticlesName(By by, String errorMessage, long timeoutInSeconds)
    {
        waitForElementPresent(by, errorMessage, timeoutInSeconds);
        List<WebElement> list = _driver.findElements(by);
        List<String> namesList = new ArrayList<>();
        for(WebElement element : list)
        {
            namesList.add(element.getAttribute("text"));
        }
        return namesList;
    };

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(_driver);
        Dimension size = _driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipes)
    {
        int alreadySwipes = 0;
        while(_driver.findElements(by).size() == 0)
        {
            if(alreadySwipes > maxSwipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }
    }

    public void swipeElementToLeft(By by, String errorMessage)
    {
        WebElement element = waitForElementPresent(
                by,
                errorMessage,
                5
        );
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(_driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    public void assertElementNotPresent(By by, String errorMessage)
    {
        int amountOfElements = getCountOfWebElements(by, errorMessage, 5);
        if(amountOfElements > 0)
        {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutSeconds);
        return element.getAttribute(attribute);
    }
}
