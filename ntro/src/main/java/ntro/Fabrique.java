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


package ntro;

import ntro.debogage.DoitEtre;
import ntro.debogage.Erreur;
import ntro.debogage.J;

public final class Fabrique {
	
	private Fabrique() {
		J.appel(this);
	}
	
	public static <C> C nouvelleInstance(Class<C> classe) {
		J.appel(Fabrique.class);

		DoitEtre.nonNul(classe, "classe", 1);

		C instance = null;

		try {
			
			instance = (C) classe.newInstance();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | ClassCastException e) {
			
			Erreur.fatale("[FATAL] impossible d'instancier la classe: " + classe.getSimpleName(), e);

		}
		
		return instance;
	}
}
