package captcha.captchathing;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.*;
public class AppTest {
	public static void main(String args[]) throws IOException, TesseractException, InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "/Users/apple/Downloads/chromedriver"); 
	      
         WebDriver driver= new ChromeDriver() ;
   
        driver.manage().window().maximize();
   
   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
   driver.get("https://secure6.i-doxs.net/Shredit/onetimevalidate.aspx");
   
   WebElement Accounts_field=driver.findElement(By.name("ctl00$Main$ACCNO"));
   
   Accounts_field.sendKeys("12234767855");
   
   
   File src = driver.findElement(By.id("Main_XtremeCaptcha1_cimg")).getScreenshotAs(OutputType.FILE);
   String path = System.getProperty("user.dir") + "/screens/captcha.png" ;
   FileHandler.copy(src,new File(path));
     
ITesseract image = new Tesseract();
String imageText = image.doOCR(new File(path));

 String FinalText = imageText.split("below")[1].replaceAll("[^a-zA-Z0-9]", " ");
System.out.println("Final Captcha" + FinalText );

driver.findElement(By.id("Main_txtCaptcha")).sendKeys(FinalText);

driver.close();

  
   
		
	}
}
