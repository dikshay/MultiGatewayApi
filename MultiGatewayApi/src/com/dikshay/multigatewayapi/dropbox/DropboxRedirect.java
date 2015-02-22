package com.dikshay.multigatewayapi.dropbox;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dikshay.multigatewayapi.Constants;
import com.dikshay.multigatewayapi.interfaces.ICommand;
import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxWebAuth;

public class DropboxRedirect implements ICommand{

	static Logger log = null;
	static{
		log = Logger.getLogger(Dropbox.class);
	}
	@Override
	public boolean execute(HashMap<String, String> pRequestParameters,
			HashMap<String, Object> pRequestStorage,
			HashMap<String, Object> pResponseStorage) throws Exception {
		/**
		 * @author Dikshay
		 * @desc code for dropbox implementation
		 */
		log.info("Logging Successful");
	/*	DbxAppInfo dbxappinfo = new DbxAppInfo(Constants.DROPBOX_APP_KEY,Constants.DROPBOX_APP_SECRET);
		DbxRequestConfig requestconfig = new DbxRequestConfig("MultiGatewayApi", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webauth = new DbxWebAuthNoRedirect(requestconfig, dbxappinfo);
		
		String authorizeUrl = webauth.start();
		//here we are generating an authorization url
		//The URL is provided to the user so that the user can authorize the application.
		//Also use a callback url so that the user returns back to the application after authorizing the application
		System.out.println(authorizeUrl);
		((HttpServletResponse) pRequestStorage.get(Constants.SERVLET_RESPONSE_COMMAND)).sendRedirect(authorizeUrl);
		System.out.println("Please enter code");
		String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
		log.info("code" + code);
		DbxAuthFinish dbxfinish = webauth.finish(code);
		String accessToken = dbxfinish.accessToken;
		log.info("AccessToken: " + accessToken);
		DbxClient dbxclient = new DbxClient(requestconfig, accessToken);
		DbxAccountInfo dbxaccountinfo = dbxclient.getAccountInfo();
		System.out.println("Name: " + dbxaccountinfo.displayName);
		System.out.println("Country: " + dbxaccountinfo.country);
		System.out.println("Trying to upload a file");
		File inputFile = new File("D:\\MultiGatewayApi_Logs\\GeneratedLog.log");
		FileInputStream inputStream = new FileInputStream(inputFile);
		DbxEntry.File uploadedfile = dbxclient.uploadFile("/GeneratedLog.log", DbxWriteMode.add(), inputFile.length(), inputStream);
		System.out.println("Uploaded file : " + uploadedfile);
		System.out.println("Listing of files");
		DbxEntry.WithChildren listing = dbxclient.getMetadataWithChildren("/");
		for(DbxEntry child: listing.children){
			System.out.println(child.name + " "+child.toString());
		}
		System.out.println("downloading file");
		FileOutputStream outputstream = new FileOutputStream("GeneratedLog.log");
		DbxEntry.File downloadedfile = dbxclient.getFile("/GeneratedLog.log", null, outputstream);
		System.out.println(downloadedfile.toString());*/
		HttpServletRequest lRequest = (HttpServletRequest) pRequestStorage.get(Constants.SERVLET_REQUEST_COMMAND);
		String code = pRequestParameters.get("code");
		Dropbox dropbox = DropboxCache.getInstance().getDropboxObject(new Long(1));
		DbxWebAuth dbxWebAuth = dropbox.getDbxWebAuth();
		
		DbxAuthFinish dbxauthfinish = dbxWebAuth.finish(lRequest.getParameterMap());
		//DbxAuthFinish dbxauthfinish = DropboxUtility.getInstance().getDbxWebAuthNoRedirect().finish(code);
		String accessToken =  dbxauthfinish.accessToken;
		DbxClient dbxclient = new DbxClient(dropbox.getDbxRequestConfig(), accessToken);
		DbxAccountInfo dbxaccountinfo = dbxclient.getAccountInfo();
		System.out.println("Display name" + dbxaccountinfo.displayName);
		System.out.println("Country" + dbxaccountinfo.country);
		return false;
	}

	


}
