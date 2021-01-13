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

import java.util.HashMap;
import java.util.Map;

import ntro.Fabrique;
import ntro.debogage.DoitEtre;
import ntro.debogage.Erreur;
import ntro.debogage.J;

@SuppressWarnings({"rawtypes"})
public final class FabriqueCommande {
	
	private FabriqueCommande() {
		J.appel(this);
	}
	
	
	protected static Map<Class<? extends Commande>, RecepteurCommande> recepteurs = new HashMap<>();
	protected static Map<Class<? extends Commande>, ReactionApresCommande> reactionsApresCommande = new HashMap<>();

	public static <CPE extends CommandePourEnvoi,
			       CR extends CommandeRecue,
			       C extends Commande<CPE,CR>> 

		   void installerRecepteur(Class<C> classeCommande, 
								   RecepteurCommande<CR> recepteur) {

		J.appel(FabriqueCommande.class);

		DoitEtre.nonNul(classeCommande, "classeCommande", 1);
		DoitEtre.nonNul(recepteur, "recepteur", 1);

		recepteurs.put(classeCommande, recepteur);
	}

	public static <C extends Commande> 
				  void installerReactionApresCommande(Class<C> classeCommande, 
										     ReactionApresCommande reactionApresCommande) {

		J.appel(FabriqueCommande.class);
		
		reactionsApresCommande.put(classeCommande, reactionApresCommande);

	}


	@SuppressWarnings("unchecked")
	public static <CPE extends CommandePourEnvoi,
				   CR extends CommandeRecue,
				   C extends Commande<CPE,CR>>
	
	       CPE obtenirCommandePourEnvoi(Class<C> classeCommande){

		J.appel(FabriqueCommande.class);

		DoitEtre.nonNul(classeCommande, "classeCommande", 1);

		RecepteurCommande recepteur = recepteurs.get(classeCommande);
		if(recepteur == null) {
			Erreur.nonFatale(String.format("Aucun recepteur installé pour la Commande %s", classeCommande.getSimpleName()));
		}

		ReactionApresCommande reaction = reactionsApresCommande.get(classeCommande);
		
		if(reaction == null) {
			Erreur.avertissement(String.format("ReactionVideParDefaut pour la Commande %s", classeCommande.getSimpleName()));
			reaction = new ReactionVideParDefaut();
		}
		
		Commande commande = Fabrique.nouvelleInstance(classeCommande);

		commande.setRecepteur(recepteur);
		commande.setReaction(reaction);
		
		CPE commandePourEnvoi = null;

		try {
			
			commandePourEnvoi = (CPE) commande;

		}catch(ClassCastException e) {
			
			Erreur.fatale(String.format("Impossible de créer la CommandePourEnvoi. Est-ce que la Commande %s est bien paramétrée?", 
								        classeCommande.getSimpleName()), e);
		}

		return commandePourEnvoi;
	}
	
}
