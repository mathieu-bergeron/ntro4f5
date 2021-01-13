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


package ntro.debogage;

import java.util.Enumeration;

import ntro.systeme.Systeme;

@SuppressWarnings({"rawtypes"})
public class DoitEtre {
	
	private static final int INCREMENT_PILE = 1;
	private static final String ECHEC = "[FATAL]";

	public static void nonNul(Object valeur, String nomVar, int incrementPile) {
		int incPile = INCREMENT_PILE + incrementPile;
		
		if(valeur == null) {
			
			String message = "valeur nulle";
			message = nomVar + " ne doit pas être null";
			messagePuisQuitter(message, incPile);
		}
	}
	
	public static void nonNul(Object valeur, String... messages) {
		int incrementPile = INCREMENT_PILE;
		
		if(valeur == null) {
			
			String message = "valeur nulle";
			
			if(messages.length > 0) {
				
				message += ": " + messages[0];
			}

			messagePuisQuitter(message, incrementPile);
		}
	}

	public static void nonVide(Enumeration enumeration, String... messages) {

		if(!enumeration.hasMoreElements()) {
			
			String message = "enumération vide";
			
			if(messages.length > 0) {
				
				message = messages[0];
				
			}
				
			messagePuisQuitter(message, INCREMENT_PILE);

		}
		
	}

	private static void messagePuisQuitter(String message, int incrementPile) {
		J.messageErreur(incrementPile, ECHEC, message);
		Systeme.quitter();
	}
	
}
