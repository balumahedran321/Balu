package Week1;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Azure {
	
	@Test
	public void evaluationTWOTc() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		ChromeDriver driver = new ChromeDriver();
		System.out.println("Browser Launched");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		System.out.println("Browser Maximized");
		
		//1) Go to https://azure.microsoft.com/en-in/
			driver.get("https://azure.microsoft.com/en-in/");
			System.out.println("Successfully URL Loaded 1");

		//2) Click on Pricing
			driver.findElementById("navigation-pricing").click();
			System.out.println("Successfully Click on Pricing");

		//3) Click on Pricing Calculator
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Pricing calculator')]")));
			driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
			System.out.println("Successfully Pricing Calculator clicked");

		//4) Click on Containers
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Containers']")));
			driver.findElementByXPath("//button[text()='Containers']").click();
			System.out.println("Successfully Containers clicked");

		//5) Select Container Instances
			driver.findElementByXPath("(//button[@title='Container Instances']//span[text()='Container Instances'])[2]").click();
			System.out.println("Successfully Select Container Instances");

		//6) Click on Container Instance Added View
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View']")));
			driver.findElementByXPath("//a[text()='View']").click();
			System.out.println("Successfully Click on Container Instance Added View");

		//7) Select Region as "South India"
			WebElement regionElement = driver.findElementByXPath("(//select[@name='region'])[1]");
			Select sec = new Select(regionElement);
			sec.selectByVisibleText("South India");
			System.out.println("Successfully Select Region South India selected");
			
		//8) Set the Duration as 180000 seconds	
			//driver.findElement(By.name("seconds")).sendKeys(Keys.chord(Keys.CONTROL,"a"),"180000");
			driver.findElementByXPath("(//div[@class='wa-textNumber']//input[@name='seconds'])[1]").sendKeys(Keys.BACK_SPACE);
			driver.findElementByXPath("(//div[@class='wa-textNumber']//input[@name='seconds'])[1]").sendKeys(Keys.BACK_SPACE);
			driver.findElementByXPath("(//div[@class='wa-textNumber']//input[@name='seconds'])[1]").sendKeys("180000");
			System.out.println("Successfully Set the Duration as 180000 seconds	");
			
		//9) Select the Memory as 4GB
	    	
	    	WebElement memory = driver.findElement(By.name("memory"));
	    	Select memSelect = new Select(memory);
	    	memSelect.selectByValue("4");
	    	System.out.println("Successfully Select the Memory as 4GB");
	    	
	    //10) Enable SHOW DEV/TEST PRICING
	    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("devtest-toggler"))).click();
	    	//driver.findElement(By.className("toggler-slide ")).click();
	    	System.out.println("Successfully Enable SHOW DEV/TEST PRICING");
	    	Thread.sleep(3000);
	    	
	   	//11) Select Indian Rupee  as currency
	    	
	    	WebElement currency1 = driver.findElement(By.xpath("//select[contains(@class,'select currency-dropdown')]"));
	    	Select currSelect = new Select(currency1);
	    	currSelect.selectByValue("INR");    	
	    	System.out.println("Successfully Select Indian Rupee  as currency");
	    	
	    //12) Print the Estimated monthly price
	    	
	    	String monthlyCost = driver.findElement(By.xpath("//span[text()='Monthly cost']//parent::div/span[2]/span")).getText();
	    	String substring = monthlyCost.substring(1, 7);
	    	double monthlyPrice = Double.parseDouble(substring);
	    	System.out.println("Successfully Estimated monthly price is : "+ monthlyPrice);
	    	Thread.sleep(3000);
	    	
	    //13) Click on Export to download the estimate as excel
	    	driver.findElementByXPath("//button[text()='Export']").click();
	    	Thread.sleep(5000);
	    	File file = new File("C:\\Users\\Admin\\Desktop\\Balu\\Eclipse\\ExportedEstimate.xlsx");
		
	    	if(file.exists())
	    	{
			System.out.println("Successfully Estimate Downloaded successfully");
	    	}
	    	else
	    	{
			System.out.println("Estimate does not exists in the expected folder");
	    	}

		
		//15) Navigate to Example Scenarios and Select CI/CD for Containers
    	
	    	WebElement expScenario = driver.findElement(By.xpath("//a[text()='Example Scenarios']"));
	    	action.moveToElement(expScenario).click().build().perform();
    		
	    	driver.findElement(By.xpath("//span[text()='CI/CD for Containers']")).click();
	    	System.out.println("Successfully Navigate to Example Scenarios and Select CI/CD for Containers ");
	    	Thread.sleep(3000);
    		
	    //16) Click Add to Estimate
    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to estimate']"))).click();
	    	System.out.println("Successfully Click Add to Estimate");
	    	Thread.sleep(3000);
	    	
	    //17) Enable SHOW DEV/TEST PRICING
		action.moveToElement(driver.findElementByXPath("//div[contains(@class,'toggler-slide')]")).click().build().perform();
		System.out.println("Successfully SHOW DEV/TEST PRICING clicked");
		Thread.sleep(3000); 

		//18) Export the Estimate*/
		action.moveToElement(driver.findElementByXPath("//button[text()='Export']")).click().build().perform();
		Thread.sleep(5000); 
		File file1 = new File("C:\\\\Users\\\\Admin\\\\Desktop\\\\Balu\\\\Eclipse\\ExportedEstimate (1).xlsx");
		if(file1.exists())
		{
			System.out.println("Estimate export successful");
		}
		else
		{
			System.out.println("Estimate download is not successful");
		}
		Thread.sleep(5000);
		//	driver.close();
}
}
