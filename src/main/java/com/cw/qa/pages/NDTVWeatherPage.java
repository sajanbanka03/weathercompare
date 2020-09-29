package com.cw.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cw.qa.base.BaseClass;

public class NDTVWeatherPage extends BaseClass
{
	public String temp;
	//ObjectRepository
	@FindBy(id = "messages")
	WebElement citynames;
	
//	@FindBy(css = "input:checked[type='checkbox']")
//	List<WebElement> citycheckboxes;
	
	@FindBy(id = "searchBox")
	WebElement citySearchBox;
	
	@FindBy(id = "Bhubaneswar")// update the code to pass cityname from dataprovider
	WebElement cityName;
	
	@FindBy(xpath = "//div[@class='temperatureContainer']")
	WebElement tempDetails;
	
	@FindBy(className = "tempRedText")
	WebElement temperature;
	
	@FindBy(xpath = "//div[@class = 'leaflet-popup-content']")
	WebElement weatherDetails;
	
	public NDTVWeatherPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String verifyWeatherPagetitle()
	{
		return driver.getTitle();
	}
	
	public void SearchCity(String city)
	{
		if(citynames.isDisplayed())
		{
			List<WebElement> checkboxes = citynames.findElements(By.cssSelector("input:checked[type='checkbox']"));
			for(WebElement we : checkboxes)
			{
				we.click();
			}
			
			if(citySearchBox.isDisplayed())
			{
				citySearchBox.click();
				citySearchBox.sendKeys(city);
				if(cityName.isDisplayed())
				{
					cityName.click();
				}
				
			}
			
			Assert.assertTrue(tempDetails.isDisplayed());
		}
		
	}
	
	public double captureTemp(){
		if(tempDetails.isDisplayed())
		{
			temperature.click();
			Assert.assertTrue(weatherDetails.isDisplayed());
			if(weatherDetails.isDisplayed())
			{
				temp = temperature.getText();
				System.out.println("Temp is: "+temp);
			}
		}
		
		double ndtvtemp = Double.parseDouble(temp.substring(0, 2));
		 System.out.println("After splitting the temp"+ndtvtemp);
		 
		 return ndtvtemp;
	}
	
	
}
