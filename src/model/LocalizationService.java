package model;

import java.util.HashMap;

public  class LocalizationService {

	static private String appLanguage = "ENGLISH";
	static private HashMap<String,String> english = new HashMap<>();
	static private HashMap<String,String> spanish = new HashMap<>();
	static private HashMap<String,String> galician = new HashMap<>();
	
	/**
	 * Initializes the LocalizationService to contain the words used in the app and their equivalent in other languages.
	 * To be eventually substituted with json parsing for more decoupling and ease of translation.
	 * 
	 */
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
		
		english.put("patients", "patients");
		spanish.put("patients", "pacientes");
		galician.put("patients", "pacientes");
		
		english.put("sex", "sex");
		spanish.put("sex", "sexo");
		galician.put("sex", "sexo");
		
		english.put("municipality", "municipality");
		spanish.put("municipality", "municipio");
		galician.put("municipality", "municipio");
		
		english.put("address", "address");
		spanish.put("address", "dir.");
		galician.put("address", "dir.");
		
		english.put("status", "status");
		spanish.put("status", "estado");
		galician.put("status", "estado");
		
		english.put("date", "date");
		spanish.put("date", "fecha");
		galician.put("date", "fecha");
		
		english.put("freq", "freq");
		spanish.put("freq", "frec");
		galician.put("freq", "frec");
		
		english.put("assist", "assist");
		spanish.put("assist", "tecn");
		galician.put("assist", "tecn");

		english.put("cancel", "cancel");
		spanish.put("cancel", "cancelar");
		galician.put("cancel", "cancelar");
		
		english.put("confirm", "confirm");
		spanish.put("confirm", "confirmar");
		galician.put("confirm", "confirmar");
		
		english.put("compare", "compare");
		spanish.put("compare", "comparar");
		galician.put("compare", "comparar");
		
		english.put("from", "from");
		spanish.put("from", "de");
		galician.put("from", "de");
		
		english.put("date", "date");
		spanish.put("date", "fecha");
		galician.put("date", "fecha");
		
		english.put("frequency", "frequency");
		spanish.put("frequency", "frecuencia");
		galician.put("frequency", "frecuencia");
		
	}
	
	/**
	 * Returns the equivalent of a word or string to be used in a given scenario in the selected language.
	 * 
	 * @param word	the word or string to be used
	 * @return	the equivalent in the selected language
	 */
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

	/**
	 * Changes from one selected language to the next.
	 */
	public static void rotate() {
		if(appLanguage == "ENGLISH") appLanguage = "SPANISH";
		else if(appLanguage == "SPANISH") appLanguage = "GALICIAN";
		else if(appLanguage == "GALICIAN") appLanguage = "ENGLISH";
		else {
			System.out.println("ERROR RORATING LANGUAGE: Defaulting to ENGLISH");
			appLanguage = "ENGLISH";
		}
	}

	/**
	 * Returns the current language as a string.
	 * 
	 * @return the current language
	 */
	public static String getLanguage() {
		return appLanguage;
	}
	
}
