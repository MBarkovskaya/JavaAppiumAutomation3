package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/hdd/Projects_Mariya/study/JavaAppiumAutomation/apks/org.wikipedia_10280_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL(appiumURL), capabilities);
    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        super.tearDown();
    }

}
