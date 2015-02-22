package com.dikshay.multigatewayapi.dropbox;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dikshay.multigatewayapi.Constants;
import com.dikshay.multigatewayapi.interfaces.ICommand;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.DbxWebAuthNoRedirect;

public class DropboxUtility implements ICommand{
	DbxAppInfo dbxappinfo;
	DbxRequestConfig dbxrequestconfig;
	DbxWebAuthNoRedirect dbxwebauthnoredirect;
	static DropboxUtility dropboxutility;
	DbxWebAuth dbxwebauth;

	public DbxWebAuth getDbxWebAuth(){
		return dbxwebauth;
	}
	public DbxAppInfo getDbxAppinfo(){
		if(dbxappinfo==null)
		{
			dbxappinfo = new DbxAppInfo(Constants.DROPBOX_APP_KEY,Constants.DROPBOX_APP_SECRET);
		}
		return dbxappinfo;
	}
	public DbxRequestConfig getDbxRequestConfig()
	{
		if(dbxrequestconfig==null)
		{
			dbxrequestconfig = new DbxRequestConfig("MultiGatewayApi", Locale.getDefault().toString());
		}
		return dbxrequestconfig;
	}
	public DbxWebAuthNoRedirect getDbxWebAuthNoRedirect()
	{
		if(dbxwebauthnoredirect==null)
		{
			dbxwebauthnoredirect = new DbxWebAuthNoRedirect(getDbxRequestConfig(), getDbxAppinfo());
		}
		
		return dbxwebauthnoredirect;
	}
	public static DropboxUtility getInstance()
	{
		if(dropboxutility==null)
		{
			dropboxutility = new DropboxUtility();
		}
		return dropboxutility;
	}
	public String getAuthorizationUrlForDropbox()
	{	
	
		String authorizeUrl = getDbxWebAuthNoRedirect().start();
		authorizeUrl += "&redirect_uri=" + Constants.REDIRECT_URI_FOR_DROPBOX + "&force_reapprove=" + Constants.TRUE;
		return authorizeUrl;
	}
	@Override
	public boolean execute(HashMap<String, String> pRequestParameters,
			HashMap<String, Object> pRequestStorage,
			HashMap<String, Object> pResponseStorage) throws Exception {
		HttpServletRequest lRequest = (HttpServletRequest) pRequestStorage.get(Constants.SERVLET_REQUEST_COMMAND);
		HttpServletResponse lResponse = (HttpServletResponse) pRequestStorage.get(Constants.SERVLET_RESPONSE_COMMAND);
		HttpSession lSession = lRequest.getSession(true);
		String sessionKey = "dropbox-auth-csrf-token";
		Dropbox dropbox = new Dropbox();
		DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(lSession,sessionKey);
		dbxrequestconfig = new DbxRequestConfig("MultiGatewayApi", Locale.getDefault().toString());
		dbxappinfo = new DbxAppInfo(Constants.DROPBOX_APP_KEY,Constants.DROPBOX_APP_SECRET);
		String redirectUri = Constants.REDIRECT_URI_FOR_DROPBOX;
		dbxwebauth = new DbxWebAuth(dbxrequestconfig, dbxappinfo, redirectUri, csrfTokenStore);
		
		dropbox.setDbxAppInfo(dbxappinfo);
		dropbox.setDbxRequestConfig(dbxrequestconfig);
		dropbox.setDbxWebAuth(dbxwebauth);
		DropboxCache.getInstance().setDropboxObject(new Long(1), dropbox);
		String authorizeurl = dbxwebauth.start();
		lResponse.sendRedirect(authorizeurl);
		// TODO Auto-generated method stub
		return false;
	}
}
