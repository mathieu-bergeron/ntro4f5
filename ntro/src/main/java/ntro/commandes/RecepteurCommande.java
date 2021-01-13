package ntro.commandes;

import ntro.debogage.J;

public abstract class RecepteurCommande<CR extends CommandeRecue> {

	public abstract void executerCommande(CR commande);

	public boolean siCommandePossible(CR commande) {
		J.appel(this);

		// par défaut:
		return true;
	}

}
