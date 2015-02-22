package com.dikshay.multigatewayapi;

import javax.servlet.http.HttpServlet;
import com.dikshay.multigatewayapi.Application;


public class Loader extends HttpServlet{
	/**
	 * @author Dikshay
	 * @desc This is the first class to be loaded is it is specified in web.xml
	 */
	@Override
	public void init(){

		Application.getInstance().setCommand();
		
	}
}
