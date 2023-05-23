package pagePOM;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class gsCartPage {

    AndroidDriver driver;
    WebElement el;
    String strText, strXpath;

    By prodName = null;
    By prodPrice = null;
    By sendMeEmail = By.xpath("//android.widget.CheckBox[contains(@text, 'Send me e-mails on discounts')]");
    By visitWebsite = By.id("com.androidsample.generalstore:id/btnProceed");

    public gsCartPage(AndroidDriver driver){
        this.driver = driver;
    }

    public String getProductName(int i){
        strXpath = "//android.widget.RelativeLayout["+i+"]";
        strText = driver.findElement(By.xpath(strXpath +"/android.widget.LinearLayout/android.widget.TextView")).getText();
        return strText;
    }

    public String getProductPrice(int i){
        strText = driver.findElement(
                By.xpath("//android.widget.RelativeLayout["+i+"]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView")).getText();
        return strText;
    }

    public String getCartTotal(){
        strText = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        return strText;
    }

    public void clickSendMeEmails(){
        driver.findElement(sendMeEmail).click();
    }

    public void clickVisitWebsite(){
        driver.findElement(visitWebsite).click();
    }
}
