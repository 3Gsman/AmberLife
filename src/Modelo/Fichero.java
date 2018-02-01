package Modelo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Fichero {
	
    BufferedWriter escritor;
    BufferedReader lector;
    File ficheroUsuario = new File("FicheroUsuarios.txt");
    String p = ";";

    public String[] comprobarUsuario(String usuario, String Password) throws IOException{	
	Boolean busqueda=false;
	lector=new BufferedReader(new FileReader(ficheroUsuario));
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

    
    }