package com.ipiecoles.java.java240;

import java.io.IOException;

public class BitcoinService {

    private Double rate = null;
    private Boolean forceRefresh = false;
	private WebPageManager webPageManager;

	public void setForceRefresh(Boolean forceRefresh) {
		this.forceRefresh = forceRefresh;
	}

    public void setWebPageManager(WebPageManager webPageManager) {
    	this.webPageManager = webPageManager;
    }
    /**
     * M�thode qui renvoie le cours du Bitcoin
     * @return le cours du bitcoin
     * @throws IOException si impossible d'acceder � la bourse
     */
    public Double getBitcoinRate() throws IOException {
        if(rate != null && !forceRefresh){
            System.out.println("R�cup�ration du cours du bitcoin en cache...");
            return rate;
        }

        System.out.println("R�cup�ration du cours du bitcoin sur site distant");

        String apiResponse = webPageManager.getPageContents("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR");
        apiResponse = apiResponse.replace("{\"EUR\":","");
        apiResponse = apiResponse.replace("}","");
        rate = Double.parseDouble(apiResponse);
        return rate;
    }

    /**
     * M�thode qui renvoie l'�quivalent en bitcoin du prix en euro pass� en param�tre
     * @param prixEnEuro le prix � convertir
     * @return le prix en bitcoin au taux actuel
     * @throws IOException si impossible d'acceder � la bourse
     */
    public Double getBitcoinPrice(Double prixEnEuro) throws IOException {
        if(rate == null){
            getBitcoinRate();
        }
        return prixEnEuro / rate;
    }

}
