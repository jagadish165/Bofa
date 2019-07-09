package com.bofa.bofalogin;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetOTPviaMail {
	private String baseUrl;
	  private WebDriver driver;
	  public static String code;

	  @BeforeClass
	  public void setup(){
		System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  baseUrl = "https://www.gmail.com";
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }
	  @Test (priority=2)	
	  public void f() throws InterruptedException {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("jagadish.dabbiru");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
		element.sendKeys("youngster0613");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//input[@placeholder = 'Search mail']")).sendKeys("\"Bank of America Alert: One Time Password\"");
		driver.findElement(By.xpath("//button[@aria-label='Search Mail']")).click();
		//driver.findElement(By.xpath("//span[text()='Bank of America Alert: One Time Password']")).click();
		driver.findElement(By.xpath("//div[text()='Inbox']")).click();
		String fulltext = driver.findElement(By.xpath("(//font/span[contains(text(),'Password')][@class= 'il'][1]/..)[last()]")).getText();
		System.out.println(fulltext);
		String delimiter =  "\\d{1,6}";
		Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE); 
		Matcher m = pattern.matcher(fulltext); 
	    m.find();
		code = m.group();
		System.out.println(code);
		//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label ='Delete'][@data-tooltip = 'Delete']")));
		//Thread.sleep(10000);
		List<WebElement> elements = driver.findElements(By.cssSelector("div[title = 'Delete']>div>div"));
		for(WebElement ele: elements) {
		Actions action = new Actions(driver);
		action.click(ele).perform();
		}
		driver.quit();
		
	  }
}
