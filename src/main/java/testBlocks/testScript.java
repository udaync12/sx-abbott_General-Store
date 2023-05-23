package testBlocks;

import org.testng.annotations.Test;
import utilities.ReadExcelData;

public class testScript {
    @Test(priority = -1)
    public void testInput() throws Exception{
       ReadExcelData input = new ReadExcelData(System.getProperty("user.dir")+"\\src\\main\\resources\\ExcelInput\\General_Store_Input_Master.xlsx");
        String val = input.getDeviceConf("DeviceName");
        System.out.println("Cust Name: "+val);

        val = input.getDeviceConf("PlatformName");
        System.out.println("Country: " +val);
        System.out.println("User Directory: " + System.getProperty("user.dir"));

    }
}
