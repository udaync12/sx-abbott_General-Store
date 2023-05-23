package utilities;
import java.io.File;
import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class GetScreenShot {
    public static String capture(AndroidDriver driver, String screenShotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\test-output\\Screenshots\\"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);

        return dest;
    }
}
