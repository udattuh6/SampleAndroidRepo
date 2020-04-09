package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import static java.time.Duration.ofSeconds;

public class ReusableCommandsPage {
	static TouchAction a;
	public ReusableCommandsPage(AndroidDriver<AndroidElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		a = new TouchAction(driver);
	}
	
	public void tapAnElement(WebElement element) {
		
		a.tap(tapOptions().withElement(element(element))).perform();
	}
	
	public static void longPressAndWaitOnAnElement(WebElement element, int duration) {
		a.longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(duration))).release().perform();
	}
	
	public static void longPressAnElement(WebElement element) {
		a.longPress(element(element)).release().perform();
	}
	
	public static void dragAndDropAnElement(WebElement firstDotElement, WebElement secondDotElement) {
		a.longPress(element(firstDotElement)).moveTo(element(secondDotElement)).release().perform();
	}
	
	public static void scrollUntilElementDisplayed(WebElement element, String text) {
		element.findElement(
				MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0))"
						+ ".scrollIntoView(new UiSelector().textMatches(\""+ text+"\")).instance(0)"));
	}
	
}
