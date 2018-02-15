package model;

import java.util.HashMap;

public  class LocalizationService {

	static private String appLanguage = "ENGLISH";
	static private HashMap<String,String> english = new HashMap<>();
	static private HashMap<String,String> spanish = new HashMap<>();
	static private HashMap<String,String> galician = new HashMap<>();
	
	public static void initialize() {
		//This needs to read a JSON in the future, for now it's hardcoded
		english.put("username", "username");
		spanish.put("username", "usuario");
		galician.put("username", "usuario");
		
		english.put("password", "password");
		spanish.put("password", "contraseña");
		galician.put("password", "contraseña");
		
		english.put("patient", "patient");
		spanish.put("patient", "paciente");
		galician.put("patient", "paciente");
		
		english.put("name", "name");
		spanish.put("name", "nombre");
		galician.put("name", "nome");
		
		english.put("surname", "surname");
		spanish.put("surname", "apellido");
		galician.put("surname", "apellido");
		
		english.put("surname", "surname");
		spanish.put("surname", "apellido");
		galician.put("surname", "apellido");
		
		english.put("id", "i.d");
		spanish.put("id", "d.n.i");
		galician.put("id", "d.n.i");
		
		english.put("nomessages", "no messages");
		spanish.put("nomessages", "sin mensajes");
		galician.put("nomessages", "sin mensaxes");
		
		english.put("adminconsole", "Administrator Console");
		spanish.put("adminconsole", "Consola de Administrador");
		galician.put("adminconsole", "Consola do Administrador");
		
		english.put("searchpatient", "search patient");
		spanish.put("searchpatient", "buscar paciente");
		galician.put("searchpatient", "buscar paciente");
		
		english.put("user", "user");
		spanish.put("user", "usuario");
		galician.put("user", "usuario");
		
		english.put("measure", "measure");
		spanish.put("measure", "medir");
		galician.put("measure", "medir");
		
		english.put("ssn", "s.s.n");
		spanish.put("ssn", "n.s.s");
		galician.put("ssn", "n.s.s");
		
		english.put("login", "login");
		spanish.put("login", "entrar");
		galician.put("login", "entrar");
		
		english.put("doctors", "doctors");
		spanish.put("doctors", "doctores");
		galician.put("doctors", "doctores");
		
		english.put("assistants", "assistants");
		spanish.put("assistants", "técnicos");
		galician.put("assistants", "técnicos");
		
		english.put("search", "search");
		spanish.put("search", "buscar");
		galician.put("search", "buscar");
		
	}
	
	public static String getWord(String word) {
		if(appLanguage == "ENGLISH") {
			return english.get(word);
		}else if(appLanguage == "SPANISH") {
			return spanish.get(word);
		} else if(appLanguage == "GALICIAN") {
			return galician.get(word);
		} else {
			System.out.println("LANGUAGE UNDEFINED: Defaulting to ENGLISH");
			return english.get(word);
		}
	}

	public static void rotate() {
		if(appLanguage == "ENGLISH") appLanguage = "SPANISH";
		else if(appLanguage == "SPANISH") appLanguage = "GALICIAN";
		else if(appLanguage == "GALICIAN") appLanguage = "ENGLISH";
		else {
			System.out.println("ERROR RORATING LANGUAGE: Defaulting to ENGLISH");
			appLanguage = "ENGLISH";
		}
	}

	public static String getLanguage() {
		return appLanguage;
	}
	
}
