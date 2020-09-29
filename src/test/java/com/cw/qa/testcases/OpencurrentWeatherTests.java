package com.cw.qa.testcases;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cw.qa.base.BaseClass;
import com.cw.qa.restAPI.OpenWeatherCurrentPage;

public class OpencurrentWeatherTests extends BaseClass
{
	String serviceURL;
	String apiURL;
	String reqURL1;
	String reqURL2;
	String apiKey;
	String uri;
	OpenWeatherCurrentPage openweatherobj;
	String queryParam;
	String url;
	public OpencurrentWeatherTests()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		//initialize();
		openweatherobj = new OpenWeatherCurrentPage();
		serviceURL = properties.getProperty("serviceURL");
		apiURL = properties.getProperty("apiURL");
		reqURL1 = properties.getProperty("reqURL1");
		reqURL2 = properties.getProperty("reqURL2");
		apiKey = properties.getProperty("apiKey");
		uri = serviceURL+apiURL+reqURL1+reqURL2+apiKey;
		queryParam = properties.getProperty("queryParam");
		url = properties.getProperty("baseurl");
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException
	{
		
		openweatherobj.getRestAPI(uri);
		openweatherobj.getRestAPI(url,"null","null",queryParam,"get");
	}
	
//	@AfterMethod
//	public void wrapup()
//	{
//		driver.quit();
//	}

}
