package com.crm.GenericLibaray;

import java.util.Iterator;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class is used to create Generic Method
 * @author GAURAV
 *
 */


public class WebDriverUtility {
	
	/**
	 * This method is used to maximize the Window
	 * @param driver
	 */
	
	public void windowMaximize(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used for implicitlyWait.
	 * @param driver
	 */
	
	public void waitUntilPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * This method is used for ExplicityWait.
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisibility(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used for custom wait
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick(WebElement element) throws Throwable
	{
		int count =0;
		while(count<20)
		{
			try
			{
				element.click();
				break;
			}
			catch(Throwable e)
			{
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	/**
	 * This method is used for Selecting the element by index value.
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index)
	{
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * Thi s method is used for Selecting the element by VisibleText
	 * @param element
	 * @param option
	 */
	public void select(WebElement element,String option)
	{
		Select select=new Select(element);
		select.selectByVisibleText(option);
	}
	
	/**
	 * This method is used for mouseHover
	 * @param driver
	 * @param element
	 */
	public void mouseOver(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method is use for perform rightClick.
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method is use for handle the window
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWinTitle)
	{
		Set<String>window=driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		
		while(it.hasNext())
		{
			String winld = it.next();
			String title = driver.switchTo().window(winld).getTitle();
			if(title.contains(partialWinTitle))
			{
				break;
			}
		}
	}
	
	/**
	 * This method is use to handle the alertpop-up and accept
	 * @param driver
	 */
	public  void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 *  This method is use to handle the alertpop-up and Cancel
	 * @param driver
	 */
	
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is use to handle the disable element
	 * @param driver
	 * @param element
	 */
	public void scrollToWebElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y=element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	/**
	 * This method is use to handle the Frame by index
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is use to handle the Frame by element
	 * @param driver
	 * @param element
	 */
	public void switchFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is use to handle the Frame by ID Or Name
	 * @param driver
	 * @param idOrName
	 */
	public void switchFrame(WebDriver driver,String idOrName)
	{
		driver.switchTo().frame(idOrName);
	}
	
	/**
	 * This method is use to take the screenshot  
	 * @param driver
	 * @param screenshotName
	 * @throws IOException
	 */
	public void takeScreenShot(WebDriver driver,String screenshotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    File dest=new File("./ScreenShot/"+screenshotName+".PNG");
	    Files.copy(src, dest);
	}

}
