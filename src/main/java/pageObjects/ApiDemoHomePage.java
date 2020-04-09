package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ApiDemoHomePage {
	public ApiDemoHomePage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(uiAutomator = "text(\"Preferences\")")
	private WebElement menuPreferences;
	
	@AndroidFindBy(uiAutomator = "text(\"Views\")")
	private WebElement menuViews;
	
	public void openPreferences() {
		menuPreferences.click();
	}
	
	public void openViews() {
		menuViews.click();
	}
}
