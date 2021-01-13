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


package ntro.systeme;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import ntro.debogage.Erreur;
import ntro.debogage.J;

public class Systeme {
	
	private static Quitteur quitteur;
	
	public static void installerQuitteur(Quitteur quitteur) {
		J.appel(Systeme.class);
		Systeme.quitteur = quitteur;
	}

	public static void quitter() {
		if(quitteur != null) {
			quitteur.quitter();
		}else {
			Erreur.fatale("Systeme.quitteur n'a pas été installé");
			System.exit(0);
		}
	}
	
	public static Path getHome() {
		J.appel(Systeme.class);
		
		String cheminHome = System.getProperty("user.home");

		return Paths.get(cheminHome);
	}

	public static String cheminDansHome(File fichierChoisi) {
		J.appel(Systeme.class);
		
		Path home = getHome();
		
		Path dansHome = home.relativize(fichierChoisi.toPath());

		return dansHome.toString();
	}

	public static File aPartirCheminDansHome(String cheminDansHome) {
		J.appel(Systeme.class);
		
		Path home = getHome();
		
		return new File(home.toString(), cheminDansHome);
	}

}
