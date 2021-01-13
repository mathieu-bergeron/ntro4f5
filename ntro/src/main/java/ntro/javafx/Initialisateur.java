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


package ntro.javafx;

import ntro.debogage.J;
import ntro.systeme.Quitteur;
import ntro.systeme.Systeme;
import javafx.application.Platform;

public class Initialisateur {
	
	public static void initialiser() {

		J.initialiser(J.Contexte.JAVAFX);

		J.appel(Initialisateur.class);
		
		Systeme.installerQuitteur(new Quitteur() {
			@Override
			public void quitter() {
				Platform.exit();
				System.exit(0);
			}
		});
	}
}
