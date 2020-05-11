package Week1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HpLapDay4 {

	private static final String Output = null;

	@Test
	public void homePage() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://store.hp.com/in-en/default");

		try {
			driver.findElementByXPath("//span[contains(@class,'close-icon')]").click();
		} catch (Exception e) {
		}

		HpLapDay4 hp = new HpLapDay4();
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

	// 2) Mouse over on Laptops menu and click on Pavilion
		Thread.sleep(3000);
		act.moveToElement(driver.findElementByXPath("(//a[@class='level-top ui-corner-all has-dropdown-menu'])[2]")).build().perform();
		Thread.sleep(3000);
		act.moveToElement(driver.findElementByXPath("(//span[text()='Pavilion'])[1]/parent::a")).click().build().perform();
		System.out.println("Mouse over on Laptops menu and click on Pavilion : 2");
		// driver.findElementByXPath("(//span[text()='Pavilion'])[1]/parent::a").click();
		
	//	driver.findElementByXPath("//span[@class='spinwheel_close_icon']").click();
		System.out.println("Circle Closed");
		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		System.out.println("Dont MissOut Box");
		Thread.sleep(2000);

	//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		System.out.println("Select Processor");
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
		System.out.println("Select Intel Core i7 : 3");
		Thread.sleep(3000);
		
		js.executeScript("window.scrollBy(0,550)");
		System.out.println("Scroll Successfully");
		Thread.sleep(2000);
		
	//4) Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("(//span[text()='More than 1 TB'])/parent::a").click();		
		System.out.println("Click on More than 1TB : 4");
		
	//5) Select Sort By: Price: Low to High
		/*WebElement Price = driver.findElementById("sorter");
		Price.click();		
		Select sel = new Select(Price);
		sel.selectByVisibleText("Price : Low to High");
		System.out.println("Select Sort By: Price: Low to High : 5"); */
		
		WebElement sort = driver.findElementById("sorter");
		//sort.click();
		Select sorts = new Select(sort);
		sorts.selectByIndex(2);	
		System.out.println("Select Sort By: Price: Low to High : 5");
		Thread.sleep(2000);
		
	//6) Print the First resulting Product Name and Price
		String LapTitle = driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText();
		System.out.println("First resulting Product Name 6 :" +LapTitle);
		
		String Price1 = driver.findElementByXPath("(//span[@class='price'])[2]").getText();
		String replaceAll = Price1.replaceAll("\\D", "");
		int Amount = Integer.parseInt(replaceAll);
		System.out.println("Get Amount : " +Amount);
		System.out.println("Successfully Get Product Price Amount : 6");		
		js.executeScript("window.scrollBy(0,750)");
		
	//7) Click on Add to Cart
		Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		System.out.println("Click on Add to Cart : 7");


	//8) Click on Shopping Cart icon --> Click on View and Edit Cart
		Thread.sleep(3000);
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("(//div[@class='primary']//a)[1]").click();
		System.out.println("Click on View and Edit Cart : 8");


	//9) Check the Shipping Option --> Check availability at Pincode
		Thread.sleep(2000);
		//driver.findElementById("block-deliver-heading").click();
		driver.findElementByXPath("//input[@placeholder='Pincode']").sendKeys("600095");
		driver.findElementByXPath("//button[text()='check']").click();
		System.out.println("Check availability at Pincode : 9");
		Thread.sleep(3000);
		
		
		
		//10) Verify the order Total against the product price
		//String ProductPrice = driver.findElementByXPath("//tr[@class='grand totals']//span").getText().replaceAll("\\D", "");
		String ProductPrice = driver.findElementByXPath("//tr[@class='grand totals']//span").getText();
		String replaceAll2 = ProductPrice.replaceAll("\\D", "");
		int ProdPrice = Integer.parseInt(replaceAll2);
		System.out.println("ProductTotal : "  +ProdPrice);
		
		Assert.assertEquals(Amount, ProdPrice);
		System.out.println("order Total against the product price : 10");
		
		//11) Proceed to Checkout if Order Total and Product Price matches
		driver.findElementByXPath("(//button[@id='sendIsCAC'])[1]").click();
		System.out.println("Checkout if Order Total : 11");
		
		//12) Click on Place Order
		driver.findElementByXPath("(//button[@class='action primary checkout'])[6]").click();
		System.out.println(" Click on Place Order : 12");
		
		
		//13) Capture the Error message and Print
		js.executeScript("window.scrollBy(0,950)");
		String ErrorTitle = driver.findElementByXPath("//span[text()='Please specify a payment method.']").getText();
		System.out.println(" Capture the Error message and Print : 13 " +ErrorTitle);
		
		
		//WebElement Capture = driver.findElementByClassName("//div[@class='message notice']");
		File cap = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./target/test.png");
		FileUtils.copyFile(cap, dest);
		System.out.println("Capture the Error Message");
		
		driver.close();
					
	}
}