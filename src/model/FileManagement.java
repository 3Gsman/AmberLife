package model;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
@Deprecated
public class FileManagement {
	
    private BufferedWriter escritor;
    private BufferedReader lector;
    private BufferedReader lectorm;
    private BufferedReader lectorp;
    private File ficheroUsuario = new File("FicheroUsuarios.txt");
    private String p = ";";

    /**
     * Checks whether an user exists or not by reading into the DB (currently a txt file as a placeholder) and if it
     * does, returns the type of user that it is (Assistant, Doctor, or Admin)
     * 
     * @param usuario	username of the checked user
     * @param Password	password of the checked user
     * @return	the type of user, if it exists
     * @throws IOException
     */
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
    
  
    /**
     * Checks whether a patient exists within the DB using their id number (Currently a txt file as a placeholder)
     * and returns an array containing the data that the Assistant has the authorization to see, 
     * to protect confidential information.
     * 
     * @param id	the id of the patient to check
     * @return an array with the authorized data of the patient
     * @throws IOException
     */
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
    
    /**
     * Checks whether a patient exists within the DB using their ssn number (Currently a txt file as a placeholder)
     * and returns an array containing the data that the Assistant has the authorization to see, 
     * to protect confidential information.
     * 
     * @param id	the ssn of the patient to check
     * @return	an array with the authorized data of the patient
     * @throws IOException
     */
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
    				pt.setSsn(lineatxt[4]);
    		}
    	}
            lector.close();
    	return pt;	
    }
    
    /**
     * Checks whether an assistant exists and returns their information from their username.
     * 
     * @param username	the username of the assistant to check
     * @return an array of strings containing the data of the assistant
     * @throws IOException
     */
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
    
    /**
     * Gets all assistants from the DB (currently a txt file) and returns a list with their data.
     * 
     * @return	the list of assistants registered within the system.
     * @throws IOException
     */
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
    
    /**
     * Checks whether an doctor exists and returns their information from their username.
     * 
     * @param username	the username of the doctor to check
     * @return an array of strings containing the data of the doctor
     * @throws IOException
     */
    public Doctor readDoctor(String username) throws IOException{
    	lectorm = new BufferedReader(new FileReader("src/resources/" + username + ".txt"));
    	String linea;
    	Vector<Patient> listapacientes = new Vector<Patient>();
    	
    	linea = lectorm.readLine();
    	String[] lineatxt = linea.split(";");
    	Doctor medico = new Doctor(lineatxt[0],lineatxt[1],lineatxt[2],lineatxt[3],lineatxt[5]);
    	
    	while((linea=lectorm.readLine())!=null){
    		Patient p = new Patient(linea);
    		
    		listapacientes.add(p);

    	}
    	
    	medico.setPatientlist(listapacientes);
    	
    	lectorm.close();
    	return medico;
    	
    }
    
    /**
     * Gets all doctors from the DB (currently a txt file) and returns a list with their data.
     * 
     * @return	the list of doctors registered within the system.
     * @throws IOException
     */
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
    
    /**
     * Parses an ECG file into an ECG object.
     * 
     * @param filename	The path to the ECG file
     * @return	the resultant ECG object
     * @throws IOException
     */
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
     
  
     /**
      * Checks whether a patient exists in the DB (currently txt files) and returns their data as a Patient object,
      * containing confidential information that only Doctors may see.
      * 
      * @param username		the username of the patient to check
      * @return	the resultant Patient file
      * @throws IOException
      */
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
     
     /**
      * Reads a list of all messages relating to a patient from the DB (currently txt files)
      * 
      * @param number	id number of the patient
      * @return	a list of all the messages relating to a patient
      * @throws IOException
      */
     public Vector<String> readPatientMessages(String number) throws IOException{
    	 
    	 Vector<String> messages = new Vector<String>();
    	 Patient p = readPatient(number);
    	 messages.add(p.getMessage());
    	 
    	 return messages;
     }
    
    }