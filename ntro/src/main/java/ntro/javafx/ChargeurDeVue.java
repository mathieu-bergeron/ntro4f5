package ntro.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import ntro.debogage.DoitEtre;
import ntro.debogage.Erreur;
import ntro.debogage.J;
import ntro.client.mvc.Vue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ChargeurDeVue<V extends Vue>{
    
    private FXMLLoader loader;
    private Parent parent;

    public ChargeurDeVue(String cheminFxml) {
        J.appel(this);
        
        DoitEtre.nonNul(cheminFxml);

        creerLoader(cheminFxml);
        chargerParent();
    }

    public ChargeurDeVue(String cheminFxml, String cheminCss) {
    	J.appel(this);

        DoitEtre.nonNul(cheminFxml);
        DoitEtre.nonNul(cheminCss);
        
        creerLoader(cheminFxml);
        chargerParent();
        ajouterCss(cheminCss);
    }

    public ChargeurDeVue(String cheminFxml, String cheminCss, String cheminChaines) {
        J.appel(this);
        
        DoitEtre.nonNul(cheminFxml);
        DoitEtre.nonNul(cheminCss);
        DoitEtre.nonNul(cheminChaines);
        
        creerLoader(cheminFxml, cheminChaines);
        chargerParent();
        ajouterCss(cheminCss);
    }

    public Scene nouvelleScene(float largeurScenePourcentage, 
            float hauteurScenePourcentage, 
            float hauteurPolicePourcentage) {

        J.appel(this);

        Rectangle2D tailleEcran = getTailleEcran();

        int largeur = (int) (tailleEcran.getWidth() * largeurScenePourcentage / 100.0);
        int hauteur = (int) (tailleEcran.getHeight() * hauteurScenePourcentage / 100.0);
        int taillePolice = (int) (tailleEcran.getHeight() * hauteurPolicePourcentage / 100.0);

        Scene scene = creerScene(largeur, hauteur, taillePolice);
        
        return scene;
        
    }

    private Scene creerScene(int largeur, int hauteur, int taillePolice) {
        J.appel(this);

        DoitEtre.nonNul(parent);

        Scene scene = new Scene(parent, largeur, hauteur);
        scene.getRoot().setStyle(String.format("-fx-font-size: %dpx;", taillePolice));

        return scene;
    }

    private Rectangle2D getTailleEcran() {
        J.appel(this);

        Rectangle2D tailleEcran = Screen.getPrimary().getVisualBounds();

        return tailleEcran;
    }
    
    public V getVue() {
    	J.appel(this);
    	
    	V vue = loader.getController();
    	
    	DoitEtre.nonNul(vue);

    	return vue;
    }

    
    public Scene nouvelleScene(double largeur, double hauteur) {
        J.appel(this);
        
        DoitEtre.nonNul(parent);

        return new Scene(parent, largeur, hauteur);
    }

    private void creerLoader(String cheminFxml) {
        J.appel(this);
        
        URL fichierFxml = getFichierFxml(cheminFxml);

        DoitEtre.nonNul(fichierFxml);
        
        loader = new FXMLLoader(fichierFxml);
        
        DoitEtre.nonNul(loader);

    }

    private void creerLoader(String cheminFxml, String cheminChaines) {
        J.appel(this);
        
        URL fichierFxml = getFichierFxml(cheminFxml);
        
        ResourceBundle chaines = getResourceBundle(cheminChaines);

        DoitEtre.nonNul(fichierFxml);
        DoitEtre.nonNul(chaines);
        
        loader = new FXMLLoader(fichierFxml, chaines);
        
        DoitEtre.nonNul(loader);
    }

    private ResourceBundle getResourceBundle(String cheminChaines) {
        J.appel(this);

        ResourceBundle chaines = null;

        try {

            chaines = ResourceBundle.getBundle(cheminChaines);

        }catch(MissingResourceException e) {
            
            Erreur.fatale("cheminChaines non-trouvé '" + cheminChaines + "'", e);
            
        }

        return chaines;
    }

    private URL getFichierFxml(String cheminFxml) {
        J.appel(this);

        URL fichierFxml = ChargeurDeVue.class.getResource(cheminFxml);

        DoitEtre.nonNul(fichierFxml, "cheminFxml non-trouvé '" + cheminFxml + "'");

        return fichierFxml;
    }
    
    private void chargerParent() {
        J.appel(this);

        try {

            parent = loader.load();

        } catch (IOException e) {
            
            Erreur.fatale("impossible de charger le parent", e);

        }

        DoitEtre.nonNul(parent);
    }

    private void ajouterCss(String cheminCss) {
        J.appel(this);

		URL fichierCss = ChargeurDeVue.class.getResource(cheminCss);
            
		DoitEtre.nonNul(fichierCss);
            
		parent.getStylesheets().add(fichierCss.toExternalForm());
    }

	public Parent getParent() {
		J.appel(this);
		
		return parent;
	}
}
