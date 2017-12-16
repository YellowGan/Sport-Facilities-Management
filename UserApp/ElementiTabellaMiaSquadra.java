package Client;

import java.io.Serializable;

/**
 * Classe che serve per riempire la tabella dove ci sono le squadre di cui fa
 * parte un determinato giocatore
 */
public class ElementiTabellaMiaSquadra implements Serializable{
    
    private String nomeSquadra;
    private String capitanoSquadra;
    
    public ElementiTabellaMiaSquadra(){
        this.nomeSquadra = null;
        this.capitanoSquadra = null;
    }
    
    public ElementiTabellaMiaSquadra(String nomeSquadra, String capitanoSquadra){
        this.nomeSquadra = nomeSquadra;
        this.capitanoSquadra = capitanoSquadra;
    }
    
    public String getNomeSquadra(){
        return this.nomeSquadra;
    }
    
    public String getCapitanoSquadra(){
        return this.capitanoSquadra;
    }
}
