package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DragAndDropPage {

	public DragAndDropPage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		ReusableCommandsPage reusable = new ReusableCommandsPage(driver);
	}
	
	@AndroidFindBy(id="io.appium.android.apis:id/drag_dot_1")
	private WebElement firstDotElement;
	
	@AndroidFindBy(id="io.appium.android.apis:id/drag_dot_2")
	private WebElement secondDotElement;
	
	@AndroidFindBy(className = "android.view.View")
	private List<WebElement> noOfDots;
	
	public void validateDragAndDrop() {
		int noOfDotsBefore = noOfDots.size();
		ReusableCommandsPage.dragAndDropAnElement(firstDotElement, secondDotElement);
		Assert.assertTrue(noOfDotsBefore!= noOfDots.size());
	}
	
}
