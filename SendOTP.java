package com.bofa.bofalogin;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SendOTP {
  private String baseUrl;
  private WebDriver driver;
@BeforeClass
  public void setup(){
	System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver_win32\\chromedriver.exe");
  driver = new ChromeDriver();
  baseUrl = "https://ssologon-prd.sm.bankofamerica.com/ssoconnect/sendOTP.html";
  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  @Test (priority=1)	
  public void f() {
	driver.get(baseUrl);
	driver.findElement(By.id("USER")).sendKeys("zknozxc");;
	driver.findElement(By.id("PASSWORD")).sendKeys("Work3boi");;
	driver.findElement(By.xpath("//input[@name='signon']")).click();
	driver.findElement(By.xpath("//input[@name='signon']")).click();
    driver.quit();
  }
}
