package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella dove vengono visualizzati sia i 
 * tornei terminati che quelli in corso o in programma
 */
public class ElementiTabellaTornei implements Serializable{
    private String nomeTorneo;
    private String dataInizio;
    private String tipoTorneo;
    
    public ElementiTabellaTornei(){
        this.nomeTorneo = null;
        this.dataInizio = null;
        this.tipoTorneo = null;
    }
    
    public ElementiTabellaTornei(String nome, String data, String tipoTorneo){
        this.nomeTorneo = nome;
        this.dataInizio = data;
        this.tipoTorneo = tipoTorneo;
    }
    
    public String getNomeTorneo(){
        return this.nomeTorneo;
    }
    
    public String getDataInizio(){
        return this.dataInizio;
    }
    
    public String getTipoTorneo(){
        return this.tipoTorneo;
    } 
}
