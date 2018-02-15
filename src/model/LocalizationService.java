package model;

import java.util.HashMap;

public  class LocalizationService {

	static private String appLanguage = "ENGLISH";
	static private HashMap<String,String> english;
	static private HashMap<String,String> spanish;
	static private HashMap<String,String> galician;
	
	public static void initialize() {
		//This needs to read a JSON in the future, for now it's hardcoded
		english.put("username", "username");
		
	}
	
	
	
}
