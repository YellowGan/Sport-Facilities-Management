package Client;

import java.io.Serializable;

/**
 * Classe in cui ci sono all'interno le informazioni per creare le notifiche che
 * ogni utente riceve ogni volta che un altro utente invia la richiesta di 
 * partecipazione a una squadra
 */
public class ElementiAggiornaNotifiche implements Serializable{
    private String idUtente;
    private String nomeSquadra;
    
    public ElementiAggiornaNotifiche(){
        this.idUtente = null;
        this.nomeSquadra = null;
    }
    
    public ElementiAggiornaNotifiche(String idUtente, String nomeSquadra){
        this.idUtente = idUtente;
        this.nomeSquadra = nomeSquadra;
    }
    
    public String getIdUtente(){
        return this.idUtente;
    }
    
    public String getNomeSquadra(){
        return this.nomeSquadra;
    }
}
