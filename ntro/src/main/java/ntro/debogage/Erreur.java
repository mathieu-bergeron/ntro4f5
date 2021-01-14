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
			if(e instanceof NullPointerException) {
				e.printStackTrace();
			}
			if(e.getCause() != null) {
				afficherExceptions(e.getCause());
			}
		}
	}

	private static void afficherExceptions(Throwable... exceptions) {
		for(Throwable e : exceptions) {
			System.out.println("\t\t" + e.getClass().getSimpleName() + " " + e.getMessage());
			if(e instanceof NullPointerException) {
				e.printStackTrace();
			}
			if(e.getCause() != null) {
				afficherExceptions(e.getCause());
			}
		}
	}
}
