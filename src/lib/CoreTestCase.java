package lib;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

public class CoreTestCase extends TestCase {

    protected AppiumDriver _driver;
    protected boolean _isPortraitDefaultState = true;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        _driver = Platform.getInstance().getDriver();

        setupOrientation(_isPortraitDefaultState);
    }

    @Override
    protected void tearDown() throws Exception
    {
        _driver.quit();

        super.tearDown();
    }

    protected void changeOrientation(ScreenOrientation orientation)
    {
        _driver.rotate(orientation);
    }

    protected void backgroundApp(int seconds)
    {
        _driver.runAppInBackground(seconds);
    }

    private void setupOrientation(boolean isPortrait)
    {
        if(isPortrait)
        {
            this.changeOrientation(ScreenOrientation.PORTRAIT);
        }
        else
        {
            this.changeOrientation(ScreenOrientation.LANDSCAPE);
        }
    }
}
