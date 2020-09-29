package com.cw.qa.restAPI;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.cw.qa.base.BaseClass;
import com.cw.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import okhttp3.Headers;

public class OpenWeatherCurrentPage extends BaseClass
{
	String apiheader;
	String apiparam;
	String method;
	String request;
	HashMap<String,String> headerReq = new HashMap<String,String>();
	HashMap<String,String> paramReq = new HashMap<String,String>();
	HashMap<String,String> queryparamsReq = new HashMap<String,String>();
	public double getRestAPI(String url) throws ClientProtocolException, IOException
	{
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpget = new HttpGet(url);
//		CloseableHttpResponse httpResponse = httpclient.execute(httpget);
//		int status = httpResponse.getStatusLine().getStatusCode();
//		
//		Assert.assertEquals(status, TestUtil.Status_Code_200);
//		//System.out.println("Status code is: "+status);
//		String responsestring = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
//		//System.out.println("API Response in String format: "+responsestring);
//		JSONObject jsonResponse = new JSONObject(responsestring);
//		//System.out.println("API Response in JSON format: "+jsonResponse);
//		double tempfromOpenWeather = Double.parseDouble(TestUtil.getValueByJPath(jsonResponse,"/main/temp"));
//		System.out.println(tempfromOpenWeather);
		
		//to get all headers
//		Header[] headers = httpResponse.getAllHeaders();
//		HashMap<String,String> allheaders = new HashMap<String,String>();
//		for(Header header: headers)
//		{
//			allheaders.put(header.getName(), header.getValue());
//		}
//		
//		System.out.println("Headers of response: "+allheaders);
//		request = "RestAssured.given()";
//		
//		if(headerst != "null")
//		{
//		
//		headerReq  = TestUtil.addvalues(headerst);
//		request = request+".headers(headerReq)";
//		}
//		
//		if(params != "null")
//		{
//		
//		paramReq  = TestUtil.addvalues(params);
//		request = request + ".params(paramReq)";
//		}
//		
//		if(queryparams != "null"){
//		
//		queryparamsReq  = TestUtil.addvalues(queryparams);
//		request = request + ".queryParams(queryparamsReq)";
//		}
//		
//		if(HTTPreq == "get")
//			method = "Method.GET";
//		else if(HTTPreq == "put")
//			method = "Method.PUT";
//		else if(HTTPreq == "post")
//			method = "method.POST";
//		else if(HTTPreq == "delete")
//			method = "Method.DELETE";
//		RestAssured.baseURI = url;
//		//RequestSpecification httpRequest = RestAssured.given().queryParams(arg0).headers(arg0);
//		RequestSpecification httpRequest = RestAssured.given().headers(headerReq).queryParams(queryparamsReq).params(paramReq);
//		Response newResp = httpRequest.request(method);
//		JsonPath jsonevaluatorRA = newResp.jsonPath();
//		double temp = jsonevaluatorRA.getDouble("main.temp");
//		System.out.println("Temperature using rest assured: "+temp);
				
		Response resp = RestAssured.get(url);
		String body = resp.asString();
		JsonPath jsonevaluator = resp.jsonPath();
		double temperature = jsonevaluator.getDouble("main.temp");
		System.out.println("Temperature using rest assured: "+temperature);
		
		//httpclient.close();
		
		return temperature;
	}
	
	public void getRestAPI(String url,String headerst,String params,String queryparams,String HTTPreq) throws ClientProtocolException, IOException
	{
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpget = new HttpGet(url);
//		CloseableHttpResponse httpResponse = httpclient.execute(httpget);
//		int status = httpResponse.getStatusLine().getStatusCode();
//		
//		Assert.assertEquals(status, TestUtil.Status_Code_200);
//		//System.out.println("Status code is: "+status);
//		String responsestring = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
//		//System.out.println("API Response in String format: "+responsestring);
//		JSONObject jsonResponse = new JSONObject(responsestring);
//		//System.out.println("API Response in JSON format: "+jsonResponse);
//		double tempfromOpenWeather = Double.parseDouble(TestUtil.getValueByJPath(jsonResponse,"/main/temp"));
//		System.out.println(tempfromOpenWeather);
		
		//to get all headers
//		Header[] headers = httpResponse.getAllHeaders();
//		HashMap<String,String> allheaders = new HashMap<String,String>();
//		for(Header header: headers)
//		{
//			allheaders.put(header.getName(), header.getValue());
//		}
//		
//		System.out.println("Headers of response: "+allheaders);
		request = "RestAssured.given()";
		
		if(headerst != "null")
		{
		System.out.println("Inside header");
		headerReq  = TestUtil.addvalues(headerst);
		request = request+".headers(headerReq)";
		}
		
		if(params != "null")
		{
			System.out.println("Inside param");
		paramReq  = TestUtil.addvalues(params);
		request = request + ".params(paramReq)";
		}
		
		if(queryparams != "null"){
			System.out.println("Inside queryparam");
		queryparamsReq  = TestUtil.addvalues(queryparams);
		request = request + ".queryParams(queryparamsReq)";
		}
		
		if(HTTPreq == "get")
			method = "Method.GET";
		else if(HTTPreq == "put")
			method = "Method.PUT";
		else if(HTTPreq == "post")
			method = "method.POST";
		else if(HTTPreq == "delete")
			method = "Method.DELETE";
		RestAssured.baseURI = url;
		//RequestSpecification httpRequest = RestAssured.given().queryParams(arg0).headers(arg0);
		//RequestSpecification httpRequest = RestAssured.given().headers(headerReq).queryParams(queryparamsReq).params(paramReq);
		RequestSpecification httpRequest = RestAssured.given().queryParams(queryparamsReq);
		//RequestSpecification newhttpRequest = RestAssured.given().filter(arg0)
		Response newResp = httpRequest.request(method);
		String body = newResp.getBody().toString();
		System.out.println(body);
		JsonPath jsonevaluatorRA = newResp.jsonPath();
		double temp = jsonevaluatorRA.getDouble("main.temp");
		System.out.println("Temperature using rest assured Scalable app: "+temp);
				
//		Response resp = RestAssured.get(url);
//		String body = resp.asString();
//		JsonPath jsonevaluator = resp.jsonPath();
//		double temperature = jsonevaluator.getDouble("main.temp");
//		System.out.println("Temperature using rest assured: "+temperature);
		
		//httpclient.close();
		
		//return temp;
	}
	
	
}
