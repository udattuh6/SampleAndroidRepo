package hema.SampleAndroid;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.ApiDemoHomePage;
import pageObjects.DragAndDropPage;
import pageObjects.ReusableCommandsPage;
import pageObjects.ViewsPage;

public class TestViews extends BaseClass {
	
	Properties prop = new Properties();
	
	
	@Test
	public void testDragAndDrop() throws IOException, InterruptedException {
		String userdir =System.getProperty("user.dir");
		startAppiumServer();

		FileInputStream file = new FileInputStream(userdir + "\\src\\main\\java\\resources\\global.properties");
		prop.load(file);
		AndroidDriver<AndroidElement> driver = driverCapabilities(prop.getProperty("appName"));
		ApiDemoHomePage home = new ApiDemoHomePage(driver);
		home.openViews();
		ViewsPage views = new ViewsPage(driver);
		views.openDragAndDropView();
		DragAndDropPage dragPage = new DragAndDropPage(driver);
		dragPage.validateDragAndDrop();
	}
	
	

}
