
import java.util.LinkedList;
import java.util.List;

public class Ligne {

    private String nom;
    private int nbStation;

    public Ligne(String n) {
        nom = n;
        nbStation = 0;
    }

    //nom
    public String getNom() {
        return nom;
    }

    public void setNom(String newname) {
        nom = newname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Ligne other = (Ligne) obj;
        if ((this.nom == null) ? (other.nom != null) : !this.nom.toUpperCase().equals(other.nom.toUpperCase())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.nom != null ? this.nom.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Ligne " + nom + " (" + nbStation + " stations)";
    }
}