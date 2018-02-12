package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FIleManager {
	
    BufferedWriter escritor;
    BufferedReader lector;
    BufferedReader lectorm;
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
    
    public String[] leerTecnico(String username) throws IOException{
    	Boolean busqueda = false;
    	lector=new BufferedReader(new FileReader("src/resources/Tecnicos.txt"));
    	String tecnico[] = new String[5];
    	String linea;
    	
    	while((linea=lector.readLine())!=null &&(!busqueda)){
    		String[] lineatxt=linea.split(";");	
    		if(lineatxt[4].equals(username)){ //equalsIgnoreCases
    				busqueda=true;
    				tecnico = lineatxt;
    		}
    	}
    	

    	
    	
    	lector.close();
    	return tecnico;
    }
    
    public Assistant[] obtenerTecnicos() throws IOException {
    	lector = new BufferedReader(new FileReader("src/resources/Tecnicos.txt"));
    	Assistant[] lista = new Assistant[6];
    	String linea;
    	int i = 0;
    	
    	while((linea=lector.readLine())!=null){
    		String[] lineatxt=linea.split(";");	
    		lista[i] = new Assistant(lineatxt[0],lineatxt[1],lineatxt[2],lineatxt[3],lineatxt[4]);
    		i++;
    	}
    	
    	lector.close();
    	return lista;
    }
    
    public Doctor leerMedico(String username) throws IOException{
    	lectorm = new BufferedReader(new FileReader("src/resources/" + username + ".txt"));
    	String linea;
    	String[] listapacientes = new String[20];
    	int i = 0;
    	
    	linea = lectorm.readLine();
    	String[] lineatxt = linea.split(";");
    	Doctor medico = new Doctor(lineatxt[0],lineatxt[1],lineatxt[2],lineatxt[3],lineatxt[4],lineatxt[5]);
    	
    	while((linea=lectorm.readLine())!=null){
    		

    		listapacientes[i] = linea;    		
    		i++;
    	}
    	
    	medico.setListapacientes(listapacientes);
    	
    	lectorm.close();
    	return medico;
    	
    }
    
    public Doctor[] obtenerMedicos() throws IOException{
    	lector = new BufferedReader(new FileReader("src/resources/Users.txt"));
    	Doctor[] lista = new Doctor[5];
    	String linea;
    	int i = 0;
    	
    	
    	while((linea=lector.readLine())!=null){

    		
    		String[] lineatxt=linea.split(";");	
    		if(lineatxt[1].equals("medico")){ //equalsIgnoreCases
    			lista[i] = leerMedico(lineatxt[0]);
    			i++;
    		}

    	}
    	
    	
    	lector.close();
    	return lista;
    }

    
    }