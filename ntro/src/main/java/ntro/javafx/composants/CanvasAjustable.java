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


package ntro.javafx.composants;


import ntro.debogage.J;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public abstract class CanvasAjustable extends Pane {
	
	protected GraphicsContext pinceau;
	
	public CanvasAjustable() {
		J.appel(this);
		
		installerCanvas();
		
		installerObservateurLargeur();
		installerObservateurHauteur();
	}

	private void installerCanvas() {
		J.appel(this);
		
		Canvas canvas = new Canvas();
		pinceau = canvas.getGraphicsContext2D();
		
		this.getChildren().add(canvas);

		canvas.widthProperty().bind(this.widthProperty());
		canvas.heightProperty().bind(this.heightProperty());
	}
	
	private void installerObservateurLargeur() {
		J.appel(this);
		
		widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				J.appel(this);
				
				double ancienneLargeur = (double) oldValue;
				double nouvelleLargeur = (double) newValue;
				
				if(ancienneLargeur == 0) {
					
					reagirLargeurInitiale(nouvelleLargeur);
					
				}else {
					
					reagirNouvelleLargeur(ancienneLargeur, nouvelleLargeur);
				}
			}
		});
	}

	private void installerObservateurHauteur() {
		J.appel(this);

		heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				J.appel(this);

				double ancienneHauteur = (double) oldValue;
				double nouvelleHauteur = (double) newValue;
				
				if(ancienneHauteur == 0) {
					
					reagirHauteurInitiale(nouvelleHauteur);
					
				}else {
					
					reagirNouvelleHauteur(ancienneHauteur, nouvelleHauteur);
				}
			}
		});
	}

	protected abstract void reagirLargeurInitiale(double largeurInitiale);
	protected abstract void reagirHauteurInitiale(double hauteurInitiale);

	protected abstract void reagirNouvelleLargeur(double ancienneLargeur, double nouvelleLargeur);
	protected abstract void reagirNouvelleHauteur(double ancienneHauteur, double nouvelleHauteur);
}
