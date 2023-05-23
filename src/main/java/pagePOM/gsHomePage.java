package pagePOM;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.touch.offset.PointOption.point;

public class gsHomePage {
    AndroidDriver driver;
    WebElement el;

   // String cGender = "Female";
   // String country = null;

    By toolBarTitle = By.id("com.androidsample.generalstore:id/toolbar_title");
    By countryDropDown = By.id("com.androidsample.generalstore:id/spinnerCountry");
    By countryToSelect = null;
    By custName = By.id("com.androidsample.generalstore:id/nameField");
    By custGender = null;
    By btnShop = By.id("com.androidsample.generalstore:id/btnLetsShop");
    By product = null;
    By parentProd = null;


    public gsHomePage(AndroidDriver driver){
        this.driver = driver;
    }

    public String getToolBarTitle(){
        return driver.findElement(this.toolBarTitle).getText();
    }

    public void clickCountryDropDown(){
        driver.findElement(this.countryDropDown).click();
    }

    public void setCountryToSelect(String country) {
        this.countryToSelect = By.xpath("//android.widget.TextView[@text='"+country+"']");
    }

    public boolean chkCountry(String whichCountry){
        setCountryToSelect(whichCountry);
        return driver.findElement(this.countryToSelect).isDisplayed();
    }
    public void selectCountry(String whichCountry){
        setCountryToSelect(whichCountry);
        driver.findElement(this.countryToSelect).click();
    }

    public void setProductToSelect(String product) {
        this.parentProd = By.xpath("//android.widget.TextView[@text='"+ product+ "']/..");
        this.product = By.xpath("//android.widget.TextView[@text='"+ product+ "']");
    }
    public boolean chkProduct(String whichProduct){
        setProductToSelect(whichProduct);
        return driver.findElement(this.product).isDisplayed();
    }
    public void addProductToCart(String whichProduct){
        setProductToSelect(whichProduct);
        this.el = this.driver.findElement(this.parentProd);
        this.el.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
    }

    public String getProductPrice(String whichProduct){
        setProductToSelect(whichProduct);
        this.el = this.driver.findElement(this.parentProd);
        return this.el.findElement(By.id("com.androidsample.generalstore:id/productPrice")).getText();
    }

    public void setCustName(String cName){
        driver.findElement(this.custName).sendKeys(cName);
    }

    public void setCustGender(String cGender){
        this.custGender = By.id("com.androidsample.generalstore:id/radio" + cGender);
        driver.findElement(this.custGender).click();
    }

    public void clickLetsShop(){
        this.driver.findElement(btnShop).click();
    }

    public void enterCustomerDetails(String custName, String custGender){
        this.setCustName(custName);
        this.setCustGender(custGender);
        this.clickLetsShop();
    }


}
