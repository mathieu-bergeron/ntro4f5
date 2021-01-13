package ntro.mvc.controleurs;

import ntro.debogage.J;
import ntro.messages.MessageRecu;
import ntro.messages.RecepteurMessage;

@SuppressWarnings("rawtypes")
public abstract class RecepteurMessageMVC<MR extends MessageRecu> extends RecepteurMessage<MR>{
	
	private ControleurVue controleur;
	
	void setControleur(ControleurVue controleur) {
		J.appel(this);
		
		this.controleur = controleur;
	}

	@Override
	public void recevoirMessage(MR messageRecu) {
		J.appel(this);

		recevoirMessageMVC(messageRecu);
		
		controleur.notifierMessageTraite();
	}
	
	public abstract void recevoirMessageMVC(MR messageRecu);

}
