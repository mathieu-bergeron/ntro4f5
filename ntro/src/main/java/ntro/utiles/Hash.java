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


package ntro.utiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import ntro.debogage.J;

public class Hash {

    private static MessageDigest sha1;

    static {
        try {

            sha1 = MessageDigest.getInstance("SHA-1");

        } catch (NoSuchAlgorithmException e) {

            J.valeurs("[FATAL] impossible d'instantier SHA-1");
            System.exit(0);

        }
    }

    public static String hash(String entree) {

        sha1.update(entree.getBytes());

        return versHex(sha1.digest());
    }

    private static String versHex(final byte[] hash){

        String resultat;

        Formatter formatter = new Formatter();

        for (byte b : hash) {
            formatter.format("%02x", b);
        }

        resultat = formatter.toString();
        formatter.close();

        return resultat;
    }

}
