package ntro.mvc.controleurs;

import ntro.commandes.CommandeRecue;
import ntro.commandes.RecepteurCommande;
import ntro.debogage.J;

public abstract class RecepteurCommandeMVC<CR extends CommandeRecue> extends RecepteurCommande<CR>{

	@Override
	public void executerCommande(CR commande) {
		J.appel(this);

		executerCommandeMVC(commande);

		commande.notifierCommandeTraitee();
	}
	
	public abstract void executerCommandeMVC(CR commande);

}
