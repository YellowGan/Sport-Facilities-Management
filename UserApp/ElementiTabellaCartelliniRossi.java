package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella dei cartellini rossi complessivi di
 * un giocatore in un determinato torneo
 */
public class ElementiTabellaCartelliniRossi implements Serializable{
    
    private String nomeGiocatoreEspulso;
    private String numeroRossi;

    public ElementiTabellaCartelliniRossi(String nomeGiocatoreEspulso, String numeroRossi) {
        this.nomeGiocatoreEspulso = nomeGiocatoreEspulso;
        this.numeroRossi = numeroRossi;
    }

    public String getNomeGiocatoreEspulso() {
        return nomeGiocatoreEspulso;
    }

    public String getNumeroRossi() {
        return numeroRossi;
    }
    
    
    
}
