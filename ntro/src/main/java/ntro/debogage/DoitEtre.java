package ntro.debogage;

import java.util.Enumeration;

import ntro.systeme.Systeme;

@SuppressWarnings({"rawtypes"})
public class DoitEtre {
	
	private static final int INCREMENT_PILE = 1;
	private static final String ECHEC = "[FATAL]";

	public static void nonNul(Object valeur, String nomVar, int incrementPile) {
		int incPiel = INCREMENT_PILE + incrementPile;
		
		if(valeur == null) {
			
			String message = "valeur nulle";
			message = nomVar + " ne doit pas être null";
			messagePuisQuitter(message, incrementPile);
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
