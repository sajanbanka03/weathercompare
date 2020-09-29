package com.cw.qa.testcases;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cw.qa.base.BaseClass;
import com.cw.qa.pages.NDTVHomePage;
import com.cw.qa.pages.NDTVWeatherPage;
import com.cw.qa.restAPI.OpenWeatherCurrentPage;
import com.cw.qa.util.TestUtil;

public class WeatherComparatorTest extends BaseClass
{
	NDTVHomePage ndtvhomeobj;
	NDTVWeatherPage ndtvweatherpage;
	String serviceURL;
	String apiURL;
	String reqURL1;
	String reqURL2;
	String apiKey;
	String uri;
	OpenWeatherCurrentPage openweatherobj;
	double variance;
	double tempdiff;
	String sheetName = "NDTV";
	
	public WeatherComparatorTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialize();
		ndtvhomeobj = new NDTVHomePage();
		ndtvweatherpage = ndtvhomeobj.navigatetoWeatherPage();
		openweatherobj = new OpenWeatherCurrentPage();
		serviceURL = properties.getProperty("serviceURL");
		apiURL = properties.getProperty("apiURL");
		reqURL1 = properties.getProperty("reqURL1");
		reqURL2 = properties.getProperty("reqURL2");
		apiKey = properties.getProperty("apiKey");
		uri = serviceURL+apiURL+reqURL1+reqURL2+apiKey;
		
	}
	@DataProvider
	public List<String> getCity() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{
		List<String> testcity =  new ArrayList<String>();
		testcity = TestUtil.ReadData(sheetName);
		return testcity;
	}
	@Test(dataProvider = "getCity")
	public void compareWeather(String city) throws ClientProtocolException, IOException{
		ndtvweatherpage.SearchCity(city);
		double ndtvtemp = ndtvweatherpage.captureTemp();
		double openweathertemp = (openweatherobj.getRestAPI(uri) - 273.15);
		variance = Double.parseDouble(properties.getProperty("variance"));
		if(ndtvtemp>openweathertemp)
		{
			tempdiff = ndtvtemp - openweathertemp;
		}
		else if(ndtvtemp<openweathertemp)
			tempdiff = openweathertemp - ndtvtemp;
			
		if(tempdiff <= variance || tempdiff == 0)
		{
			System.out.println("TestCasePassed");
		}
		else
			System.out.println("Difference is not under the variance range, hence failed");
		
	}
	
	@AfterMethod
	public void wrapup(){
		driver.quit();
	}
}
