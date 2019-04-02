package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	//1.GET Method without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpGet httpget = new HttpGet(url); // http get Request
	CloseableHttpResponse closableHttpesponse = httpClient.execute(httpget);	// hit the GET url
	
	return closableHttpesponse;
	
	}
	
	//1.GET Method with Header
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {	
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpGet httpget = new HttpGet(url); // http get Request
	
	for(Map.Entry<String, String> entry: headerMap.entrySet()) {
		httpget.addHeader(entry.getKey(), entry.getValue());
	}
	CloseableHttpResponse closableHttpesponse = httpClient.execute(httpget);	// hit the GET url	
	return closableHttpesponse;
	
	}
	
	//POST Method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); // post call
		httpPost.setEntity(new StringEntity(entityString)); // for payload
		
		// for headers
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closableHttpesponse = httpClient.execute(httpPost);
		return closableHttpesponse;
		
		
	}
	
		

}
