package com.ipiecoles.java.java240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.StringJoiner;

public class WebPageManager {

    private HashMap<String, String> cacheContent = new HashMap<>();

    /**
     * Fait une requ�te GET à l'url pass�e en param�tre
     * @param address URL à contacter
     * @return le contenu renvoy� par la requ�te GET
     * @throws IOException si l'adresse n'a pas pu �tre contact�e.
     */
    public String getPageContents(String address) throws IOException {
        System.out.println("Consultation de l'adresse " + address);
        BufferedReader br = null;
        StringJoiner lines = new StringJoiner(System.lineSeparator());

        try {
            URL url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toString();

    }

    /**
     * Fait appel à getPageContents si l'adresse n'a jamais �t� jointe auparavant, sinon renvoie le contenu de l'appel pr�c�dent
     * @param address URL à contacter
     * @return Le contenu en cache (si d�j� consult�) de la page situ�e à l'adresse en param�tre
     * @throws IOException si l'adresse n'a pas pu �tre contact�e.
     */
    public String getPageContentsFromCacheIfExists(String address) throws IOException {
        if(cacheContent.containsKey(address)){
            System.out.println("R�cup�ration dans le cache...");
            return cacheContent.get(address);
        } else {
            return getPageContents(address);
        }
    }

}
