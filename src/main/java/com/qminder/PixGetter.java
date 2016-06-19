package com.qminder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class PixGetter{  
	
	@Autowired
	Environment env;

	static List<WebElement> imageurls;  
	   
	
	public void startProcessing(){
		   // Initialize Firefox driver
		 String path =  "geckodriver.exe";
		 System.setProperty("webdriver.gecko.driver", path);
		 WebDriver driver = new MarionetteDriver();
	     String websiteaddress = "https://www.instagram.com/";  
	     //Go to website   
	     driver.get(websiteaddress);  
	     WebElement loginClick = new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/span/button")));
	     loginClick.click();
	     WebElement email = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='email']")));
	     email.sendKeys(env.getProperty(PixGetterConstants.fbId));
	     WebElement password = driver.findElement(By.xpath(".//*[@id='pass']"));
	     password.sendKeys(env.getProperty(PixGetterConstants.fbPassword));
	     WebDriverWait wait = new WebDriverWait(driver, 30);
	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='loginbutton']")));
	     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     driver.findElement(By.xpath(".//*[@id='loginbutton']")).click();
	     WebElement search = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='react-root']/section/nav/div/div/div/div[1]/input")));
	     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     search.click();
	     search.sendKeys(env.getProperty(PixGetterConstants.clientName));
	     WebElement name = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='react-root']/section/nav/div/div/div/div[1]/div[2]/div[2]/div/a[1]")));
	     name.click();
	     WebElement loadMore = new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='react-root']/section/main/article/div/div[3]/a")));
	     loadMore.click();
	     int loops = Integer.parseInt(env.getProperty(PixGetterConstants.noOfLoops));
	     int l = 0;
	     do {
	    	 driver.findElement(By.xpath(".//*[@id='react-root']/section/main/article/div/div[1]/div[1]")).sendKeys(Keys.END);
	    	 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	 l++;
	     } while( l < loops );
	     imageurls = driver.findElements(By.xpath(".//*[@id='react-root']/section/main/article/div//img")); 
	     downloadImages(imageurls);
	     driver.quit(); 
	}
	
   
   private void downloadImages(List<WebElement> imageurls2){
	   String image = null;
	     //get all images url  
	     for (int m = 0; m < imageurls.size(); m++) {
			try {
				image = imageurls.get(m).getAttribute("src");
				if( image != null && !("").equals(image)){
		    	 	 String[] srcContents = image.trim().split("\\/");  
		    		 System.out.println(image + "  " + srcContents[srcContents.length - 1]); 
		    		 String imageName = srcContents[srcContents.length - 1].split("\\?")[0];
		    		 System.out.println(imageName);
		    		 //download image  
					 URL url = new URL(image.trim());  
					 InputStream in = new BufferedInputStream(url.openStream());  
					 OutputStream out = new BufferedOutputStream(new FileOutputStream(env.getProperty(PixGetterConstants.downloadDir)+ "\\" + imageName));  
					 for (int i; (i = in.read()) != -1;) { 
		                 out.write(i);
		                 }  
		             in.close();  
		             out.close();  
	           	 }  
			} catch (Exception e) {
				continue;
			}
	     } 
   }
 }  
