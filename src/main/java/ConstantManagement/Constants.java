package ConstantManagement;

public interface Constants {
    public String BASE_URL = "https://niftygateway.com/collections";
    public String MARKET_PLACE_URL = "https://niftygateway.com/marketplace";
    public String LOCAL_CHROME_PATH = "C://Users//Zahraa Marchawala//Downloads//chromedriver_win32 (1)//chromedriver.exe";
    public String LOGIN_XPATH = "//a[contains(@href,'/new-login')]";
    public String EMAIL_ID = "email";
    public String PASSWORD_ID = "password";
    public String LOGIN_SUBMIT_XPATH = "//button[@type='submit']";
    public String MARKET_PLACE_XPATH = "(//a[contains(@href,'/marketplace')])[1]";
    public String WHOLE_PAGE_DATA = "//*[@id='root']/div/div[2]/div[2]/div/div[2]";
    public String INDIVIDUAL_ITEMS_COUNT = "//div[contains(@class, 'MuiCardContent-root')]";
    public String ELEMENTS = "(//div[contains(@style,'background-image')] | //video/..)";
}
