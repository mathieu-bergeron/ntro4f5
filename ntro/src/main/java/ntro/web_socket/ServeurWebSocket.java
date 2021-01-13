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

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import ntro.debogage.Erreur;
import ntro.debogage.J;
import ntro.messages.FabriqueMessage;

public abstract class ServeurWebSocket extends WebSocketServer {

    public ServeurWebSocket(int port) {
        super( new InetSocketAddress( port ) );
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
    	J.appel(this);
    	
    	FabriqueMessage.ajouterCanalPourRelais(new CanalWebSocket(conn));
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    	J.appel(this);
    	
    	FabriqueMessage.retirerCanalPourRelais(new CanalWebSocket(conn));
    }

    @Override
    public void onMessage(WebSocket socket, String chaineMessage) {
    	J.appel(this);
    	
    	FabriqueMessage.recevoirOuRelayerMessage(new CanalWebSocket(socket), chaineMessage);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
    	J.appel(this);
    	
    	if(conn != null) {
			FabriqueMessage.retirerCanalPourRelais(new CanalWebSocket(conn));
    	}

    	Erreur.nonFatale("Déconnexion sur erreur", ex);
    }

    @Override
    public void onStart() {
        J.appel(this);
    }
}
