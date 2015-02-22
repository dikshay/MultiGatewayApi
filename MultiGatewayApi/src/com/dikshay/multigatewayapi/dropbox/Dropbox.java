package com.dikshay.multigatewayapi.dropbox;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;







import com.dikshay.multigatewayapi.interfaces.ICommand;
import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuth;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import com.dikshay.multigatewayapi.Constants;

public class Dropbox{
	Long lId;
	DbxAppInfo lDbxAppInfo;
	DbxRequestConfig lDbxRequestConfig;
	DbxWebAuth lDbxWebAuth;
	public void setId(Long pId)
	{
		lId = pId;
	}
	public Long getId()
	{
		return lId;
	}
	public DbxAppInfo getDbxAppInfo()
	{
		return lDbxAppInfo;
	}
	public void setDbxAppInfo(DbxAppInfo pDbxAppInfo)
	{
		lDbxAppInfo = pDbxAppInfo;
	}
	public DbxRequestConfig getDbxRequestConfig()
	{
		return lDbxRequestConfig;
	}
	public void setDbxRequestConfig(DbxRequestConfig pDbxRequestConfig)
	{
		lDbxRequestConfig = pDbxRequestConfig;
	}
	public DbxWebAuth getDbxWebAuth()
	{
		return lDbxWebAuth;
	}
	public void setDbxWebAuth(DbxWebAuth pDbxWebAuth)
	{
		lDbxWebAuth = pDbxWebAuth;
	}
}
