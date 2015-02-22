<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.dikshay.multigatewayapi.Constants"%>
    <%@page import="com.dropbox.core.DbxAppInfo" %>
    <%@page import="com.dropbox.core.DbxRequestConfig" %>
    <%@page import="com.dropbox.core.DbxWebAuthNoRedirect" %>
    <%@page import="com.dikshay.multigatewayapi.dropbox.DropboxUtility" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script>
		
		function spitauthorizationurl()
		{
			var lAuthorizationUrl = "<%=DropboxUtility.getInstance().getAuthorizationUrlForDropbox() %>";
			var lHeaderStringArray=new Array();
			var lParameterStringArray = new Array();
			lHeaderStringArray = lAuthorizationUrl.split("?");
			
			var parameterNames = ["locale","client_id","response_type","redirect_uri","force_reapprove"];
			document.getElementById("dropboxform").action = lHeaderStringArray[0];
			
			lParameterStringArray = lHeaderStringArray[1].split("&");
			for(var count=0;count<lParameterStringArray.length;count++)
				{
				for(var parameterNamesCount=0;parameterNamesCount<parameterNames.length;parameterNamesCount++)
				{var lParameterTypeStringArray = lParameterStringArray[count].split("=");
				if(lParameterStringArray[count].indexOf(parameterNames[parameterNamesCount])!=-1)
					{
					document.getElementById(parameterNames[parameterNamesCount]).value=lParameterTypeStringArray[1];
					break;
					}
				
				}
				}
			return true;
		}

		</script>
	</head>
	<body>
		<form id="dropboxform" action = "DropboxUtility.kar" method="POST" >
			
			
			<input  type="submit" 	value="submit"/>
		
		</form>

	</body>
</html>
