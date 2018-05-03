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
		english.put("username", "Username");
		spanish.put("username", "Usuario");
		galician.put("username", "Usuario");
		
		english.put("password", "Password");
		spanish.put("password", "Contraseña");
		galician.put("password", "Contraseña");
		
		english.put("patient", "Patient");
		spanish.put("patient", "Paciente");
		galician.put("patient", "Paciente");
		
		english.put("name", "Name");
		spanish.put("name", "Nombre");
		galician.put("name", "Nome");
		
		english.put("surname", "Surname");
		spanish.put("surname", "Apellido");
		galician.put("surname", "Apellido");
		
		english.put("surname", "Surname");
		spanish.put("surname", "Apellido");
		galician.put("surname", "Apellido");
		
		english.put("id", "I.D");
		spanish.put("id", "D.N.I");
		galician.put("id", "D.N.I");
		
		english.put("nomessages", "No messages");
		spanish.put("nomessages", "Sin mensajes");
		galician.put("nomessages", "Sin mensaxes");
		
		english.put("adminconsole", "Administrator Console");
		spanish.put("adminconsole", "Consola de Administrador");
		galician.put("adminconsole", "Consola do Administrador");
		
		english.put("searchpatient", "Search patient");
		spanish.put("searchpatient", "Buscar paciente");
		galician.put("searchpatient", "Buscar paciente");
		
		english.put("user", "User");
		spanish.put("user", "Usuario");
		galician.put("user", "Usuario");
		
		english.put("measure", "MEASURE");
		spanish.put("measure", "MEDIR");
		galician.put("measure", "MEDIR");
		
		english.put("ssn", "S.S.N");
		spanish.put("ssn", "N.S.S");
		galician.put("ssn", "N.S.S");
		
		english.put("login", "LOGIN");
		spanish.put("login", "ENTRAR");
		galician.put("login", "ENTRAR");
		
		english.put("doctors", "Doctors");
		spanish.put("doctors", "Doctores");
		galician.put("doctors", "Doctores");
		
		english.put("assistants", "Assistants");
		spanish.put("assistants", "Técnicos");
		galician.put("assistants", "Técnicos");
		
		english.put("search", "SEARCH");
		spanish.put("search", "BUSCAR");
		galician.put("search", "BUSCAR");
		
		english.put("patients", "Patients");
		spanish.put("patients", "Pacientes");
		galician.put("patients", "Pacientes");
		
		english.put("sex", "Sex");
		spanish.put("sex", "Sexo");
		galician.put("sex", "Sexo");
		
		english.put("municipality", "Municipality");
		spanish.put("municipality", "Municipio");
		galician.put("municipality", "Municipio");
		
		english.put("address", "Address");
		spanish.put("address", "Dir.");
		galician.put("address", "Dir.");
		
		english.put("status", "Status");
		spanish.put("status", "Estado");
		galician.put("status", "Estado");
		
		english.put("date", "Date");
		spanish.put("date", "Fecha");
		galician.put("date", "Fecha");
		
		english.put("freq", "Freq");
		spanish.put("freq", "Frec");
		galician.put("freq", "Frec");
		
		english.put("assist", "Assist");
		spanish.put("assist", "Tecn");
		galician.put("assist", "Tecn");

		english.put("cancel", "CANCEL");
		spanish.put("cancel", "CANCELAR");
		galician.put("cancel", "CANCELAR");
		
		english.put("confirm", "COMFIRM");
		spanish.put("confirm", "CONFIRMAR");
		galician.put("confirm", "CONFIRMAR");
		
		english.put("compare", "COMPARE");
		spanish.put("compare", "COMPARAR");
		galician.put("compare", "COMPARAR");
		
		english.put("from", "From");
		spanish.put("from", "De");
		galician.put("from", "De");
		
		english.put("date", "Date");
		spanish.put("date", "Fecha");
		galician.put("date", "Fecha");
		
		english.put("frequency", "Frequency");
		spanish.put("frequency", "Frecuencia");
		galician.put("frequency", "Frecuencia");
		
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
