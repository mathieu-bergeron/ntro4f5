package ntro.client;

import java.net.ConnectException;
import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import ntro.debogage.Erreur;
import ntro.debogage.J;
import ntro.messages.Canal;
import ntro.messages.FabriqueMessage;
import ntro.messages.Message;
import ntro.utiles.Json;

@SuppressWarnings("rawtypes")
public abstract class ClientWebSocket extends WebSocketClient implements Canal {
	
	public ClientWebSocket(URI serverUri) {
		super(serverUri);
		J.appel(this);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		J.appel(this);
		
		FabriqueMessage.memoriserCanalPourEnvoi(this);
	}

	@Override
	public void onMessage(String chaineMessage) {
		J.appel(this);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		J.appel(this);
	}

	@Override
	public void onError(Exception ex) {
		J.appel(this);

		Erreur.nonFatale("Connexion perdue sur erreur", ex);
	}

	@Override
	public boolean siOuvert() {
		J.appel(this);
		
		return isOpen();
	}

	@Override
	public void envoyer(Message message) {
		J.appel(this);
		
		send(Json.versJson(message));
	}

	@Override
	public void envoyer(String chaineMessage) {
		J.appel(this);
		
		send(chaineMessage);
	}

}
