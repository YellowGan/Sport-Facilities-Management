package Client;

import java.io.Serializable;

/*
 * Classe in cui ci sono i dati che servono per la ricerca del giocatore
 */
public class ElementiTabellaRicercaGiocatori implements Serializable{
    private String nick;
    private String nomeCognome;
    
    private String subString;
    
    public ElementiTabellaRicercaGiocatori(){
        this.nick = null;
        this.nomeCognome = null;
    }
    
    /**
     * 
     * @param subString: in questo parametro c'è la sottostringa che serve per
     * cercare un giocatore
     */
    public ElementiTabellaRicercaGiocatori(String subString){
        super();
        this.subString = subString;
    }
    
    public ElementiTabellaRicercaGiocatori(String nick, String nomeCognome){
        super();
        this.nick = nick;
        this.nomeCognome = nomeCognome;
    }
    
    public String getNick(){
        return this.nick;
    }
    
    public String getNomeCognome(){
        return this.nomeCognome;
    }
    
    public String getSubString(){
        return this.subString;
    }
}