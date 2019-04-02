package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closableHttpesponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		//-->  https://reqres.in/api/users
		url = serviceUrl + apiUrl;
		
		
	}
	
	@Test(priority =1)
	public void getAPITestwithoutHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closableHttpesponse= restClient.get(url);
		
		// status code
		int statusCode = closableHttpesponse.getStatusLine().getStatusCode();
		System.out.println("status Code --->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Sattus Code is not 200");
		
		//JSON String
		String responseString = EntityUtils.toString(closableHttpesponse.getEntity(), "UTF-8");
		
		JSONObject jsonResponse = new JSONObject(responseString);
		System.out.println("Response JSON From API---> " + jsonResponse);
		
		//single value assertion
		//per_page
		String perPageValue = TestUtil.getValueByJpath(jsonResponse, "/per_page");
		System.out.println("The value of per page is "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total
		String totalValue = TestUtil.getValueByJpath(jsonResponse, "/total");
		System.out.println("The value of total is "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from JSON Array
		
		String lastName = TestUtil.getValueByJpath(jsonResponse, "/data[0]/last_name");
		String id = TestUtil.getValueByJpath(jsonResponse, "/data[0]/id");
		String avatar = TestUtil.getValueByJpath(jsonResponse, "/data[0]/avatar");
		String FirstName = TestUtil.getValueByJpath(jsonResponse, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(FirstName);
		
		
		
		// All Headers
		Header[] headersArray = closableHttpesponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("All Headers---> "+ allHeaders );
		
	}
	

	@Test(priority =2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test123");
//		headerMap.put("Auth-token", "12345");
		
		
		
		closableHttpesponse= restClient.get(url, headerMap);
		
		// status code
		int statusCode = closableHttpesponse.getStatusLine().getStatusCode();
		System.out.println("status Code --->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Sattus Code is not 200");
		
		//JSON String
		String responseString = EntityUtils.toString(closableHttpesponse.getEntity(), "UTF-8");
		
		JSONObject jsonResponse = new JSONObject(responseString);
		System.out.println("Response JSON From API---> " + jsonResponse);
		
		//single value assertion
		//per_page
		String perPageValue = TestUtil.getValueByJpath(jsonResponse, "/per_page");
		System.out.println("The value of per page is "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total
		String totalValue = TestUtil.getValueByJpath(jsonResponse, "/total");
		System.out.println("The value of total is "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get the value from JSON Array
		
		String lastName = TestUtil.getValueByJpath(jsonResponse, "/data[0]/last_name");
		String id = TestUtil.getValueByJpath(jsonResponse, "/data[0]/id");
		String avatar = TestUtil.getValueByJpath(jsonResponse, "/data[0]/avatar");
		String FirstName = TestUtil.getValueByJpath(jsonResponse, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(FirstName);
		
		
		
		// All Headers
		Header[] headersArray = closableHttpesponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("All Headers---> "+ allHeaders );
		
	}

}
