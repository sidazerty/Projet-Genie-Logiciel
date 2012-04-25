
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Fragment {

    private Station depart, arrivee;
    private int tempsParcours;
    private Set<Ligne> lignes;
    private Incident incident;
    
    public Fragment(Station sd, Station sa, int tp) {
        tempsParcours = tp;
        incident = null;
        depart = sd;
        arrivee = sa;
        lignes = new HashSet<>();
    }
	
	public Fragment(Station dep, Station arr, int tpsparc, Incident inci) {
        depart=dep;
        arrivee=arr;
        tempsParcours=tpsparc;
        lignes = new HashSet<>();
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Fragment other = (Fragment) obj;
        if (!(Objects.equals(this.depart, other.depart)
            || Objects.equals(this.depart, other.arrivee))) {
            return false;
        }
        if (!(Objects.equals(this.arrivee, other.depart)
            || Objects.equals(this.arrivee, other.arrivee))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.depart);
        hash = 19 * hash + Objects.hashCode(this.arrivee);
        hash = 19 * hash + this.tempsParcours;
        hash = 19 * hash + Objects.hashCode(this.lignes);
        hash = 19 * hash + Objects.hashCode(this.incident);
        return hash;
    }
    
    
}