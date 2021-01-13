package ntro.commandes;

import ntro.debogage.Erreur;
import ntro.debogage.J;

@SuppressWarnings({"unchecked"})
public abstract class Commande<CPE extends CommandePourEnvoi, 
							   CR extends CommandeRecue> 

				implements CommandePourEnvoi, 
			        	   CommandeRecue {
	
	protected RecepteurCommande<CR> recepteur;
	protected ReactionApresCommande reaction;
	
	void setRecepteur(RecepteurCommande<CR> recepteur) {
		J.appel(this);

		this.recepteur = recepteur;
	}
	
	void setReaction(ReactionApresCommande reaction) {
		J.appel(this);
		
		this.reaction = reaction;
	}
	
	@Override
	public void envoyerCommande() {
    	J.appel(this);

    	if(siCommandePossible()) {
    		recepteur.executerCommande((CR) this);
    	}
    }

	@Override
    public boolean siCommandePossible(){
        J.appel(this);
        
        boolean siPossible = false;
        
        if(recepteur == null) {
        	
        	Erreur.nonFatale(String.format("Aucun recepteur pour la commande ", this.getClass().getSimpleName()));

        }else {

			siPossible = recepteur.siCommandePossible((CR) this);
        }
        
        return siPossible;
    }

	@Override
	public void notifierCommandeTraitee() {
		J.appel(this);

		reaction.reagirApresCommande();
	}
}
