package Client;

import java.io.Serializable;

public class ElementiTabellaCalendarioStatistiche implements Serializable{
    private String idPartita;
    private String casa;
    private String trasferta;
    private String data;
    private String risultato;

    public ElementiTabellaCalendarioStatistiche(){
        this.idPartita = null;
        this.casa = null;
        this.trasferta = null;
        this.data = null;
        this.risultato = null;
    }
    public ElementiTabellaCalendarioStatistiche(String idPartita, String casa, String trasferta, String data, String risultato) {
        super();
        this.idPartita = idPartita;
        this.casa = casa;
        this.trasferta = trasferta;
        this.data = data;
        this.risultato = risultato;
    }

    public String getIdPartita() {
        return idPartita;
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
