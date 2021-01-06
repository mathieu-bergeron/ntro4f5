package ntro.client.commandes;

import ntro.debogage.J;

public class ReactionVideParDefaut extends ReactionApresCommande {

	@Override
	public void reagirApresCommande() {
		J.appel(this);
		
		// rien
	}


}
