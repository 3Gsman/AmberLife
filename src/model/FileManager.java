package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class FileManager {
	
    BufferedWriter escritor;
    BufferedReader lector;
    BufferedReader lectorm;
    File ficheroUsuario = new File("FicheroUsuarios.txt");
    String p = ";";

    public String[] checkUser(String usuario, String Password) throws IOException{	
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
    
    public Patient checkId(String id) throws IOException {
    	Boolean busqueda=false;
    	lector=new BufferedReader(new FileReader("src/resources/pacientes.txt"));
    	Patient pt = new Patient("null", "null", "null", "null");
    	String linea;

    	
    	while((linea=lector.readLine())!=null &&(!busqueda)){
    		String[] lineatxt=linea.split(";");	
    		if(lineatxt[3].equals(id)){ //equalsIgnoreCases

    				busqueda=true;	
    				pt.setNumber(lineatxt[0]);
    				pt.setName(lineatxt[1]);
    				pt.setLastname(lineatxt[2]);
    				pt.setId(lineatxt[3]);
    		}
    	}
            lector.close();
    	return pt;	
    }
    
    public String[] readAssistant(String username) throws IOException{
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
    
    public Assistant[] getAssistants() throws IOException {
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
    
    public Doctor readDoctor(String username) throws IOException{
    	lectorm = new BufferedReader(new FileReader("src/resources/" + username + ".txt"));
    	String linea;
    	Patient[] listapacientes = new Patient[20];
    	int i = 0;
    	
    	linea = lectorm.readLine();
    	String[] lineatxt = linea.split(";");
    	Doctor medico = new Doctor(lineatxt[0],lineatxt[1],lineatxt[2],lineatxt[3],lineatxt[4],lineatxt[5]);
    	
    	while((linea=lectorm.readLine())!=null){
    		Patient p = new Patient(linea, "null", "null", "null");
    		
    		listapacientes[i] = p;
    		i++;
    	}
    	
    	medico.setPatientlist(listapacientes);
    	
    	lectorm.close();
    	return medico;
    	
    }
    
    public Doctor[] getDoctors() throws IOException{
    	lector = new BufferedReader(new FileReader("src/resources/Users.txt"));
    	Doctor[] lista = new Doctor[5];
    	String linea;
    	int i = 0;
    	
    	
    	while((linea=lector.readLine())!=null){

    		
    		String[] lineatxt=linea.split(";");	
    		if(lineatxt[1].equals("medico")){ //equalsIgnoreCases
    			lista[i] = readDoctor(lineatxt[0]);
    			i++;
    		}

    	}
    	
    	
    	lector.close();
    	return lista;
    }
    
    public Vector<Double> readECG() throws IOException{
    	lector = new BufferedReader(new FileReader("src/resources/ECG.txt"));
    	Vector<Double> ecg = new Vector<>() ;

    	
    	String linea = null;
    	String [] numeros = null;
    	int i = 0;
    	
    	linea = lector.readLine();
    	
    	while((linea = lector.readLine()) != null) {
    		numeros = linea.split(";");
    		for(i = 0; i<numeros.length;i++) {
    			ecg.add(Double.valueOf(numeros[i]));
    		}
    	}
    	
    	lector.close();
    	return ecg;
    	
    	
    }

    
    }