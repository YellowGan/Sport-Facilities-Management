package Client;

import java.io.Serializable;

public class ElementiCreaTorneo implements Serializable{
    private String nomeTorneo;
    private String descrizione;
    private String maxIscrizioni;
    private String data;
    private String idTipoTorneo;
    
    public ElementiCreaTorneo(){
        this.nomeTorneo = null;
        this.descrizione = null;
        this.maxIscrizioni = null;
        this.data = null;
        this.idTipoTorneo = null;
    }

    public ElementiCreaTorneo(String nomeTorneo, String descrizione, String maxIscrizioni, String data, String idTipoTorneo) {
        this.nomeTorneo = nomeTorneo;
        this.descrizione = descrizione;
        this.maxIscrizioni = maxIscrizioni;
        this.data = data;
        this.idTipoTorneo = idTipoTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getMaxIscrizioni() {
        return maxIscrizioni;
    }

    public String getData() {
        return data;
    }

    public String getIdTipoTorneo() {
        return idTipoTorneo;
    }
    
    
}
