package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fichero {
	
    BufferedWriter escritor;
    BufferedReader lector;
    File ficheroUsuario = new File("FicheroUsuarios.txt");
    String p = ";";

    public String[] comprobarUsuario(String usuario, String Password) throws IOException{	
	Boolean busqueda=false;
	lector=new BufferedReader(new FileReader("src/resources/Users.txt"));
	String linea;	
	String tipo[]=new String[3];
	tipo[0]="false";
	tipo[1]="null";
	tipo[2]="null";
	while((linea=lector.readLine())!=null &&(!busqueda)){
		String[] lineatxt=linea.split(";");	
		if(lineatxt[0].equals(usuario)){ //equalsIgnoreCases
			if(lineatxt[2].equals(Password)){
				busqueda=true;	
				tipo[0]="true";
				tipo[1]=lineatxt[1];
				tipo[2]=lineatxt[2];
			}
		}
	}
        lector.close();
	return tipo;	
    }
    
    public String[] comprobarId(String id) throws IOException {
    	Boolean busqueda=false;
    	lector=new BufferedReader(new FileReader("src/resources/pacientes.txt"));
    	String paciente[] = new String[5];
    	String linea;
    	paciente[0]="false";
    	paciente[1]="null";
    	paciente[2]="null";
    	paciente[3]="null";
    	paciente[4]="null";
    	
    	while((linea=lector.readLine())!=null &&(!busqueda)){
    		String[] lineatxt=linea.split(";");	
    		if(lineatxt[3].equals(id)){ //equalsIgnoreCases

    				busqueda=true;	
    				paciente[0]="true";
    				paciente[1]=lineatxt[0];
    				paciente[2]=lineatxt[1];
    				paciente[3]=lineatxt[2];
    				paciente[4]=lineatxt[3];
    		}
    	}
            lector.close();
    	return paciente;	
    }

    
    }