package model;

import java.util.Vector;

public class Utilities {
	

	public static Vector<Patient> sortPatientsByName(Vector<Patient> Patientlist) {
		
		if(Patientlist.isEmpty()) {
			return Patientlist;
		}
		
		return quickSortPatients(Patientlist, 0, Patientlist.size() - 1);
	}
	
	public static Vector<Patient> quickSortPatients(Vector<Patient> Patientlist, int ini, int end){
		
		int i = ini;
		int j = end;
		Patient pivot = Patientlist.get(ini+(end-ini)/2);
		
		while(i <= j) {
			while(Patientlist.get(i).getLastname().compareTo(pivot.getLastname()) < 0 && i < end && j > i) {
				i++;
				
				//System.out.println(Patientlist.get(i).getLastname() + " "+i+" " + pivot.getLastname());
			}
			
			while(Patientlist.get(j).getLastname().compareTo(pivot.getLastname()) > 0  && j > ini && j >= i) {
				j--;
				
				//System.out.println(Patientlist.get(j).getLastname() + " "+j+" " + pivot.getLastname());
			}
		
			if(i <= j) {
				Patient temporal = new Patient(Patientlist.get(i).getSsn(), Patientlist.get(i).getName(),
						Patientlist.get(i).getLastname(), Patientlist.get(i).getId());
				
				temporal.setAddress(Patientlist.get(i).getAddress());
				temporal.setECGs(Patientlist.get(i).getECGs());
				temporal.setGender(Patientlist.get(i).getGender());
				temporal.setMunicipality(Patientlist.get(i).getMunicipality());
				
				Patientlist.get(i).setAddress(Patientlist.get(j).getAddress());
				Patientlist.get(i).setECGs(Patientlist.get(j).getECGs());
				Patientlist.get(i).setGender(Patientlist.get(j).getGender());
				Patientlist.get(i).setId(Patientlist.get(j).getId());
				Patientlist.get(i).setLastname(Patientlist.get(j).getLastname());
				Patientlist.get(i).setMunicipality(Patientlist.get(j).getMunicipality());
				Patientlist.get(i).setName(Patientlist.get(j).getName());
				Patientlist.get(i).setSsn(Patientlist.get(j).getSsn());
				
				Patientlist.get(j).setAddress(temporal.getAddress());
				Patientlist.get(j).setECGs(temporal.getECGs());
				Patientlist.get(j).setGender(temporal.getGender());
				Patientlist.get(j).setId(temporal.getId());
				Patientlist.get(j).setLastname(temporal.getLastname());
				Patientlist.get(j).setMunicipality(temporal.getMunicipality());
				Patientlist.get(j).setName(temporal.getName());
				Patientlist.get(j).setSsn(temporal.getSsn());
				
				i++;
				j--;
				
				
				
				//System.out.println(ini +" "+ j +" " + i + " "+ end);
			}
			
		}

		//System.out.println(ini +" "+ j +" " + i + " "+ end);
		
		if( ini < j) {
			quickSortPatients(Patientlist, ini, j);
		}
		if(i < end) {
			quickSortPatients(Patientlist, i, end);
		}
		
		
		
		return Patientlist;
		
		
	}

}
