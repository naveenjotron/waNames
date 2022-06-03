package commons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class getCookies {
	
	WebDriver driver;
	String driverPath=System.getProperty("user.dir")+"\\CommonFiles\\geckodriver.exe";
	@Test
public void readCookies() throws InterruptedException {
	System.setProperty("webdriver.gecko.driver", driverPath);
    driver = new FirefoxDriver();
    driver.get("https://web.whatsapp.com");
    driver.manage().window().maximize();
   				
    // Input Email id and Password If you are already Register		
//    driver.findElement(By.name("username")).sendKeys("abc123");							
//    driver.findElement(By.name("password")).sendKeys("123xyz");							
//    driver.findElement(By.name("submit")).click();		
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	
	WebElement proPic = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".YtmXM > div:nth-child(1) > img")));
	if(proPic.isDisplayed()) {
		System.out.println("whatsapp is loaded");
	}
    	Thread.sleep(5000);	
    // create file named Cookies to store Login Information		
    File file = new File("Cookies.data");							
    try		
    {	  
        // Delete old file if exists
		file.delete();		
        file.createNewFile();
        FileWriter fileWrite = new FileWriter(file);							
        BufferedWriter Bwrite = new BufferedWriter(fileWrite);							
        // loop for getting the cookie information 		
        	
        // loop for getting the cookie information 		
        for(Cookie ck : driver.manage().getCookies())							
        {			
            Bwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));																									
            Bwrite.newLine();             
        }			
        Bwrite.close();			
        fileWrite.close();	
        
    }
    catch(Exception ex)					
    {		
        ex.printStackTrace();			
    }		
}		
@AfterTest
public void quit() throws InterruptedException {
	Thread.sleep(10000);
	driver.quit();
}
}
