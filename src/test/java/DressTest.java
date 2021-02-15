import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DressTest {

    private static RemoteWebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void test() {
    	System.out.println("starting test");
    	System.out.println("navigating to site http://automationpractice.com/index.php");
        driver.get("http://automationpractice.com/index.php");
        System.out.println("searching for dress");
        driver.findElementByXPath("//*[@id=\"search_query_top\"]").sendKeys("dress");
        System.out.println("submitting the search");
        driver.findElementByXPath("//*[@id=\"searchbox\"]/button").submit();
        System.out.println("checking for dress in name of items returned");
        driver.findElementsByXPath("//*[@id='center_column']/ul/li[1]/div/div[2]/h5/child").forEach(item -> {
        	 assertTrue(item.getCssValue("title").contains("Dress"));
        });
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
        System.out.println("driver closed");
    }
    
    // Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
    public static ChromeOptions chromeCfg() {
     Map<String, Object> prefs = new HashMap<String, Object>();
     ChromeOptions cOptions = new ChromeOptions();
      
     // Settings
     prefs.put("profile.default_content_setting_values.cookies", 2);
     prefs.put("network.cookie.cookieBehavior", 2);
     prefs.put("profile.block_third_party_cookies", true);

     // Create ChromeOptions to disable Cookies pop-up
     cOptions.setExperimentalOption("prefs", prefs);

     return cOptions;
     }
    
}