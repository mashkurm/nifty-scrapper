import com.google.common.base.Function;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


import static java.lang.Thread.*;

import static ConstantManagement.Constants.*;

public class NiftyGetProductData {

    public WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", LOCAL_CHROME_PATH);
        //Create a map to store  preferences
        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("profile.default_content_setting_values.notifications", 1);

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void scarpIt() {
        doLogin("mashkurm@hotmail.com", "mashkur786", driver);
        goToMarketPlace();
        getAlltheElementsFromThePage();
        Integer totalItems = getAlltheItemsFromThePage();
        String iterativeXpath = INDIVIDUAL_ITEMS_COUNT;

        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.get("https://niftygateway.com/marketplace");
        List<String> elements = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(INDIVIDUAL_ITEMS_COUNT))).stream().map(element->element.getText()).collect(Collectors.toList());

        Integer totalElements = driver.findElements(By.xpath(ELEMENTS)).size();

        for(int j = 1; j <=2; j++) {
            for (int i = 1; i <= totalElements; i++) {
                driver.findElement(By.xpath(ELEMENTS + "[" + i + "]")).click();
                new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div[2]/div[5]/div/div/div[1]/div/text")));
                String url = driver.getCurrentUrl();
                String[] localid = url.split("/");

            System.out.println("Item name "+driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[5]/div/div/div[1]/div/text")).getText()+
                        "Original primary market price "+driver.findElement(By.xpath("//p[contains(text(),'original primary market price:')]/../p[2]")).getText()+
                        "Average re-sale price "+driver.findElement(By.xpath("//p[contains(text(),'Average Resale Price')]/../p[2]")).getText()+
                        "Local id "+ localid[6]);
                driver.navigate().back();

            }
            driver.findElement(By.xpath("//button[@aria-label='Go to next page']"));
        }
    }

    public void doLogin(String username, String password, WebDriver driver) {
        try {
            driver.findElement(By.xpath(LOGIN_XPATH)).click();
            driver.findElement(By.id(EMAIL_ID)).sendKeys(username);
            driver.findElement(By.id(PASSWORD_ID)).sendKeys(password);
            driver.findElement(By.xpath(LOGIN_SUBMIT_XPATH)).click();
        } catch (Exception e) {
            System.out.println("Something went wrong while logging in");
        }
    }
        public void goToMarketPlace() {
            driver.navigate().to(MARKET_PLACE_URL);
            driver.findElement(By.xpath(MARKET_PLACE_XPATH)).click();
        }

        public WebElement getAlltheElementsFromThePage(){

           WebElement allData =  driver.findElement(By.xpath(WHOLE_PAGE_DATA));
           return allData;
        }

    public int getAlltheItemsFromThePage(){
        Integer totalSize = driver.findElements(By.xpath(INDIVIDUAL_ITEMS_COUNT)).size();
        System.out.println("Total size of the elements = "+totalSize);
        return totalSize;
    }
    }

