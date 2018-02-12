package control;

import java.io.IOException;

import model.FIleManager;
import model.Doctor;
import model.Assistant;
import view.AdminFr;

public class AdminCtrl {

	AdminFr af;
	Assistant[] listatecnicos;
	Doctor[] listamedicos;
	
	public AdminCtrl(AdminFr vm) throws IOException {
		FIleManager conseguirListas = new FIleManager();
		
		af = vm;
		listatecnicos = conseguirListas.obtenerTecnicos();
		listamedicos = conseguirListas.obtenerMedicos();
	}
	
	
}
