package ntro.client.mvc.controleurs;

import ntro.debogage.J;
import ntro.modeles.Modele;
import ntro.modeles.ModeleLectureSeule;
import ntro.client.commandes.Commande;
import ntro.client.commandes.FabriqueCommande;
import ntro.client.commandes.ReactionApresCommande;
import ntro.client.mvc.Afficheur;
import ntro.client.mvc.Vue;

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
