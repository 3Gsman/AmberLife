package control;

import java.io.IOException;

import model.Fichero;
import model.Medico;
import model.Tecnico;
import view.AdminFr;

public class AdminCtrl {

	AdminFr af;
	Tecnico[] listatecnicos;
	Medico[] listamedicos;
	
	public AdminCtrl(AdminFr vm) throws IOException {
		Fichero conseguirListas = new Fichero();
		
		af = vm;
		listatecnicos = conseguirListas.obtenerTecnicos();
		listamedicos = conseguirListas.obtenerMedicos();
	}
	
	
}
