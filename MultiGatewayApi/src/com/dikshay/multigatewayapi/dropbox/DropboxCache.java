package com.dikshay.multigatewayapi.dropbox;

import java.util.HashMap;

public class DropboxCache {
	static DropboxCache dropboxcache;
	public static HashMap<Long,Dropbox> gDropboxInstanceMap = new HashMap<Long,Dropbox>();
	
	public static DropboxCache getInstance()
	{
		if(dropboxcache == null)
		{
			dropboxcache = new DropboxCache();
		}
		return dropboxcache;
	}
	public void setDropboxObject(Long pId,Dropbox pDropbox)
	{
		gDropboxInstanceMap.put(pId, pDropbox);
	}
	public Dropbox getDropboxObject(Long pId)
	{
		return gDropboxInstanceMap.get(pId);
	}
}
