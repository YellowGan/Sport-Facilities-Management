package Client;

import java.io.Serializable;

/**
 * Classe dove vi sono i dati che servono per elaborare se ha vinto la squadra
 * in casa o in trasferta di una determinata partita e l'eventuale risultato
 */
public class ElementiTabellone implements Serializable{
    
    private String casa;
    private String trasferta;
    private String data;
    private String risultato;
    
    public ElementiTabellone(){
        this.casa = null;
        this.trasferta = null;
        this.data = null;
        this.risultato = null;
    }
    
    public ElementiTabellone(String casa, String trasferta, String data, String risultato){
        this.casa = casa;
        this.trasferta = trasferta;
        this.data = data;
        this.risultato = risultato;
    }

    public String getCasa() {
        return casa;
    }

    public String getTrasferta() {
        return trasferta;
    }

    public String getData() {
        return data;
    }

    public String getRisultato() {
        return risultato;
    }
    
    
}
