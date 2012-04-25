
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Mami Sall
 */
public class Plan {

    private Set<Station> stations;
    private Set<Fragment> fragments;
    private Station util;

    public Plan() {
        stations = new HashSet<>();
        fragments = new HashSet<>();
        util = null;
    }

    public Plan(String fichier) {
        stations = new HashSet<>();
        fragments = new HashSet<>();
        util = null;
        chargementPlan(fichier);
    }

    //Fragment
    public Set<Fragment> getFragment() {
        return fragments;
    }

    public boolean addFragment(Fragment f) {
        return fragments.add(f);
    }

    //Stations
    public Set<Station> getStations() {
        return stations;
    }

    public boolean addStation(Station s) {
        return stations.add(s);
    }

    //Station utilisateur
    public Station getStationUtil() {
        return util;
    }

    public void setStationUtil(Station s) {
        util = s;
    }

    private void chargementPlan(String fichier) {
        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);

            String ligne;
            while ((ligne = br.readLine()) != null) {
                traitementLigneTexte(ligne);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void traitementLigneTexte(String chaine) {
        if (chaine != null) {
            String[] ligne = chaine.split("\t");

            //Station de départ
            String[] coord = ligne[1].split(":");
            Coordonnee cd = new Coordonnee(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
            Station sd = new Station(ligne[0], cd);

            //Station d'arrivée
            coord = ligne[3].split(":");
            Coordonnee ca = new Coordonnee(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
            Station sa = new Station(ligne[2], ca);

            if (!sd.equals(sa)) {

                //Ajout des stations de depart et d'arrivée si elle n'existe pas
                stations.add(sd);
                stations.add(sa);

                //creation d'une ligne 
                Ligne li = new Ligne(ligne[5].toUpperCase());
                
                // creation du fragment 
                Fragment d = new Fragment(sd, sa, Integer.parseInt(ligne[4]));
                //ajout de la ligne
                d.addLigne(li);

                // Ajout du fragment dans le plan
                fragments.add(d);
            }
        }
    }

    @Override
    public String toString() {
        String s = "* Plan :";
        s += "\n\t- Nombre de stations : " + stations.size();
        s += "\n\t- Nombre de fragments : " + fragments.size();
        s += "\n";
        s += "* Stations :";
        Iterator<Station> is = stations.iterator();
        while (is.hasNext()) {
            s += "\n\t- " + is.next();
        }
        return s;
    }

    public Station getStationProche(Coordonnee coord) {
        Station res = null, tmp;
        double min, distance;
        
        Iterator<Station> is = stations.iterator();
        if (is.hasNext()) {
            tmp = is.next(); 
            res = tmp;
            min = coord.distance(tmp.getCoord());
            while (is.hasNext()) {
                tmp = is.next();
                distance = coord.distance(tmp.getCoord());
                if (distance < min) {
                    res = tmp;
                    min = distance;
                }
            }
        }

        return res;
    }
}
