package com.cw.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cw.qa.base.BaseClass;
import com.cw.qa.pages.NDTVHomePage;
import com.cw.qa.pages.NDTVWeatherPage;

public class NDTVHomePageTest extends BaseClass{
	
	NDTVHomePage ndtvhomeobj;
	NDTVWeatherPage ndtvweatherpage;
	
	public NDTVHomePageTest(){
		super(); //We are calling super class constructor i.e. we are calling BaseClass constructor because we have to initialize properties
	}
	
	@BeforeMethod
	public void setup(){
		initialize();// since we have called the constructor above, we will not get null pointer exception for objects used in this method
		ndtvhomeobj = new NDTVHomePage();
	}
	
	@Test(priority = 1)
	public void comapreTitle(){
		String title = ndtvhomeobj.pageTitle();
		Assert.assertEquals(title, "NDTV: Latest News, India News, Breaking News, Business, Bollywood, Cricket, Videos & Photos");
	}
	
	@Test(priority = 2)
	public void navigatetoWeatherPageTest(){
		ndtvweatherpage = ndtvhomeobj.navigatetoWeatherPage();
	}
	
	@AfterMethod
	public void  wrapup()
	{
		driver.quit();
	}

}
