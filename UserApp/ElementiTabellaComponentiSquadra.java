package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella dove vengono visualizzati i giocatori
 * di una determinata squadra
 */
public class ElementiTabellaComponentiSquadra implements Serializable{
    private String Giocatore;
    
    public ElementiTabellaComponentiSquadra(String giocatore){
        this.Giocatore = giocatore;
    }
    
    public void setGiocatore(String giocatore){
        this.Giocatore = giocatore;
    }
    
    public String getGiocatore(){
        return this.Giocatore;
    }
    
}
