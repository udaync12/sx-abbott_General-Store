package connectDevice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.ReadExcelData;

import java.net.URL;

public class DeviceCapabilities {

    public AndroidDriver AndroidDeviceCapabilities(URL url_appium) throws Exception {
        ReadExcelData input = new ReadExcelData(System.getProperty("user.dir")+"\\src\\main\\resources\\ExcelInput\\General_Store_Input_Master.xlsx");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("deviceName", input.getDeviceConf("DeviceName"));
        dc.setCapability("udid", input.getDeviceConf("UDID"));
        dc.setCapability("platformName", input.getDeviceConf("PlatformName"));
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("app", System.getProperty("user.dir")+"\\src\\main\\resources\\androidApps\\General-Store.apk");
        dc.setCapability("appPackage", input.getDeviceConf("AppPackage"));
        AndroidDriver driver = new AndroidDriver(url_appium, dc);
        System.out.println("Connection established to Appium server with device");

        return driver;
    }
}
