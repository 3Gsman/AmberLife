package control;

import java.io.IOException;

import model.Fichero;
import model.Tecnico;
import view.AdminFr;

public class AdminCtrl {

	AdminFr af;
	Tecnico[] listatecnicos;
	
	public AdminCtrl(AdminFr vm) throws IOException {
		Fichero tecnicos = new Fichero();
		
		af = vm;
		listatecnicos = tecnicos.obtenerTecnicos();
	}
	
	
}
