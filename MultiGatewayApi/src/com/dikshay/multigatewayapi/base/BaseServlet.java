package com.dikshay.multigatewayapi.base;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dikshay.multigatewayapi.Application;
import com.dikshay.multigatewayapi.Constants;

public class BaseServlet extends HttpServlet{

	/**
	 * 
	 */
	private HashMap<String,Object> gCommands = new HashMap<String,Object>();
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest pRequest,HttpServletResponse pResponse)
	{
		doPost(pRequest,pResponse);
	}
	@Override
	public void doPost(HttpServletRequest pRequest,HttpServletResponse pResponse)
	{	
		HashMap<String,String> pRequestParameters;
		HashMap<String,Object>	pRequestStorage = new HashMap<String,Object>();
		HashMap<String,Object> pResponseStorage = new HashMap<String,Object>();
		pRequestParameters = getParameterNames(pRequest);
		try {
			pRequestStorage.put(Constants.SERVLET_REQUEST_COMMAND, pRequest);
			pRequestStorage.put(Constants.SERVLET_RESPONSE_COMMAND, pResponse);
			Application.getInstance().getCommand(pRequest).execute(pRequestParameters, pRequestStorage, pResponseStorage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
