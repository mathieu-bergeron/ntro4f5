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


package ntro.messages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ntro.Fabrique;
import ntro.debogage.DoitEtre;
import ntro.debogage.J;
import ntro.utiles.Json;

@SuppressWarnings({"rawtypes", "unchecked"})
public class FabriqueMessage {

    private static final Pattern TYPE_PATTERN = Pattern.compile("_Type[\"']\\s*:\\s*[\"'](\\w+)[\"']");
    
    private static Map<String, Class<? extends Message>> classeParNom = new HashMap<>();
    private static Map<Class<? extends Message>, RecepteurMessage> recepteurs = new HashMap<>();
    
    private static Set<Canal> canauxPourRelais = new HashSet<>();
    private static Canal canalPourEnvoi;

    public static void memoriserCanalPourEnvoi(Canal canalPourEnvoi) {
    	J.appel(FabriqueMessage.class);
    	
    	FabriqueMessage.canalPourEnvoi = canalPourEnvoi;
    }
    
    public static void ajouterCanalPourRelais(Canal canalPourRelais) {
    	J.appel(FabriqueMessage.class);
    	
    	canauxPourRelais.add(canalPourRelais);
    }

    public static void retirerCanalPourRelais(Canal canalPourRelais) {
    	J.appel(FabriqueMessage.class);
    	
    	canauxPourRelais.remove(canalPourRelais);
    }
    
    public static String nomClasseMessage(String chaineMessage) {
    	J.appel(FabriqueMessage.class);
    	
    	String nomClasseMessage = null;

        Matcher matcher = TYPE_PATTERN.matcher(chaineMessage);
        
        if(matcher.find()) {
            
            nomClasseMessage = matcher.group(1);

        }else {

            throw new RuntimeException("[FATAL] impossible d'analyser le message: " + chaineMessage);

        }
    	
    	return nomClasseMessage;
    }


    private static Message aPartirChaineMessage(String chaineMessage, Class<? extends Message> classeMessage) {
        J.appel(Message.class);

        return Json.aPartirJson(chaineMessage, classeMessage);
    }

    public static void installerRecepteur(Class<? extends Message> classeMessage, RecepteurMessage recepteur) {
    	J.appel(FabriqueMessage.class);

		DoitEtre.nonNul(classeMessage, "classeMessage", 1);
		DoitEtre.nonNul(recepteur, "recepteur", 1);
    	
    	recepteurs.put(classeMessage, recepteur);
    	classeParNom.put(classeMessage.getSimpleName(), classeMessage);
    }

	public static <M extends Message> M obtenirMessagePourEnvoi(Class<M> classeMessage) {
    	J.appel(FabriqueMessage.class);

		DoitEtre.nonNul(classeMessage, "classeMessage", 1);
    	
    	M message = Fabrique.nouvelleInstance(classeMessage);
    	
    	message.setCanalPourEnvoi(canalPourEnvoi);
    	
    	return message;
	}

	static <M extends Message> M obtenirMessagePourEnvoi(Class<M> classeMessage, Canal canalPourEnvoi) {
    	J.appel(FabriqueMessage.class);
    	
    	M message = Fabrique.nouvelleInstance(classeMessage);
    	
    	message.setCanalPourEnvoi(canalPourEnvoi);
    	
    	return message;
	}

	public static void recevoirOuRelayerMessage(Canal recuSur, String chaineMessage) {
		J.appel(FabriqueMessage.class);
		
		String nomClasseMessage = nomClasseMessage(chaineMessage);
		
		Class<? extends Message> classeMessageRecu = classeParNom.get(nomClasseMessage);
		
		if(classeMessageRecu == null) {

			Set<Canal> canauxPourRelais = new HashSet<>();
			canauxPourRelais.addAll(FabriqueMessage.canauxPourRelais);
			canauxPourRelais.remove(recuSur);

			relayerMessage(canauxPourRelais, chaineMessage);

		}else {

			recevoirMessage(canauxPourRelais, classeMessageRecu, chaineMessage);
		}
	}

	public static void relayerMessage(Set<Canal> canauxPourRelais, String chaineMessage) {
		J.appel(FabriqueMessage.class);
		
		for(Canal canalPourRelais : canauxPourRelais) {
			
			if(canalPourRelais.siOuvert()) {
				
				canalPourRelais.envoyer(chaineMessage);
			}
		}
	}

	public static void recevoirMessage(Set<Canal> canauxPourRelais, 
			                           Class<? extends Message> classeMessage, 
			                           String chaineMessage) {

		J.appel(FabriqueMessage.class);

		RecepteurMessage recepteur = recepteurs.get(classeMessage);
		
		if(recepteur == null) {
			
			relayerMessage(canauxPourRelais, chaineMessage);

		}else {

			recevoirMessage(canauxPourRelais, recepteur, classeMessage, chaineMessage);
		}
	}

	public static void recevoirMessage(Set<Canal> canauxPourRelais, 
									   RecepteurMessage recepteur,
			                           Class<? extends Message> classeMessage, 
			                           String chaineMessage) {

		Message message = aPartirChaineMessage(chaineMessage, classeMessage);
		message.setCanauxPourRelai(canauxPourRelais);
		recepteur.recevoirMessage(message);
	}
}
