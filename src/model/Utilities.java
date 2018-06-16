package model;

import java.util.Collections;
import java.util.Vector;

public class Utilities {
	
	public static int sortUser(Vector<? extends User>v, int ini, int fin){
		
		if(ini<fin){
            int x = pivotUser(v,ini,fin);
            return sortUser(v,ini,(int)x-1)+
            		sortUser(v,(int)x+1,fin);
        } else {
            return 0;
        }
	}
	
	private static int pivotUser(Vector<? extends User>v, int ini, int fin){
		
		int i = ini;
        String p = v.get(ini).getLastname() + " " + v.get(ini).getName();
        for(int j=ini+1;j<=fin;++j){
            if((v.get(j).getLastname() + " " + v.get(j).getName()).compareTo(p)  == -1){
                i++;
                if(i!=j){
                	Collections.swap(v,i,j);
                }
            }
        }
        Collections.swap(v,ini,i);
    	return i;	
	}
	
	

	public static int sortPatients(Vector<Patient>v, int ini, int fin){
		
		if(ini<fin){
            int x = pivotPatients(v,ini,fin);
            return sortPatients(v,ini,(int)x-1)+
            		sortPatients(v,(int)x+1,fin);
        } else {
            return 0;
        }
	}
	
	private static int pivotPatients(Vector<Patient>v, int ini, int fin){
		
		int i = ini;
        String p = v.get(ini).getLastname() + " " + v.get(ini).getName();
        for(int j=ini+1;j<=fin;++j){
            if((v.get(j).getLastname() + " " + v.get(j).getName()).compareTo(p)  == -1){
                i++;
                if(i!=j){
                	Collections.swap(v,i,j);
                }
            }
        }
        Collections.swap(v,ini,i);
    	return i;	
	}
	
	
	public static int sortMessages(Vector<Message> v, int ini, int fin){
		  
		if(ini<fin){
	            int x = pivotMessages(v,ini,fin);
	            return sortMessages(v,ini,(int)x-1)+
	            		sortMessages(v,(int)x+1,fin);
	        } else {
	            return 0;
	        }
		
	}

	private static int pivotMessages(Vector<Message> v, int ini, int fin) {
		
		int i = ini;
        String p = v.get(ini).getTimestamp();
        for(int j=ini+1;j<=fin;++j){
            if(v.get(j).getTimestamp().compareTo(p)  == -1){
                i++;
                if(i!=j){
                	Collections.swap(v,i,j);
                }
            }
        }
        Collections.swap(v,ini,i);
    	return i;	
	}
	
    public static String findUsername(Vector<String> a, String username) {
        if (a.size() == 0 || (a.get(0).compareTo(username) == 1) || (a.get(a.size()-1).compareTo(username) == -1)) {
            return null;
        } else {
            return BusBinDV(a, 0, a.size() - 1, username);
        }
    }
 
    private static String BusBinDV(Vector<String> a, int ini, int fin, String x) {
        if (ini > fin) {
            return null;
        } else {
            int k = (ini + fin) / 2;
           
            if (x.compareTo(a.get(k)) == 0) {
                return a.get(k);
            } else {
                if (x.compareTo(a.get(k)) == -1) {
                    return BusBinDV(a, ini, k - 1, x);
                } else {
                    return BusBinDV(a, k + 1, fin, x);
                }
            }
        }
    }
	
	
}
