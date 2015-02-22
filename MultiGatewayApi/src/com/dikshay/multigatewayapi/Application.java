package com.dikshay.multigatewayapi;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;



import com.dikshay.multigatewayapi.interfaces.ICommand;
import com.dikshay.multigatewayapi.dropbox.*;

public class Application {
	
	private static Application application;
	private HashMap<String,ICommand> gCommands = new HashMap<String,ICommand>();
	
	
	/**
	 * @author Dikshay
	 * @desc   The function returns the current instance of Application class. 
	 * @return Application
	 */
	public static Application getInstance()
	{
		if(application==null)
		{
			application = new Application();
		}
		return application;
	}
	public void setCommand(){
		ICommand dropboxRedirectObject = new DropboxRedirect();
		ICommand dropboxUtility = new DropboxUtility();
		gCommands.put(Constants.DROPBOX_REDIRECT_COMMAND, dropboxRedirectObject);
		gCommands.put(Constants.DROPBOX_UTILITY_COMMAND, dropboxUtility);
	}
	public ICommand getCommand(HttpServletRequest pRequest){
		String requestString = pRequest.getRequestURI();
		int slashPosition = requestString.lastIndexOf("/");
		requestString = requestString.substring(slashPosition+1);
		if(requestString.endsWith(".kar"))
		{
			requestString = requestString.substring(0, requestString.length()-4);
		}
		return gCommands.get(requestString);
	}
	
}
