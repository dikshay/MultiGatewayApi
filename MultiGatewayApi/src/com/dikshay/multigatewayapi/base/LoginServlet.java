package com.dikshay.multigatewayapi.base;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
			
	@Override
	public void doGet(HttpServletRequest pRequest, HttpServletResponse pResponse)
	{
		doPost(pRequest,pResponse);
	}
	@Override
	public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse)
	{
		HashMap<String,String> lRequestParameters = getParameterNames(pRequest);
		String username = lRequestParameters.get("username");
		String password = lRequestParameters.get("password");
		/*
		 * if Login successfull show the next page with list of api
		 * else show error message
		 */
	}
	
	private HashMap<String,String> getParameterNames(HttpServletRequest pRequest)
	{	
		HashMap<String,String> lRequestParameters = new HashMap<String,String>();
		String lParameterKey,lParameterValue;
		String lParameterValues[];
		Enumeration<String> lParameterNames = pRequest.getParameterNames();
		while(lParameterNames.hasMoreElements()){
			lParameterValue = "";
			lParameterKey = (String)lParameterNames.nextElement();
			lParameterValues = pRequest.getParameterValues(lParameterKey);
		
			for(String lValue : lParameterValues)
			{
				lParameterValue += lValue;
			}
			lRequestParameters.put(lParameterKey, lParameterValue);
		}
		return lRequestParameters;
	}
}
