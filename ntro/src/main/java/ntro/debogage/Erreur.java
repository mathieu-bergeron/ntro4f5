package ntro.debogage;

import ntro.systeme.Systeme;

public class Erreur {
	
	private static final String FATAL = "[FATAL]";
	private static final String ERREUR = "[ERREUR]";
	private static final String AVERTISSEMENT = "[AVERTISSEMENT]";

	public static void avertissement(String message) {
		J.messageErreur(0, AVERTISSEMENT, message);
	}

	public static void nonFatale(String message, Exception... exceptions) {
		J.messageErreur(0, ERREUR, message);
		afficherExceptions(exceptions);
	}

	public static void fatale(String message, Exception... exceptions) {
		J.messageErreur(0, FATAL, message);
		afficherExceptions(exceptions);
		Systeme.quitter();
	}

	public static void fatale(String message, Throwable... exceptions) {
		J.messageErreur(0, FATAL, message);
		afficherExceptions(exceptions);
		Systeme.quitter();
	}

	private static void afficherExceptions(Exception... exceptions) {
		for(Exception e : exceptions) {
			System.out.println("\t\t" + e.getClass().getSimpleName() + " " + e.getMessage());
			if(e.getCause() != null) {
				afficherExceptions(e.getCause());
			}
		}
	}

	private static void afficherExceptions(Throwable... exceptions) {
		for(Throwable e : exceptions) {
			System.out.println("\t\t" + e.getClass().getSimpleName() + " " + e.getMessage());
			if(e.getCause() != null) {
				afficherExceptions(e.getCause());
			}
		}
	}
}
