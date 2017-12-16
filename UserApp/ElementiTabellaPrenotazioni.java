package Client;

import java.io.Serializable;

/**
 * Classe dove ci sono al suo interno i dati che servono per riempire la tabella
 * dove vengono visualizzati per ogni giornata tutte le fascie orarie occupate 
 * e non di un determinato campo e se quest'ultimo è occuapato il tipo di 
 * partita che si deve svolgere
 */
public class ElementiTabellaPrenotazioni implements Serializable{
    private String fascia_oraria;
    private String stato;
    private String prenotato_da;
    private String tipo_partita;
    
    public ElementiTabellaPrenotazioni(String fascia_oraria,String stato, String prenotato_da, String tipo_partita){
        this.fascia_oraria = fascia_oraria;
        this.stato = stato;
        this.prenotato_da = prenotato_da;
        this.tipo_partita = tipo_partita;
    }
    
    public String getFasciaOraria(){
        return this.fascia_oraria;
    }
    
    public String getStato(){
        return this.stato;
    }
    
    public String getPrenotatoDa(){
        return this.prenotato_da;
    }
    
    public String getTipoPartita(){
        return this.tipo_partita;
    }
  
}