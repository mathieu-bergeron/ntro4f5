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
