package ntro.client.mvc.controleurs;

import ntro.debogage.J;
import ntro.client.commandes.CommandeRecue;
import ntro.client.commandes.RecepteurCommande;

public abstract class RecepteurCommandeMVC<CR extends CommandeRecue> extends RecepteurCommande<CR>{

	@Override
	public void executerCommande(CR commande) {
		J.appel(this);

		executerCommandeMVC(commande);

		commande.notifierCommandeTraitee();
	}
	
	public abstract void executerCommandeMVC(CR commande);

}
