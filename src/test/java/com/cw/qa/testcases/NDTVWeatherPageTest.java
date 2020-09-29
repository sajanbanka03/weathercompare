package com.cw.qa.testcases;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cw.qa.base.BaseClass;
import com.cw.qa.pages.NDTVHomePage;
import com.cw.qa.pages.NDTVWeatherPage;
import com.cw.qa.util.TestUtil;

public class NDTVWeatherPageTest extends BaseClass{
	NDTVHomePage ndtvhomeobj;
	NDTVWeatherPage ndtvweatherpage;
	String sheetName = "NDTV";
	
	public NDTVWeatherPageTest(){
		super(); //We are calling super class constructor i.e. we are calling BaseClass constructor because we have to initialize properties
	}
	
	@BeforeMethod
	public void setup(){
		initialize();// since we have called the constructor above, we will not get null pointer exception for objects used in this method
		ndtvhomeobj = new NDTVHomePage();
		ndtvweatherpage = ndtvhomeobj.navigatetoWeatherPage();
	}
	
	@DataProvider
	public List<String> getCity() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{
		List<String> testcity =  new ArrayList<String>();
		testcity = TestUtil.ReadData(sheetName);
		return testcity;
	}

	@Test(priority = 2, dataProvider = "getCity")
	public void validatecitywithTemperatutre(String city){
		ndtvweatherpage.SearchCity(city);
	}
	
	@Test(priority = 1, dataProvider = "getCity")
	public void validateWeatherDetails(String city)
	{
		ndtvweatherpage.SearchCity(city);
		ndtvweatherpage.captureTemp();
	}
	
	@AfterMethod
	public void  wrapup()
	{
		driver.quit();
	}
}
