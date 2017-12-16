package Client;

import java.io.Serializable;

public class ElementiNomiSquadreStatisticheTorneo implements Serializable{
    String nomeCasa;
    String nomeTrasferta;
    
    public ElementiNomiSquadreStatisticheTorneo(){
        this.nomeCasa = null;
        this.nomeTrasferta = null;
    }

    public ElementiNomiSquadreStatisticheTorneo(String nomeCasa, String nomeTrasferta) {
        this.nomeCasa = nomeCasa;
        this.nomeTrasferta = nomeTrasferta;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getNomeTrasferta() {
        return nomeTrasferta;
    } 
}
