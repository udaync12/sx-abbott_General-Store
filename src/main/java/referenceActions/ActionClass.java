package referenceActions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import pagePOM.gsHomePage;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.touch.offset.PointOption.point;

public class ActionClass {
    static AndroidDriver driver;
    static ExtentReports extent;
    static ExtentTest test;
    static String strText;
    WebElement el = null;
    static boolean chkCond = true;
    static gsHomePage objHomePage;

    public ActionClass() throws MalformedURLException {
    }

    /*public static void swipeUp(AndroidDriver driver){
        int end_y_cord = 600;
        new TouchAction(driver)
                .press(point(240, 1150))
                .moveTo(PointOption.point(240, end_y_cord))
                .release()
                .perform();

    }*/

    /*public static void searchAndSelectCountry(String countryName){
        while (chkCond) {
            try {
                boolean Display = objHomePage.chkCountry(countryName);
                if (Display) {
                    objHomePage.selectCountry(countryName);
                    test = extent.createTest("GS Mobile App: Selected country: " + countryName, "PASSED test case");
                    break;
                }
            } catch (Exception e) {
                objHomePage.swipeUp();
            }
        }
    }*/

    /*public static void addProductsToCart(String productName){
        while (chkCond) {
            try {
                boolean Display = objHomePage.chkProduct(productName);
                if (Display) {
                    //el = driver.findElement(By.xpath("//android.widget.TextView[@text='PG 3']/.."));
                    //strText = el.findElement(By.id("com.androidsample.generalstore:id/productPrice")).getText();
                    strText = objHomePage.getProductPrice(productName);
                    System.out.println("Price of the product: " + strText);
                    objHomePage.addProductToCart(productName);
                    test = extent.createTest("GS Mobile App: Price of the Product " + productName + " is: " + strText, "PASSED test case");
                    break;
                }
            } catch (Exception e) {
                objHomePage.swipeUp();
            }
        }
    }*/
}
