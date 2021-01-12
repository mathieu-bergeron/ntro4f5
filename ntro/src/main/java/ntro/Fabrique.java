package ntro;

import ntro.debogage.DoitEtre;
import ntro.debogage.Erreur;
import ntro.debogage.J;

public final class Fabrique {
	
	private Fabrique() {
		J.appel(this);
	}
	
	public static <C> C nouvelleInstance(Class<C> classe) {
		J.appel(Fabrique.class);

		DoitEtre.nonNul(classe, "classe", 1);

		C instance = null;

		try {
			
			instance = (C) classe.newInstance();

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | ClassCastException e) {
			
			Erreur.fatale("[FATAL] impossible d'instancier la classe: " + classe.getSimpleName(), e);

		}
		
		return instance;
	}
}
