package Client;

import java.io.Serializable;

/**
 * Classe che serve per visualizzare le fasi del torneo a eliminazione diretta 
 * nel proprio tabellone
 */
public class ElementiPassaggioFasi implements Serializable{
    private String sq1;
    private String sq2;
    private String nomeTorneo;
    private String ris;
    
    public ElementiPassaggioFasi(){
        this.sq1 = null;
        this.sq2 = null;
        this.ris = null;
    }

    public ElementiPassaggioFasi(String sq1, String sq2, String nomeTorneo) {
        this.sq1 = sq1;
        this.sq2 = sq2;
        this.nomeTorneo = nomeTorneo;
    }
    
    public ElementiPassaggioFasi(String ris){
        this.ris = ris;
    }

    public String getSq1() {
        return sq1;
    }

    public String getSq2() {
        return sq2;
    }

    public String getRis() {
        return ris;
    }
    
    public void setRis(String ris){
        this.ris = ris;
    }
    
    public String getNomeTorneo(){
        return this.nomeTorneo;
    }
    
    
}
