package ntro.client.mvc.controleurs;


import ntro.Fabrique;
import ntro.debogage.DoitEtre;
import ntro.debogage.J;
import ntro.modeles.Modele;
import ntro.modeles.ModeleLectureSeule;
import ntro.client.mvc.Afficheur;
import ntro.client.mvc.Vue;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class FabriqueControleur {
	
	private FabriqueControleur() {
		J.appel(this);
	}

	public static <C extends ControleurVue> C creerControleur(Class<C> classeControleur, Vue vue) {
		J.appel(FabriqueControleur.class);

		C controleur = Fabrique.nouvelleInstance(classeControleur);
		
		controleur.setVue(vue);

		initialiser(vue, controleur);
		
		return controleur;
	}

	public static <C extends ControleurModeleVue,
				   MLS extends ModeleLectureSeule,
				   M extends Modele<MLS>,
				   V extends Vue,
				   A extends Afficheur> 
	
			C creerControleur(Class<C> classeControleur, M modele, V vue, A afficheur) {

		J.appel(FabriqueControleur.class);

		C controleur = Fabrique.nouvelleInstance(classeControleur);
		
		DoitEtre.nonNul(controleur);
		
		controleur.setModele(modele);
		controleur.setVue(vue);
		controleur.setAfficheur(afficheur);
		
		controleur.initialiserAffichage();

		initialiser(vue, controleur);

		return controleur;
	}

	private static <C extends ControleurVue> void initialiser(Vue vue, C controleur) {
		J.appel(FabriqueControleur.class);

		controleur.obtenirMessagesPourEnvoi();
		controleur.installerReceptionCommandes();
		controleur.installerReceptionMessages();

		installerEnvoiDesCommandes(vue);
		
		controleur.demarrer();
	}



	private static <C extends ControleurModeleVue> void installerEnvoiDesCommandes(Vue vue) {
		J.appel(FabriqueControleur.class);

		vue.obtenirCommandesPourEnvoi();
		vue.installerCapteursEvenementsUsager();
		vue.verifierCommandesPossibles();
	}
}
