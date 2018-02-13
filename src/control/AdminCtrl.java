package control;

import java.io.IOException;

import model.FileManager;
import model.Doctor;
import model.Assistant;
import view.AdminFr;

public class AdminCtrl {

	AdminFr af;
	Assistant[] listatecnicos;
	Doctor[] listamedicos;
	
	public AdminCtrl(AdminFr vm) throws IOException {
		FileManager conseguirListas = new FileManager();
		
		af = vm;
		listatecnicos = conseguirListas.getAssistants();
		listamedicos = conseguirListas.getDoctors();
	}
	
	
}
