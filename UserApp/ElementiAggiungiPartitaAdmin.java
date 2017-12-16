package Client;

import java.io.Serializable;

public class ElementiAggiungiPartitaAdmin implements Serializable{
    private String casa;
    private String trasferta;
    private String fascia;
    private String data;
    private String nomeTorneo;
    private int idCampo;
    private String idUtente;
    
    public ElementiAggiungiPartitaAdmin(){
        this.casa = null;
        this.trasferta = null;
        this.fascia = null;
        this.data = null;
        this.nomeTorneo = null;
        this.idCampo = 0;
        this.idUtente = null;
    }

    public ElementiAggiungiPartitaAdmin(String casa, String trasferta, String fascia, String data, String nomeTorneo, int idCampo, String idUtente) {
        super();
        this.casa = casa;
        this.trasferta = trasferta;
        this.fascia = fascia;
        this.data = data;
        this.nomeTorneo = nomeTorneo;
        this.idCampo = idCampo;
        this.idUtente = idUtente;
    }

    public String getCasa() {
        return casa;
    }

    public String getTrasferta() {
        return trasferta;
    }

    public String getFascia() {
        return fascia;
    }

    public String getData() {
        return data;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public int getIdCampo() {
        return idCampo;
    }

    public String getIdUtente() {
        return idUtente;
    }

}
