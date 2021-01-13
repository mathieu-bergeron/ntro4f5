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


package ntro.javafx;

import java.net.URI;

import ntro.debogage.J;
import ntro.messages.FabriqueMessage;
import ntro.web_socket.ClientWebSocket;
import javafx.application.Platform;

public abstract class ClientWebSocketFX extends ClientWebSocket {
	
	public ClientWebSocketFX(URI serverUri) {
		super(serverUri);
		J.appel(this);
	}

	@Override
	public void onMessage(String chaineMessage) {
		J.appel(this);

		// XXX: pour que ça soit une événement JavaFX
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				J.appel(this);
				
				FabriqueMessage.recevoirOuRelayerMessage(ClientWebSocketFX.this, chaineMessage);
			}
		});
	}
}
