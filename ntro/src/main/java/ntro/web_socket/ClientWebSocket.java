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


package ntro.web_socket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import ntro.debogage.DoitEtre;
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
		
		DoitEtre.nonNul(serverUri, "serverUri", 1);
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

		DoitEtre.nonNul(message, "message", 1);

		send(Json.versJson(message));
	}

	@Override
	public void envoyer(String chaineMessage) {
		J.appel(this);
		
		send(chaineMessage);
	}

}
