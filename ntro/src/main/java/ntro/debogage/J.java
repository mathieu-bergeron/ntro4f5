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


@SuppressWarnings({"rawtypes"})
public class J {
    
    private static int INDICE_APPEL_A_AFFICHER = 4;

	private static boolean siActif = true;

    public enum Contexte{ANDROID,JAVAFX;}

    private static final String PREFIXE = "J#";

    public static void initialiser(Contexte contexte){
        switch(contexte){
            case JAVAFX:
                INDICE_APPEL_A_AFFICHER = 4;
                break;

            default:
            case ANDROID:
                INDICE_APPEL_A_AFFICHER = 5;
                break;
        }
    }

    public static void appel(Class classeAppelee){
    	if(!siActif) {return;}

        String nomClasseAppelee = classeAppelee.getSimpleName();

        afficherMethode(nomClasseAppelee);

    }

    public static void appel(Object objetAppele){
    	if(!siActif) {return;}

        String nomClasseAppelee = objetAppele.getClass().getSimpleName();

        afficherMethode(nomClasseAppelee);

    }
    
    static void messageErreur(int incrementPile, String typeErreur, String message) {
    	
    	int indiceAppel = INDICE_APPEL_A_AFFICHER + incrementPile;

        StackTraceElement appelAAfficher = getAppel(indiceAppel);

        String nomFichier = appelAAfficher.getFileName();

        int numeroLigne = appelAAfficher.getLineNumber();

        String etiquette = PREFIXE + " (" + nomFichier  + ":" + numeroLigne + ")";
        
        System.out.println(etiquette);

        System.out.println("\t" + typeErreur + " " + message);
    	
    }

    public static void ici(){
    	if(!siActif) {return;}

    	int indiceAppel = INDICE_APPEL_A_AFFICHER - 1;

        StackTraceElement appelAAfficher = getAppel(indiceAppel);

        String nomFichier = appelAAfficher.getFileName();

        int numeroLigne = appelAAfficher.getLineNumber();

        String etiquette = PREFIXE + " (" + nomFichier  + ":" + numeroLigne + ") ICI";

        System.out.println(etiquette);
    }


    public static void valeurs(Object... valeurs){
    	if(!siActif) {return;}

    	int indiceAppel = INDICE_APPEL_A_AFFICHER - 1;

        String chaineValeurs = getChaineValeurs(valeurs);

        StackTraceElement appelAAfficher = getAppel(indiceAppel);

        String nomFichier = appelAAfficher.getFileName();

        int numeroLigne = appelAAfficher.getLineNumber();

        String etiquette = PREFIXE + " (" + nomFichier  + ":" + numeroLigne + ") VALEURS";

        System.out.println(etiquette + ": " + chaineValeurs);

    }


    private static StackTraceElement getAppel(int indiceAppelAAfficher){

        StackTraceElement[] pileAppels = Thread.currentThread().getStackTrace();
        StackTraceElement appelAAfficher = pileAppels[indiceAppelAAfficher];

        return appelAAfficher;

    }

    private static void afficherMethode(String nomClasseAppelee){

        StackTraceElement appelAAfficher = getAppel(INDICE_APPEL_A_AFFICHER);

        String nomMethode = getNomMethode(appelAAfficher);

        String nomFichier = appelAAfficher.getFileName();

        int numeroLigne = appelAAfficher.getLineNumber();

        String etiquette = PREFIXE + " (" + nomFichier + ":" + numeroLigne + ") APPEL";

        String chaineAppel = nomClasseAppelee + "." + nomMethode;

        System.out.println(etiquette + ": " + chaineAppel);

    }

    private static String getNomMethode(StackTraceElement appelAAfficher){

        String nomMethode = appelAAfficher.getMethodName();

        return nomMethode;
    }

    private static String getChaineValeurs(Object... valeurs){
        if(valeurs.length == 0){
            return "";
        }

        String chaineValeurs = "";

        for(int i=0; i < valeurs.length; i++){

            chaineValeurs += getChaineValeur(valeurs[i]);

            if(i<(valeurs.length-1)){

                chaineValeurs += ", ";

            }
        }

        return chaineValeurs;
    }

    private static String getChaineValeur(Object valeur){

        if(valeur instanceof Long
                || valeur instanceof Boolean
                || valeur instanceof Integer
                || valeur instanceof Float
                || valeur instanceof Double
                || valeur instanceof String){

            return valeur.toString();

        }else if(valeur == null){

            return "null";

        }else{

            return valeur.getClass().getSimpleName();

        }
    }

	public static void setActif(boolean siActif) {
		J.siActif = siActif;
	}

}
