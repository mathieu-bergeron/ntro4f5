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

import java.io.File;

import ntro.debogage.DoitEtre;
import ntro.debogage.J;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogueModal {
	
	private static Stage fenetrePrincipale;
	
	public static void enregistreFenetrePrincipale(Stage fenetrePrincipale) {
		J.appel(DialogueModal.class);

		DoitEtre.nonNul(fenetrePrincipale, "fenetrePrincipale", 1);

		DialogueModal.fenetrePrincipale = fenetrePrincipale;
	}

	public static Stage ouvrirDialogueModal(Scene scene) {
		J.appel(DialogueModal.class);

		DoitEtre.nonNul(scene, "scene", 1);

        Stage fenetreModale = new Stage();
        fenetreModale.setScene(scene);
        fenetreModale.initOwner(fenetrePrincipale);
        fenetreModale.initModality(Modality.APPLICATION_MODAL);
        
        Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				J.appel(this);

				fenetreModale.showAndWait();
			}
		});
        
        return fenetreModale;
	}

	public static File ouvrirDialogueFichiers() {
		J.appel(DialogueModal.class);
		
	     FileChooser fileChooser = new FileChooser();
         File fichierChoisi = fileChooser.showSaveDialog(fenetrePrincipale);
         
         return fichierChoisi;
	}
}
