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
