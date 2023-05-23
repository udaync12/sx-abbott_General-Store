package testBlocks;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import static io.appium.java_client.touch.offset.PointOption.point;
import static jdk.nashorn.internal.objects.NativeString.trim;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import pagePOM.gsCartPage;
import pagePOM.gsHomePage;
import org.openqa.selenium.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.GetScreenShot;
import referenceActions.ActionClass;
import utilities.ReadExcelData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class HomePage {
    AndroidDriver driver;;
    URL url_appium = new URL("http://127.0.0.1:4723/wd/hub");
    WebElement el = null;

    public HomePage() throws MalformedURLException {
    }

    /*ExtentHtmlReporter htmlReporter; */
    ExtentReports extent;
    ExtentTest test;
    String strText;
    boolean chkCond = true;
    gsHomePage objHomePage;
    gsCartPage objCartPage;
    ReadExcelData input;


    @BeforeTest
    public void setUp() throws Exception{
        extent = utilities.ExtentReport.startExtentReport();
        driver = new connectDevice.DeviceCapabilities().AndroidDeviceCapabilities(url_appium);
        test = extent.createTest("GS Mobile App: Device and App Setup Completed", "PASSED test case");
        input = new ReadExcelData(System.getProperty("user.dir")+"\\src\\main\\resources\\ExcelInput\\General_Store_Input_Master.xlsx");
        objHomePage = new gsHomePage(driver);
        objCartPage = new gsCartPage(driver);
    }

    @Test(priority = -1)
    public void validateAppLaunch() throws Exception {
        Thread.sleep(5000);

        strText = objHomePage.getToolBarTitle();
        //strText = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        if (strText.equals("General Store")) {
            System.out.println("App Launched Successfully");
            test = extent.createTest("GS Mobile App: Mobile App Launched", "PASSED test case");
        } else {
            System.out.println("App NOT Launched Successfully");
            test = extent.createTest("GS Mobile App: Mobile App Not Launched", "FAILED test case");
        }
    }

    @Test(priority = 1)
    public void gsEnterHomePageDetails() throws Exception {

        objHomePage.clickCountryDropDown();
        Thread.sleep(3000);
        test = extent.createTest("GS Mobile App: Clicked Country Drop-Down", "PASSED test case");

        //Scroll to select the desired Country Name to Shop
        searchAndSelectCountry(input.getCustData("Country"));


        //Add your name to the Filed on the app
        String cName = input.getCustData("CustName");
        String cGender = input.getCustData("CustGender");
        objHomePage.enterCustomerDetails(cName, cGender);
        test = extent.createTest("GS Mobile App: Customer details updated", "PASSED test case");
    }

    @Test(priority = 2)
    public void gsAddProductsToCart() throws Exception {
        //Validating the Product screen is launched successfully

        Thread.sleep(3000);
        strText = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
        if(strText.equals("Products")){
            test = extent.createTest("GS Mobile App: Products screen launched succesfully", "PASSED test case");
            //Adding Products to Cart - PG 3
            addProductsToCart(input.getCustData("Product_1"));
            addProductsToCart(input.getCustData("Product_2"));

        } else {
            test = extent.createTest("GS Mobile App: Products screen did not launch", "FAILED test case");
        }

    }

    @Test(priority = 2)
    public void gsValidateCartItems() throws Exception {

        strText = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();
        test = extent.createTest("GS Mobile App: " + strText + " Products added to cart", "PASSED test case");

        int cartItemCnt = Integer.parseInt(trim(strText));
        //Click on Cart icon to go to cart page
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
        strText = objHomePage.getToolBarTitle();

        //Verify that the cart screen is displayed and that the correct items have been added to the cart
        if(strText.equals("Cart")){
            String prodName, prodPrice = null;
            double cartTotal, prodTotal = 0;

            //Verify that the total purchase amount is correctly displayed
            for(int i = 1; i<=cartItemCnt; i++){
                prodName = objCartPage.getProductName(i);
                prodPrice = objCartPage.getProductPrice(i);
                prodTotal = prodTotal + Double.parseDouble(trim(prodPrice.replace("$", "")));
                System.out.println("Prod Name: " + prodName + " Prod Price: " + prodPrice + " Cart Total: " + prodTotal);
            }
            cartTotal = Double.parseDouble(trim(objCartPage.getCartTotal().replace("$", "")));
            if(cartTotal == prodTotal){
                test = extent.createTest("GS Mobile App: Products total is EQUAL to Cart total", "PASSED test case"
                + "Product Total: $" + prodTotal + " || Cart Total: $" + cartTotal);
            } else {
                test = extent.createTest("GS Mobile App: Products total is EQUAL to Cart total", "FAILED test case"
                        + "Product Total: $" + prodTotal + " || Cart Total: $" + cartTotal);
            }

            //Tap the "Send me emails for discounts" checkbox
            objCartPage.clickSendMeEmails();
            test = extent.createTest("GS Mobile App: Cart validation completed", "PASSED test case");
            objCartPage.clickVisitWebsite();
        }

    }

    @Test(priority = 3)
    public void gsSearchWebsite() throws Exception {
        Thread.sleep(5000);
        chkCond = driver.findElement(By.xpath("//android.view.View[1]/android.widget.Image")).isDisplayed();
        if(chkCond){
            test = extent.createTest("GS Mobile App: Website Launched from Mobile App", "PASSED test case");
            driver.findElement(By.className("android.widget.EditText")).sendKeys("General Store");
            Thread.sleep(3000);
            test = extent.createTest("GS Mobile App: 'General Store' entered in search bar'", "PASSED test case");
            driver.navigate().back();

            Thread.sleep(5000);
            strText = objHomePage.getToolBarTitle();

            if (strText.equals("General Store")) {
                System.out.println("Navigated to Home Page of the App");
                test = extent.createTest("GS Mobile App: Navigated to Home Page of the App from website", "PASSED test case");
            }
        }
    }

    @AfterMethod
    void getResults(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {
            String screenShotPath = GetScreenShot.capture(driver, result.getName());
            System.out.println("Failed Screen shot path: " + screenShotPath);
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(screenShotPath);
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            String screenShotPath = GetScreenShot.capture(driver, result.getName());
            System.out.println("Passed Screen shot path: " + screenShotPath);
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
            test.addScreenCaptureFromPath(screenShotPath);
        }
        else {
            String screenShotPath = GetScreenShot.capture(driver, result.getName());
            System.out.println("SKIP Screen shot path: " + screenShotPath);
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
            test.addScreenCaptureFromPath(screenShotPath);
        }
    }

    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
        extent.flush();
    }

    public void swipeUp(){
        int end_y_cord = 600;
        new TouchAction(driver)
                .press(point(240, 1150))
                .moveTo(PointOption.point(240, end_y_cord))
                .release()
                .perform();

    }

    public void searchAndSelectCountry(String countryName){
        while (chkCond) {
            try {
                boolean Display = objHomePage.chkCountry(countryName);
                if (Display) {
                    objHomePage.selectCountry(countryName);
                    test = extent.createTest("GS Mobile App: Selected country: " + countryName, "PASSED test case");
                    break;
                }
            } catch (Exception e) {
                swipeUp();
            }
        }
    }

    public void addProductsToCart(String productName){
        while (chkCond) {
            try {
                boolean Display = objHomePage.chkProduct(productName);
                if (Display) {
                    strText = objHomePage.getProductPrice(productName);
                    System.out.println("Price of the product: " + strText);
                    objHomePage.addProductToCart(productName);
                    test = extent.createTest("GS Mobile App: Price of the Product " + productName + " is: " + strText, "PASSED test case");
                    break;
                }
            } catch (Exception e) {
                swipeUp();
            }
        }
    }

}
