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


package ntro.mvc.modeles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ntro.Fabrique;
import ntro.debogage.DoitEtre;
import ntro.debogage.J;
import ntro.utiles.Json;

public class EntrepotDeModeles {
	
	private static String CHEMIN_REPERTOIRE_ENTREPOT = "entrepot";
	private static String EXTENSION_JSON = ".json";
	
	public static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> M obtenirModele(Class<M> classeModele, String idModele) throws IOException {
		J.appel(EntrepotDeModeles.class);
		
		DoitEtre.nonNul(classeModele, "classeModele", 1);
		DoitEtre.nonNul(idModele, "idModele", 1);
		
		Path cheminSauvegarde = Paths.get(CHEMIN_REPERTOIRE_ENTREPOT, classeModele.getSimpleName(), idModele + EXTENSION_JSON);
		
		File fichierSauvegarde = cheminSauvegarde.toFile();
		
		return aPartirFichier(classeModele, fichierSauvegarde);
	}

	private static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> M aPartirFichier(Class<M> classeModele, File fichierSauvegarde) throws IOException {
		J.appel(EntrepotDeModeles.class);

		M modele = Json.aPartirFichier(fichierSauvegarde, classeModele);
		
		modele.apresChargementJson();

		return modele;
	}

	public static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> M obtenirModele(Class<M> classeModele, File fichierSauvegarde) throws IOException {
		J.appel(EntrepotDeModeles.class);

		DoitEtre.nonNul(classeModele, "classeModele", 1);
		DoitEtre.nonNul(fichierSauvegarde, "fichierSauvegarde", 1);

		return aPartirFichier(classeModele, fichierSauvegarde);
	}

	public static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> void sauvegarderModele(M modele) throws IOException {
		J.appel(EntrepotDeModeles.class);

		DoitEtre.nonNul(modele, "modele", 1);
		
		Path cheminRepertoireModeles = Paths.get(CHEMIN_REPERTOIRE_ENTREPOT, modele.getClass().getSimpleName());

		File repertoireModeles = cheminRepertoireModeles.toFile();

		if(!repertoireModeles.exists()) {
			repertoireModeles.mkdirs();
		}
		
		Path cheminSauvegarde = Paths.get(repertoireModeles.getPath(), modele.getId() + EXTENSION_JSON);
		
		File fichierSauvegarde = cheminSauvegarde.toFile();
		
		Json.sauvegarder(fichierSauvegarde, modele);
	}

	public static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> M creerModele(Class<M> classeModele, String idModele) {
		J.appel(EntrepotDeModeles.class);

		DoitEtre.nonNul(classeModele, "classeModele", 1);
		DoitEtre.nonNul(idModele, "idModele", 1);

		M modele = Fabrique.nouvelleInstance(classeModele);
		
		modele.setId(idModele);
		
		modele.apresCreation();
		
		return modele;
	}
}
