	package com.bofa.bofalogin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


public class Login {
  private String baseUrl;
  private WebDriver driver;
  private String downloadPath;
  @BeforeClass
  public void setup() throws IOException{

	System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver_win32\\chromedriver.exe");
	downloadPath = "C:\\Users\\hp\\Documents\\Citrix";
	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("profile.default_content_setting_values.notifications",2);	
	prefs.put("download.default_directory", downloadPath);
	
	ChromeOptions opts = new ChromeOptions();
	opts.setExperimentalOption("prefs", prefs);
	//opts.addArguments("disable-infobars");
	//opts.addArguments("--disable-notifications");
//	DesiredCapabilities dc = new DesiredCapabilities();
//    dc.setCapability(ChromeOptions.CAPABILITY, opts);
    driver = new ChromeDriver(opts);
  baseUrl = "https://portal-asia.bankofamerica.com/logon/LogonPoint/tmindex.html";
  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }
  @Test (priority=3)	
  public void f() throws IOException, InterruptedException {
	driver.get(baseUrl);
	driver.manage().window().maximize();
	WebDriverWait wait = new WebDriverWait(driver, 20);
	WebElement el = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("login"))));
	el.sendKeys("zknozxc");
	driver.findElement(By.id("passwd1")).sendKeys("Work3boi");
	//String str = JOptionPane.showInputDialog(null, "Enter Captcha", null);
	driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(GetOTPviaMail.code);
	Select sl = new Select(driver.findElement(By.id("domain")));
	sl.selectByVisibleText("ASIA");
	driver.findElement(By.xpath("//a[@id='Logon']")).click();
	Thread.sleep(10000);
	WebElement element = driver.findElement(By.xpath("//a[@id='userMenuBtn'][@href='#']"));
	Actions builder = new Actions(driver);
    Actions seriesOfActions = builder.moveToElement(element).click();
    seriesOfActions.perform();
    Thread.sleep(2000);
	driver.findElement(By.xpath("//a[@id = 'protocolhandler-welcome-installButton']")).click();	
	
	try {
		checkAlert();
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.findElement(By.xpath("//a[@id='protocolhandler-detect-alreadyInstalledLink']")).click();
	File alfiles = new File(downloadPath);
	for(File file: alfiles.listFiles()) {
	    if (!file.isDirectory()) {
	        file.delete();
	    }
	}
	driver.findElement(By.xpath("//img[@alt='KF4390908646D']")).click();
	Thread.sleep(5000);
	
	  // specify your directory
//	Path dir = Paths.get(downloadPath);
//	Optional<Path> lastFilePath = Files.list(dir)    // here we get the stream with full directory listing
//	    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
//	    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field
//	
//	if ( lastFilePath.isPresent() ) // your folder may be empty
//	{
//		File file = new File(lastFilePath.get().toString());
	File alfilesnew = new File(downloadPath);
	while(alfilesnew.listFiles().length==0) {
		System.out.println("length 0");
	}
	for(File file: alfilesnew.listFiles()) {
	    if (!file.isDirectory()) {
	    	Desktop desktop = Desktop.getDesktop();
		    desktop.open(file);
	    }
	}
	    
	  
  }
  public void checkAlert() throws AWTException   {
	    	       
			 Robot robot = new Robot();
	        robot.keyPress(KeyEvent.VK_ENTER);
	    
	}
  @AfterSuite
  public void kill() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("taskkill /im chromedriver.exe /f /t");  
  }
}
