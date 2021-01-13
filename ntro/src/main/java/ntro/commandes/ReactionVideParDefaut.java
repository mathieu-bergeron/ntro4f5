package ntro.commandes;

import ntro.debogage.J;

public class ReactionVideParDefaut extends ReactionApresCommande {

	@Override
	public void reagirApresCommande() {
		J.appel(this);
		
		// rien
	}


}
