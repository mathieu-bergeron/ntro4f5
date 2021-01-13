package ntro.mvc.controleurs;

import ntro.debogage.J;
import ntro.messages.FabriqueMessage;
import ntro.messages.Message;
import ntro.messages.MessagePourEnvoi;
import ntro.messages.MessageRecu;
import ntro.mvc.Vue;
import ntro.commandes.Commande;
import ntro.commandes.CommandePourEnvoi;
import ntro.commandes.CommandeRecue;
import ntro.commandes.FabriqueCommande;
import ntro.commandes.ReactionApresCommande;
import ntro.commandes.RecepteurCommande;

@SuppressWarnings("rawtypes")
public abstract class ControleurVue<V extends Vue> {
	
	private V vue;

	public V getVue() {
		return vue;
	}
	
	protected ControleurVue() {
		J.appel(this);
	}
	
	void setVue(V vue) {
		J.appel(this);
		
		this.vue = vue;
	}
	
	protected <CPE extends CommandePourEnvoi,
			   CR extends CommandeRecue,
			   C extends Commande<CPE,CR>> 

			 void installerRecepteurCommande(Class<C> classeCommande, 
											  RecepteurCommandeMVC<CR> recepteur) {
		J.appel(this);
		
		FabriqueCommande.installerRecepteur(classeCommande, (RecepteurCommande<CR>) recepteur);
		
		installerReactionApresCommande(classeCommande);
	}
	
	protected <ME extends MessagePourEnvoi, 
	           MR extends MessageRecu,
	           M extends Message<ME, MR>>
	
			void installerRecepteurMessage(Class<M> classeMessage, RecepteurMessageMVC<MR> recepteur) {
		
		J.appel(this);
		
		recepteur.setControleur(this);

		FabriqueMessage.installerRecepteur(classeMessage, recepteur);
	}
	
	void installerReactionApresCommande(Class<? extends Commande> classeCommande) {
		J.appel(this);
		
		FabriqueCommande.installerReactionApresCommande(classeCommande, new ReactionApresCommande() {
			@Override
			public void reagirApresCommande() {
				J.appel(this);

				vue.verifierCommandesPossibles();
			}
		});
	}
	
	void notifierMessageTraite() {
		J.appel(this);
		
		return;
	}

	protected abstract void obtenirMessagesPourEnvoi();
	protected abstract void installerReceptionCommandes();
	protected abstract void installerReceptionMessages();
	protected abstract void demarrer();

}
