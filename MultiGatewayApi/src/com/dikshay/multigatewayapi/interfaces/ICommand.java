package com.dikshay.multigatewayapi.interfaces;

import java.util.HashMap;

public interface ICommand {
	
	
	public boolean execute(HashMap<String,String>pRequestParameters,HashMap<String,Object>pRequestStorage,HashMap<String,Object>pResponseStorage) throws Exception;

	
}
