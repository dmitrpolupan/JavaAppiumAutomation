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
import java.util.regex.Pattern;

public class MainPageObject
{
    protected AppiumDriver _driver;

    public MainPageObject(AppiumDriver driver)
    {
        _driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String errorMessage)
    {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage);
        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String expectedText, String errorMessage)
    {
        WebElement element = waitForElementPresent(locator, "Cannot find element for test", 5);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(errorMessage, expectedText, elementText);
    }

    public int getCountOfWebElements(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        List<WebElement> list = _driver.findElements(by);
        return list.size();
    }

    public boolean isElementDisplayed(String locator)
    {
        By by = this.getLocatorByString(locator);
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

    public List<String> getArticlesName(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        waitForElementPresent(locator, errorMessage, timeoutInSeconds);
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

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes)
    {
        By by = this.getLocatorByString(locator);
        int alreadySwipes = 0;
        while(_driver.findElements(by).size() == 0)
        {
            if(alreadySwipes > maxSwipes)
            {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage)
    {
        WebElement element = waitForElementPresent(
                locator,
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

    public void assertElementNotPresent(String locator, String errorMessage)
    {
        int amountOfElements = getCountOfWebElements(locator, errorMessage, 5);
        if(amountOfElements > 0)
        {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutSeconds);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locatorWithType)
    {
        String[] expandedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = expandedLocator[0];
        String locator = expandedLocator[1];

        if(byType.equals("xpath"))
        {
            return By.xpath(locator);
        }
        else if(byType.equals("id"))
        {
            return By.id(locator);
        }
        else
        {
            throw new IllegalArgumentException("Cannot get type of locator. Locatoor - " + locator);
        }
    }
}
