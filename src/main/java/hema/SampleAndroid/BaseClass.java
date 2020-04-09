package hema.SampleAndroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	
	public static AppiumDriverLocalService startAppiumServer() {
		
		if(!checkIfServerIsRunnning(4723)) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
	
	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(10000);
	}
	
	public static AndroidDriver<AndroidElement> driverCapabilities(String appName) throws IOException, InterruptedException {
		String userdir =System.getProperty("user.dir");
		FileInputStream file = new FileInputStream(userdir + "\\src\\main\\java\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(file);
		
		File appDir = new File("src");
		File app = new File(appDir, appName);
		
		String device = prop.getProperty("deviceName");
		if(device.contains("emu")) {
			startEmulator();
		}
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@AfterTest
	public static void killExistingServer() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	public static void getScreenShot(String testName) throws IOException {
		/*
		 * File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); Date
		 * dt = new Date(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("dd-MM-yyyy HH:MM:SS"); String userDir =
		 * System.getProperty("user.dir"); FileUtils.copyDirectory(src, new File(userDir
		 * + "\\screenshots\\" + testName + sdf.format(dt).replace(":", "") + ".png"));
		 */
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		String userDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(srcFile, new File(userDir+"\\screenshots\\" + testName + "_" + sdf.format(d).replaceAll(":", "")+".png"));
	}
	
	
	
	
	
	
}
