import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static ConstantManagement.Constants.*;

public class NiftyGetProductData {

    public WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", LOCAL_CHROME_PATH);
        //Create a map to store  preferences



        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable( LogType.PERFORMANCE, Level.ALL );
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("goog:loggingPrefs", logPrefs);



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


        for(int j = 1; j <=5; j++) {
            for (int i = 1; i <= elements.size(); i++) {
                //String text = driver.findElement(By.xpath("//div[contains(@class, 'MuiCardContent-root')]"+"["+i+"]")).getText();

               /* if(texts.contains(text)){
                    break;
                }
                else
                    texts.add(text);
*/
                driver.findElement(By.xpath(ELEMENTS + "[" + i + "]")).click();
                new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div[2]/div[5]/div/div/div[1]/div/text")));

                String url = driver.getCurrentUrl();

               String[] localid = url.split("/");

                AddExcel addExcel = new AddExcel();
                addExcel.setItemName(driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[5]/div/div/div[1]/div/text")).getText());
                addExcel.setOriginalPrimaryMarket(driver.findElement(By.xpath("//p[contains(text(),'original primary market price:')]/../p[2]")).getText());
                addExcel.setAvgResalePrice(driver.findElement(By.xpath("//p[contains(text(),'Average Resale Price')]/../p[2]")).getText());
                addExcel.setPriceChangedFromPrimaryMarket(driver.findElement(By.xpath("//p[contains(text(),'price change from primary market')]/../p[2]")).getText());
                addExcel.setHighestAvgBid(driver.findElement(By.xpath("//p[contains(text(),'highest active bid')]/../p[2]")).getText());
                addExcel.setLowestActiveAsk(driver.findElement(By.xpath("//p[contains(text(),'lowest active ask')]/../p[2]")).getText());
                addExcel.setSecondayMarketVolume(driver.findElement(By.xpath("//p[contains(text(),'Secondary Market Volume')]/../p[2]")).getText());
                addExcel.setDateCreated(driver.findElement(By.xpath("//p[contains(text(),'Date Created')]/../p[2]")).getText());
                addExcel.setArtistName(driver.findElement(By.xpath("//*[contains(text(),'Created by')]//../a/div//text")).getText());
                addExcel.setLastSoldPrice(driver.findElement(By.xpath("//text[contains(text(),'This Edition Last Sold For:')]/../text[2]")).getText());
                addExcel.setSecondarySales(driver.findElement(By.xpath("//p[contains(text(),'Number of Secondary Sales')]/../p[2]")).getText());
                addExcel.setPrimarySales(driver.findElement(By.xpath("//p[contains(text(),'Number of Primary Sales')]/../p[2]")).getText());
                addExcel.setLocalId(localid[6]);

                driver.findElement(By.xpath("//*[contains(text(),'Created by')]//../a/div//text")).click();
                if(driver.findElements(By.xpath("//a[contains(@href,'twitter.com/')]")).size()>0) {
                    System.out.println("Twitter URL " + driver.findElement(By.xpath("//a[contains(@href,'twitter.com/')]")).getAttribute("href"));
                    addExcel.setTwitterURL(driver.findElement(By.xpath("//a[contains(@href,'twitter.com/')]")).getAttribute("href"));
                }
                else
                    System.out.println("NO Twitter URLs found");

                if(driver.findElements(By.xpath("//a[contains(@href,'instagram.com/')]")).size()>0) {
                    System.out.println("Twitter URL " + driver.findElement(By.xpath("//a[contains(@href,'instagram.com/')]")).getAttribute("href"));
                    addExcel.setInstagramURl(driver.findElement(By.xpath("//a[contains(@href,'instagram.com/')]")).getAttribute("href"));
                }
                else
                    System.out.println("NO instagram URLs found");

                driver.navigate().back();
                ExcelWrite.write(addExcel);
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

