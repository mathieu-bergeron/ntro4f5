// Copyright (C) (2020) (Mathieu Bergeron) (mathieu.bergeron@cmontmorency.qc.ca)
//
// This file is part of ntro4f5
//
// ntro4f5 is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// ntro4f5 is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>


package ntro.mvc.controleurs;


import ntro.Fabrique;
import ntro.debogage.DoitEtre;
import ntro.debogage.J;
import ntro.mvc.Afficheur;
import ntro.mvc.Vue;
import ntro.mvc.modeles.Modele;
import ntro.mvc.modeles.ModeleLectureSeule;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class FabriqueControleur {
	
	private FabriqueControleur() {
		J.appel(this);
	}

	public static <C extends ControleurVue> C creerControleur(Class<C> classeControleur, Vue vue) {
		J.appel(FabriqueControleur.class);
		
		DoitEtre.nonNul(classeControleur, "classeControleur", 1);
		DoitEtre.nonNul(vue, "vue", 1);

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

		DoitEtre.nonNul(classeControleur, "classeControleur", 1);
		DoitEtre.nonNul(modele, "modele", 1);
		DoitEtre.nonNul(vue, "vue", 1);
		DoitEtre.nonNul(afficheur, "afficheur", 1);

		C controleur = Fabrique.nouvelleInstance(classeControleur);
		
		DoitEtre.nonNul(controleur, "controleur");
		
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
