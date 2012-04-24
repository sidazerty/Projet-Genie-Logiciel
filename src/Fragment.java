
import java.util.HashSet;
import java.util.Set;

public class Fragment {

    private Station depart, arrivee;
    private int tempsParcours;
    private Set<Ligne> lignes;
    private Incident incident;
    
    public Fragment(Station sd, Station sa, int tp, Ligne l) {
        tempsParcours = tp;
        incident = null;
        depart = sd;
        arrivee = sa;
        lignes = new HashSet<>();
        lignes.add(l);
    }
	
	public Fragment(Station dep, Station arr, int tpsparc, Ligne l, Incident inci) {
        depart=dep;
        arrivee=arr;
        tempsParcours=tpsparc;
        lignes = new HashSet<>();
        lignes.add(l);
        incident=inci;
    }

	//depart
    public Station getStationDep() {
        return depart;
    }
    
    public void setStationDep(Station newdep) {
        depart=newdep;
    }
  
	//arrivee
    public Station getStationArr() {
        return arrivee;
    }
    
    public void setStationArr(Station newarr){
        arrivee=newarr;
    }
	
    //temps de parcours 
    public int getTempsDeParcours(){
        return tempsParcours;
    }
    
    public void setTempsDeParcours(int newtpsparc){
        tempsParcours=newtpsparc;
    }
    
    //Lignes
    public boolean addLigne(Ligne l) {
        return lignes.add(l);
    }
    
    //incident
    public Incident getIncident(){
        return incident;
    }
    
    public void setIncident(Incident newinci) {
        incident=newinci;
    }

    @Override
    public String toString() {
        return "tempsParcours=" + tempsParcours + ", depart=" + depart + ", arrivee=" + arrivee;
    }
}