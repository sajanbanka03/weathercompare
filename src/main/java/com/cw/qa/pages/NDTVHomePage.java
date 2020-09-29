package com.cw.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cw.qa.base.BaseClass;
import com.cw.qa.util.TestUtil;

public class NDTVHomePage extends BaseClass 
{

	//ObjectRepository
	@FindBy(className = "topnavmore")
	WebElement submenu;
	
	@FindBy(className = "noti_wrap")
	WebElement popup;
	
	@FindBy(className = "notnow")
	WebElement notnow;
	
	@FindBy(linkText = "WEATHER")
	WebElement weatherlink;
	
	//@FindBy()
	
	//add an object to verify NDTV logo
	
	public NDTVHomePage()
	{
		PageFactory.initElements(driver, this); //this pointing towards current class
		//driver.get(properties.getProperty("ndtvURL"));
	}
	
	public String pageTitle()
	{
		return driver.getTitle();
	}
	
	public NDTVWeatherPage navigatetoWeatherPage(){ //We have changes return type to NDTVWeatherPage because when we run this function we will land to weather page i..e. it is returning weather page
		if(submenu.isDisplayed())
		{
			if(popup.isDisplayed())
			{
				System.out.println("NDTV pop-up displayed");
				notnow.click();
			}
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			submenu.click();
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			weatherlink.click();
		}
		
		return new NDTVWeatherPage();
	}
	
	
}
