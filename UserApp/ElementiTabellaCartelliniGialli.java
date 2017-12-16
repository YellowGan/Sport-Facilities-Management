package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella dei cartellini gialli complessivi di
 * un giocatore in un determinato torneo
 */
public class ElementiTabellaCartelliniGialli implements Serializable{
    
    private String nomeGiocatoreAmmonito;
    private String numeroGialli;

    public ElementiTabellaCartelliniGialli(String nomeGiocatoreAmmonito, String numeroGialli) {
        this.nomeGiocatoreAmmonito = nomeGiocatoreAmmonito;
        this.numeroGialli = numeroGialli;
    }

    public String getNomeGiocatoreAmmonito() {
        return nomeGiocatoreAmmonito;
    }

    public String getNumeroGialli() {
        return numeroGialli;
    }
    
    
}
