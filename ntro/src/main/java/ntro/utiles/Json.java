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


package ntro.utiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ntro.debogage.J;

public class Json {
    
    private static final Gson gson = new GsonBuilder().create();
    private static final Gson gsonPourFichier = new GsonBuilder().setPrettyPrinting().create();
    
    public static <T> T aPartirJson(String chaineJson, Class<T> classeT) {
        J.appel(Json.class);

        return gson.fromJson(chaineJson, classeT);
    }

    public static String versJson(Object objet) {
        J.appel(Json.class);

        return gson.toJson(objet);
    }

	public static void sauvegarder(File fichierSortie, Object objet) throws IOException {
		J.appel(Json.class);
		
		OutputStream sortieStream = new FileOutputStream(fichierSortie);
		
		sortieStream.write(gsonPourFichier.toJson(objet).getBytes());
		sortieStream.close();
	}

	public static <T> T aPartirFichier(File fichier, Class<T> classeT) throws IOException {
		J.appel(Json.class);
		
		FileReader reader = new FileReader(fichier);
		
		T resultat = null; 
		
		try {
			
			resultat = gsonPourFichier.fromJson(reader, classeT);

		} catch(Exception e) {
			
			throw new IOException(e);
		}

		return resultat;
	}

}
