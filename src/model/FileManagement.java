package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileManagement {
	
    BufferedWriter escritor;
    BufferedReader lector;
    BufferedReader lectorm;
    BufferedReader lectorp;
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
    				pt.setSsn(lineatxt[4]);
    		}
    	}
            lector.close();
    	return pt;	
    }
    
    public Patient checkSsn(String id) throws IOException {
    	Boolean busqueda=false;
    	lector=new BufferedReader(new FileReader("src/resources/pacientes.txt"));
    	Patient pt = new Patient("null", "null", "null", "null");
    	String linea;

    	
    	while((linea=lector.readLine())!=null &&(!busqueda)){
    		String[] lineatxt=linea.split(";");	
    		if(lineatxt[4].equals(id)){ //equalsIgnoreCases

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
    
    public Vector<Assistant> getAssistants() throws IOException {
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
    	
    	Vector<Assistant> v = new Vector<>();
    	for(Assistant a : lista) {
    		v.add(a);
    	}
    	
    	return v;
    }
    
    public Doctor readDoctor(String username) throws IOException{
    	lectorm = new BufferedReader(new FileReader("src/resources/" + username + ".txt"));
    	String linea;
    	Vector<Patient> listapacientes = new Vector<Patient>();
    	
    	linea = lectorm.readLine();
    	String[] lineatxt = linea.split(";");
    	Doctor medico = new Doctor(lineatxt[0],lineatxt[1],lineatxt[2],lineatxt[3],lineatxt[4],lineatxt[5]);
    	
    	while((linea=lectorm.readLine())!=null){
    		Patient p = new Patient(linea);
    		
    		listapacientes.add(p);

    	}
    	
    	medico.setPatientlist(listapacientes);
    	
    	lectorm.close();
    	return medico;
    	
    }
    
    public Vector<Doctor> getDoctors() throws IOException{
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
    	
    	Vector<Doctor> v = new Vector<>();
    	for(Doctor d : lista) {
    		v.add(d);
    	}
    	
    	return v;
    }
    
     public ECG readECG(String filename) throws IOException{
    	lector = new BufferedReader(new FileReader("src/resources/" + filename));
    	ECG ecg = new ECG();
    	Vector<Double> num = new Vector<>() ;

    	
    	String linea = null;
    	String [] numeros = null;
    	int i = 0;
    	
    	String frequency = lector.readLine();
    	
    	linea = lector.readLine();
    		numeros = linea.split(";");
    		for(i = 0; i<numeros.length;i++) {
    			num.add(Double.valueOf(numeros[i]));
    		}

    	
    	lector.readLine();
    	String info = lector.readLine();
    	
    	lector.close();
    	
    	if(info == null)
    		info = "";
    	
    	
    	ecg.setData(num);
    	ecg.setName(filename);
    	ecg.setFrequency(Integer.parseInt(frequency));
    	ecg.setReport(info);
    	
    	//Check the reading of the ecgs
		//System.out.print(ecg.toString());
		
		
    	
    	return ecg;
    	
    	
    }
     
     public Patient readPatient(String username) throws IOException{
    	 Patient p = new Patient(username);
    	 lectorp = new BufferedReader(new FileReader("src/resources/" + username + ".txt"));
     	 String linea = lectorp.readLine();
     	 String[] lineatxt = linea.split(";");
     	 ECG ecg = new ECG();
     	 Vector<ECG> vector = new Vector<>();
     	 
     	 p.setName(lineatxt[0]);
     	 p.setLastname(lineatxt[1]);
     	 p.setId(lineatxt[2]);
     	 p.setSsn(lineatxt[3]);
     	 p.setMunicipality(lineatxt[4]);
     	 p.setAddress(lineatxt[5]);
     	 p.setGender(lectorp.readLine());
     	 p.setStatus(lectorp.readLine());
     	 p.setMessage(lectorp.readLine());
     	 
     	while((linea=lectorp.readLine())!=null){

     		 ecg = readECG(linea + ".txt");

     		 vector.add(ecg);

     	 }
     
     	p.setECGs(vector);
    	 
    	 
    	 lectorp.close();
    	 return p;
     }
     
     public Vector<String> readPatientMessages(String number) throws IOException{
    	 
    	 Vector<String> messages = new Vector<String>();
    	 Patient p = readPatient(number);
    	 messages.add(p.getMessage());
    	 
    	 return messages;
     }
    
    }