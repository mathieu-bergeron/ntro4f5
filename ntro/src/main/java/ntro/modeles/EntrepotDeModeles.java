package ntro.modeles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ntro.Fabrique;
import ntro.debogage.J;
import ntro.utiles.Json;

public class EntrepotDeModeles {
	
	private static String CHEMIN_REPERTOIRE_ENTREPOT = "entrepot";
	private static String EXTENSION_JSON = ".json";
	
	public static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> M obtenirModele(Class<M> classeModele, String idModele) throws IOException {
		J.appel(EntrepotDeModeles.class);

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

		return aPartirFichier(classeModele, fichierSauvegarde);
	}

	public static <MLS extends ModeleLectureSeule, M extends Modele<MLS>> void sauvegarderModele(M modele) throws IOException {
		J.appel(EntrepotDeModeles.class);
		
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

		M modele = Fabrique.nouvelleInstance(classeModele);
		
		modele.setId(idModele);
		
		modele.apresCreation();
		
		return modele;
	}
	

}
