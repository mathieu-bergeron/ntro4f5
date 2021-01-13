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

import ntro.debogage.J;
import ntro.messages.Canal;
import ntro.messages.Message;
import ntro.utiles.Json;

import org.java_websocket.WebSocket;

@SuppressWarnings("rawtypes")
public class CanalWebSocket implements Canal {
	
	private WebSocket socket;

	public CanalWebSocket(WebSocket socket) {
		J.appel(this);
		
		this.socket = socket;
	}

	@Override
	public boolean siOuvert() {
		J.appel(this);
		
		return socket.isOpen();
	}

	@Override
	public void envoyer(Message message) {
		J.appel(this);
		
		socket.send(Json.versJson(message));
	}

	@Override
	public void envoyer(String chaineMessage) {
		J.appel(this);

		socket.send(chaineMessage);
	}

	protected WebSocket getSocket() {
		J.appel(this);
		
		return socket;
	}

	@Override 
	public int hashCode() {
		return socket.hashCode();
	}

	@Override 
	public boolean equals(Object autreObjet) {
		if(autreObjet instanceof CanalWebSocket) {

			return ((CanalWebSocket) autreObjet).getSocket().equals(socket);
		}

		return false;
	}

}
