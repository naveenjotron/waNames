package StepDefinition;


import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import commons.ExcelUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WAnames {
	
	WebDriver driver;
	
	String driverPath=System.getProperty("user.dir")+"/CommonFiles/geckodriver.exe";

	@Given("I want to write a step with precondition")
	public void i_want_to_write_a_step_with_precondition() {
		
	System.setProperty("webdriver.gecko.driver", driverPath);
    driver = new FirefoxDriver();
    try{			
        
        File file = new File("Cookies.data");							
        FileReader fileReader = new FileReader(file);							
        BufferedReader Buffreader = new BufferedReader(fileReader);							
        String strline;			
        while((strline=Buffreader.readLine())!=null){									
        StringTokenizer token = new StringTokenizer(strline,";");									
        while(token.hasMoreTokens()){					
        String name = token.nextToken();					
        String value = token.nextToken();					
        String domain = token.nextToken();					
        String path = token.nextToken();					
        Date expiry = null;					
        		
        String val;			
        if(!(val=token.nextToken()).equals("null"))
		{		
//        	expiry = new Date(val);
        	expiry = java.text.DateFormat.getDateInstance().parse(val);
        }
        
        @SuppressWarnings("deprecation")
		Boolean isSecure = new 	Boolean(token.nextToken()).booleanValue();
        Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);			
        System.out.println(ck);
        driver.manage().addCookie(ck); // This will add the stored cookie to your current session					
        }		
        }		
        }catch(Exception ex){					
        ex.printStackTrace();			
        }		
        
    driver.get("https://web.whatsapp.com");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Given("some other precondition")
	public void some_other_precondition() {
	    
	}

	@When("whatsapp tab is open in a browser")
	public void whatsapp_tab_is_open_in_a_browser() {
		
	
	}
	
	@When("loaded all the messages")
	public void loaded_all_the_messages() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		WebElement proPic = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".YtmXM > div:nth-child(1) > img")));
		if(proPic.isDisplayed()) {
			System.out.println("whatsapp is loaded");
		}
	}
//	@Test
	@Given("^open specified conversation (.*)$")
	public void open_specified_conversation(String CnvrstnName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
	    System.out.println("Specified converstion is : "+CnvrstnName);
	    
	    Thread.sleep(3000);
	    WebElement search= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("._13NKt")));
	    search.click();
	    search.sendKeys(CnvrstnName);
	    Thread.sleep(3000);
	    String cssSelector = "span[title='"+CnvrstnName+"']";
	    System.out.println(cssSelector);
	    WebElement result= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
	   
	    String resultText = result.getText();
	    boolean resultCheck = resultText.contains(CnvrstnName);
	  
	  if(resultCheck=true) {
		  System.out.println("***Conversation Found***");
		  result.click();
	  }
	}
	
//	@Then("Repeat the action until the end or specified number {int}")
//	public int repeat_number(int int1) {
////		int m=int1;
//	    System.out.println("Range of list= "+int1);
//	    return int1;
//	}

	@When("^read person name (.*) and read list name (.*) and No:of words (.*)$")
	public void read_person_name_and_read_list_name(String name, String listName, int int1) throws IOException, InterruptedException {
//		this.repeat_number(int1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		int m=int1;
		
		String listName1="AnimalNames";
		int stringCompare1 = listName1.compareTo(listName);
		System.out.println(stringCompare1);
		String listName2="swearWords";
		int stringCompare2 = listName2.compareTo(listName);
		System.out.println(stringCompare2);
		
		if(stringCompare1==0) {
			for(int x=0;x<=m; x++) {
			String word = ExcelUtility.getList1CellData(x, 1);
//			System.out.println(name+" is "+word);
			Thread.sleep(2000);
			WebElement messageArea = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[title='Type a message']")));
			messageArea.click();
			messageArea.sendKeys("\n"+name+" is "+word);
			messageArea.sendKeys(Keys.RETURN);
			System.out.println(name+" is "+word);
		}	}
			else if(stringCompare2==0){
				for(int x=0;x<=m; x++) {
				String word = ExcelUtility.getList2CellData(x, 0);
				System.out.println(word);
				
				Thread.sleep(1000);
				WebElement messageArea = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[title='Type a message']")));
				messageArea.click();
				messageArea.sendKeys("\n"+name+" is "+word);
				messageArea.sendKeys(Keys.RETURN);
				System.out.println(name+" is "+word);
			}
		}
	}

	@Then("type defined message and send")
	public void type_defined_message_and_send() throws InterruptedException {
	    Thread.sleep(10000);
	    driver.quit();
	}

	
}
