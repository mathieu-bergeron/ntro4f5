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

import ntro.debogage.J;
import ntro.mvc.Afficheur;
import ntro.mvc.Vue;
import ntro.mvc.modeles.Modele;
import ntro.mvc.modeles.ModeleLectureSeule;
import ntro.commandes.Commande;
import ntro.commandes.FabriqueCommande;
import ntro.commandes.ReactionApresCommande;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class ControleurModeleVue<MLS extends ModeleLectureSeule, 
                                          M extends Modele<MLS>, 
                                          V extends Vue, 
                                          A extends Afficheur> 

				extends ControleurVue<V> {
	
	private M modele;
    private A afficheur;

	public M getModele() {
		return modele;
	}

	public A getAfficheur() {
		return afficheur;
	}
		
	
	protected ControleurModeleVue() {
		super();
		J.appel(this);
	}
	
	void setModele(M modele) {
		J.appel(this);

		this.modele = modele;
	}

	void setAfficheur(A afficheur) {
		J.appel(this);

		this.afficheur = afficheur;
	}
	
	void initialiserAffichage() {
		J.appel(this);
		
		afficheur.initialiserAffichage(modele, getVue());
		afficheur.rafraichirAffichage(modele, getVue());
	}
	
	@Override
	void installerReactionApresCommande(Class<? extends Commande> classeCommande) {
		J.appel(this);
		
		FabriqueCommande.installerReactionApresCommande(classeCommande, new ReactionApresCommande() {
			@Override
			public void reagirApresCommande() {
				J.appel(this);
				
				afficheur.rafraichirAffichage((MLS) modele, getVue());
				
				getVue().verifierCommandesPossibles();
			}
		});
	}
	
	@Override
	void notifierMessageTraite() {
		J.appel(this);
		
		afficheur.rafraichirAffichage(modele, getVue());
	}

}
