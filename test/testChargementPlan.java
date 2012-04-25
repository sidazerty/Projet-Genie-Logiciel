/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;

/**
 *
 * @author Loïc Cimon
 */
public class testChargementPlan extends TestCase {
   
    /*
     * Test de la méthode tequals de Station
     */
    public void testEqualsStation() {
        assertFalse(new Station("rose").equals(null));
        
        assertTrue(new Station("rose").equals(new Station("rose")));
        assertFalse(new Station("rose").equals(new Station("tulipe")));
        
        assertTrue(new Station("rose").equals(new Station("Rose")));
        assertFalse(new Station("rose").equals(new Station("Tulipe")));
        
        assertTrue(new Station("rose", new Coordonnee(1.1, 2.1)).equals(new Station("rose")));
        assertFalse(new Station("rose", new Coordonnee(1.1, 2.1)).equals(new Station("tulipe")));
    }
    
    /*
     * Test de la méthode equals de Ligne
     */
    public void testEqualsLigne() {
        assertFalse(new Ligne("A").equals(null));
        
        assertTrue(new Ligne("A").equals(new Ligne("A")));
        assertFalse(new Ligne("A").equals(new Ligne("B")));
        
        assertTrue(new Ligne("A").equals(new Ligne("a")));
        assertFalse(new Ligne("A").equals(new Ligne("b")));
    }
    
        /*
     * Test de la méthode equals de Ligne
     */
    public void testEqualsFragment() {
        Station s1 = new Station("s1", new Coordonnee(47.91, 2.30));
        Station s2 = new Station("s2", new Coordonnee(48.91, 3.50));
        Station s3 = new Station("s3", new Coordonnee(49.00, 4.30));
        
        Fragment f1 = new Fragment(s1, s2, 2);        
        
        assertFalse(f1.equals(null));
        
        assertTrue(f1.equals(f1));
        
        //On ne tiens pas compte des stations
        f1.addLigne(new Ligne("A"));
        assertTrue(f1.equals(new Fragment(s1, s2, 2)));
        
        //On ne tient pas compte de l'ordre
        assertTrue(f1.equals(new Fragment(s2, s1, 2)));
        
        assertFalse(f1.equals(new Fragment(s1, s3, 2)));  
        assertFalse(f1.equals(new Fragment(s3, s1, 2)));  
    }
    
    /*
     * Test de la méthode traitementLigne de Plan
     */
    public void testTraitementLigne() {
        Plan p = new Plan();
        Plan res = new Plan();
        
        p.traitementLigneTexte(null);
        
        String chaine;
        //Ne doit rien créer, les stations sont identiques
        chaine = "Sauge	48.91:2.30	Sauge	48.89:2.35	2	A";
        p.traitementLigneTexte(chaine);
        
        assertEquals(res.getStations(), p.getStations());
        assertEquals(res.getFragment(), p.getFragment()); 

        //Crée les stations, le fragment et la ligne
        chaine = "Sauge	48.91:2.30	Capucine	48.89:2.35	2	B";
        p.traitementLigneTexte(chaine);
        
        Station sauge = new Station("Sauge", new Coordonnee(48.91, 2.30));
        Station capucine = new Station("Capucine", new Coordonnee(48.89, 2.35));
        Fragment f1 = new Fragment(sauge, capucine, 2);
        Ligne b = new Ligne("B");
        
        res.addStation(sauge);
        res.addStation(capucine);
        f1.addLigne(b);
        res.addFragment(f1);
        
        assertEquals(res.getStations(), p.getStations());
        assertEquals(res.getFragment(), p.getFragment());
             
        //Ne crée rien tout existe déjà
        chaine = "Sauge	48.91:2.30	Capucine	48.89:2.35	2	B";
        p.traitementLigneTexte(chaine);
        
        assertEquals(res.getStations(), p.getStations());
        assertEquals(res.getFragment(), p.getFragment());
        
        //Crée la station Myosotis et ajoute le fragment à la ligne B (attention à la casse)
        chaine = "Sauge	48.91:2.30	Myosotis	48.87:2.32	2	b";
        p.traitementLigneTexte(chaine);
        
        Station myosotis = new Station("Myosotis", new Coordonnee(48.87, 2.32));
        res.addStation(myosotis);
        Fragment f2 = new Fragment(sauge, myosotis, 2);
        f2.addLigne(b);
        res.addFragment(f2);
        
        assertEquals(res.getStations(), p.getStations());
        assertEquals(res.getFragment(), p.getFragment());
    }
}
